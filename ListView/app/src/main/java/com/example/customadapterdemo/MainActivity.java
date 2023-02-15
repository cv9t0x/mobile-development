package com.example.customadapterdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    UserListAdapter adapter;
    ListView listView;

    Button sortByNameBtn, sortByPhoneNumberBtn, sortBySexBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.list);

        sortByNameBtn = findViewById(R.id.sortByName);
        sortByPhoneNumberBtn = findViewById(R.id.sortByPhoneNumber);
        sortBySexBtn = findViewById(R.id.sortBySex);

        String jsonFileString = Utils.getJsonFromAssets(getApplicationContext(), "users.json");

        Gson gson = new Gson();
        Type listUserType = new TypeToken<List<User>>() { }.getType();

        final ArrayList<User> users = gson.fromJson(jsonFileString, listUserType);

        adapter = new UserListAdapter(this, users);
        listView.setAdapter(adapter);

        sortByNameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Collections.sort(users, new Comparator<User>() {
                    @Override
                    public int compare(User user, User other) {
                        return user.name.compareTo(other.name);
                    }
                });
                adapter.notifyDataSetChanged();
            }
        });

        sortByPhoneNumberBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Collections.sort(users, new Comparator<User>() {
                    @Override
                    public int compare(User user, User other) {
                        return user.phoneNumber.toString().compareTo(other.phoneNumber.toString());
                    }
                });
                adapter.notifyDataSetChanged();
            }
        });

        sortBySexBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Collections.sort(users, new Comparator<User>() {
                    @Override
                    public int compare(User user, User other) {
                        return Sex.getOrderNumber(user.sex) - Sex.getOrderNumber(other.sex);
                    }
                });
                adapter.notifyDataSetChanged();
            }
        });
    }
}
