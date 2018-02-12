package cz.jakubturcovsky.ex1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MessageActivity
        extends AppCompatActivity {

    private static final String TAG = MessageActivity.class.getSimpleName();
    private static final String EXTRA_MESSAGE = "message";

    public static Intent newIntent(@NonNull Context context, @NonNull String message) {
        Intent intent = new Intent(context, MessageActivity.class);
        intent.putExtra(EXTRA_MESSAGE, message);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        String message = getIntent().getStringExtra(EXTRA_MESSAGE);
        TextView messageView = findViewById(R.id.message);
        messageView.setText(message);
    }
}
