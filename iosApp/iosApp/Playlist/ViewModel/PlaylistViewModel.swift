import Foundation
import shared
import Combine
import KMPNativeCoroutinesCombine

class PlaylistViewModel: ObservableObject {

  let getVideoListUseCase: GetVideoListIos

  @Published var viewState: PlaylistViewState = PlaylistViewState.idle()

  private var cancellables = Set<AnyCancellable>()

  private let intents = PassthroughSubject<PlaylistIntent, Never>()

  init(getVideoListUseCase: GetVideoListIos) {
    self.getVideoListUseCase = getVideoListUseCase

    compose()
  }

  deinit {
    cancellables.removeAll()
  }

  func emits(_ intent: PlaylistIntent) {
    intents.send(intent)
  }

  private func compose() {
    intents
      .map { self.intentToAction($0) }
      .flatMap { self.actionToResult($0) }
      .scan(PlaylistViewState.idle()) { (state, result) -> PlaylistViewState in
        return self.reduce(result, state)
      }
      .receive(on: DispatchQueue.main)
      .eraseToAnyPublisher()
      .removeDuplicates()
      .assign(to: \.viewState, on: self)
      .store(in: &cancellables)
  }

  //  func getPlaylist() {
  //    cancellables = createPublisher(for: getVideoListUseCase.execute())
  //      .removeDuplicates()
  //      .receive(on: DispatchQueue.main)
  //      .sink { completion in
  //
  //      } receiveValue: { videoDomain in
  //        self.status = .Success(videoDomain)
  //      }
  //  }

  private func intentToAction(_ intent: PlaylistIntent) -> PlaylistAction {
    switch intent {
    case .GetPlaylistIntent:
      return .GetPlaylistAction
    }
  }

  private func actionToResult(
    _ action: PlaylistAction
  ) -> AnyPublisher<PlaylistResult, Never> {
    switch action {
    case .GetPlaylistAction:
      return createPublisher(for: getVideoListUseCase.execute())
        .map { videoDomain in
          return .GetPlaylistResult(videoDomain)
        }
        .catch { receivedError in
          return Just(.GetPlaylistResult([VideoDomain]()))
        }
        .eraseToAnyPublisher()
    }
  }

  private func reduce(
    _ result: PlaylistResult,
    _ previousState: PlaylistViewState
  ) -> PlaylistViewState {
    var state = previousState
    switch result {
    case .GetPlaylistResult(let videosDomain):
      state.videosDomain = videosDomain
    case .Loading: break
      
    }
    return state
  }
}

enum PlaylistIntent {
  case GetPlaylistIntent
}

enum PlaylistAction {
  case GetPlaylistAction
}

enum PlaylistResult {
  case GetPlaylistResult(_ videoDomain: [VideoDomain])
  case Loading
}

struct PlaylistViewState: Equatable {
  var videosDomain: [VideoDomain] = [VideoDomain]()

  static func idle() -> PlaylistViewState {
    return PlaylistViewState()
  }
}
