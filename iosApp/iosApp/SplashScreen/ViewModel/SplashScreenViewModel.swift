import Foundation
import shared

class SplashScreenViewModel : ObservableObject {

  let getUserTokenUseCase: GetUserTokenIos
  let deleteUserTokenUseCase: DeleteUserTokenIos
  let scopeHandler = ScopeProvider().getScopeForIos()

  @Published var state: SplashScreenState = SplashScreenState.Loading

  init(getUserTokenUseCase: GetUserTokenIos, deleteUserTokenUseCase: DeleteUserTokenIos) {
    self.getUserTokenUseCase = getUserTokenUseCase
    self.deleteUserTokenUseCase = deleteUserTokenUseCase
  }

  func getUserToken() {
    getUserTokenUseCase.execute()
      .subscribe(scope: scopeHandler, onSuccess: { token in
        if token == "" {
          self.state = .IsTokenEmpty(true)
        } else {
          self.state = .IsTokenEmpty(false)
        }
      }, onError: { KotlinThrowable in
        self.state = .IsTokenEmpty(true)
      })
  }

  func deleteUserToken() {
    deleteUserTokenUseCase.execute().subscribe(scope: scopeHandler) {_ in
      self.state = .IsTokenEmpty(true)
    } onError: { KotlinThrowable in
    }
  }
}

enum SplashScreenState {
  case Loading
  case IsTokenEmpty(Bool = true)
}
