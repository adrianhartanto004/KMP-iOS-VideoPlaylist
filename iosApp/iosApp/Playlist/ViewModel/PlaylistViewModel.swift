//
//  PlaylistViewModel.swift
//  iosApp
//
//  Created by Pras Adi on 07/07/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import Foundation
import shared

class PlaylistViewModel: ObservableObject {
  let repository: VideoPlaylistRepository
  @Published var status: StatusPlaylist = StatusPlaylist.Loading
  init(repository: VideoPlaylistRepository) {
    self.repository = repository
  }
  func getPlaylist() {
    repository.getVideos(completionHandler: { videos, error in
      if let videos = videos {
        print(videos)
        self.status = .Success(videos)
      } else {
        self.status = .Error
      }
    })
  }
}

enum StatusPlaylist {
  case Loading
  case Success([VideoDomain])
  case Error
}
