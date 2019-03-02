package cz.pv239.seminar2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import androidx.recyclerview.widget.RecyclerView

class ImageListAdapter(private val users: List<User>) : RecyclerView.Adapter<ImageListAdapter.ViewHolder>() {

    /**
     * Creates new ViewHolder instances and inflates them with XML layout.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.image_list_item, parent, false))
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
        var icon: ImageView = itemView.findViewById(R.id.icon)
        var name: TextView = itemView.findViewById(R.id.name)
        var surname: TextView = itemView.findViewById(R.id.surname)

        fun bind(user: User) {
            icon.setImageResource(R.drawable.ic_launcher_foreground)
            name.text = user.name
            surname.text = user.surname
        }
    }
}
