package cz.jakubturcovsky.seminar6

import android.app.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.IBinder
import android.util.Log
import androidx.localbroadcastmanager.content.LocalBroadcastManager

class BroadcastService : Service() {

    companion object {
        val TAG = BroadcastService::class.java.simpleName
        const val FOO_ACTION = "FooAction"
    }

    private val receiver = object:BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            Log.d(TAG, "Broadcast received! Message = ${intent?.getStringExtra("message")}")
        }

    }
    override fun onBind(intent: Intent?): IBinder? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val filter = IntentFilter(FOO_ACTION)
        LocalBroadcastManager.getInstance(this).registerReceiver(receiver, filter)

        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        LocalBroadcastManager.getInstance(this).unregisterReceiver(receiver)
    }
}