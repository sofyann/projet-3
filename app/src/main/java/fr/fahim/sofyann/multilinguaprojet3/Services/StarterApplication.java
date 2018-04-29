package fr.fahim.sofyann.multilinguaprojet3.Services;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseACL;

/**
 * Created by Sofyann on 14/09/2017.
 */

public class StarterApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);

        // Add your initialization code here
        Parse.initialize(new Parse.Configuration.Builder(getApplicationContext())
                .applicationId("548288af5d53466ec81ba0b2d3f82d93f0e9bcf1")
                .clientKey("0e104c4706161cc89a429d06e9cf3b17e130bbb8")
                .server("http://18.188.234.234:80/parse/")
                .build()
        );



        //   ParseUser.enableAutomaticUser();
        ParseACL defaultACL = new ParseACL();
        defaultACL.setPublicReadAccess(true);
        defaultACL.setPublicWriteAccess(true);
        ParseACL.setDefaultACL(defaultACL, true);

    }
}
