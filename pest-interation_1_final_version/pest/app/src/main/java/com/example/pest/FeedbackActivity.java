package com.example.pest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Feedback;

public class FeedbackActivity extends AppCompatActivity {
    Button submit;
    EditText name;
    EditText email;
    EditText comment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        submit = (Button) findViewById(R.id.submit);
        name = (EditText)findViewById(R.id.fname);
        email = (EditText)findViewById(R.id.femail);
        comment = (EditText)findViewById(R.id.fcomment);


        Context context = getApplicationContext();
        CharSequence text = "Please input all information";
        CharSequence success = "Feedback successful";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        Toast toast1 = Toast.makeText(context, success, duration);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (name.getText().toString().equals("")|| email.getText().toString().equals("") || comment.getText().toString().equals("")) {
                    toast.show();
                }else {
                    Feedback f = Feedback.builder()
                            .name(name.getText().toString())
                            .email(email.getText().toString())
                            .comment(comment.getText().toString())
                            .build();

                    Amplify.DataStore.save(f,
                            result -> toast1.show(),
                            error -> Log.e("MyAmplifyApp",  "Error creating post", error)
                    );
                }

            }
        });

    }
}