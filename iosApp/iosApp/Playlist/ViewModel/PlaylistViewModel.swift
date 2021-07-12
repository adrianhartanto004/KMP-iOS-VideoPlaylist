import Foundation
import shared

class PlaylistViewModel: ObservableObject {

  let getVideoListUseCase: GetVideoListIos
  let deleteUserTokenUseCase: DeleteUserTokenIos
  let scopeHandler = ScopeProvider().getScopeForIos()

  @Published var status: StatusPlaylist = StatusPlaylist.Loading
  init(getVideoListUseCase: GetVideoListIos, deleteUserTokenUseCase: DeleteUserTokenIos) {
    self.getVideoListUseCase = getVideoListUseCase
    self.deleteUserTokenUseCase = deleteUserTokenUseCase
  }
  func getPlaylist() {
    getVideoListUseCase.execute().subscribe(scope: scopeHandler, onSuccess: { videoDomain in
      self.status = .Success(videoDomain as! [VideoDomain])
    }, onError: { KotlinThrowable in
      self.status = .Error
    })
  }
  func clearUserToken() {
    deleteUserTokenUseCase.execute().subscribe(scope: scopeHandler) {_ in
      self.status = .Logout
    } onError: { KotlinThrowable in
    }
  }
}

enum StatusPlaylist {
  case Loading
  case Success([VideoDomain])
  case Error
  case Logout
}
