package com.deyu.stupidgameone.monster;

import android.widget.ImageView;

/**
 * Created by huangeyu on 15/3/23.
 */
public interface Monster {
    public String say();
    public void feelHurt(int damage);
    public ImageView getImage();
}
