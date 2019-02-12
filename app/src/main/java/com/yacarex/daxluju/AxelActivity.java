package com.yacarex.daxluju;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class AxelActivity extends AppCompatActivity {

    TextView axelTextView;
    ImageView axelImage;
    Button editar, editar2, axelMap;

    String edicion = "EDITADO";
    String edicion2 = "EDITADO 2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_axel);

        axelTextView = findViewById(R.id.axel_text);
        editar = findViewById(R.id.axel_editar);
        editar2 = findViewById(R.id.axel_editar_2);
        axelImage = findViewById(R.id.axel_image);
        axelMap = findViewById(R.id.axel_map);

        editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                axelTextView.setText(edicion);
            }
        });
        editar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Glide.with(getBaseContext())
                        .load("https://picsum.photos/200/300/?random")
                        .into(axelImage);
            }
        });

        axelMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(), MapsActivity.class));
            }
        });
   }

}
