import SwiftUI
import shared

struct PlaylistView: View {
  @ObservedObject var viewModel: PlaylistViewModel
  let onLogout: ()->Void

  init(getVideoListUseCase: GetVideoListIos, onLogout: @escaping ()->Void) {
    viewModel = PlaylistViewModel(getVideoListUseCase: getVideoListUseCase)
    self.onLogout = onLogout
    viewModel.getPlaylist()
  }

  var body: some View {
    ScrollView {
      playlist()
    }
  }

  private func playlist() -> AnyView {
    switch viewModel.status {
    case .Loading :
      return AnyView(Text("loading").multilineTextAlignment(.center))
    case .Success(let videos) :
      return AnyView(
        ForEach(videos, id:\.self) { video in
          NavigationLink (
            destination: DetailView(video: video, listMoreVideo: videos)
          ) {
            CardPlaylist(video: video)
          }
          .buttonStyle(PlainButtonStyle())
        }
        .navigationBarTitle("Playlist")
        .navigationBarItems(trailing: Button("Logout", action: onLogout))
      )
    case .Error :
      return AnyView(Text("error").multilineTextAlignment(.center))
    }
  }
}

