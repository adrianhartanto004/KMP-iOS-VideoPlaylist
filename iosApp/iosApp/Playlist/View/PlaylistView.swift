import SwiftUI
import shared

struct PlaylistView: View {
  @ObservedObject var viewModel: PlaylistViewModel
  let onLogout: ()->Void

  init(getVideoListUseCase: GetVideoListIos, onLogout: @escaping ()->Void) {
    self.onLogout = onLogout
    viewModel = PlaylistViewModel(getVideoListUseCase: getVideoListUseCase)
    viewModel.emits(.GetPlaylistIntent)
  }

  var body: some View {
    ScrollView {
      playlist()
    }
  }

  private func playlist() -> AnyView {
    switch viewModel.viewState {
    case .idle() :
      return AnyView(Text("loading").multilineTextAlignment(.center))
    default:
      return AnyView(
        ForEach(viewModel.viewState.videosDomain, id:\.self) { video in
          NavigationLink (
            destination: DetailView(video: video, listMoreVideo: viewModel.viewState.videosDomain, getVideoListUseCase: .init())
          ) {
            CardPlaylist(video: video)
          }
          .buttonStyle(PlainButtonStyle())
        }
        .navigationBarTitle("Playlist")
        .navigationBarItems(trailing: Button("Logout", action: onLogout))
      )
    }
  }
}

