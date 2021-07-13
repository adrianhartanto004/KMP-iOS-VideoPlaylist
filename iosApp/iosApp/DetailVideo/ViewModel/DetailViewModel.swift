//
//  DetailViewModel.swift
//  iosApp
//
//  Created by Pras Adi on 07/07/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import Foundation
import shared

class DetailViewModel: ObservableObject {
  let getVideoListUseCase: GetVideoListIos
  let scopeHandler = ScopeProvider().getScopeForIos()

  @Published var listMoreVideo: [VideoDomain]

  @Published var video: VideoDomain
  @Published var status: StatusPlaylist = .Loading

  init(getVideoListUseCase: GetVideoListIos, video: VideoDomain, listMoreVideo: [VideoDomain]) {
    self.getVideoListUseCase = getVideoListUseCase
    self.video = video
    self.listMoreVideo = listMoreVideo
  }

  func getMoreVideos() {
    getVideoListUseCase.execute().subscribe(
      scope: scopeHandler,
      onSuccess: { videoDomain in
        self.status = .Success(videoDomain as! [VideoDomain])
      },
      onError: { KotlinThrowable in
        self.status = .Error
      }
    )
  }
}
