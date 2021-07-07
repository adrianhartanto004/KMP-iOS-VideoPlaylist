//
//  VideoInfo.swift
//  iosApp
//
//  Created by Pras Adi on 07/07/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI

struct VideoInfo: View {
    var body: some View {
      VStack(alignment: .leading) {
        Text("Title nya").font(/*@START_MENU_TOKEN@*/.title/*@END_MENU_TOKEN@*/)
        Text("")
        Text("Author nya")
        Text("")
        Text("Description")
      }.padding()
    }
}

struct VideoInfo_Previews: PreviewProvider {
    static var previews: some View {
        VideoInfo()
    }
}
