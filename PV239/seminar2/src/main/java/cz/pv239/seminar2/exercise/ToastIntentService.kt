package cz.pv239.seminar2.exercise

import android.app.IntentService
import android.content.Intent

// TODO Don't forget to register it in the manifest
class ToastIntentService : IntentService(ToastIntentService::class.java.simpleName) {
    companion object {
        const val EXTRA_MESSAGE = "message"
    }

    override fun onHandleIntent(intent: Intent?) {
        // Get extra string from the intent argument
        // Create a toast and show it
    }
}