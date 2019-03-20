package cz.pv239.seminar5.activity

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import cz.pv239.seminar5.R
import cz.pv239.seminar5.fragment.MainFragment

/**
 * TODO Tasks:
 * * Create about dialog instead of a Toast
 * * Create ListPreference with at least 3 options
 * * Create your own notification (add PendingIntent and handle it if you have time)
 * * Add another menu item from the MainFragment
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {       // Important, otherwise there'd be a new Fragment created with every orientation change
            supportFragmentManager
                    ?.beginTransaction()
                    ?.replace(android.R.id.content, MainFragment.newInstance(), MainFragment::class.java.simpleName)
                    ?.commit()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.settings -> startActivity(Intent(this, SettingsActivity::class.java))
            R.id.notification -> makeNotification()
            R.id.about -> Toast.makeText(this@MainActivity, "App by @JakubTurcovsky", Toast.LENGTH_SHORT).show()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            if (manager.getNotificationChannel(CHANNEL_ID) != null) {
                return
            }

            // Create the NotificationChannel
            val name = getString(R.string.notification_channel_test_name)
            val description = getString(R.string.notification_channel_test_description)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance)
            channel.description = description
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            manager.createNotificationChannel(channel)
        }
    }

    /**
     * WTF Android? We have to create channel with NotificationManager and notification with NotificationManagerCompat?!
     *
     * More about notifications:
     * https://developer.android.com/training/notify-user/build-notification.html#add_the_support_library
     *
     * If you want to handle tap action:
     * https://developer.android.com/training/notify-user/build-notification#click
     */
    private fun makeNotification() {
        createNotificationChannel()

        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_child_care_white_24dp)
                .setContentTitle("Title")
                .setContentText("Content")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        val manager = NotificationManagerCompat.from(this)
        manager.notify(NOTIFICATION_ID, builder.build())
    }

    companion object {

        private val TAG = MainActivity::class.java.simpleName

        val CHANNEL_ID = "channel_test"
        val NOTIFICATION_ID = 42
    }
}
