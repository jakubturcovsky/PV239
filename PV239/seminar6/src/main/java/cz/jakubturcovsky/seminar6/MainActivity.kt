package cz.jakubturcovsky.seminar6

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startService(Intent(this, BroadcastService::class.java))

        send_broadcast.setOnClickListener {
            val intent = Intent(BroadcastService.FOO_ACTION).apply { putExtra("message", "Do bears have claws?") }
            LocalBroadcastManager.getInstance(this).sendBroadcast(intent)
        }
    }
}
