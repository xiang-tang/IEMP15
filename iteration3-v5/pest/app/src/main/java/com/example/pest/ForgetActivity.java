package com.example.pest;

import androidx.appcompat.app.AppCompatActivity;

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

public class ForgetActivity extends AppCompatActivity {

    Button check;
    Button sub;
    EditText email;
    EditText ans;
    TextView pass;
    TextView ques;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget);
        check = findViewById(R.id.forcheck);
        sub = findViewById(R.id.forsubmit);
        email = findViewById(R.id.forEmail);
        ans = findViewById(R.id.foranswer);
        pass = findViewById(R.id.forpass);
        ques = findViewById(R.id.forwques);


        ArrayList<String> arr = new ArrayList<>();
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uemail = email.getText().toString();
                if (uemail.equals("")){
                    email.setError("Please input email");
                    return;
                }
                Amplify.API.query(
                        ModelQuery.list(UserInfor.class, UserInfor.UEMAIL.contains(uemail)),
                        response -> {
                            for (UserInfor u : response.getData()) {
                                 String s1 = u.getQuestion();
                                 String s2 = u.getAnswer();
                                 String s3 = u.getPassword();
                               arr.add(s1);
                               arr.add(s2);
                               arr.add(s3);
                            }
                            if (arr.size()!= 0){
                                String qu = arr.get(0);
                                ques.setText(qu);
                            }else {
                                setcon();
                            }
                        },
                        error -> Log.e("MyAmplifyApp", "Query failure", error)
                );

            }
        });

     sub.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             String s = ans.getText().toString();
             String aa = arr.get(1);
             String pp = arr.get(2);
             if (s.equals("")){
                 ans.setError("Please answer the question");
                 return;
             }
             if (s.equals(aa)){
                 pass.setText("Recovered Password is:  "+pp);
             }else {
                 ans.setError("Answer is incorrect");
             }
         }
     });


    }
    private void setcon(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                email.setError("Email does not exist");
            }
        });
    }

}