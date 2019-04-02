package com.yacarex.daxluju.axel;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.yacarex.daxluju.R;
import com.yacarex.daxluju.models.axel.PixelArtModel;

public class PreviewDialog extends Dialog implements View.OnClickListener {

    private Button confirm, cancel;
    private ImageView previewImage;
    private TextView title;
    private EditText inputArea;
    PreviewInterface listener;
    Bitmap bitmap;
    PixelArtModel pixelArtModel;

    public PreviewDialog(Context context, Bitmap bitmap, PixelArtModel pixelArtModel, PreviewInterface listener) {
        super(context);
        this.bitmap = bitmap;
        this.pixelArtModel= pixelArtModel;
        this.listener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.preview_dialog);
        initView();
    }

    private void initView() {
        title = findViewById(R.id.input_title_text);
        inputArea = findViewById(R.id.input_title_edit_text);
        confirm = findViewById(R.id.preview_confirm);
        cancel = findViewById(R.id.preview_cancel);
        previewImage = findViewById(R.id.preview_image);

        previewImage.setImageBitmap(bitmap);

        confirm.setOnClickListener(this);
        cancel.setOnClickListener(this);

        inputArea.setText(pixelArtModel.getName());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.preview_confirm:
                listener.onPreviewConfirm(inputArea.getText().toString());
                dismiss();
                break;
            case R.id.preview_cancel:
                listener.onPreviewCancel();
                dismiss();
                break;
        }
    }
}
