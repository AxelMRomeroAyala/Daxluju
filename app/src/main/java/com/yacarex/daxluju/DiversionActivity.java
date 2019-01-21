package com.yacarex.daxluju;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DiversionActivity extends AppCompatActivity {

    TextView juanfraTextView;
    Button contar;

    int contador = 0;
    String frase = "Me tocaste ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diversion);

        juanfraTextView = findViewById(R.id.juan_text);
        contar = findViewById(R.id.juan_tocame);

        contar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                contador++;
                juanfraTextView.setText(frase + String.valueOf(contador));
            }
        });
        }
}
