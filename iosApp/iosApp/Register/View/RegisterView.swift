import SwiftUI
import shared

struct RegisterView: View {

  @ObservedObject var viewModel: RegisterViewModel

  @Environment(\.presentationMode) var presentation

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
      self.presentation.wrappedValue.dismiss()
      viewModel.status = .Ready
      return AnyView(RegisterContentView(viewModel: viewModel))
    case .AuthError(let error):
      return AnyView(RegisterContentView(viewModel: viewModel, errorText: error))
    }
  }
}

struct RegisterContentView : View {

  @ObservedObject var viewModel: RegisterViewModel

  var errorText = ""

  @State private var email = ""
  @State private var name = ""
  @State private var password = ""
  @State private var confirmPassword = ""

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

        if email.isEmpty {
          Text("Email is not valid")
            .font(.caption)
            .foregroundColor(.white)
        }

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
        Text("Sign Up")
          .font(.headline)
          .foregroundColor(.white)
          .padding()
          .frame(width: 300, height: 50)
          .background(Color.green)
          .cornerRadius(15.0)
          .shadow(radius: 10.0, x: 20, y: 10)
      }.padding(.top, 50)

      Spacer()
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
