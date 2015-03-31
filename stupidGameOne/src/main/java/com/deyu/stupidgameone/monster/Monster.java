package com.deyu.stupidgameone.monster;

/**
 * Created by huangeyu on 15/3/23.
 */
public interface Monster {
    public String say();
    public void feelHurt(int damage);
    public void move(int ArenaW , int ArenaH);
    public void setSize(int width , int height);
    public int getWidth();
    public int getHeight();
    public void setLocation(MonsterLocation location);
    public MonsterLocation getLocation();
    public int getImageRes();
}
