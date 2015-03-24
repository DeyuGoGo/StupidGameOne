package com.deyu.stupidgameone.monster;

import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by huangeyu on 15/3/23.
 */
public abstract class BaseMonster {
    protected final String Name;
    protected ImageView FaceImageView;
    protected TextView SayTextView ;
    protected MonsterListener Listener;
    protected int HP;
    protected int Speed;
    public BaseMonster(String name , MonsterListener listener){
        this.Name = name ;
        this.Listener = listener;
    }

    protected abstract void getFace(ImageView imageView);

    protected abstract void getSayView(TextView textView);

    protected boolean isAlive(){
        return HP>0;
    }

    protected void onDead(){
        if(Listener!=null)Listener.OnDead();
    }
}
