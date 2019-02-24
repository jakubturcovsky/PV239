package cz.pv239.seminar5.activity;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.core.app.FragmentManager;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import cz.pv239.seminar5.R;
import cz.pv239.seminar5.fragment.MainFragment;

/**
 * TODO Tasks:
 * * Create ListPreference with at least 3 options
 * * Create your own notification (add PendingIntent and handle it if you have time)
 * * Add another menu item from the MainFragment
 */
public class MainActivity
        extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    public static final String CHANNEL_ID = "channel_test";
    public static final int NOTIFICATION_ID = 42;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {       // Important, otherwise there'd be a new Fragment created with every orientation change
            FragmentManager fragmentManager = getSupportFragmentManager();
            if (fragmentManager != null) {
                fragmentManager.beginTransaction()
                        .replace(android.R.id.content,
                                MainFragment.newInstance(),
                                MainFragment.class.getSimpleName())
                        .commit();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.settings:
                startActivity(new Intent(this, SettingsActivity.class));
                break;
            case R.id.notification:
                makeNotification();
                break;
            case R.id.about:
                Toast.makeText(MainActivity.this, "App by @JakubTurcovsky", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void createNotificationChannel(@NonNull NotificationManager manager) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            if (manager.getNotificationChannel(CHANNEL_ID) != null) {
                return;
            }

            // Create the NotificationChannel
            CharSequence name = getString(R.string.notification_channel_test_name);
            String description = getString(R.string.notification_channel_test_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            manager.createNotificationChannel(channel);
        }
    }

    /**
     * WTF Android? We have to create channel with NotificationManager and notification with NotificationManagerCompat?!
     *
     * More about notifications:
     * https://developer.android.com/training/notify-user/build-notification.html#add_the_support_library
     */
    private void makeNotification() {
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        if (manager == null) {
            // Show error info
            return;
        }

        createNotificationChannel(manager);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_child_care_white_24dp)
                .setContentTitle("Title")
                .setContentText("Content")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat compatManager = NotificationManagerCompat.from(this);
        compatManager.notify(NOTIFICATION_ID, builder.build());
    }
}
