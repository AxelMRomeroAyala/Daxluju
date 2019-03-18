package com.yacarex.daxluju;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.provider.MediaStore;
import android.provider.MediaStore.Audio.Media;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class AldeanActivity extends AppCompatActivity {

    private ImageButton toastyButton;
    private ImageButton fbiButton;
    private ImageButton glideButton, imageList;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aldean);

        toastyButton = findViewById(R.id.Toasty);
        fbiButton = findViewById(R.id.fbiCaller);
        glideButton = findViewById(R.id.gliding);
        imageList = findViewById(R.id.imageList);

        toastyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(),AldeanToastyActivity.class));
            }
        });

        fbiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(),AldeanFbiActivity.class));
            }
        });

        glideButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(),AldeanGlidingActivity.class));
            }
        });

        imageList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(),AldeanImageListActivity.class));
            }
        });

    }
}
