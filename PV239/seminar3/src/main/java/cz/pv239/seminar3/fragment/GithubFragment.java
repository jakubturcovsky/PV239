package cz.pv239.seminar3.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cz.pv239.seminar3.api.GithubApi;
import cz.pv239.seminar3.R;
import cz.pv239.seminar3.model.User;
import cz.pv239.seminar3.adapter.WatchersAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GithubFragment
        extends Fragment {

    private static final String TAG = GithubFragment.class.getSimpleName();

    private GithubApi mGithubApi;
    private WatchersAdapter mAdapter;

    private Unbinder mUnbinder;
    @BindView(android.R.id.list) RecyclerView mList;
    @BindView(R.id.avatar) ImageView mAvatar;
    @BindView(R.id.name) TextView mName;
    @BindView(R.id.watchers_wrapper) LinearLayout mWatchersWrapper;

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
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        mUnbinder = ButterKnife.bind(this, view);

        loadUser("jakubturcovsky");

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    @OnClick(R.id.watchers)
    public void onLoadWatchersClicked() {
        if (mWatchersWrapper.getVisibility() == View.GONE) {
            mWatchersWrapper.setVisibility(View.VISIBLE);
            mAdapter = new WatchersAdapter(new ArrayList<User>());
            mList.setAdapter(mAdapter);
            mList.setLayoutManager(new LinearLayoutManager(getContext()));
        }
        loadWatchers("openwrt", "openwrt");
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
