//
//  ItemMoreVideo.swift
//  iosApp
//
//  Created by Pras Adi on 07/07/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI
import shared

struct ItemMoreVideoView: View {
  let video: VideoDomain
    var body: some View {
      HStack (alignment: .top) {
        AsyncImage(url: URL(string: video.thumbnailUrl)!,
                   placeholder: { Text("Loading ...") },
                   image: {
                    Image(uiImage: $0).resizable()})
          .aspectRatio(contentMode: .fit)
          .frame(maxWidth: UIScreen.screenWidth/4)
        VStack(alignment: .leading) {
          HStack {
            Text(video.title)
            Spacer()
            Text(video.author+"\n").font(.caption)
          }
          Text(video.desc)
            .lineLimit(2)
            .font(.caption)
        }
        .padding(.trailing)
      }
      .padding(.vertical)
      .overlay(
        RoundedRectangle(cornerRadius: 10)
          .stroke(Color(.sRGB, red: 150/255, green: 150/255, blue: 150/255, opacity: 0.1), lineWidth: 1)
      )
    }
}

struct ItemMoreVideo_Previews: PreviewProvider {
    static var previews: some View {
      ItemMoreVideoView(video: VideoDomain(id: 1, desc: "", videoUrl: "", author: "", thumbnailUrl: "", title: ""))
    }
}
