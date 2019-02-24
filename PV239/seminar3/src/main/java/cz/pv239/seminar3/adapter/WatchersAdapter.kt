package cz.pv239.seminar3.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
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
        return ViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.item_user, parent, false))
    }

    /**
     * Gets inflated ViewHolder instance and fills views with data.
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(users[position])
    }

    /**
     * Adapter needs to know how many items are there to show.
     */
    override fun getItemCount(): Int {
        return users.size
    }

    /**
     * Reusable ViewHolder objects.
     */
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var avatar: ImageView = itemView.findViewById(R.id.avatar)
        var login: TextView = itemView.findViewById(R.id.login)

        fun bind(user: User) {
            val context = avatar.context
            Glide.with(context)
                    .load(user.avatarUrl)
                    .into(avatar)
            login.text = user.login
        }
    }
}
