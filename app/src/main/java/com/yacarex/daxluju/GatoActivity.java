package com.yacarex.daxluju;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GatoActivity extends AppCompatActivity {

    TextView juanfraTextView;
    Button gato;
    Gato gatoObjeto = new Gato();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gato);

        juanfraTextView = findViewById(R.id.gato_texto);
        gato = findViewById(R.id.abrir_gato);
        gato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                juanfraTextView.setText(gatoObjeto.maullar());

            }

        });
        juanfraTextView = findViewById(R.id.gato_texto);
        gato = findViewById(R.id.tomar_agua);
        gato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                juanfraTextView.setText(gatoObjeto.beber());


            }
        });
    }
}