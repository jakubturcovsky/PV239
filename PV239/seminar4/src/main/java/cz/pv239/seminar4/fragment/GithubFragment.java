package cz.pv239.seminar4.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import androidx.core.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cz.pv239.seminar4.R;
import cz.pv239.seminar4.activity.FileActivity;
import cz.pv239.seminar4.adapter.WatchersAdapter;
import cz.pv239.seminar4.api.GithubApi;
import cz.pv239.seminar4.model.User;
import io.realm.Realm;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GithubFragment
        extends Fragment {

    private static final String TAG = GithubFragment.class.getSimpleName();

    private GithubApi mGithubApi;
    private WatchersAdapter mAdapter;
    private Realm mRealm;

    private Unbinder mUnbinder;
    @BindView(android.R.id.list) RecyclerView mList;
    @BindView(R.id.avatar) ImageView mAvatar;
    @BindView(R.id.name) TextView mName;

    public static GithubFragment newInstance() {
        return new GithubFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mGithubApi = new GithubApi();
        mRealm = Realm.getDefaultInstance();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        mUnbinder = ButterKnife.bind(this, view);

        loadUser("jakubturcovsky");

        RealmResults<User> users = mRealm.where(User.class).findAll();
        mAdapter = new WatchersAdapter(getContext(), users);
        mList.setAdapter(mAdapter);
        mList.setLayoutManager(new LinearLayoutManager(getContext()));
        mList.setHasFixedSize(true);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mRealm.close();
    }

    @OnClick(R.id.watchers)
    public void onLoadWatchersClicked() {
        loadWatchers("openwrt", "openwrt");
    }

    @OnClick(R.id.saving_file)
    protected void onStartFileActivityClicked() {
        getActivity().startActivity(new Intent(getActivity(), FileActivity.class));
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
                saveResult(response.body());
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    /**
     * NOTE: It's better to write and read asynchronously. But for the sake of simplicity...
     */
    private void saveResult(final List<User> watchers) {
        Realm realm = null;
        try {
            realm = Realm.getDefaultInstance();
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    realm.insertOrUpdate(watchers);
                }
            });
        } finally {
            if(realm != null) {
                realm.close();
            }
        }
    }
}
