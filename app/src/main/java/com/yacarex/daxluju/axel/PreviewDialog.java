package com.yacarex.daxluju.axel;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

import com.yacarex.daxluju.R;

public class PreviewDialog extends Dialog implements View.OnClickListener {

    Button confirm, cancel;
    ImageView previewImage;
    PreviewInterface listener;
    Bitmap bitmap;

    public PreviewDialog(Context context, Bitmap bitmap, PreviewInterface listener) {
        super(context);
        this.bitmap= bitmap;
        this.listener= listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.preview_dialog);
        initView();
    }

    private void initView() {
        confirm = findViewById(R.id.preview_confirm);
        cancel = findViewById(R.id.preview_cancel);
        previewImage = findViewById(R.id.preview_image);

        previewImage.setImageBitmap(bitmap);

        confirm.setOnClickListener(this);
        cancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.preview_confirm:
                listener.onPreviewConfirm();
                dismiss();
                break;
            case R.id.preview_cancel:
                listener.onPreviewCancel();
                dismiss();
                break;
        }
    }
}
