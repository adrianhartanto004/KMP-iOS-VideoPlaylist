import SwiftUI
import shared

@main
struct iOSApp: App {
  init(){
    KoinIosKt.startKoin()
  }

  var body: some Scene {
    WindowGroup {
      NavigationView {
        SplashScreenView(getUserTokenUseCase: .init(), deleteUserTokenUseCase: .init())
      }
    }
  }
}
