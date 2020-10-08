package com.example.pest.fragment;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;
import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.PestInfor;
import com.example.pest.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicReference;

import static com.amazonaws.mobile.auth.core.internal.util.ThreadUtils.runOnUiThread;


public class MapFragment extends Fragment implements OnMapReadyCallback {
    GoogleMap gMap;
    MapView googleMap;
    View view;
    ArrayList<HashMap<String,Float>> turtleInfor = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_map, container, false);
        getActivity().setTitle("InvasiPests-Location");

        Amplify.API.query(
                ModelQuery.list(PestInfor.class, PestInfor.NAME.contains("Red-eared Slider Turtle")),
                response -> {
                    for (PestInfor pe : response.getData()) {
                        String turtleLa  = pe.getLatitud();
                        String turtleL0  = pe.getLongtitud();
                        float turtleLaf = Float.parseFloat(turtleLa);
                        float turtleLao = Float.parseFloat(turtleL0);
                        HashMap<String, Float> map = new HashMap<>();
                        /*map.put("turtleLa",turtleLaf);
                        map.put("turtleLo",turtleLao);
                        turtleInfor.add(map);*/
                        LatLng turtle = new LatLng(turtleLaf,turtleLao);
                        int num = pe.getNum();
                        addTurtleMarker(turtle,num);
                       // gMap.addMarker(new MarkerOptions().position(turtle).title("Red_eared slider turtle"));
                    }

                },
                error -> Log.e("MyAmplifyApp", "Query failure", error)
        );

        Amplify.API.query(
                ModelQuery.list(PestInfor.class, PestInfor.NAME.contains("Goat")),
                response -> {
                    for (PestInfor pe : response.getData()) {
                        String turtleLa  = pe.getLatitud();
                        String turtleL0  = pe.getLongtitud();
                        float turtleLaf = Float.parseFloat(turtleLa);
                        float turtleLao = Float.parseFloat(turtleL0);
                        int num = pe.getNum();
                        LatLng goat = new LatLng(turtleLaf,turtleLao);
                        addGoatMarker(goat,num);
                        // gMap.addMarker(new MarkerOptions().position(turtle).title("Red_eared slider turtle"));
                    }

                },
                error -> Log.e("MyAmplifyApp", "Query failure", error)
        );

        Amplify.API.query(
                ModelQuery.list(PestInfor.class, PestInfor.NAME.contains("Pigeon")),
                response -> {
                    for (PestInfor pe : response.getData()) {
                        String turtleLa  = pe.getLatitud();
                        String turtleL0  = pe.getLongtitud();
                        float turtleLaf = Float.parseFloat(turtleLa);
                        float turtleLao = Float.parseFloat(turtleL0);
                        int num = pe.getNum();
                        LatLng pigeon = new LatLng(turtleLaf,turtleLao);
                        addPiegeonMarker(pigeon,num);
                        // gMap.addMarker(new MarkerOptions().position(turtle).title("Red_eared slider turtle"));
                    }

                },
                error -> Log.e("MyAmplifyApp", "Query failure", error)
        );

        Amplify.API.query(
                ModelQuery.list(PestInfor.class, PestInfor.NAME.contains("Canada Goose")),
                response -> {
                    for (PestInfor pe : response.getData()) {
                        String turtleLa  = pe.getLatitud();
                        String turtleL0  = pe.getLongtitud();
                        float turtleLaf = Float.parseFloat(turtleLa);
                        float turtleLao = Float.parseFloat(turtleL0);
                        int num = pe.getNum();
                        LatLng goose = new LatLng(turtleLaf,turtleLao);
                        addGooseMarker(goose,num);
                        // gMap.addMarker(new MarkerOptions().position(turtle).title("Red_eared slider turtle"));
                    }

                },
                error -> Log.e("MyAmplifyApp", "Query failure", error)
        );




      //  HashMap<String, Float> map = turtleInfor.get(0);
      //   LatLng turtle = new LatLng(map.get("turtleLa"),map.get("turtleLo"));
      //  gMap.addMarker(new MarkerOptions().position(turtle).title("Red_eared slider turtle"));
        return view;
    }
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        googleMap = view.findViewById(R.id.google_map);

        if(googleMap != null){
            googleMap.onCreate(null);
            googleMap.onResume();
            googleMap.getMapAsync(this);

        }

    }


    public void onMapReady(GoogleMap googleMap) {

        MapsInitializer.initialize(getContext());
        gMap = googleMap;
        gMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        gMap.getUiSettings().setZoomControlsEnabled(true);
        gMap.getUiSettings().setScrollGesturesEnabledDuringRotateOrZoom(true);


      /*  gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(pHome,12));
        gMap.addMarker(new MarkerOptions().position(pHome).title("My Home"));
        for (int i = 0; i < allcinameInfor.size();i++ ){
            LatLng p = convertAddress(getActivity(),allcinameInfor.get(i).get(1));
            gMap.addMarker(new MarkerOptions().position(p).
                    title(allcinameInfor.get(i).get(0)).
                    icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
        }*/

        //String turtleLa = turtleInfor.get(0).get("Latitude");
       // String turtleL0 = turtleInfor.get(0).get("Longitude");

       // LatLng red_turtle = new LatLng(turtleLaf, turtleLao);
        // Add a marker in Sydney and move the camera
       // LatLng turtle = new LatLng(map.get("turtleLa"),map.get("turtleLo"));
        LatLng melbourne = new LatLng(-37.8, 144.9);
        gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(melbourne,8));
        gMap.addMarker(new MarkerOptions().position(melbourne).title("Melbourne"));
       // gMap.addMarker(new MarkerOptions().position(turtle).title("Red_eared slider turtle"));
        gMap.moveCamera(CameraUpdateFactory.newLatLng(melbourne));
    }

    private void addTurtleMarker(LatLng value,int num){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                gMap.addMarker(new MarkerOptions().position(value).title("Red_eared slider turtle: " + num)
                        .icon(vectorToBitmap(R.drawable.turtle)));
            }
        });
    }

    private void addGoatMarker(LatLng value,int num){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                gMap.addMarker(new MarkerOptions().position(value).title("Goat: " + num)
                        .icon(vectorToBitmap(R.drawable.goats)));
            }
        });
    }

    private BitmapDescriptor vectorToBitmap(@DrawableRes int id) {
        Drawable vectorDrawable = ResourcesCompat.getDrawable(getResources(), id, null);
        Bitmap bitmap = Bitmap.createBitmap(100,
                100, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        vectorDrawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        vectorDrawable.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }

    private void addGooseMarker(LatLng value,int num){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                gMap.addMarker(new MarkerOptions().position(value).title("Goose: " + num)
                        .icon(vectorToBitmap(R.drawable.gooes)));
            }
        });
    }

    private void addPiegeonMarker(LatLng value,int num){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                gMap.addMarker(new MarkerOptions().position(value).title("Pigeon: " + num)
                        .icon(vectorToBitmap(R.drawable.apiegeon)));
            }
        });
    }

}