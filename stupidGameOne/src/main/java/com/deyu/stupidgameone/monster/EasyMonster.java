package com.deyu.stupidgameone.monster;

import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by huangeyu on 15/3/23.
 */
public class EasyMonster extends BaseMonster {

    public EasyMonster(String name , int HP , int Speed) {
        super(name);
        this.HP = HP ;
        this.Speed = Speed;
    }

    @Override
    protected ImageView getFace() {
        return null;
    }

    @Override
    protected TextView getSayView() {
        return null;
    }

    @Override
    public String say() {
        return "Hi";
    }


    @Override
    public void feelHurt(int damage) {
        HP -= damage;
        if(HP<0)HP=0;
    }

}
