package cz.pv239.seminar1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import cz.jakubturcovsky.ex1.R;

public class MessageActivity
        extends AppCompatActivity {

    private static final String TAG = MessageActivity.class.getSimpleName();
    private static final String EXTRA_MESSAGE = "message";

    public static Intent newIntent(@NonNull Context context, @NonNull String message) {
        // Intent for starting a new MessageActivity
        Intent intent = new Intent(context, MessageActivity.class);
        // If I want to put some info to the new Activity, I can put it into the Intent this way
        intent.putExtra(EXTRA_MESSAGE, message);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        // Get the intent which started this Activity
        Intent startingIntent = getIntent();
        // Get the extra content I've put to the Intent, in this case - String with message
        String message = startingIntent.getStringExtra(EXTRA_MESSAGE);

        // Set the message as a text to this Activity's TextView
        TextView messageView = findViewById(R.id.message);
        messageView.setText(message);
    }
}
