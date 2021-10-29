package com.example.assigmentandroidnc.social;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.assigmentandroidnc.R;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.login.widget.ProfilePictureView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

public class SocialActivity extends AppCompatActivity {

     Button bt_logout;
     TextView tv_Name,tv_Email,tv_FirstName;
     ProfilePictureView profilePictureView;
    LoginButton loginButton;
    CallbackManager callbackManager;
    String name,email,fistName,id;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social);
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        callbackManager=CallbackManager.Factory.create();
        initView();

        tv_Email.setVisibility(View.INVISIBLE);
        tv_FirstName.setVisibility(View.INVISIBLE);
        tv_Name.setVisibility(View.INVISIBLE);
        bt_logout.setVisibility(View.INVISIBLE);
        loginButton.setReadPermissions(Arrays.asList("public_profile","email"));
        setLogin_button();
        setLogout_button();

    }

    private void setLogout_button() {
        bt_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginManager.getInstance().logOut();
                        tv_Email.setVisibility(View.INVISIBLE);
                        tv_FirstName.setVisibility(View.INVISIBLE);
                        tv_Name.setVisibility(View.INVISIBLE);
                        bt_logout.setVisibility(View.INVISIBLE);
                        tv_Email.setText("");
                        tv_FirstName.setText("");
                        tv_Name.setText("");
                        loginButton.setVisibility(View.VISIBLE);
            }
        });

    }

    private void setLogin_button() {

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {


                loginButton.setVisibility(View.INVISIBLE);
                tv_Email.setVisibility(View.VISIBLE);
                tv_FirstName.setVisibility(View.VISIBLE);
                tv_Name.setVisibility(View.VISIBLE);
                bt_logout.setVisibility(View.VISIBLE);
                result();
            }
            @Override
            public void onCancel() {
            }
            @Override
            public void onError(FacebookException error) {
            }
        });
    }

    private void result() {

        GraphRequest graphRequest= GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                Log.d("JSON",response.getJSONObject().toString());
                try {
                    email= object.getString("email");
                    name=object.getString("name");
                    fistName=object.getString("first_name");
                     id =object.getString("id");

                    tv_Email.setText(email);
                    tv_FirstName.setText(fistName);
                    tv_Name.setText(name);




                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });

        Bundle prameter=new Bundle();
        prameter.putString("fields","name,email,first_name");
        graphRequest.setParameters(prameter);
        graphRequest.executeAsync();


    }

    private void initView() {
        bt_logout= findViewById(R.id.btn_dangxuat);
        tv_Name= findViewById(R.id.tvName);
        tv_FirstName=findViewById(R.id.tvFirstName);
        tv_Email = findViewById(R.id.tvEmail);
        loginButton = findViewById(R.id.login_button);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onStart() {
        LoginManager.getInstance().logOut();
        super.onStart();
    }
}