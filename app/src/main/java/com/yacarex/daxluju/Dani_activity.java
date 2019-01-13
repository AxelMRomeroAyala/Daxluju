package com.yacarex.daxluju;

import android.support.v13.view.DragStartHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Dani_activity extends AppCompatActivity {

    TextView daniTextView;
    Button editar;

    String edicion = "EDITADO";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dani_activity);

        daniTextView = findViewById(R.id.dani_text);
        editar = findViewById(R.id.dani_editar);

        editar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                daniTextView.setText(edicion);
            }
        });
    }
}
