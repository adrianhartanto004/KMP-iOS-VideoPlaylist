import Foundation
import shared

class RegisterViewModel : ObservableObject {

  let postRegisterUseCase: PostRegisterIos
  let scopeHandler = ScopeProvider().getScopeForIos()

  @Published var status: RegisterState = RegisterState.Ready
  @Published var showAlert = false

  init(postRegisterUseCase: PostRegisterIos) {
    self.postRegisterUseCase = postRegisterUseCase
  }

  func postRegister(email: String, name: String, password: String, confirmPassword: String) {
    self.showAlert = false
    if(!validateEmail(email: email)){
      self.showAlert = true
      self.status = .AuthError("Email is empty")
    } else if !validateName(name: name) {
      self.showAlert = true
      self.status = .AuthError("Name is empty")
    } else if !validatePassword(password: password) {
      self.showAlert = true
      self.status = .AuthError("Password is empty")
    } else if !validateConfirmPassword(password: password, confirmPassword: confirmPassword) {
      self.showAlert = true
      self.status = .AuthError("Password and confirm password must be matched")
    } else {
      postRegisterUseCase.execute(email: email, name: name, password: password)
        .subscribe(scope: scopeHandler, onSuccess: { registerDomain in
          if registerDomain?.isSuccess ?? true {
            self.status = .Success
          } else {
            self.showAlert = true
            self.status = .AuthError("Register failed, please try again later")
          }
        }, onError: { KotlinThrowable in
          self.status = .Error
        })
    }
  }

  private func validateEmail(email:String)-> Bool{
    if(!email.isEmpty){
      return true
    }
    return false
  }

  private func validateName(name: String)-> Bool{
    if(!name.isEmpty){
      return true
    }
    return false
  }

  private func validatePassword(password: String)-> Bool{
    if(!password.isEmpty){
      return true
    }
    return false
  }

  private func validateConfirmPassword(password: String, confirmPassword: String)-> Bool{
    if(password == confirmPassword){
      return true
    }
    return false
  }
}

enum RegisterState {
  case Ready
  case Loading
  case Success
  case AuthError(String)
  case Error
}
