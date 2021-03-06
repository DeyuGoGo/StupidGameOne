package com.deyu.stupidgameone.arena;

import com.deyu.stupidgameone.monster.LowLevelMonsterEnum;

/**
 * Created by huangeyu on 15/3/24.
 */
public interface BattleArena extends ArenaReporterCenter {

    public void addLowLevelMonster(LowLevelMonsterEnum lowLevelMonsterEnum);

    public void start();

    public void resume();

    public void stop();
}
