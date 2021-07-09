import SwiftUI
import shared

struct RegisterView: View {

  @ObservedObject var viewModel: RegisterViewModel

  init(postRegisterIos: PostRegisterIos) {
    viewModel = RegisterViewModel(postRegisterUseCase: postRegisterIos)
  }

  var body: some View {
    registerViews()
  }

  private func registerViews() -> AnyView {
    switch viewModel.status {
    case .Ready, .Loading, .Error :
      return AnyView(RegisterContentView(viewModel: viewModel))
    case .Success :
      return AnyView(LoginView(postLoginIos: .init()))
    case .AuthError(let error):
      return AnyView(RegisterContentView(viewModel: viewModel, errorText: error))
    }
  }
}

struct RegisterContentView : View {

  @ObservedObject var viewModel: RegisterViewModel

  var errorText = ""

  @State private var email = "pras"
  @State private var name = "pras"
  @State private var password = "pras"
  @State private var confirmPassword = "pras"

  var body: some View {
    VStack() {
      Text("Register")
        .font(.largeTitle).foregroundColor(Color.white)
        .padding([.top, .bottom], 40)
        .shadow(radius: 10.0, x: 20, y: 10)

      VStack(alignment: .leading, spacing: 15) {
        TextField("Email", text: self.$email)
          .padding()
          .background(Color.themeTextField)
          .cornerRadius(20.0)
          .shadow(radius: 10.0, x: 20, y: 10)

        TextField("Name", text: self.$name)
          .padding()
          .background(Color.themeTextField)
          .cornerRadius(20.0)
          .shadow(radius: 10.0, x: 20, y: 10)

        SecureField("Password", text: self.$password)
          .padding()
          .background(Color.themeTextField)
          .cornerRadius(20.0)
          .shadow(radius: 10.0, x: 20, y: 10)

        SecureField("Confirm Password", text: self.$confirmPassword)
          .padding()
          .background(Color.themeTextField)
          .cornerRadius(20.0)
          .shadow(radius: 10.0, x: 20, y: 10)
      }.padding([.leading, .trailing], 27.5)

      Button(action: {
        viewModel.postRegister(email: self.email, name: self.name, password: self.password, confirmPassword: self.confirmPassword)
      }) {
        Text("Sign In")
          .font(.headline)
          .foregroundColor(.white)
          .padding()
          .frame(width: 300, height: 50)
          .background(Color.green)
          .cornerRadius(15.0)
          .shadow(radius: 10.0, x: 20, y: 10)
      }.padding(.top, 50)

      Spacer()
      HStack(spacing: 0) {
//        Text("Don't have an account? ")
//        Button(action: {}) {
//          Text("Sign Up")
//            .foregroundColor(.black)
//        }
      }
    }
    .background(
      LinearGradient(gradient: Gradient(colors: [.purple, .blue]), startPoint: .top, endPoint: .bottom)
        .edgesIgnoringSafeArea(.all))
    .alert(isPresented: $viewModel.showAlert) {
      Alert(title: Text("Register Failed"),
            message: Text(errorText))
    }
  }
}
