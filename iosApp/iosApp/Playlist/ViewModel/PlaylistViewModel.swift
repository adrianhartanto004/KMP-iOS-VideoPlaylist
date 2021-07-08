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

  let getVideoListUseCase: GetVideoListIos
  let scopeHandler = ScopeProvider().getScopeForIos()

  @Published var status: StatusPlaylist = StatusPlaylist.Loading
  init(getVideoListUseCase: GetVideoListIos) {
    self.getVideoListUseCase = getVideoListUseCase
  }
  func getPlaylist() {
    getVideoListUseCase.execute().subscribe(scope: scopeHandler, onSuccess: { videoDomain in
      self.status = .Success(videoDomain as! [VideoDomain])
    }, onError: { KotlinThrowable in

    })
//    repository.getVideos(completionHandler: { videos, error in
//      if let videos = videos {
//        print(videos)
//        self.status = .Success(videos)
//      } else {
//        self.status = .Error
//      }
//    })
  }
}

enum StatusPlaylist {
  case Loading
  case Success([VideoDomain])
  case Error
}
