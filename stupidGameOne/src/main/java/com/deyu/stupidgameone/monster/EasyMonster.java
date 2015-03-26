package com.deyu.stupidgameone.monster;

/**
 * Created by huangeyu on 15/3/23.
 */
public class EasyMonster extends BaseMonster {

    public EasyMonster(String name , int HP , int Speed) {
        super(name);
        this.HP = HP ;
        this.Speed = Speed;
    }
    public EasyMonster(String name , int HP , int Speed , int face) {
        this(name,HP,Speed);
        this.FaceImageId = face;

    }

    @Override
    protected String getSay() {
        return null;
    }

    @Override
    public void feelHurt(int damage) {
        HP -= damage;
        if(HP<0)HP=0;
    }

}
