package com.yacarex.daxluju;

import android.support.constraint.solver.Cache;
import android.support.v4.widget.CircularProgressDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.signature.ObjectKey;

public class AldeanGlidingActivity extends AppCompatActivity {

    private ImageView glideView;
    private Button loadButton, cleanButton;
//    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aldean_gliding);

        glideView   = findViewById(R.id.glideImageView);
        loadButton  = findViewById(R.id.imageLoadButton);
        cleanButton = findViewById(R.id.imageCleanButton);

        loadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CircularProgressDrawable progressBar = new CircularProgressDrawable(getBaseContext());
                progressBar.setStrokeWidth(10f);
                progressBar.setCenterRadius(100f);
                progressBar.setBackgroundColor(5);
                progressBar.start();

                Glide.with(getBaseContext())
                        .load("https://oyster.ignimgs.com/mediawiki/apis.ign.com/spyro-the-dragon/b/b3/Spyro_Reignited_Trilogy_20181115213349.png")
                        .apply(new RequestOptions()
                                .placeholder(progressBar)
                                .signature(new ObjectKey("aldeanGlideCache"))
                                .skipMemoryCache(true))
                        .transition(DrawableTransitionOptions.withCrossFade(200))
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
