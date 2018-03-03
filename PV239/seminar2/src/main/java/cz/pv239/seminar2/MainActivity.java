package cz.pv239.seminar2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity
        extends AppCompatActivity {

    @BindView(R.id.simple_list) Button mSimpleList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

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

    @OnClick(R.id.simple_list)
    protected void onSimpleListClicked() {
        startListActivity(ListActivity.ListType.LIST_VIEW_SIMPLE);
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
