package com.example.pest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.UserInfor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignupActivity extends AppCompatActivity {
    Button create;
    EditText siemial;
    EditText siusname;
    EditText pwd;
    EditText conpwd;
    ArrayList<String> uemailList;
    ArrayList<String> unamelList;
    TextView term;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        create = findViewById(R.id.sigincreate);
        siemial = findViewById(R.id.signEmail);
        siusname = findViewById(R.id.signusername);
        pwd = findViewById(R.id.signpwd);
        conpwd = findViewById(R.id.signrepwd);
        uemailList = new ArrayList<>();
        unamelList = new ArrayList<>();
        Amplify.API.query(
                ModelQuery.list(UserInfor.class, UserInfor.UEMAIL.ne("")),
                response -> {
                    for (UserInfor u : response.getData()) {
                        uemailList.add(u.getUemail());
                        unamelList.add(u.getUname());
                    }
                },
                error -> Log.e("MyAmplifyApp", "Query failure", error)
        );

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = siemial.getText().toString();
                String usename = siusname.getText().toString();
                String passw = pwd.getText().toString();
                String repas = conpwd.getText().toString();
                if (email.equals("")){
                    siemial.setError("Please input email");
                }
                if (usename.equals("")){
                    siusname.setError("Please input user name");
                }
                if (passw.equals("")){
                    pwd.setError("Please input password");
                }
                if (repas.equals("")){
                    conpwd.setError("Please confirm password");
                }
                if (!passw.equals(repas)){
                    conpwd.setError("Password is not same");
                }
                boolean ce = checkMial(email);
                if (!ce){
                    siemial.setError("Please input correct email ");
                }

                boolean ceuname =checkuname(usename);
                if (!ceuname){
                    siusname.setError("Have same user name");
                }
                boolean ceumail =checkEmail(email);
                if (!ceumail){
                    siemial.setError("Have same email");
                }

                if (!email.equals("") && !usename.equals("") &&
                  !passw.equals("") && !repas.equals("") &&
                        passw.equals(repas) && ce && ceuname && ceumail
                )
                {
                    UserInfor u = UserInfor.builder()
                            .uemail(email)
                            .uname(usename)
                            .password(passw)
                            .build();
                    Amplify.DataStore.save(
                            u,
                            success -> Log.i("Tutorial", "Saved item:"),
                            error -> Log.e("Tutorial", "Could not save item to DataStore", error));
                    Intent intent = new Intent(SignupActivity.this,LoginActivity.class);
                    intent.putExtra("email",email);
                    intent.putExtra("pwd",passw);
                    intent.putExtra("uname",usename);
                    startActivity(intent);
                    finish();
                }

                //startActivity(new Intent( SignupActivity.this,LoginActivity.class));
            }
        });

        term = findViewById(R.id.signterm);
        SpannableString content = new SpannableString("I agree to the Terms and Conditions");
        content.setSpan(new UnderlineSpan(), 15, content.length(), 0);
        term.setText(content);
        term.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignupActivity.this,TermActivity.class));
            }
        });
    }

    public boolean checkMial(String s){
        String check = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        Pattern regex = Pattern.compile(check);
        Matcher matcher = regex.matcher(s);
        return matcher.matches();
    }

    public boolean checkEmail(String email){
        for (String s : uemailList) {
            if (s.equals(email)){
                return  false;
            }
        }
        return true;
    }

    public boolean checkuname(String name){
        for (String s : unamelList) {
            if (s.equals(name)){
                return  false;
            }
        }
        return true;
    }


}