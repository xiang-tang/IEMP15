package com.example.pest;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Report;
import com.amplifyframework.predictions.models.Label;
import com.amplifyframework.predictions.models.LabelType;
import com.amplifyframework.predictions.result.IdentifyLabelsResult;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.mlkit.common.model.DownloadConditions;
import com.google.mlkit.common.model.RemoteModelManager;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.label.ImageLabel;
import com.google.mlkit.vision.label.ImageLabeler;
import com.google.mlkit.vision.label.ImageLabeling;
import com.google.mlkit.vision.label.automl.AutoMLImageLabelerLocalModel;
import com.google.mlkit.vision.label.automl.AutoMLImageLabelerOptions;
import com.google.mlkit.vision.label.automl.AutoMLImageLabelerRemoteModel;
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
import java.text.DecimalFormat;
import java.util.List;

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
    ImageLabeler labeler;
    AutoMLImageLabelerLocalModel localModel;
    AutoMLImageLabelerOptions autoMLImageLabelerOptions;
    Button report;
    TextView con;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identify);
        btn = findViewById(R.id.select);
        img = findViewById(R.id.img_view);
        id = findViewById(R.id.res);
        report = findViewById(R.id.report);
        con = findViewById(R.id.confidence);
        this.setTitle("Pest-Identify");
         localModel =
                new AutoMLImageLabelerLocalModel.Builder()
                    .setAssetFilePath("model/manifest.json")
                      //  .setAbsoluteFilePath("/assets/model/manifest.json")
                        .build();

         autoMLImageLabelerOptions =
                new AutoMLImageLabelerOptions.Builder(localModel)
                        .setConfidenceThreshold(0.0f)  // Evaluate your model in the Firebase console
                        // to determine an appropriate value.
                        .build();
         labeler = ImageLabeling.getClient(autoMLImageLabelerOptions);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(Identify.this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Attention")
                        .setMessage("Please take a clear photo of suspected pest")
                        .setPositiveButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                               // finish();//Exit Activity
                            }
                        })
                        .setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                onChooseFile(v);
                            }
                        }).create().show();


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
                InputImage image = null;
                try {
                    image = InputImage.fromFilePath( getApplicationContext(),image_url);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                labeler.process(image)
                        .addOnSuccessListener(new OnSuccessListener<List<ImageLabel>>() {
                            @Override
                            public void onSuccess(List<ImageLabel> labels) {
                                // Task completed successfully
                                // ...

                                ImageLabel labelfirst = labels.get(0);
                                String labe = labelfirst.getText();
                                float confidencef = labelfirst.getConfidence();
                                DecimalFormat decimalFormat=new DecimalFormat(".00");
                                confidencef = confidencef * 100;
                                String pd = decimalFormat.format(confidencef);
                                if ((labe.equals("Red_Eared_Slider_Turtle")
                                        ||labe.equals("Canadian_Goose")
                                        ||labe.equals("Goat")
                                        ||labe.equals("Indian_Mynah")
                                        ||labe.equals("Piegeon"))
                                        && confidencef > 80) {

                                    if(labe.equals("Indian_Mynah")){
                                        setText("Yes! This is Indian Mynah");
                                        setcon(pd);
                                    }else if(labe.equals("Canadian_Goose")){
                                        setText("Yes! This is Canadian Goose" );
                                        setcon(pd);
                                    }else if(labe.equals("Red_Eared_Slider_Turtle")){
                                        setText("Yes! This is Red_eared Slider Turtle" );
                                        setcon(pd);
                                    }
                                    else {
                                        setText("Yes! This is " + labe);
                                        setcon(pd);
                                    }

                                    report.setVisibility(View.VISIBLE);
                                    report.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            Intent intent = new Intent(Identify.this,ReportActivity.class);
                                            intent.setData(image_url);
                                            intent.putExtra("path",path);
                                            intent.putExtra("name",labe);
                                            startActivity(intent);
                                        }
                                    });
                                }else if(confidencef > 80){
                                    setcon(pd);
                                    setText(labe);
                                    report.setVisibility(View.INVISIBLE);
                                }else {
                                    setcon(pd);
                                    report.setVisibility(View.INVISIBLE);
                                    setText("Image detected does not reveal any recognised pests");
                                }

                                /*for (ImageLabel label : labels) {
                                    String lab = label.getText();
                                    float confidence = label.getConfidence();
                                    //DecimalFormat decimalFormat=new DecimalFormat(".00");
                                    if (lab.equals("Red_Eared_Slider_Turtle")) {
                                        if (confidence > 0.77) {
                                            setText("Yes! This is Red-eared slider turtle");
                                            confidence = confidence * 100;
                                            String p=decimalFormat.format(confidence);
                                            setcon(p);
                                            report.setVisibility(View.VISIBLE);
                                            report.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    Intent intent = new Intent(Identify.this,ReportActivity.class);
                                                    intent.setData(image_url);
                                                    intent.putExtra("path",path);
                                                    startActivity(intent);
                                                }
                                            });
                                        }
                                        else {
                                            report.setVisibility(View.INVISIBLE);
                                            setText("Keep working hard and you will find it");
                                            confidence = confidence * 100;
                                            String p=decimalFormat.format(confidence);
                                            setcon(p);
                                        }
                                        break;
                                    }
                                    else if (lab.equals("Common_Long_Necked_Turtle")) {
                                        if (confidence > 0.77) {
                                            report.setVisibility(View.INVISIBLE);
                                            setText("This is a Native Long Neck Turtle, it is a safe animal and not a pest");
                                            confidence = confidence * 100;
                                            String p=decimalFormat.format(confidence);
                                            setcon(p);
                                            break;
                                        }
                                    }
                                    else if (lab.equals("Animals")) {
                                        if (confidence > 0.5) {
                                            report.setVisibility(View.INVISIBLE);
                                            setText("Image detected does not reveal any recognised pests");
                                            confidence = confidence * 100;
                                            String p=decimalFormat.format(confidence);
                                            setcon(p);
                                            break;
                                        }

                                    }

                                    }*/
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {

                                // ...
                            }
                        });
                //uploadFile();
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

    private void setcon( String value){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                con.setText("Accuracy: "+value + "%");
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