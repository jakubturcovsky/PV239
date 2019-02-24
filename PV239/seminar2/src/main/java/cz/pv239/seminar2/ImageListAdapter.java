package cz.pv239.seminar2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class ImageListAdapter
        extends RecyclerView.Adapter<ImageListAdapter.ViewHolder> {

    private List<User> mUsers;

    public ImageListAdapter(List<User> users) {
        mUsers = users;
    }

    /**
     * Creates new ViewHolder instances and inflates them with XML layout.
     */
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.image_list_item, parent, false));
    }

    /**
     * Gets inflated ViewHolder instance and fills views with data.
     */
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mIcon.setImageResource(R.drawable.ic_launcher_foreground);
        holder.mName.setText(mUsers.get(position).getName());
        holder.mSurname.setText(mUsers.get(position).getSurname());
    }

    /**
     * Adapter needs to know how many items are there to show.
     */
    @Override
    public int getItemCount() {
        return mUsers.size();
    }

    /**
     * Reusable ViewHolder objects.
     */
    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView mIcon;
        TextView mName;
        TextView mSurname;

        public ViewHolder(View itemView) {
            super(itemView);
            mIcon = itemView.findViewById(R.id.icon);
            mName = itemView.findViewById(R.id.name);
            mSurname = itemView.findViewById(R.id.surname);
        }
    }
}
