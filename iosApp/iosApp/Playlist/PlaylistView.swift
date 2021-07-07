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
    case .Success(_) :
      return AnyView(
        ForEach(0...8, id:\.self) { num in
          NavigationLink (
            destination: Detail()
          ) {
            CardPlaylist()
          }
          .buttonStyle(PlainButtonStyle())
        }
      )
    case .Error :
      return AnyView(Text("error").multilineTextAlignment(.center))
    }
  }
}

