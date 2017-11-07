package com.example.f1.a09_retrofit;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this); // Initiate Realm in the app
        // Define Realm configuration:
        RealmConfiguration config = new RealmConfiguration
                .Builder()
                .name("myrealm.realm")  // name of the database file
                .schemaVersion(1)       // version of the database
                .build();
        // Set configuration (defined above) as default:
        Realm.setDefaultConfiguration(config);
    }

}
