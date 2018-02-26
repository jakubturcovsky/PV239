package cz.pv239.seminar2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity
        extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.simple_list)
                .setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        startListActivity(ListActivity.ListType.LIST_VIEW_SIMPLE);
                    }
                });

        findViewById(R.id.recycler)
                .setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        startListActivity(ListActivity.ListType.RECYCLER_VIEW);
                    }
                });

        findViewById(R.id.fragments)
                .setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        startFragmentActivity();
                    }
                });

        findViewById(R.id.service)
                .setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        startNotificationService();
                    }
                });
    }

    private void startListActivity(@ListActivity.ListType int listType) {
        Intent intent = ListActivity.newIntent(this, listType);
        startActivity(intent);
    }

    private void startFragmentActivity() {
        Intent intent = FragmentActivity.newIntent(this);
        startActivity(intent);
    }

    private void startNotificationService() {
        Intent intent = NotificationService.newIntent(this);
        startService(intent);
    }
}
