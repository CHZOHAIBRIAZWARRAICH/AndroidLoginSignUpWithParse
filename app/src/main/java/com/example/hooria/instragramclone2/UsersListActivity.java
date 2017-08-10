package com.example.hooria.instragramclone2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import android.util.Log;

public class UsersListActivity extends AppCompatActivity {

    ListView usersListView;
    ArrayList< HashMap<String,String> > usersList;
    ArrayAdapter arrayAdapter;
    UsersListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_list);
        Intent intent = getIntent();

        usersListView = (ListView) findViewById(R.id.users_list_view);

        usersList = new ArrayList<>();
        usersList.clear();

/*
        HashMap<String,String> users = new HashMap<>();
        users.put("1","Zohaib Riaz");
        users.put("2","Sadia Zohaib");
        users.put("3","Hooria Zohaib");

        usersList.add(users);
        arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1, usersList);

        usersListView.setAdapter(arrayAdapter);


       // output is a single list item like { 1=Zohaib Riaz, 2=Sadia Zohaib, 3=Hooria Zohaib}

*/

/*

        HashMap<String,String> user1 = new HashMap<>();
        user1.put("username","Zohaib Riaz");
        user1.put("email","zohaib@y.com");

        HashMap<String,String> user2 = new HashMap<>();
        user2.put("username","Sadia Zohaib");
        user2.put("email","saida@y.com");

        HashMap<String,String> user3 = new HashMap<>();
        user3.put("username","Hooria Zohaib");
        user3.put("email","hooria@y.com");


        usersList.add(user1);
        usersList.add(user2);
        usersList.add(user3);



        arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1, usersList);

        usersListView.setAdapter(arrayAdapter);
        /*
        //output is 3 items in the list as shown below.
         // {email=zohaib@y.com, username=Zohaib Riaz}
         // {email=sadia@y.com, username=Sadia Zohaib}
         // {email=hooria @y.com, username=Hooria Zohaib}



 */

//===================Custom List Adapter======= Works Fine============
/*
        HashMap<String,String> user1 = new HashMap<>();
        user1.put("username","Zohaib Riaz");
        user1.put("email","zohaib@y.com");

        HashMap<String,String> user2 = new HashMap<>();
        user2.put("username","Sadia Zohaib");
        user2.put("email","saida@y.com");

        HashMap<String,String> user3 = new HashMap<>();
        user3.put("username","Hooria Zohaib");
        user3.put("email","hooria@y.com");


        usersList.add(user1);
        usersList.add(user2);
        usersList.add(user3);

        adapter = new UsersListAdapter(this,usersList);

        usersListView.setAdapter(adapter);


*/
//================================GET DATA FROM SERVER.

        ParseQuery<ParseUser> query = ParseUser.getQuery();

//        query.whereNotEqualTo("username", ParseUser.getCurrentUser().getUsername()); //this line will not give any result.

//        String loggedInUsername = ParseUser.getCurrentUser().getUsername();
//        query.whereNotEqualTo("username", loggedInUsername.toString());

        //Log.i("LoggedIn User: ",ParseUser.getCurrentUser().getUsername()); //shows the user name but will not work in query.
                                                                            //to work with query you have to convert toString().

        query.whereNotEqualTo("username",ParseUser.getCurrentUser().getUsername().toString());

        query.findInBackground(new FindCallback<ParseUser>() {
            @Override
            public void done(List<ParseUser> objects, ParseException e) {
                Log.i("Users :",objects.toString());

                if ( e == null && objects.size() > 0){


                    for(ParseUser object : objects){

                        HashMap<String,String> user = new HashMap<String, String>();

                        if (object.get("username") != null){

                            user.put("username", object.get("username").toString());
                        }else{

                            user.put("username", "Username not found");
                        }


                        if(object.get("email") != null) {

                            user.put("email", object.get("email").toString());

                        }else{

                            user.put("email", "Email not found");

                        }


                        usersList.add(user);
                    }

                }//no error and retrieve records.

            }
        });

        adapter = new UsersListAdapter(this,usersList);

        usersListView.setAdapter(adapter);




    }//end of onCreate method.

}//endo of UsersListActivity class.
