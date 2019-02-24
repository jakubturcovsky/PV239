package cz.pv239.seminar3.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import cz.pv239.seminar3.R;
import cz.pv239.seminar3.model.User;

public class WatchersAdapter
        extends RecyclerView.Adapter<WatchersAdapter.ViewHolder> {

    private Context mContext;
    private List<User> mUsers;

    public WatchersAdapter(@NonNull List<User> users) {
        mUsers = users;
    }

    public void refreshUsers(@NonNull List<User> users) {
        mUsers = users;
        // This tells the adapter that data has changed and it should invalidate everything
        notifyDataSetChanged();
    }

    /**
     * Creates new ViewHolder instances and inflates them with XML layout.
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_user, parent, false));
    }

    /**
     * Gets inflated ViewHolder instance and fills views with data.
     */
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        User user = mUsers.get(position);
        Glide.with(mContext)
                .load(user.avatarUrl)
                .into(holder.mAvatar);
        holder.mLogin.setText(user.login);
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

        ImageView mAvatar;
        TextView mLogin;

        public ViewHolder(View itemView) {
            super(itemView);
            mAvatar = itemView.findViewById(R.id.avatar);
            mLogin = itemView.findViewById(R.id.login);
        }
    }
}
