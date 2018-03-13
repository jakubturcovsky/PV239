package cz.pv239.seminar4.api;

import java.util.List;

import cz.pv239.seminar4.model.User;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GithubService {

    // https://developer.github.com/v3/users/
    @GET("users/{username}")
    Call<User> getUser(@Path("username") String username);

    // https://developer.github.com/v3/activity/watching/
    @GET("repos/{username}/{reponame}/subscribers")
    Call<List<User>> getWatcherList(@Path("username") String username, @Path("reponame") String reponame);

}
