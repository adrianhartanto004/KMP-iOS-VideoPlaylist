import AVKit
import Foundation
import shared
import Combine
import KMPNativeCoroutinesCombine

class DetailViewModel: ObservableObject {
  let getVideoListUseCase: GetVideoListIos

  @Published var listMoreVideo: [VideoDomain]

  @Published var video: VideoDomain
  @Published var status: StatusPlaylist = .Loading
  @Published var avPlayer: AVPlayer = AVPlayer()

  private var cancellable: AnyCancellable? = nil

  init(getVideoListUseCase: GetVideoListIos, video: VideoDomain, listMoreVideo: [VideoDomain]) {
    self.getVideoListUseCase = getVideoListUseCase
    self.video = video
    self.listMoreVideo = listMoreVideo
  }

  func getMoreVideos() {
    cancellable = createPublisher(for: getVideoListUseCase.execute())
      .receive(on: DispatchQueue.main)
      .sink { completion in

      } receiveValue: { videoDomain in
        self.status = .Success(videoDomain)
      }
  }

  func changeVideo(video: VideoDomain) {
    self.video = video
    avPlayer = AVPlayer(url: URL(string: video.videoUrl)!)
  }
}
