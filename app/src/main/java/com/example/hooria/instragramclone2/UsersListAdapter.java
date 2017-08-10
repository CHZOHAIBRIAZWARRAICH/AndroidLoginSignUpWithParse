package com.example.hooria.instragramclone2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.content.Context;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * Created by Hooria on 09-08-2017.
 */

public class UsersListAdapter extends BaseAdapter {
    Context context; ArrayList< HashMap<String,String> > usersList;

    public UsersListAdapter(Context context, ArrayList<HashMap<String, String>> usersList) {
        this.context = context;
        this.usersList = usersList;
    }

    @Override
    public int getCount() {
        return usersList.size();
    }

    @Override
    public Object getItem(int position) {
        return usersList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = View.inflate(context,R.layout.users_list_item,null);
        TextView tv_username = (TextView) view.findViewById(R.id.user_name);
        TextView tv_userEmail = (TextView) view.findViewById(R.id.user_email);

        HashMap<String,String> user = new HashMap<>();
//
        user = usersList.get(position);
//
        tv_username.setText(user.get("username"));
        tv_userEmail.setText(user.get("email"));

//        tv_userEmail.setText("abd");

//        view.setTag(user.get("email"));



        return view;
    }
}
