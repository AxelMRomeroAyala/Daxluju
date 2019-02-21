package com.yacarex.daxluju;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import com.bumptech.glide.Glide;

public class AldeanGlidingActivity extends AppCompatActivity {

    private ImageView glideView;
    private Button loadButton, cleanButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aldean_gliding);

        glideView = findViewById(R.id.glideImageView);
        loadButton = findViewById(R.id.imageLoadButton);
        cleanButton = findViewById(R.id.imageCleanButton);


        loadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Glide.with(getBaseContext())
                        .load("https://oyster.ignimgs.com/mediawiki/apis.ign.com/spyro-the-dragon/b/b3/Spyro_Reignited_Trilogy_20181115213349.png")
                        .into(glideView);

            }
        });

        cleanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                glideView.setImageResource(0);
            }
        });
    }
}
