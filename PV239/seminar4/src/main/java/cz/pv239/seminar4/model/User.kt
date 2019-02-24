package cz.pv239.seminar4.model

import com.google.gson.annotations.SerializedName
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class User(@SerializedName("avatar_url") var avatarUrl: String? = null,
                @PrimaryKey var login: String? = null,
                var name: String? = null) : RealmObject()
