//
//  Status.swift
//  iosApp
//
//  Created by Pras Adi on 07/07/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import Foundation
import shared

enum Status {
  case Loading
  case Success([VideoDomain])
  case Error(String)
}
