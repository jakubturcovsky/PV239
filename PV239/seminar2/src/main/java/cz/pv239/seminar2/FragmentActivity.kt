package cz.pv239.seminar2

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

class FragmentActivity : AppCompatActivity() {

    companion object {

        fun newIntent(context: Context): Intent {
            return Intent(context, FragmentActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment)

        val fragmentManager = supportFragmentManager ?: return

        // Put a new instance of FirstFragment to FrameLayout with id R.id.content1
        fragmentManager.beginTransaction().add(R.id.content1,
                FirstFragment.newInstance(),
                FirstFragment::class.java.simpleName).commit()

        fragmentManager.beginTransaction().add(R.id.content2,
                SecondFragment.newInstance(),
                SecondFragment::class.java.simpleName).commit()
    }
}
