package com.example.pest;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Report;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Calendar;

public class ReportActivity extends AppCompatActivity {
    ImageView image;
    Spinner spin;
    EditText num;
    TextView date;
    EditText location;
    EditText desc;
    private DatePickerDialog picker;
    Button report;
    String path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        image = findViewById(R.id.report_img);
        spin = findViewById(R.id.spin);
        date = findViewById(R.id.selectDate);
        num = findViewById(R.id.report_num);
        location = findViewById(R.id.report_loca);
        desc = findViewById(R.id.report_desc);
        Uri uri = getIntent().getData();
        Intent intent = getIntent();
        path = intent.getStringExtra("path");

        image.setImageURI(uri);
        report = findViewById(R.id.report_sub);

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int d = calendar.get(Calendar.DAY_OF_MONTH);
                int m = calendar.get(Calendar.MONTH);
                int y = calendar.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(ReportActivity.this, AlertDialog.THEME_HOLO_DARK,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                date.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                            }
                        }, y, m, d);
                picker.show();
            }
        });

        report.setOnClickListener(new View.OnClickListener() {
            Context context = getApplicationContext();
            CharSequence success = "Report successful";
            CharSequence text = "Please input all information";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, success, duration);
            Toast toast1 = Toast.makeText(context, text, duration);
            @Override
            public void onClick(View v) {
                if (date.getText().toString().equals("") ||date.getText().toString().equals("")||
                        desc.getText().toString().equals("") ||num.getText().toString().equals("") ||
                        location.getText().toString().equals("")) {
                    toast1.show();
                }
                else {
                    uploadFile();
                    int num1 = Integer.valueOf(num.getText().toString());
                Report re = Report.builder().pest("red_eared slider turtle")
                        .date(date.getText().toString())
                        .description(desc.getText().toString())
                        .num(num1)
                        .location(location.getText().toString())
                        .build();

                Amplify.DataStore.save(re,
                        result -> toast.show(),
                        error -> Log.e("MyAmplifyApp", "Error creating post", error));}
            }
        });
    }

    private void uploadFile() {
        String finalName = date.getText().toString() +"-"+ location.getText().toString() +"-"+
                num.getText().toString() +"-"+ "red_eared slider turtle" + ".jpeg";
        File exampleFile = new File(getApplicationContext().getFilesDir(), finalName);
        try {

            BufferedInputStream reader = new BufferedInputStream(new FileInputStream(path));
            BufferedOutputStream writer = new BufferedOutputStream(new FileOutputStream(exampleFile));
            byte[] bArr = new byte[1024 * 1024];
            int res = 0;
            while ((res = reader.read(bArr)) != -1) {
                writer.write(bArr, 0, res);
            }
            writer.close();
        } catch (Exception exception) {
            Log.e("MyAmplifyApp", "Upload failed", exception);
        }

        Amplify.Storage.uploadFile(
                finalName,
                exampleFile,
                result -> Log.i("MyAmplifyApp", "Successfully uploaded: " + result.getKey()),
                storageFailure -> Log.e("MyAmplifyApp", "Upload failed", storageFailure)
        );
    }

}