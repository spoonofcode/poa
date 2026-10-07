import SwiftUI
import FirebaseCore
import FirebaseMessaging
import ComposeApp

class AppDelegate: NSObject, UIApplicationDelegate, MessagingDelegate, UNUserNotificationCenterDelegate {

    var notificationId: String? = nil

    func application(_ application: UIApplication,
                     didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey : Any]? = nil) -> Bool {

        FirebaseApp.configure()
        Messaging.messaging().delegate = self

        UNUserNotificationCenter.current().delegate = self

        application.registerForRemoteNotifications()

        // Check if launched from notification (cold start)
        if let remoteNotification = launchOptions?[.remoteNotification] as? [String: Any],
           let notificationId = Self.extractNotificationId(from: remoteNotification) {
            self.notificationId = notificationId
            DispatchQueue.main.asyncAfter(deadline: .now() + 0.5) {
                NotificationNavigationManager.shared.handleNotificationClick(notificationId: notificationId)
            }
        }

        return true
    }

    func application(_ application: UIApplication, didRegisterForRemoteNotificationsWithDeviceToken deviceToken: Data) {
        Messaging.messaging().apnsToken = deviceToken
    }

    func messaging(_ messaging: Messaging, didReceiveRegistrationToken fcmToken: String?) {
        print("New token iOS: \(String(describing: fcmToken))")
    }

    func userNotificationCenter(_ center: UNUserNotificationCenter,
                                 didReceive response: UNNotificationResponse,
                                 withCompletionHandler completionHandler: @escaping () -> Void) {
        let userInfo = response.notification.request.content.userInfo
        if let notificationId = Self.extractNotificationId(from: userInfo) {
            self.notificationId = notificationId
            NotificationNavigationManager.shared.handleNotificationClick(notificationId: notificationId)
        }
        completionHandler()
    }

    static func extractNotificationId(from userInfo: [AnyHashable: Any]) -> String? {
        return userInfo["notification_id"] as? String
    }
}

@main
struct iOSApp: App {

    @UIApplicationDelegateAdaptor(AppDelegate.self) var delegate

    var body: some Scene {
        WindowGroup {
            ContentView(notificationId: delegate.notificationId)
        }
    }
}