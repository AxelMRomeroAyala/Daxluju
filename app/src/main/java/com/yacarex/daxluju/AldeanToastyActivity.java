package com.yacarex.daxluju;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AldeanToastyActivity extends AppCompatActivity {

    private EditText editText;
    private Button toastButton;
    String toastContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aldean_toasty);

        editText = findViewById(R.id.insertText);

        toastButton = findViewById(R.id.toastThis);


        toastButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(AldeanToastyActivity.this, editText.getText(), Toast.LENGTH_SHORT).show();
                
            }
        });


    }
}
