package cz.pv239.seminar1

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import cz.pv239.seminar1.MessageActivity.Companion.EXTRA_MESSAGE

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Set XML layout with views to this Activity
        setContentView(R.layout.activity_main)

        /* Exercise 1 */
        // Get TextView with ID "text" from our layout
        val text = findViewById<TextView>(R.id.text)
        // Get Button with ID "button"
        val button = findViewById<Button>(R.id.button)
        // Listen for clicks on button
        button.setOnClickListener {
            Log.i(TAG, "User wants to know the answer")
            text.setText(R.string.answer)
        }

        /* Exercise 2 */
        val button2 = findViewById<Button>(R.id.button2)
        button2.setOnClickListener {
            Log.i(TAG, "User wants to send the message")
            // Intent for starting a new MessageActivity
            val intent = Intent(this, MessageActivity::class.java)
            // If I want to put some info to the new Activity, I can put it into the Intent this way
            intent.putExtra(EXTRA_MESSAGE, "Voilà")
            // Start a MessageActivity
            startActivity(intent)
        }
    }

    companion object {
        private val TAG = MainActivity::class.java.simpleName
    }
}
