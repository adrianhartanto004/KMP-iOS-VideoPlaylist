//
//  VideoInfo.swift
//  iosApp
//
//  Created by Pras Adi on 07/07/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI
import shared

struct VideoInfo: View {
  let video: VideoDomain
    var body: some View {
      VStack(alignment: .leading) {
        Text(video.title)
          .font(/*@START_MENU_TOKEN@*/.title/*@END_MENU_TOKEN@*/)
        Divider()
        Text(video.author)
        Divider()
        Text(video.desc)
        Divider()
      }.padding()
    }
}
