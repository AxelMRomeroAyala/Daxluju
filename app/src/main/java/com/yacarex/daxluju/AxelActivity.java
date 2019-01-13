package com.yacarex.daxluju;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AxelActivity extends AppCompatActivity {

    TextView axelTextView;
    Button editar;

    String edicion= "EDITADO";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_axel);

        axelTextView = findViewById(R.id.axel_text);
        editar= findViewById(R.id.axel_editar);

        editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                axelTextView.setText(edicion);
            }
        });
    }

}
