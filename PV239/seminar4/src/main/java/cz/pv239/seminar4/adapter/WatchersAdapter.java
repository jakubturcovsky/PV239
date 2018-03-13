package cz.pv239.seminar4.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import cz.pv239.seminar4.R;
import cz.pv239.seminar4.model.User;
import io.realm.OrderedRealmCollection;
import io.realm.RealmRecyclerViewAdapter;

public class WatchersAdapter
        extends RealmRecyclerViewAdapter<User, WatchersAdapter.ViewHolder> {

    private Context mContext;

    public WatchersAdapter(Context context, @Nullable OrderedRealmCollection<User> data) {
        super(data, true);
        mContext = context;
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
        User user = getItem(position);
        Glide.with(mContext)
                .load(user.avatarUrl)
                .into(holder.mAvatar);
        holder.mLogin.setText(user.login);
    }

    /**
     * Reusable ViewHolder objects.
     */
    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.avatar)
        ImageView mAvatar;
        @BindView(R.id.login)
        TextView mLogin;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
