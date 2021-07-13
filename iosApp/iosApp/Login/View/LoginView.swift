import SwiftUI
import shared

struct LoginView: View {

  var isFromRegisterPage = false
  let onLogin: () -> Void

  @ObservedObject var viewModel: LoginViewModel
  init(postLoginIos: PostLoginIos, isFromRegisterPage: Bool, onLogin: @escaping () -> Void) {
    viewModel = LoginViewModel(postLoginUseCase: postLoginIos)
    self.onLogin = onLogin
    self.isFromRegisterPage = isFromRegisterPage
    viewModel.isFromRegisterPage = isFromRegisterPage
  }

  var body: some View {
    loginViews()
  }

  private func loginViews() -> AnyView {
    switch viewModel.uiState {
    case .Ready, .Loading, .Error :
      return AnyView(LoginContentView(viewModel: viewModel))
    case .Success :
      onLogin()
      return AnyView(LoginContentView(viewModel: viewModel))
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
    ZStack {
      VStack {
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

          if (!email.emailRegex() && email.count > 0) {
            Text("Email is not valid")
              .font(.caption)
              .foregroundColor(.white)
          }

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
      .navigationBarItems(trailing: Text(""))
      if viewModel.uiState == LoginState.Loading {
        Rectangle()
          .fill(Color.black).opacity(0.6)
          .edgesIgnoringSafeArea(.all)

        VStack(spacing: 48) {
          ProgressView().scaleEffect(2.0, anchor: .center)
          Text("Loading...").font(.title).fontWeight(.semibold)
        }
        .frame(width: 250, height: 200)
        .background(Color.white)
        .foregroundColor(Color.primary)
        .cornerRadius(16)
      }
    }
  }
}

extension Color {
  static var themeTextField: Color {
    return Color(red: 220.0/255.0, green: 230.0/255.0, blue: 230.0/255.0, opacity: 1.0)
  }
}
