import UIKit
import SwiftUI
import ComposeApp

struct ComposeView: UIViewControllerRepresentable {
    var notificationId: String?

    func makeUIViewController(context: Context) -> UIViewController {
        MainViewControllerKt.MainViewController(notificationId: notificationId)
    }

    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {}
}

struct ContentView: View {
    var notificationId: String?

    var body: some View {
        ComposeView(notificationId: notificationId)
                .id(notificationId)
                .ignoresSafeArea(.keyboard)
    }
}



