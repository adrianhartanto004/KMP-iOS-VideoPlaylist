import Foundation

extension String {
  func emailRegex() -> Bool {
    let pattern = #"^[\w-\.]+@([\w-]+\.)+[\w-]{2,4}$"#
    let regex = try! NSRegularExpression(pattern: pattern, options: [])

    let range = NSRange(location: 0, length: self.utf16.count)
    return regex.firstMatch(in: self, options: [], range: range) != nil
  }
}
