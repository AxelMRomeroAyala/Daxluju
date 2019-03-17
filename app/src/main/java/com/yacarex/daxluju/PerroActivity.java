package com.yacarex.daxluju;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;

public class PerroActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    PerritoAdapter adapter;
    ArrayList<PerritoModel> arrayList= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perro);

        recyclerView= findViewById(R.id.recyclerView);

        arrayList.add(new PerritoModel(
                "Khe",
                "https://i.pinimg.com/564x/74/74/29/747429a61563d3a2857ac78f41443fd6.jpg"));
        arrayList.add(new PerritoModel(
                "Your Balls",
                "https://pm1.narvii.com/5920/3681b7c06ae3cf065b74ffb58f8d0ce3a38e5d85_hq.jpg"));


        adapter= new PerritoAdapter(arrayList, getBaseContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));


    }

}
