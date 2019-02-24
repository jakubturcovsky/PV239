package cz.pv239.seminar3.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import cz.pv239.seminar3.R;
import cz.pv239.seminar3.adapter.WatchersAdapter;
import cz.pv239.seminar3.api.GithubApi;
import cz.pv239.seminar3.model.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GithubFragment
        extends Fragment {

    private static final String TAG = GithubFragment.class.getSimpleName();

    private GithubApi mGithubApi;
    private WatchersAdapter mAdapter;

    RecyclerView mList;
    ImageView mAvatar;
    TextView mName;
    LinearLayout mWatchersWrapper;

    public static GithubFragment newInstance() {
        return new GithubFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mGithubApi = new GithubApi();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        mList = view.findViewById(android.R.id.list);
        mAvatar = view.findViewById(R.id.avatar);
        mName = view.findViewById(R.id.name);
        mWatchersWrapper = view.findViewById(R.id.watchers_wrapper);
        view.findViewById(R.id.watchers).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (mWatchersWrapper.getVisibility() == View.GONE) {
                    mWatchersWrapper.setVisibility(View.VISIBLE);
                    mAdapter = new WatchersAdapter(new ArrayList<User>());
                    mList.setAdapter(mAdapter);
                    mList.setLayoutManager(new LinearLayoutManager(getContext()));
                }
                loadWatchers("openwrt", "openwrt");
            }
        });
        loadUser("jakubturcovsky");

        return view;
    }

    /**
     * Downloads a user.
     * WARNING: Potential of memory leaks. This is just a simple example. Think, what happens if I rotate screen?
     */
    private void loadUser(@NonNull String username) {
        Call<User> userCall = mGithubApi.getService().getUser(username);
        userCall.enqueue(new Callback<User>() {

            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User user = response.body();
                if (user == null) {
                    return;
                }

                Glide.with(getContext())
                        .load(user.avatarUrl)
                        .into(mAvatar);
                mName.setText(user.name);
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    /**
     * Loads subscribers of a user.
     */
    private void loadWatchers(@NonNull String username, @NonNull String repositoryName) {
        Call<List<User>> userCall = mGithubApi.getService().getWatcherList(username, repositoryName);
        userCall.enqueue(new Callback<List<User>>() {

            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                populateList(response.body());
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void populateList(List<User> watchers) {
        if (watchers  == null) {
            return;
        }

        mAdapter.refreshUsers(watchers);
    }
}
