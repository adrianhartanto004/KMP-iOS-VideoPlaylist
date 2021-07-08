//
//  PlaylistView.swift
//  iosApp
//
//  Created by Pras Adi on 07/07/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI

struct PlaylistView: View {
  @ObservedObject private(set) var viewModel: PlaylistViewModel

  var body: some View {
    ScrollView {
      playlist()
    }
    .navigationBarTitle("Playlist")
    .onAppear {
      viewModel.getPlaylist()
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
            destination: Detail()
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

