package com.yacarex.daxluju.axel;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.yacarex.daxluju.R;

import top.defaults.colorpicker.ColorPickerPopup;

public class AxelPixelDrawActivity extends AppCompatActivity implements View.OnClickListener {

    FrameLayout squareFrame;
    Button clearView, undoAction, gridButton;
    ImageView colorPicker;
    AxelPixelGridView pixelGridView;
    int pixelRoot= 16;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_axel_pixel_draw);
        initViews();
        initComponents();
    }

    public void initComponents() {
        pixelGridView = new AxelPixelGridView(this);
        pixelGridView.setNumColumns(pixelRoot);
        pixelGridView.setNumRows(pixelRoot);
        squareFrame.addView(pixelGridView);

        clearView.setOnClickListener(this);
        undoAction.setOnClickListener(this);
        gridButton.setOnClickListener(this);
        colorPicker.setOnClickListener(this);
    }

    public void initViews() {
        squareFrame = findViewById(R.id.squared_frame);
        clearView = findViewById(R.id.clear);
        undoAction = findViewById(R.id.undo);
        gridButton= findViewById(R.id.show_grid);
        colorPicker = findViewById(R.id.color_picker);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.clear:
                pixelGridView = new AxelPixelGridView(this);
                pixelGridView.setNumColumns(pixelRoot);
                pixelGridView.setNumRows(pixelRoot);
                squareFrame.removeAllViews();
                squareFrame.addView(pixelGridView);
                break;
            case R.id.undo:
                pixelGridView.undo();
                break;
            case R.id.show_grid:
                pixelGridView.toggleGrid();
                break;
            case  R.id.color_picker:
                new ColorPickerPopup.Builder(this)
                        .initialColor(Color.RED) // Set initial color
                        .enableBrightness(true) // Enable brightness slider or not
                        .enableAlpha(true) // Enable alpha slider or not
                        .okTitle("Choose")
                        .cancelTitle("Cancel")
                        .showIndicator(true)
                        .showValue(true)
                        .build()
                        .show(squareFrame, new ColorPickerPopup.ColorPickerObserver() {
                            @Override
                            public void onColorPicked(int color) {
                                colorPicker.setColorFilter(color);
                                pixelGridView.setPaint(color);
                            }
                        });

                break;
        }
    }

}
