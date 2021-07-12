import Foundation
import shared

class SplashScreenViewModel : ObservableObject {

  let getUserTokenUseCase: GetUserTokenIos
  let scopeHandler = ScopeProvider().getScopeForIos()

  @Published var state: SplashScreenState = SplashScreenState.Loading

  init(getUserTokenUseCase: GetUserTokenIos) {
    self.getUserTokenUseCase = getUserTokenUseCase
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
}

enum SplashScreenState {
  case Loading
  case IsTokenEmpty(Bool = true)
}
