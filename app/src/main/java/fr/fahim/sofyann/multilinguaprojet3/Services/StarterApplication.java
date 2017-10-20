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
                .applicationId("c8898bf0d60cea9c93fe4b89258c9fa7859d1bc5")
                .clientKey("0ad13581d3ea6cbd728ebe52298f2bb2eba364ca")
                .server("http://ec2-18-221-132-32.us-east-2.compute.amazonaws.com:80/parse/")
                .build()
        );





        //   ParseUser.enableAutomaticUser();
        ParseACL defaultACL = new ParseACL();
        defaultACL.setPublicReadAccess(true);
        defaultACL.setPublicWriteAccess(true);
        ParseACL.setDefaultACL(defaultACL, true);

    }
}
