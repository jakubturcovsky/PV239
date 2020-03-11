package cz.pv239.seminar2

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.util.Log
import java.util.*
import kotlin.concurrent.timerTask

class AnnoyingService : Service() {

    companion object {

        fun newIntent(context: Context): Intent {
            return Intent(context, AnnoyingService::class.java)
        }

        private val TAG = AnnoyingService::class.java.simpleName

        private const val TIMER_DELAY: Long = 1_000L
    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        // Do an action (display log) every TIMER_DELAY ms
        Timer().scheduleAtFixedRate(timerTask {
            Log.v(TAG, "AnnoyingService is running")
        }, TIMER_DELAY, TIMER_DELAY)

        return START_STICKY
    }
}
