package cz.pv239.seminar4.api

import cz.pv239.seminar4.model.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubService {

    // https://developer.github.com/v3/users/
    @GET("users/{username}")
    fun getUser(@Path("username") username: String): Call<User>

    // https://developer.github.com/v3/activity/watching/
    @GET("repos/{username}/{reponame}/subscribers")
    fun getWatcherList(@Path("username") username: String, @Path("reponame") reponame: String): Call<List<User>>

}
