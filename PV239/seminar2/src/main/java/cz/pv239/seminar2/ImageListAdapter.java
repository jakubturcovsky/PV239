package cz.pv239.seminar2;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ImageListAdapter
        extends RecyclerView.Adapter<ImageListAdapter.ViewHolder> {

    private List<User> mUsers;

    public ImageListAdapter(List<User> users) {
        mUsers = users;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.image_list_item, parent));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mIcon.setImageResource(R.drawable.ic_launcher_foreground);
        holder.mName.setText(mUsers.get(position).getName());
        holder.mName.setText(mUsers.get(position).getSurname());
    }

    @Override
    public int getItemCount() {
        return 0;
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
