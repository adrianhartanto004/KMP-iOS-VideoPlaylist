import SwiftUI
import AVKit
import shared

struct DetailView: View {
  @ObservedObject var viewModel: DetailViewModel
  @State var avPlayer: AVPlayer?

  init(video: VideoDomain, listMoreVideo: [VideoDomain], getVideoListUseCase: GetVideoListIos){
    self.viewModel = DetailViewModel(getVideoListUseCase: getVideoListUseCase, video: video, listMoreVideo: listMoreVideo)
    guard let videoUrl = URL(string: video.videoUrl) else { return }
    avPlayer = AVPlayer(url: videoUrl)
    viewModel.getMoreVideos()
  }

  var body: some View {
    VStack {
      VideoPlayer(player: avPlayer)
        .frame(height: 300)
      ScrollView {
        LazyVStack (alignment: .leading, pinnedViews: .sectionHeaders) {
          VideoInfoView(video: viewModel.video)
          MoreVideoView(listVideo: viewModel.listMoreVideo) {
            avPlayer?.pause()
            guard let videoUrl = URL(string: $0.videoUrl) else { return }
            avPlayer = AVPlayer(url: videoUrl)
            viewModel.video = $0
          }
          Spacer()
        }
        .navigationBarTitle(viewModel.video.title, displayMode: .inline)
      }
    }
    .onAppear{
      avPlayer?.seek(to: .zero)
    }
    .onDisappear {
      avPlayer?.pause()
    }
  }
}
