package com.deyu.stupidgameone.monster;

import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by huangeyu on 15/3/23.
 */
public abstract class BaseMonster implements Monster{
    protected final String Name;
    protected ImageView FaceImageView;
    protected TextView SayTextView ;
    protected MonsterListener Listener;
    protected int HP;
    protected int Speed;
    public BaseMonster(String name ){
        this.Name = name ;
        this.FaceImageView = getFace();
        this.SayTextView = getSayView();
    }

    protected abstract ImageView getFace();
    protected abstract TextView getSayView();

    public void setListener(MonsterListener listener) {
        Listener = listener;
    }

    protected boolean isAlive(){
        return HP>0;
    }

    protected void onDead(){
        if(Listener!=null)Listener.OnDead(this);
    }

    @Override
    public ImageView getImage() {
        return FaceImageView;
    }
}
