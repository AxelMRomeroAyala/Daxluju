package com.yacarex.daxluju;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AldeanFbiActivity extends AppCompatActivity {

    private TextView door;
    private Button openUP;
    private WebView webView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aldean_fbi);


        door = findViewById(R.id.Door);
        openUP = findViewById(R.id.openTheDoor);
        webView = findViewById(R.id.urlView);

        webView.loadUrl("https://gifimage.net/wp-content/uploads/2018/11/fbi-open-up-gif-2.gif");


        webView.setVisibility(View.INVISIBLE);

        //soundRep = (Media) findViewById(R.raw.fbi_open_the_door));

        openUP.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){

                door.setText("Abri la Puerta Manco");
                webView.setVisibility(View.VISIBLE);
            }

        });

    }
}
