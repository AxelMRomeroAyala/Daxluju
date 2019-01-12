package com.yacarex.daxluju;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button daniButton, axelButton, luchoButton, juanfraButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpButtons();
    }

    private void setUpButtons(){
        daniButton= findViewById(R.id.dani_button);
        axelButton= findViewById(R.id.axel_button);
        luchoButton= findViewById(R.id.lucho_button);
        juanfraButton= findViewById(R.id.juanfra_button);

        axelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(), AxelActivity.class));
            }
        });
    }
}
