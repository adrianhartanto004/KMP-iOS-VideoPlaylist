import SwiftUI
import AVKit
import shared

struct DetailView: View {
  @ObservedObject var viewModel: DetailViewModel

  init(video: VideoDomain, listMoreVideo: [VideoDomain], getVideoListUseCase: GetVideoListIos){
    self.viewModel = DetailViewModel(getVideoListUseCase: getVideoListUseCase, video: video, listMoreVideo: listMoreVideo)
    self.viewModel.getMoreVideos()
    self.viewModel.changeVideo(video: video)
  }

  var body: some View {
    VStack {
      VideoPlayer(player: viewModel.avPlayer)
        .frame(height: 300)
      ScrollView {
        LazyVStack (alignment: .leading, pinnedViews: .sectionHeaders) {
          VideoInfoView(video: viewModel.video)
          MoreVideoView(listVideo: viewModel.listMoreVideo) {
            viewModel.changeVideo(video: $0)
          }
          Spacer()
        }
        .navigationBarTitle(viewModel.video.title, displayMode: .inline)
      }
    }
    .onAppear{
      viewModel.avPlayer.seek(to: .zero)
    }
    .onDisappear {
      viewModel.avPlayer.pause()
    }
  }
}
