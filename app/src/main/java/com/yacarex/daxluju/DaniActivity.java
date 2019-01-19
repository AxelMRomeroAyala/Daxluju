package com.yacarex.daxluju;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DaniActivity extends AppCompatActivity {

    TextView daniTextView;
    Button editar;
    Button contar;
    Button perroButton;

    String edicion = "EDITADO";
    String edicion2 = "DANI";
    boolean activo = true;
    int contador = 0;
    String frase = "Usted apretó ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dani_activity);

        daniTextView = findViewById(R.id.dani_text);
        editar = findViewById(R.id.dani_editar);
        contar = findViewById(R.id.dani_saludo);
        perroButton=findViewById(R.id.dani_perro);

        editar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if (activo) {

                    daniTextView.setText(edicion);
                    activo = false;
                } else {
                    daniTextView.setText(edicion2);
                    activo = true;
                }
            }


        });

        contar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                contador++;
                daniTextView.setText(frase + String.valueOf(contador));
            }
        });


        perroButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                startActivity(new Intent(getBaseContext(), PerroActivity.class));

            }
        });




    }
}
