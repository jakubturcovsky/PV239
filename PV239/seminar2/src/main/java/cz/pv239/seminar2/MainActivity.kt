package cz.pv239.seminar2

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import cz.pv239.seminar2.exercise.ExerciseActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.simple_list).setOnClickListener {
            startListActivity(ListActivity.ListType.LIST_VIEW)
        }

        findViewById<View>(R.id.recycler).setOnClickListener {
            startListActivity(ListActivity.ListType.RECYCLER_VIEW)
        }

        findViewById<View>(R.id.fragments).setOnClickListener { startFragmentActivity() }

        findViewById<View>(R.id.service).setOnClickListener { startNotificationService() }

        exercise.setOnClickListener { startExerciseActivity() }
    }

    private fun startListActivity(listType: ListActivity.ListType) {
        val intent = ListActivity.newIntent(this, listType)
        startActivity(intent)
    }

    private fun startFragmentActivity() {
        val intent = FragmentActivity.newIntent(this)
        startActivity(intent)
    }

    private fun startNotificationService() {
        val intent = AnnoyingService.newIntent(this)
        startService(intent)
    }

    private fun startExerciseActivity() {
        val intent = Intent(this, ExerciseActivity::class.java)
        startActivity(intent)
    }
}
