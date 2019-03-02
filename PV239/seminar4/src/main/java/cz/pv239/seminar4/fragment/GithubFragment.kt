package cz.pv239.seminar4.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import cz.pv239.seminar4.R
import cz.pv239.seminar4.activity.FileActivity
import cz.pv239.seminar4.adapter.WatchersAdapter
import cz.pv239.seminar4.api.GithubApi
import cz.pv239.seminar4.model.User
import io.realm.Realm
import kotlinx.android.synthetic.main.fragment_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GithubFragment : Fragment() {

    companion object {

        fun newInstance(): GithubFragment {
            return GithubFragment()
        }
    }
    private val githubApi = GithubApi()

    private val realm = Realm.getDefaultInstance()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        watchers.setOnClickListener {
            loadWatchers("openwrt", "openwrt")
        }
        saving_file.setOnClickListener {
            val activity = activity ?: return@setOnClickListener
            activity.startActivity(Intent(activity, FileActivity::class.java))
        }

        loadUser("jakubturcovsky")

        val users = realm.where(User::class.java).findAll()
        list.adapter = WatchersAdapter(users)
        list.layoutManager = LinearLayoutManager(context)
        list.setHasFixedSize(true)
    }

    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }

    /**
     * Downloads a user.
     * WARNING: Potential of memory leaks. This is just a simple example. Think, what happens if I rotate screen?
     */
    private fun loadUser(username: String) {
        val userCall = githubApi.service.getUser(username)
        userCall.enqueue(object : Callback<User> {

            override fun onResponse(call: Call<User>, response: Response<User>) {
                val user = response.body() ?: return

                Glide.with(context)
                        .load(user.avatarUrl)
                        .into(avatar)
                name.text = user.name
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }

    /**
     * Loads subscribers of a user.
     */
    private fun loadWatchers(username: String, repositoryName: String) {
        val userCall = githubApi.service.getWatcherList(username, repositoryName)
        userCall.enqueue(object : Callback<List<User>> {

            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                saveResult(response.body())
            }

            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                t.printStackTrace()
            }

            /**
             * IMPORTANT NOTE: It's better to write and read asynchronously. But for the sake of simplicity...
             */
            private fun saveResult(watchers: List<User>?) {
                var realm: Realm? = null
                try {
                    realm = Realm.getDefaultInstance()
                    realm.executeTransaction { it.insertOrUpdate(watchers!!) }
                } finally {
                    realm?.close()
                }
            }
        })
    }
}
