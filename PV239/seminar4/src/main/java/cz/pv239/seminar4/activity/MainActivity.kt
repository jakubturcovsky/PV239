package cz.pv239.seminar4.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import cz.pv239.seminar4.R
import cz.pv239.seminar4.fragment.GithubFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {       // Important, otherwise there'd be a new Fragment created with every orientation change
            val fragmentManager = supportFragmentManager
            fragmentManager?.beginTransaction()?.replace(android.R.id.content,
                    GithubFragment.newInstance(),
                    GithubFragment::class.java.simpleName)?.commit()
        }
    }
}
