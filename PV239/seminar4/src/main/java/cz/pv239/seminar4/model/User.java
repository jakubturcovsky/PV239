package cz.pv239.seminar4.model;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class User extends RealmObject{

    // NOTE: You should also use a @PrimaryKey for faster queries

    @SerializedName("avatar_url")
    public String avatarUrl;
    @PrimaryKey
    public String login;
    public String name;
}
