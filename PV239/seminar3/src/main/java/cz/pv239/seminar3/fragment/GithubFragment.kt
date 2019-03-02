package cz.pv239.seminar3.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import cz.pv239.seminar3.R
import cz.pv239.seminar3.adapter.WatchersAdapter
import cz.pv239.seminar3.api.GithubApi
import cz.pv239.seminar3.model.User
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

    private var adapter: WatchersAdapter = WatchersAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        watchers.setOnClickListener {
            if (watchers_wrapper.visibility == View.GONE) {
                watchers_wrapper.visibility = View.VISIBLE
                list.adapter = adapter
                list.layoutManager = LinearLayoutManager(context)
            }
            loadWatchers("jakubturcovsky", "PV239")
        }
        loadUser("jakubturcovsky")
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
                populateList(response.body())
            }

            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }

    private fun populateList(watchers: List<User>?) {
        if (watchers == null) {
            return
        }

        adapter.refreshUsers(watchers)
    }
}
