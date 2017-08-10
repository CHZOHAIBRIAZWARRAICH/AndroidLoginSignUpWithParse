package com.example.hooria.instragramclone2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethod;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import android.util.Log;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements View.OnKeyListener, View.OnClickListener {

    EditText et_username, et_password;
    Button btn_login,btn_signUp;
    TextView tv_signUp_login;
    RelativeLayout background;
    ImageView imageView_logo;
    TextView tv_username;
    TextView tv_pasword;
    TextView tv_welcom_text;
    TextView tv_logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("1736onCreateExecuted", "onCreate Executed");
        setContentView(R.layout.activity_main);
        et_username = (EditText) findViewById(R.id.et_username);
        et_password = (EditText) findViewById(R.id.et_password);

        btn_signUp = (Button) findViewById(R.id.btnSignUp);
        btn_login = (Button) findViewById(R.id.btnLogin);
        tv_signUp_login = (TextView) findViewById(R.id.tv_signinSignup);


         background = (RelativeLayout) findViewById(R.id.my_relative_layout);
         imageView_logo = (ImageView) findViewById(R.id.imageView);
         tv_username = (TextView) findViewById(R.id.tv_username);
         tv_pasword = (TextView) findViewById(R.id.tv_password);

        tv_welcom_text = (TextView) findViewById(R.id.tv_welcome_text); //initially invisible
        tv_logout = (TextView) findViewById(R.id.tv_logout); ////initially invisible





        //ParseUser.logOut();
        if( ParseUser.getCurrentUser() != null ){
            //user is already logged-in take him/her to userlist activity.
            Toast.makeText(this,"User Already Logged In",Toast.LENGTH_LONG).show();

            //if user is already logged-in then hide et_username, et_password, btnSignUp, btnLogin, tv_signinSignup
            tv_username.setVisibility(View.INVISIBLE);
            tv_pasword.setVisibility(View.INVISIBLE);
            et_username.setVisibility(View.INVISIBLE);
            et_password.setVisibility(View.INVISIBLE);
            btn_login.setVisibility(View.INVISIBLE);
            btn_signUp.setVisibility(View.INVISIBLE);
            tv_signUp_login.setVisibility(View.INVISIBLE);

            //user is logged-in visible welcome message textview and logout textview

            tv_welcom_text.setVisibility(View.VISIBLE);
            tv_welcom_text.setText("Hi "+ParseUser.getCurrentUser().getUsername().toString());
            tv_logout.setVisibility(View.VISIBLE);
            tv_logout.setOnClickListener(this);



            Intent intent = new Intent(this,UsersListActivity.class);

//            this.startActivity(intent);
//            MainActivity.this.startActivity(intent);
            startActivity(intent);

        }else{
                //user is not logged-in





            tv_signUp_login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(btn_signUp.getVisibility() == View.INVISIBLE && btn_login.getVisibility() == View.VISIBLE){

                        btn_signUp.setVisibility(View.VISIBLE);
                        btn_login.setVisibility(View.INVISIBLE);
                        tv_signUp_login.setText("or SignIn");
                        et_username.setText("");
                        et_password.setText("");

                    }else{

                        btn_signUp.setVisibility(View.INVISIBLE);
                        btn_login.setVisibility(View.VISIBLE);
                        tv_signUp_login.setText("or SignUp");

                    }


                }//end of onClick.
            });


            et_password.setOnKeyListener(this);



//            RelativeLayout background = (RelativeLayout) findViewById(R.id.my_relative_layout);
//            ImageView imageView_logo = (ImageView) findViewById(R.id.imageView);
//            TextView tv_username = (TextView) findViewById(R.id.tv_username);
//            TextView tv_pasword = (TextView) findViewById(R.id.tv_password);

            background.setOnClickListener(this);
            imageView_logo.setOnClickListener(this);
            tv_username.setOnClickListener(this);
            tv_pasword.setOnClickListener(this);






        }//end of user not logged in



        ParseAnalytics.trackAppOpenedInBackground(getIntent());
    } //end of onCreate Method.

    public void login(View view) {

        String username = et_username.getText().toString();
        String password = et_password.getText().toString();


//        if(et_username.getText().toString().matches("") || et_password.getText().toString().matches("")){

        if(username.matches("") || password.matches("")){

            Toast.makeText(MainActivity.this,"Username and Password are required",Toast.LENGTH_LONG).show();

        }else{

//            Toast.makeText(MainActivity.this,"Username and Password are  not empty",Toast.LENGTH_LONG).show();
//            Toast.makeText(MainActivity.this,"Username: "+et_username.getText().toString(),Toast.LENGTH_LONG).show();
//            Toast.makeText(MainActivity.this,"Password: "+et_password.getText().toString(),Toast.LENGTH_LONG).show();

            ParseUser user = new ParseUser();

//            user.logInInBackground("zohaibriaz", "warraich1", new LogInCallback() {

            user.logInInBackground(username, password, new LogInCallback() {
                @Override
                public void done(ParseUser user, ParseException e) {

                    if( e == null && user != null){

                        Toast.makeText(MainActivity.this,"Welcome "+user.getUsername(),Toast.LENGTH_LONG).show();
                        Log.i("LoginSuccess", "Welcome "+user.getUsername());

                        //if the user is logged in successfully then move to users list activity.

                        if(ParseUser.getCurrentUser() != null){

                            Intent intent = new Intent(getApplicationContext(),UsersListActivity.class);
                            MainActivity.this.startActivity(intent);
                        }

                    }else{

                        Toast.makeText(MainActivity.this,"Logined Failed "+e.getMessage(),Toast.LENGTH_LONG).show();
                        Log.i("LoginFailed", e.getMessage());
                    }
                }
            });

        }


    }//end of login method

    public void signUp(View view) {

        String username = et_username.getText().toString();
        String password = et_password.getText().toString();

        if(username.matches("") || password.matches("")){

            Toast.makeText(MainActivity.this,"Username and Password are required",Toast.LENGTH_LONG).show();

        }else{

            ParseUser user = new ParseUser();

            user.setUsername(username);
            user.setPassword(password);

            user.signUpInBackground(new SignUpCallback() {
                @Override
                public void done(ParseException e) {

                    if( e == null){

                        Toast.makeText(MainActivity.this,"SignUp Successfully",Toast.LENGTH_LONG).show();

                    }else {

                        Toast.makeText(MainActivity.this,"SignUp Failed "+e.getMessage(),Toast.LENGTH_LONG).show();

                    }
                }
            });

        } //end username and password are not empty.

    }//end of singUp method.



    //onKey method is called two times. once when a key is press down and once when key is lifted up.
    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {

        Log.i("EnterPressedDown", "Enter is pressed Down");
//        if( keyCode == KeyEvent.KEYCODE_ENTER){ //with this condition Toast will display two times. once for keydown and once for keyup.
        if( keyCode == KeyEvent.KEYCODE_ENTER  && event.getAction() == KeyEvent.ACTION_DOWN){ //with this condition Toast will display once for keydown only.

            Log.i("EnterPressedDown", "Enter is pressed Down");
            Toast.makeText(this,"Focus is in Password Field and Enter is Pressed.", Toast.LENGTH_LONG).show();

            if(btn_login.getVisibility() == View.VISIBLE && btn_signUp.getVisibility() == View.INVISIBLE){

                //login method needs view as parameter. so we can pass any view. because we are not doing anything with passed view in login method.
                login(v);

            }else if ( btn_login.getVisibility() == View.INVISIBLE && btn_signUp.getVisibility() == View.VISIBLE ){

                //signup needs view as parameter. so we can pass any view. because we are not doing anything with passed view in login method.
                signUp(v);

            }else{

                //both buttons are visible or both buttons are invisible.
            }
        }

//        if(  v.getId() == R.id.et_password  ){}

        return false;

    }//end of onKey method.


    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.my_relative_layout || v.getId() == R.id.imageView || v.getId() == R.id.tv_username || v.getId() == R.id.tv_password){

            //hide the keyboard.

            //InputMethodManager which allows us to manage the method of input.
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE); //this line gets the keyboard for us.

            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);



        }
        else if(v.getId() == R.id.tv_logout){ //textview logout clicked

            if(ParseUser.getCurrentUser().getUsername() != null){

                ParseUser.logOut();
                Toast.makeText(this,"Log Out Successfully",Toast.LENGTH_LONG).show();
                //enable login form

                //if user is already logged-in then hide et_username, et_password, btnSignUp, btnLogin, tv_signinSignup
                tv_username.setVisibility(View.VISIBLE);
                tv_pasword.setVisibility(View.VISIBLE);
                et_username.setVisibility(View.VISIBLE);
                et_password.setVisibility(View.VISIBLE);
                btn_login.setVisibility(View.VISIBLE);
                btn_signUp.setVisibility(View.INVISIBLE);
                tv_signUp_login.setVisibility(View.VISIBLE);

                //user is logout hide welcome message textview and logout textview

                tv_welcom_text.setVisibility(View.INVISIBLE);
                tv_logout.setVisibility(View.INVISIBLE);
            }





        }

    }//end of onClick method


    @Override
    protected void onStart() {
        super.onStart();
        Log.d("1736onStratExecuted", "onStart Executed");

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("1736onResumeExecuted", "onResume Executed");
    }


    @Override
    protected void onPause() {
        super.onPause();
        Log.d("1736onPauseExecuted", "onPause Executed");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("1736onStopExecuted", "onStop Executed");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("1736onRestratExecuted", "onRestart Executed");
        Toast.makeText(this,"User Already Logged In",Toast.LENGTH_LONG).show();

        //if user is already logged-in then hide et_username, et_password, btnSignUp, btnLogin, tv_signinSignup
        tv_username.setVisibility(View.INVISIBLE);
        tv_pasword.setVisibility(View.INVISIBLE);
        et_username.setVisibility(View.INVISIBLE);
        et_password.setVisibility(View.INVISIBLE);
        btn_login.setVisibility(View.INVISIBLE);
        btn_signUp.setVisibility(View.INVISIBLE);
        tv_signUp_login.setVisibility(View.INVISIBLE);

        //user is logged-in visible welcome message textview and logout textview

        tv_welcom_text.setVisibility(View.VISIBLE);
        tv_welcom_text.setText("Hi "+ParseUser.getCurrentUser().getUsername().toString());
        tv_logout.setVisibility(View.VISIBLE);
        tv_logout.setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("1736onDestroyExecuted", "onDestroy Executed");
    }
}//end of MainActivity class
