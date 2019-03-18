package com.yacarex.daxluju;

import android.media.Image;
import android.widget.ImageView;

public class ImageListModel {

    private String itemThumbnail, itemTitle, itemDesc;

    public ImageListModel(String itemThumbnail, String itemTitle, String itemDescription){

        this.itemThumbnail  = itemThumbnail;
        this.itemTitle      = itemTitle;
        this.itemDesc       = itemDescription;

    }


    public String getItemThumbnail() {
        return itemThumbnail;
    }

    public void setItemThumbnail(String itemThumbnail) {
        this.itemThumbnail = itemThumbnail;
    }

    public String getItemTitle() {
        return itemTitle;
    }

    public void setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }
}
