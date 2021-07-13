import Foundation
import shared

class LoginViewModel : ObservableObject {

  let postLoginUseCase: PostLoginIos
  let scopeHandler = ScopeProvider().getScopeForIos()

  @Published var uiState: LoginState = LoginState.Ready
  @Published var showAlert = false
  @Published var isFromRegisterPage = false

  init(postLoginUseCase: PostLoginIos) {
    self.postLoginUseCase = postLoginUseCase
  }

  func postLogin(email: String, password: String) {
    self.showAlert = false
    self.uiState = .Loading
    if !email.emailRegex() {
      self.showAlert = true
      self.uiState = .AuthError("Email is not valid")
    } else {
      postLoginUseCase.execute(email: email, password: password)
        .subscribe(scope: scopeHandler, onSuccess: { loginDomain in
          if loginDomain?.token.isEmpty ?? true {
            self.showAlert = true
            self.uiState = .AuthError(loginDomain?.error ?? "Username or Password is wrong")
          } else {
            self.uiState = .Success
          }
        }, onError: { KotlinThrowable in
          self.uiState = .Error
        })
    }
  }

}

enum LoginState : Equatable{
  case Ready
  case Loading
  case Success
  case AuthError(String)
  case Error
}
