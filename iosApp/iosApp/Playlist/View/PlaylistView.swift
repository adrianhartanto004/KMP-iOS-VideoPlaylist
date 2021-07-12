import SwiftUI
import shared

struct PlaylistView: View {
  @ObservedObject var viewModel: PlaylistViewModel

  init(getVideoListUseCase: GetVideoListIos, deleteUserTokenUseCase: DeleteUserTokenIos) {
    viewModel = PlaylistViewModel(getVideoListUseCase: getVideoListUseCase, deleteUserTokenUseCase: deleteUserTokenUseCase)
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
        .navigationBarItems(trailing: Button("Logout") {
          viewModel.clearUserToken()
        })
      )
    case .Error :
      return AnyView(Text("error").multilineTextAlignment(.center))
    case .Logout :
      return AnyView(
        LoginView(postLoginIos: .init(), isFromRegisterPage: false)
      )
    }
  }
}

