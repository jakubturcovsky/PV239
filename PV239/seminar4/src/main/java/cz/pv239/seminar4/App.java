package cz.pv239.seminar4;

import android.app.Application;

import io.realm.Realm;

public class App
        extends Application {

    private static App sInstance;

    public static App getInstance() {
        return sInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;

        Realm.init(this);
    }
}
