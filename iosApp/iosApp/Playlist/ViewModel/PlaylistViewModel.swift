import Foundation
import shared

class PlaylistViewModel: ObservableObject {

  let getVideoListUseCase: GetVideoListIos
  let scopeHandler = ScopeProvider().getScopeForIos()

  @Published var status: StatusPlaylist = StatusPlaylist.Loading
  init(getVideoListUseCase: GetVideoListIos) {
    self.getVideoListUseCase = getVideoListUseCase
  }
  func getPlaylist() {
    getVideoListUseCase.execute().subscribe(scope: scopeHandler, onSuccess: { videoDomain in
      self.status = .Success(videoDomain as! [VideoDomain])
    }, onError: { KotlinThrowable in
      self.status = .Error
    })
  }
}

enum StatusPlaylist {
  case Loading
  case Success([VideoDomain])
  case Error
}
