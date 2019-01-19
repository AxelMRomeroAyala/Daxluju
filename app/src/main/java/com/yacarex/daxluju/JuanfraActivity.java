package com.yacarex.daxluju;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class JuanfraActivity extends AppCompatActivity {

    Button openGato;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juanfra);
        openGato=findViewById(R.id.open_gato);
        openGato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {startActivity(new Intent(getBaseContext(), GatoActivity.class));
            }
        });
    }
}
