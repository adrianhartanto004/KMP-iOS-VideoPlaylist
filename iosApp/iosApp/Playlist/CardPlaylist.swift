//
//  CardPlaylist.swift
//  iosApp
//
//  Created by Pras Adi on 07/07/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI

struct CardPlaylist: View {
    var body: some View {
      VStack {
        AsyncImage(url: URL(string: "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/images/BigBuckBunny.jpg")!,
                   placeholder: { Text("Loading ...") },
                   image: {
                    Image(uiImage: $0).resizable()})
          .aspectRatio(contentMode: .fit)
        VStack(alignment: .leading) {
          HStack(alignment: .top) {
            Text("Title")
            Spacer()
            Text("Authornya").font(.caption)
          }
          Text("Description").font(.caption).lineLimit(3).padding([.vertical])
        }
        .padding([.horizontal])
      }
      .cornerRadius(10)
      .overlay(
        RoundedRectangle(cornerRadius: 10)
          .stroke(Color(.sRGB, red: 150/255, green: 150/255, blue: 150/255, opacity: 0.1), lineWidth: 1))
      .padding()
    }
}

struct CardPlaylist_Previews: PreviewProvider {
    static var previews: some View {
        CardPlaylist()
    }
}
