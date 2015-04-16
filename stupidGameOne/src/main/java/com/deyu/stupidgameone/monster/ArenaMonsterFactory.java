package com.deyu.stupidgameone.monster;

import android.content.Context;

import com.deyu.stupidgameone.exception.NoMonsterException;

/**
 * Created by huangeyu on 15/3/31.
 */
public class ArenaMonsterFactory extends MonsterFactory implements ArenaMonsterCreater{
    public ArenaMonsterFactory(Context context) {
        super(context);
    }
    @Override
    public ArenaMonster createArenaMonster(LowLevelMonsterEnum lowLevelMonsterEnum) {
        String name = lowLevelMonsterEnum.getName();
        int HP = lowLevelMonsterEnum.getHP();
        int Speed = lowLevelMonsterEnum.getSpeed();
        final int FaceRes = lowLevelMonsterEnum.getFaceRes();
        if(lowLevelMonsterEnum==null) {
            throw new NoMonsterException();
        }
        return new EasyMonster(name,HP,Speed,FaceRes);
    }
}
