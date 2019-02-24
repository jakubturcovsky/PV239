package cz.pv239.seminar1

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MessageActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_MESSAGE = "message"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_message)

        // Get the intent which started this Activity
        val startingIntent = intent
        // Get the extra content I've put to the Intent, in this case - String with message
        val message = startingIntent.getStringExtra(EXTRA_MESSAGE)

        // Set the message as a text to this Activity's TextView
        val messageView = findViewById<TextView>(R.id.message)
        messageView.text = message
    }
}
