package cz.pv239.seminar4.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import androidx.core.app.FragmentManager;
import cz.pv239.seminar4.R;
import cz.pv239.seminar4.fragment.GithubFragment;

public class MainActivity
        extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {       // Important, otherwise there'd be a new Fragment created with every orientation change
            FragmentManager fragmentManager = getSupportFragmentManager();
            if (fragmentManager != null) {
                fragmentManager.beginTransaction()
                        .replace(android.R.id.content,
                                GithubFragment.newInstance(),
                                GithubFragment.class.getSimpleName())
                        .commit();
            }
        }
    }
}
