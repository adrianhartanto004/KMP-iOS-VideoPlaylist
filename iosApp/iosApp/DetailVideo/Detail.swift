//
//  Detail.swift
//  iosApp
//
//  Created by Pras Adi on 07/07/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI
import AVKit

struct Detail: View {
  @ObservedObject var viewModel: DetailViewModel = DetailViewModel()
  @State var player = AVPlayer(url: URL(string: "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4")!)

  var body: some View {
    VStack {
      VideoPlayer(player: player)
        .frame(height: 300)
      ScrollView {
        LazyVStack (alignment: .leading, pinnedViews: .sectionHeaders) {
          VideoInfo()
          MoreVideo(status: $viewModel.status)
          Spacer()
        }
        .navigationBarTitle("Video Title", displayMode: .inline)
      }
    }
    .onDisappear {
      player.pause()
    }
  }
}

struct Detail_Previews: PreviewProvider {
  static var previews: some View {
    Detail()
  }
}
