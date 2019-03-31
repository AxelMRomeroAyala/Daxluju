package com.yacarex.daxluju.axel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.GenericTransitionOptions;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory;
import com.google.gson.Gson;
import com.yacarex.daxluju.R;
import com.yacarex.daxluju.models.axel.PokemonModel;

import java.util.Random;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

public class AxelActivity extends AppCompatActivity {

    TextView axelTextView;
    ImageView axelImage;
    Button editar, editar2, axelMap, pixelGrid;
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
        pixelGrid = findViewById(R.id.axel_pixel_grid);

        editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                axelTextView.setText(edicion);
            }
        });
        editar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getPokemon();
//                Glide.with(getBaseContext())
//                        .load("https://picsum.photos/200/300/?random")
//                        .into(axelImage);
            }
        });

        axelMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(), MapsActivity.class));
            }
        });
        pixelGrid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(), AxelPixelDrawActivity.class));
            }
        });
    }


    public void getPokemon() {
// Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://pokeapi.co/api/v2/pokemon/" + String.valueOf(getRandomNumberInRange(1, 151));

// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        PokemonModel pokemonModel = new Gson().fromJson(response, PokemonModel.class);
                        loadImage(pokemonModel);
                        Log.e("LOG", response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("LOG", error.getMessage());
            }
        });

// Add the request to the RequestQueue.
        queue.add(stringRequest);

    }

    public void loadImage(PokemonModel model){

        DrawableCrossFadeFactory factory =
                new DrawableCrossFadeFactory.Builder().setCrossFadeEnabled(true).build();

        Glide.with(getBaseContext())
                .load(model.getSprites().getFront_default())
                .transition(withCrossFade(factory))
                .into(axelImage);
    }

    private static int getRandomNumberInRange(int min, int max) {

        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

}
