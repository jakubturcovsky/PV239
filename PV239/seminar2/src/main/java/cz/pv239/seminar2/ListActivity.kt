package cz.pv239.seminar2

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ListActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_LIST_TYPE = "extra_list_type"

        fun newIntent(context: Context, listType: ListType): Intent {
            val intent = Intent(context, ListActivity::class.java)
            intent.putExtra(EXTRA_LIST_TYPE, listType)
            return intent
        }
    }

    enum class ListType {
        LIST_VIEW,
        RECYCLER_VIEW
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_view)

        val listType = intent.getSerializableExtra(EXTRA_LIST_TYPE) as ListType
        initList(listType)
    }

    private fun initList(listType: ListType) {
        val listView = findViewById<ListView>(android.R.id.list)
        val recyclerView = findViewById<RecyclerView>(R.id.list_recycler)
        when (listType) {
            ListActivity.ListType.LIST_VIEW -> {
                initSimpleList()
                listView.visibility = View.VISIBLE
                recyclerView.visibility = View.GONE
            }
            ListActivity.ListType.RECYCLER_VIEW -> {
                initRecyclerView()
                listView.visibility = View.GONE
                recyclerView.visibility = View.VISIBLE
            }
        }
    }

    /**
     * The simples ListView, displays a list of names in linear vertically scrollable window.
     */
    private fun initSimpleList() {
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, UserDb().names)
        val list = findViewById<ListView>(android.R.id.list)
        list.adapter = adapter
    }

    private fun initRecyclerView() {
        val adapter = ImageListAdapter(UserDb())
        val recyclerView = findViewById<RecyclerView>(R.id.list_recycler)
        recyclerView.adapter = adapter
        // Don't forget to tell the RecyclerView how to show the items! (Linear - LinearLayoutManager, Grid - GridLayoutManager etc.)
        recyclerView.layoutManager = LinearLayoutManager(this)
    }
}
