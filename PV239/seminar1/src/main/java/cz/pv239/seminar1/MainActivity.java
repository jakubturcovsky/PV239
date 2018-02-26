package cz.pv239.seminar1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity
        extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set XML layout with views to this Activity
        setContentView(R.layout.activity_main);

        /* Exercise 1 */
        // Get TextView with ID "text" from our layout
        final TextView text = findViewById(R.id.text);
        // Get Button with ID "button"
        Button button = findViewById(R.id.button);
        // Listen for clicks on button
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.i(TAG, "User wants to know the answer");
                text.setText(R.string.answer);
            }
        });

        /* Exercise 2 */
        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.i(TAG, "User wants to send the message");
                // Get a new Intent for starting MessageActivity
                Intent intent = MessageActivity.newIntent(MainActivity.this, "Voila!");
                // Start a MessageActivity
                startActivity(intent);
            }
        });
    }
}
