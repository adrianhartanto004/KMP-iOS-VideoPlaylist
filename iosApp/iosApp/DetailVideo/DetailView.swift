import SwiftUI
import AVKit
import shared

struct DetailView: View {
  let video: VideoDomain
  let listMoreVideo: [VideoDomain]

  var avPlayer: AVPlayer?

  init(video: VideoDomain, listMoreVideo: [VideoDomain]){
    self.video = video
    self.listMoreVideo = listMoreVideo
    guard let videoUrl = URL(string: video.videoUrl) else { return }
    avPlayer = AVPlayer(url: videoUrl)
  }

  var body: some View {
    VStack {
      VideoPlayer(player: avPlayer)
        .frame(height: 300)
      ScrollView {
        LazyVStack (alignment: .leading, pinnedViews: .sectionHeaders) {
          VideoInfo(video: video)
          MoreVideo(listVideo: listMoreVideo)
          Spacer()
        }
        .navigationBarTitle(video.title, displayMode: .inline)
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
