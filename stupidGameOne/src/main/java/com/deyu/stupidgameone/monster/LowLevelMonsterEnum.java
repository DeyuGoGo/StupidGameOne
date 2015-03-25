package com.deyu.stupidgameone.monster;

/**
 * Created by huangeyu on 15/3/24.
 */
public enum LowLevelMonsterEnum {
    Cockroach(5,1,"Deyu");

    private int HP;
    private int Speed;
    private String Name;

    private LowLevelMonsterEnum(int HP , int Speed , String Name){
        this.HP = HP;
        this.Speed = Speed;
        this.Name = Name;
    }

    public int getHP() {
        return HP;
    }

    public int getSpeed() {
        return Speed;
    }

    public String getName() {
        return Name;
    }
}
