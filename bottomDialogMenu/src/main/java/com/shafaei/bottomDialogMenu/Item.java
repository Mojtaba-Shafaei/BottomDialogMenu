package com.shafaei.bottomDialogMenu;

import android.graphics.drawable.Drawable;

/**
 * Created by mojtaba on 12/31/16.
 */

public class Item {
    private int id;
    private String title;
    private Drawable icon;

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    public Item(int id, String title, Drawable icon) {
        this.id = id;
        this.title = title;
        this.icon = icon;
    }

    public Item() {
    }

}
