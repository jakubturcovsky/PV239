package cz.pv239.seminar2.exercise

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.widget.Toast

// TODO Don't forget to register it in the manifest
class ToastService : Service() {

    companion object {
        const val EXTRA_MESSAGE = "message"
    }

    /**
     * This method is used for binding a service to an activity. Since we don't need to communicate
     * from an activity to the service directly, we can simply return null.
     */
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    /**
     * Called after a service creation. This is the place you want to put your code.
     */
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        // Get extra string from the intent argument
        val message = intent?.getStringExtra(EXTRA_MESSAGE) as String
        // Create a toast and show it
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

        return START_STICKY
    }
}