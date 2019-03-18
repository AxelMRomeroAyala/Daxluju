package com.yacarex.daxluju;

import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.signature.ObjectKey;

public class AldeanGlidingActivity extends AppCompatActivity {

    private ImageView glideView;
    private Button loadButton, cleanButton;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aldean_gliding);

        glideView   = findViewById(R.id.glideImageView);
        loadButton  = findViewById(R.id.imageLoadButton);
        cleanButton = findViewById(R.id.imageCleanButton);
        progressBar = findViewById(R.id.progressBar);

        ;

        loadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressBar.setVisibility(View.VISIBLE);

                Glide.with(getBaseContext())
                    .load("https://oyster.ignimgs.com/mediawiki/apis.ign.com/spyro-the-dragon/b/b3/Spyro_Reignited_Trilogy_20181115213349.png")
                    .apply(new RequestOptions()
                        .signature(new ObjectKey("aldeanGlideCache"))
                        .skipMemoryCache(true))
                    .transition(DrawableTransitionOptions.withCrossFade(200))
                    .addListener(new RequestListener<Drawable>() {

                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {

                            progressBar.setVisibility(View.GONE);
                            glideView.setImageResource(R.drawable.fbi_logo);
                            return false;

                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {

                            progressBar.setVisibility(View.GONE);
                            return false;

                        }
                    })
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
