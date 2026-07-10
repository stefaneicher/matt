package com.stefaneicher.matt.data.remote

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MattMessagingService : FirebaseMessagingService() {
    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        // TODO: parse message and create local FamilyEvent for the event scroll
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        // TODO: send token to Firestore for the current user
    }
}
