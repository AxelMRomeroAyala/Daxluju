package com.yacarex.daxluju.axel;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.yacarex.daxluju.R;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

import top.defaults.colorpicker.ColorPickerPopup;

public class AxelPixelDrawActivity extends AppCompatActivity implements PreviewInterface, View.OnClickListener {

    FrameLayout squareFrame;
    Button clearView, undoAction, gridButton, saveButton;
    ImageView colorPicker, qCol1, qCol2, qCol3, qCol4, qCol5;
    ArrayList<Integer> quickColorList = new ArrayList<>();
    ArrayList<ImageView> quicColorViews = new ArrayList<>();
    AxelPixelGridView pixelGridView;
    int pixelRoot = 16;
    int colorPicked = -1;

    public static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 123;

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

        quicColorViews.add(qCol1);
        quicColorViews.add(qCol2);
        quicColorViews.add(qCol3);
        quicColorViews.add(qCol4);
        quicColorViews.add(qCol5);

        for (ImageView view : quicColorViews) {
            view.setOnClickListener(this);
        }

        saveButton.setOnClickListener(this);
    }

    public void initViews() {
        squareFrame = findViewById(R.id.squared_frame);
        clearView = findViewById(R.id.clear);
        undoAction = findViewById(R.id.undo);
        gridButton = findViewById(R.id.show_grid);
        colorPicker = findViewById(R.id.color_picker);
        qCol1 = findViewById(R.id.quick_color_1);
        qCol2 = findViewById(R.id.quick_color_2);
        qCol3 = findViewById(R.id.quick_color_3);
        qCol4 = findViewById(R.id.quick_color_4);
        qCol5 = findViewById(R.id.quick_color_5);
        saveButton = findViewById(R.id.save_pixel_art);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.clear:
                pixelGridView = new AxelPixelGridView(this);
                pixelGridView.setNumColumns(pixelRoot);
                pixelGridView.setNumRows(pixelRoot);
                if (colorPicked != -1) {
                    pixelGridView.setPaint(colorPicked);
                }
                squareFrame.removeAllViews();
                squareFrame.addView(pixelGridView);
                break;
            case R.id.undo:
                pixelGridView.undo();
                break;
            case R.id.show_grid:
                pixelGridView.toggleGrid();
                break;
            case R.id.color_picker:
                new ColorPickerPopup.Builder(this)
                        .initialColor(Color.RED) // Set initial color
                        .okTitle("Choose")
                        .cancelTitle("Cancel")
                        .showIndicator(true)
                        .showValue(true)
                        .build()
                        .show(squareFrame, new ColorPickerPopup.ColorPickerObserver() {
                            @Override
                            public void onColorPicked(int color) {
                                colorPicked = color;
                                colorPicker.setColorFilter(color);
                                pixelGridView.setPaint(color);
                                manageQuickColor(color);
                            }
                        });
                break;
            case R.id.save_pixel_art:
                showDialog();
                break;
            default:
                colorPicker.setColorFilter((int) view.getTag());
                pixelGridView.setPaint((int) view.getTag());
                break;
        }
    }

    public void manageQuickColor(int color) {

        quickColorList.add(0, color);

        if (quickColorList.size() > 5) {
            quickColorList.remove(5);
        }

        for (int x = 0; x < quickColorList.size(); x++) {
            quicColorViews.get(x).setColorFilter(quickColorList.get(x));
            quicColorViews.get(x).setTag(quickColorList.get(x));
        }

    }

    private void saveImage(Bitmap finalBitmap, String image_name) {

        String root = Environment.getExternalStorageDirectory().toString();
        File myDir = new File(root);
        myDir.mkdirs();
        String fname = "Image-" + image_name + ".jpg";
        File file = new File(myDir, fname);
        if (file.exists()) file.delete();
        Log.i("LOAD", root + fname);
        try {
            FileOutputStream out = new FileOutputStream(file);
            finalBitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_CONTACTS: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    showDialog();

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(getBaseContext(), "Permission Denied", Toast.LENGTH_SHORT).show();
                }
            }

        }
    }

    public void showDialog() {

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            // No explanation needed, we can request the permission.

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    MY_PERMISSIONS_REQUEST_READ_CONTACTS);

            // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
            // app-defined int constant. The callback method gets the
            // result of the request.

        } else {
            PreviewDialog previewDialog = new PreviewDialog(AxelPixelDrawActivity.this, pixelGridView.createBitmap(), this);
            previewDialog.show();
        }

    }

    @Override
    public void onPreviewConfirm() {
        saveImage(pixelGridView.createBitmap(), "GENERIC NAME");
    }

    @Override
    public void onPreviewCancel() {

    }

}
