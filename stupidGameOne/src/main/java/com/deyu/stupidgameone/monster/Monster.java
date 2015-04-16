package com.deyu.stupidgameone.monster;

/**
 * Created by huangeyu on 15/3/23.
 */
public interface Monster {
    public void feelHurt(int damage);
    public void move(int ArenaW , int ArenaH);
    public int getImageRes();
}
