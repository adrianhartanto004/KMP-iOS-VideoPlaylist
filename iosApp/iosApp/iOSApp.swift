import SwiftUI
import shared

@main
struct iOSApp: App {

  let repository = VideoPlaylistRepositoryImpl()

  init(){
    KoinIosKt.startKoin()
  }

  var body: some Scene {
    WindowGroup {
      NavigationView {
        PlaylistView(viewModel: .init(repository: repository))
      }
    }
  }
}
