import Foundation
import shared
import Combine
import KMPNativeCoroutinesCombine

class PlaylistViewModel: ObservableObject {

  let getVideoListUseCase: GetVideoListIos

  @Published var status: StatusPlaylist = StatusPlaylist.Loading

  private var cancellable: AnyCancellable? = nil

  init(getVideoListUseCase: GetVideoListIos) {
    self.getVideoListUseCase = getVideoListUseCase
  }

  func getPlaylist() {
    cancellable = createPublisher(for: getVideoListUseCase.execute())
      .receive(on: DispatchQueue.main)
      .sink { completion in

      } receiveValue: { videoDomain in
        self.status = .Success(videoDomain)
      }
  }
}

enum StatusPlaylist {
  case Loading
  case Success([VideoDomain])
  case Error
}
