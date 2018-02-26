package cz.pv239.seminar2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

public class FragmentActivity
        extends AppCompatActivity {

    private static final String TAG = FragmentActivity.class.getSimpleName();

    @NonNull
    public static Intent newIntent(@NonNull Context context) {
        return new Intent(context, FragmentActivity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        FragmentManager fragmentManager = getSupportFragmentManager();
        if (fragmentManager == null) {
            return;
        }

        fragmentManager.beginTransaction().add(R.id.content1,
                FirstFragment.newInstance(),
                FirstFragment.class.getSimpleName()).commit();
        fragmentManager.beginTransaction().add(R.id.content2,
                SecondFragment.newInstance(),
                SecondFragment.class.getSimpleName()).commit();
    }
}
