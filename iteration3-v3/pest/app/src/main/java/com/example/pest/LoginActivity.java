package com.example.pest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.PestInfor;
import com.amplifyframework.datastore.generated.model.UserInfor;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    TextView toSign;
    Button guest;
    Button signin;
    EditText uEmail;
    EditText upwd;
    ArrayList<Map<String,String>> uInfor = new ArrayList<>();
    SharedPreferences preferences;
    SharedPreferences.Editor editor;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        toSign = findViewById(R.id.signupto);
        uEmail = findViewById(R.id.uemail);
        upwd = findViewById(R.id.upwd);
        Intent intent = getIntent();
        String umail = intent.getStringExtra("email");
        String up = intent.getStringExtra("pwd");
        String userN =intent.getStringExtra("uname");
        uEmail.setText(umail);
        preferences  = getSharedPreferences("userRecord",MODE_PRIVATE);
        boolean check = preferences.getBoolean("autologin",false);
        if (check){
            startActivity(new Intent(LoginActivity.this,MainActivity.class));
            finish();
        }

        Amplify.API.query(
                ModelQuery.list(UserInfor.class, UserInfor.UEMAIL.ne("")),
                response -> {
                    for (UserInfor u : response.getData()) {
                        HashMap<String,String> map = new HashMap<>();
                        map.put("email",u.getUemail());
                        map.put("pwd",u.getPassword());
                        map.put("uname",u.getUname());
                        uInfor.add(map);
                    }
                },
                error -> Log.e("MyAmplifyApp", "Query failure", error)
        );


        toSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,SignupActivity.class));
                
            }
        });

        guest = findViewById(R.id.asguest);
        guest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,MainActivity.class));
            }
        });



        signin = findViewById(R.id.siginbtn);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(LoginActivity.this,MainActivity.class));
                String userEmail = uEmail.getText().toString();
                String userPwd = upwd.getText().toString();

                if (up!= null && up.equals(userPwd)){
                    editor = preferences.edit();
                    editor.putBoolean("autologin",true);
                    editor.putString("email",userEmail);
                    editor.putString("uname",userN);
                    editor.commit();
                    startActivity(new Intent(LoginActivity.this,MainActivity.class));
                    finish();
                }
                else {
                if (userEmail.equals("")){
                    uEmail.setError("Please input your email");
                }
                if (userPwd.equals("")){
                    upwd.setError("Please input your password");
                }

                if (!userEmail.equals("") && !userPwd.equals("")){
                    boolean check = false;
                    for (Map<String, String> map : uInfor) {
                         if (map.get("email").equals(userEmail) && map.get("pwd").equals(userPwd)){
                             check = true;
                             editor = preferences.edit();
                             editor.putBoolean("autologin",true);
                             editor.putString("email",userEmail);
                             editor.putString("uname",map.get("uname"));
                             editor.commit();
                             startActivity(new Intent(LoginActivity.this,MainActivity.class));
                             finish();
                         }
                    }
                    if (!check){
                        upwd.setError("Password or Email is not correct");
                    }
                }
            }}
        });
    }






}