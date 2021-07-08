import SwiftUI
import shared

struct PlaylistView: View {
  @ObservedObject var viewModel: PlaylistViewModel

  init(getVideoListUseCase: GetVideoListIos) {
    viewModel = PlaylistViewModel(getVideoListUseCase: getVideoListUseCase)
    viewModel.getPlaylist()
  }

  var body: some View {
    ScrollView {
      playlist()
    }
    .navigationBarTitle("Playlist")
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
      )
    case .Error :
      return AnyView(Text("error").multilineTextAlignment(.center))
    }
  }
}

