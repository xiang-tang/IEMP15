package com.example.pest;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Report;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Calendar;
import java.util.Locale;

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
    Button selectbtn;
    private static final int REQUEST_CODE_LOCATION_PERMISSION = 1;
    ProgressBar pb;
    WifiManager wifiManager;
    private final static int PLACE_PICKER_REQUEST = 999;
    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        this.setTitle("Pest Busters-Report");
        image = findViewById(R.id.report_img);
        //spin = findViewById(R.id.spin);
        date = findViewById(R.id.selectDate);
        num = findViewById(R.id.report_num);
        location = findViewById(R.id.report_loca);
        desc = findViewById(R.id.report_desc);
        Uri uri = getIntent().getData();
        Intent intent = getIntent();
        path = intent.getStringExtra("path");
        String name = intent.getStringExtra("name");
        textView = findViewById(R.id.report_name);
        textView.setText(name);
        pb = findViewById(R.id.probar);
        wifiManager= (WifiManager) this.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        image.setImageURI(uri);
        report = findViewById(R.id.report_sub);
        selectbtn = findViewById(R.id.select_btn);
        selectbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(
                        getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(
                            ReportActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                            REQUEST_CODE_LOCATION_PERMISSION
                    );
                } else {
                    getCurrentLocation();
                }
            }
        });
   /*     selectbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wifiManager.setWifiEnabled(false);
                openPlacePicker();
            }
        });*/

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int d = calendar.get(Calendar.DAY_OF_MONTH);
                int m = calendar.get(Calendar.MONTH);
                int y = calendar.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(ReportActivity.this,/* AlertDialog.THEME_HOLO_DARK,*/
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                date.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
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
                if (date.getText().toString().equals("") || date.getText().toString().equals("") ||
                        desc.getText().toString().equals("") || num.getText().toString().equals("") ||
                        location.getText().toString().equals("")) {
                    toast1.show();
                } else {
                    uploadFile();
                    int num1 = Integer.valueOf(num.getText().toString());
                    Report re = Report.builder().pest(name)
                            .date(date.getText().toString())
                            .description(desc.getText().toString())
                            .num(num1)
                            .location(location.getText().toString())
                            .build();

                    Amplify.DataStore.save(re,
                            result -> toast.show(),
                            error -> Log.e("MyAmplifyApp", "Error creating post", error));


                    new androidx.appcompat.app.AlertDialog.Builder(ReportActivity.this)
                            .setIcon(android.R.drawable.checkbox_on_background)
                            .setTitle("Report")
                            .setMessage("Thanks for your report")
                            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    startActivity(new Intent(ReportActivity.this, MainActivity.class));
                                }
                            }).create().show();
                }
            }
        });
    }

    private void uploadFile() {
        String finalName = date.getText().toString() + "-" + location.getText().toString() + "-" +
                num.getText().toString() + "-" + "red_eared slider turtle" + ".jpeg";
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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_LOCATION_PERMISSION && grantResults.length > 0) {
            getCurrentLocation();
        } else {
            Toast.makeText(this, "permission denied", Toast.LENGTH_SHORT).show();
        }
    }

    private void getCurrentLocation() {
        pb.setVisibility(View.VISIBLE);
        LocationRequest locationRequest = new LocationRequest();
        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(3000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        LocationServices.getFusedLocationProviderClient(ReportActivity.this)
                .requestLocationUpdates(locationRequest, new LocationCallback() {
                    @Override
                    public void onLocationResult(LocationResult locationResult) {
                        super.onLocationResult(locationResult);
                        LocationServices.getFusedLocationProviderClient(ReportActivity.this)
                                .removeLocationUpdates(this);
                        if (locationResult != null && locationResult.getLocations().size() > 0) {
                            int lateslocation = locationResult.getLocations().size() -1 ;
                            double latitude = locationResult.getLocations().get(lateslocation).getLatitude();
                            double longitude = locationResult.getLocations().get(lateslocation).getLongitude();
                            location.setText(String.format("Latitude: %s\nLongitude: %s",
                                    latitude,
                                    longitude
                                    ));
                        }
                        pb.setVisibility(View.GONE);
                    }
                }, Looper.getMainLooper());
 }

/*    private void openPlacePicker() {
        PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
        try {
            startActivityForResult(builder.build(this), PLACE_PICKER_REQUEST);

            //Enable Wifi
            wifiManager.setWifiEnabled(true);

        } catch (GooglePlayServicesRepairableException e) {
            Log.d("Exception",e.getMessage());

            e.printStackTrace();
        } catch (GooglePlayServicesNotAvailableException e) {
            Log.d("Exception",e.getMessage());

            e.printStackTrace();
        }

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            switch (requestCode){
                case PLACE_PICKER_REQUEST:
                    Place place = PlacePicker.getPlace(ReportActivity.this, data);

                    double latitude = place.getLatLng().latitude;
                    double longitude = place.getLatLng().longitude;
                    String PlaceLatLng = String.valueOf(latitude)+" , "+String.valueOf(longitude);
                    location.setText(PlaceLatLng);
            }
        }
    }*/

}