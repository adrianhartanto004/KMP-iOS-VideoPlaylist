import SwiftUI
import shared

struct LoginView: View {

  var isFromRegisterPage = false

  @ObservedObject var viewModel: LoginViewModel
  init(postLoginIos: PostLoginIos, isFromRegisterPage: Bool) {
    viewModel = LoginViewModel(postLoginUseCase: postLoginIos)
    self.isFromRegisterPage = isFromRegisterPage
    viewModel.isFromRegisterPage = isFromRegisterPage
  }

  var body: some View {
    loginViews()
  }

  private func loginViews() -> AnyView {
    switch viewModel.status {
    case .Ready, .Loading, .Error :
      return AnyView(LoginContentView(viewModel: viewModel))
    case .Success :
      return AnyView(PlaylistView(getVideoListUseCase: .init()))
    case .AuthError(let error):
      return AnyView(LoginContentView(viewModel: viewModel, errorText: error))
    }
  }
}

struct LoginContentView : View {

  @ObservedObject var viewModel: LoginViewModel

  var errorText = ""

  @State private var email = ""
  @State private var password = ""

  var body: some View {
    VStack() {
      Text("Login")
        .font(.largeTitle).foregroundColor(Color.white)
        .padding([.top, .bottom], 40)
        .shadow(radius: 10.0, x: 20, y: 10)

      VStack(alignment: .leading, spacing: 15) {
        TextField("Email", text: self.$email)
          .padding()
          .background(Color.themeTextField)
          .cornerRadius(20.0)
          .shadow(radius: 10.0, x: 20, y: 10)

        SecureField("Password", text: self.$password)
          .padding()
          .background(Color.themeTextField)
          .cornerRadius(20.0)
          .shadow(radius: 10.0, x: 20, y: 10)
      }.padding([.leading, .trailing], 27.5)

      Button(action: {
        viewModel.postLogin(email: self.email, password: self.password)
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
      if !viewModel.isFromRegisterPage {
        HStack(spacing: 0) {
          Text("Don't have an account? ")
          NavigationLink(
            destination: RegisterView(postRegisterIos: .init())
          ){
            Text("Sign Up")
              .foregroundColor(.black)
          }
        }
      }
    }
    .background(
      LinearGradient(gradient: Gradient(colors: [.purple, .blue]), startPoint: .top, endPoint: .bottom)
        .edgesIgnoringSafeArea(.all))
    .alert(isPresented: $viewModel.showAlert) {
      Alert(title: Text("Login Failed"),
            message: Text(errorText))
    }
  }
}

extension Color {
  static var themeTextField: Color {
    return Color(red: 220.0/255.0, green: 230.0/255.0, blue: 230.0/255.0, opacity: 1.0)
  }
}
