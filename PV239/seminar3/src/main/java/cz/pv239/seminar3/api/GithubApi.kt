package cz.pv239.seminar3.api

import cz.pv239.seminar3.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GithubApi {
    val service: GithubService

    init {
        val builder = OkHttpClient.Builder()

        if (BuildConfig.DEBUG) {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            builder.addInterceptor(interceptor)
        }

        val client = builder.build()

        val retrofit = Retrofit.Builder()
                .baseUrl(GITHUB_API_ENDPOINT)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        service = retrofit.create(GithubService::class.java)
    }

    companion object {

        private const val GITHUB_API_ENDPOINT = "https://api.github.com"
    }
}
