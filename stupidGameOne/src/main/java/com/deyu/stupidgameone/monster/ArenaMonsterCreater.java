package com.deyu.stupidgameone.monster;

/**
 * Created by huangeyu on 15/3/24.
 */
public interface ArenaMonsterCreater extends MonsterCreater{
    public ArenaMonster createArenaMonster(LowLevelMonsterEnum lowLevelMonsterEnum);
}
