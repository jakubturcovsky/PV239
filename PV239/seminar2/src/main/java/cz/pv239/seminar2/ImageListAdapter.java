package cz.pv239.seminar2;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ImageListAdapter
        extends RecyclerView.Adapter<ImageListAdapter.ViewHolder> {

    private Context mContext;
    private List<User> mUsers;

    public ImageListAdapter(Context context, List<User> users) {
        mContext = context;
        mUsers = users;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.image_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mIcon.setImageResource(R.drawable.ic_launcher_foreground);
        holder.mName.setText(mUsers.get(position).getName());
        holder.mSurname.setText(mUsers.get(position).getSurname());
    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }

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
