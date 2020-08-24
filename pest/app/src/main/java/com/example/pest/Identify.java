package com.example.pest;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.amplifyframework.core.Amplify;
import com.amplifyframework.predictions.models.Label;
import com.amplifyframework.predictions.models.LabelType;
import com.amplifyframework.predictions.result.IdentifyLabelsResult;
import com.theartofdev.edmodo.cropper.CropImage;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class Identify extends AppCompatActivity {
    private static String lab ;
    Button btn ;
    TextView id;
    ImageView img;
    Uri image_url ;
    URL u = null;
    Bitmap bit = null;
    File file = null;
    Drawable drawable;
    String path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identify);
        btn = findViewById(R.id.select);
        img = findViewById(R.id.img_view);
        id = findViewById(R.id.res);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onChooseFile(v);
            }
        });
    }

    public void onChooseFile(View v){
        CropImage.activity().start(Identify.this);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                image_url = result.getUri();
                path = result.getUri().getPath();
                try {
                    file = new File(new URI(image_url.toString()));
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }
                img.setImageURI(image_url);
                bit = ((BitmapDrawable)img.getDrawable()).getBitmap();

                uploadFile();
               /* try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(),image_url);
                    Amplify.Predictions.identify(
                            LabelType.LABELS,
                            bitmap,
                            results -> {
                                IdentifyLabelsResult identifyResult = (IdentifyLabelsResult) results;
                                Label label = identifyResult.getLabels().get(0);
                                lab = label.getName().toString();
                                Log.i("MyAmplifyApp", label.getName());
                                setText(lab);
                            },
                            error -> Log.e("MyAmplifyApp", "Entity detection failed", error)
                    );
                } catch (IOException e) {
                    e.printStackTrace();
                }*/
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception e = result.getError();
                Toast.makeText(this,"Error"+e,Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void setText( String value){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                id.setText(value);
            }
        });
    }

    private void uploadFile() {
        String[] str = path.split("/");
        String name = str[str.length -1];
        String[] str2 = name.split("[.]");
        String imageName = str2[0];
        String finalName = imageName+".jpeg";
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