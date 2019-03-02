package cz.pv239.seminar3.model

import com.google.gson.annotations.SerializedName

data class User(@SerializedName("avatar_url") val avatarUrl: String?,
                val login: String?,
                val name: String?)
