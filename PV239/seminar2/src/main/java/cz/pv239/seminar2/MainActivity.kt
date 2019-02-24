package cz.pv239.seminar2

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

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
        val intent = ToastService.newIntent(this)
        startService(intent)
    }
}
