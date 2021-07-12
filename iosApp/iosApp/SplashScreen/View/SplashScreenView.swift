import SwiftUI
import shared

struct SplashScreenView: View {

  @ObservedObject var viewModel: SplashScreenViewModel

  init(getUserTokenUseCase: GetUserTokenIos) {
    viewModel = SplashScreenViewModel(getUserTokenUseCase: getUserTokenUseCase)
    viewModel.getUserToken()
  }

  var body: some View {
    splashScreenViews()
  }

  private func splashScreenViews() -> AnyView {
    switch viewModel.state {
    case .Loading:
      return AnyView(Text("Loading..."))
    case.IsTokenEmpty(let isEmpty):
      if isEmpty {
        return AnyView(LoginView(postLoginIos: .init(), isFromRegisterPage: false))
      } else {
        return AnyView(PlaylistView(getVideoListUseCase: .init()))
      }
    }
  }
}
