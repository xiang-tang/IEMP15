package com.example.pest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Report;
import com.amplifyframework.datastore.generated.model.UserInfor;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

public class LeaderActivity extends AppCompatActivity {
    ListView listView;
    ArrayList<User> userArrayList;
    ArrayList<User> finalArrayList;
    ArrayList<String> userNameList;
    ArrayList<Integer> userPoints;
    SharedPreferences preferences;
    String userName;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leader);
        this.setTitle("InvasiPests-Leaderboard");
        listView = findViewById(R.id.listview);
        userNameList = new ArrayList<>();
        userPoints = new ArrayList<>();
        userArrayList = new ArrayList<>();
        Intent intent = getIntent();
        userNameList = intent.getStringArrayListExtra("nameList");
        userPoints = intent.getIntegerArrayListExtra("points");
        preferences  = getSharedPreferences("userRecord",MODE_PRIVATE);
        userName = preferences.getString("uname","");

        imageView = findViewById(R.id.liveim);
        Glide.with(this).load(R.drawable.live).into(imageView);
        //ArrayList<String> nameList = findDupicateInArray(userNameList);
        HashSet<String> hashSet = new HashSet<>();
        for (int i = 0; i < userNameList.size();i++){
            if (!hashSet.contains(userNameList.get(i))){
                hashSet.add(userNameList.get(i));
            }
        }
        ArrayList<String> nameList = new ArrayList<>();
        nameList.addAll(hashSet);

        finalArrayList = new ArrayList<>();
        int l = userNameList.size();
        for (int i = 0; i < l; i++){
            User user = new User(userNameList.get(i),userPoints.get(i),1);
            userArrayList.add(user);
        }

        for (int j = 0; j < nameList.size();j++){
            int countpoint = 0;
            int times = 0;
            for (int i =0; i < userArrayList.size();i++){
                    if (nameList.get(j).equals(userArrayList.get(i).getUserName())){
                        countpoint += userArrayList.get(i).getTotalPoints();
                        times += 1;
                    }
            }
            User user = new User(nameList.get(j),countpoint,times);
            finalArrayList.add(user);
        }
        Collections.sort(finalArrayList);

        for (int i = 0; i < finalArrayList.size(); i++){
            finalArrayList.get(i).setRank(i + 1);
        }
        
        UerListAdapter adapter = new UerListAdapter(this,R.layout.adapter_view_layout,finalArrayList,userName);
        listView.setAdapter(adapter);
    }

    public ArrayList<String> findDupicateInArray(ArrayList<String> a) {
        ArrayList<String> newUser = new ArrayList<>();
        int count=0;
        for(int j=0;j < a.size();j++) {
            for(int k=0;k < a.size();k++) {  //int k =j+1;k<a.length;k++改成int k=0;k<a.length;k++
                if(a.get(j).equals(a.get(k))) {
                    count++;
                }
            }
            if(count==1)
                newUser.add(a.get(j));
            count = 0;
        }
        return newUser;
    }

    public void ShowGif(View view) {
        ImageView imageView = findViewById(R.id.liveim);
        Glide.with(this).load(R.drawable.live).into(imageView);
    }
}