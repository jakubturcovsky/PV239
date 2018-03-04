package cz.pv239.seminar3.model;

import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("avatar_url")
    public String avatarUrl;
    public String login;
    public String name;
}
