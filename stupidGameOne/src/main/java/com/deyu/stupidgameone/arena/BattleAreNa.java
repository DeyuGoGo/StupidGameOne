package com.deyu.stupidgameone.arena;

import com.deyu.stupidgameone.monster.LowLevelMonster;

/**
 * Created by huangeyu on 15/3/24.
 */
public interface BattleArena {
    public void addLowLevelMonster(LowLevelMonster monster);
    public void start();
    public void stop();

}
