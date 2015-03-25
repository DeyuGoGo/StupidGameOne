package com.deyu.stupidgameone.monster;

import com.deyu.stupidgameone.R;

/**
 * Created by huangeyu on 15/3/24.
 */
public enum LowLevelMonsterEnum {
    Cockroach(5,1,R.drawable.ic1,"Deyu");

    private int HP;
    private int Speed;
    private int FaceRes;
    private String Name;

    private LowLevelMonsterEnum(int HP , int Speed ,int FaceRes ,  String Name){
        this.HP = HP;
        this.Speed = Speed;
        this.FaceRes = FaceRes;
        this.Name = Name;
    }

    public int getHP() {
        return HP;
    }

    public int getSpeed() {
        return Speed;
    }

    public int getFaceRes() {
        return FaceRes;
    }

    public String getName() {
        return Name;
    }
}
