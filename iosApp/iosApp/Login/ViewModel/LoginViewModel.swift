import Foundation
import shared

class LoginViewModel : ObservableObject {

  let postLoginUseCase: PostLoginIos
  let scopeHandler = ScopeProvider().getScopeForIos()

  @Published var status: LoginState = LoginState.Ready
  @Published var showAlert = false

  init(postLoginUseCase: PostLoginIos) {
    self.postLoginUseCase = postLoginUseCase
  }

  func postLogin(email: String, password: String) {
    self.showAlert = false
    postLoginUseCase.execute(email: email, password: password)
      .subscribe(scope: scopeHandler, onSuccess: { loginDomain in
        if loginDomain?.token.isEmpty ?? true {
          self.showAlert = true
          self.status = .AuthError(loginDomain?.error ?? "Username or Password is wrong")
        } else {
          self.status = .Success
        }
      }, onError: { KotlinThrowable in
        self.status = .Error
      })
  }

}

enum LoginState {
  case Ready
  case Loading
  case Success
  case AuthError(String)
  case Error
}
