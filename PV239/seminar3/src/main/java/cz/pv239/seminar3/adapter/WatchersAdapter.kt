package cz.pv239.seminar3.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import cz.pv239.seminar3.R
import cz.pv239.seminar3.model.User

class WatchersAdapter(private var users: List<User> = listOf()) : RecyclerView.Adapter<WatchersAdapter.ViewHolder>() {

    fun refreshUsers(users: List<User>) {
        this.users = users
        // This tells the adapter that data has changed and it should invalidate everything
        notifyDataSetChanged()
    }

    /**
     * Creates new ViewHolder instances and inflates them with XML layout.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(View(parent.context)) // TODO fix this
    }

    /**
     * Gets inflated ViewHolder instance and fills views with data.
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // TODO
    }

    /**
     * Adapter needs to know how many items are there to show.
     */
    override fun getItemCount(): Int {
        // TODO
        return 0
    }

    /**
     * Reusable ViewHolder objects.
     */
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var avatar: ImageView = itemView.findViewById(R.id.avatar)
        var login: TextView = itemView.findViewById(R.id.login)

        fun bind(user: User) {
            val context = avatar.context
            // TODO
        }
    }
}
