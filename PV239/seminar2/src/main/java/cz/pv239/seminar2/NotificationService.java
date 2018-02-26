package cz.pv239.seminar2;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class NotificationService
        extends Service {

    private static final String TAG = NotificationService.class.getSimpleName();

    private static final long TIMER_DELAY = 5000;

    public static Intent newIntent(@NonNull Context context) {
        return new Intent(context, NotificationService.class);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {
                Toast.makeText(NotificationService.this, "Service running", Toast.LENGTH_SHORT).show();
            }
        }, TIMER_DELAY, TIMER_DELAY);

        return START_STICKY;
    }
}
