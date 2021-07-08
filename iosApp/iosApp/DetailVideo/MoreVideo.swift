//
//  MoreVideo.swift
//  iosApp
//
//  Created by Pras Adi on 07/07/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI
import shared

struct MoreVideo: View {
  let listVideo: [VideoDomain]

  var body: some View {
    Section(header: HStack {
      Text("More Video")
        .padding([.leading, .vertical])
      Spacer()
    }
    .background(Color(UIColor.systemBackground))
    ) {
      ForEach(0...5, id: \.self) { num in
        ItemMoreVideo()
      }
    }
  }

//  private func moreVideoView() -> AnyView {
//    switch status {
//    case .Loading :
//      return AnyView(
//        Text("Loading")
//          .multilineTextAlignment(.center)
//          .padding())
//    case .Success :
//      return AnyView(
//        ForEach(0...5, id: \.self) { num in
//          ItemMoreVideo()
//        }
//      )
//    case .Error :
//      return AnyView(
//        Text("Error")
//          .multilineTextAlignment(.center)
//          .padding()
//      )
//    }
//  }
}
