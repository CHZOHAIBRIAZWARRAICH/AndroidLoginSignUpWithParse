package com.example.hooria.instragramclone2;

import android.app.Application;

import android.util.Log;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;


/**
 * Created by Hooria on 07-08-2017.
 */

public class StarterApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);

        // Add your initialization code here
        Parse.initialize(new Parse.Configuration.Builder(getApplicationContext())
                .applicationId("515c0399bbf29779f7f5c8be849be267799ada76")
                .clientKey("ef5c9874d25228dfad1f397f76d913c31d5fd68d")
                .server("http://ec2-18-220-131-164.us-east-2.compute.amazonaws.com:80/parse/")
                .build()
        );

        //ParseUser.enableAutomaticUser();

        ParseACL defaultACL = new ParseACL();
        defaultACL.setPublicReadAccess(true);
        defaultACL.setPublicWriteAccess(true);
        ParseACL.setDefaultACL(defaultACL, true);

    }
}


