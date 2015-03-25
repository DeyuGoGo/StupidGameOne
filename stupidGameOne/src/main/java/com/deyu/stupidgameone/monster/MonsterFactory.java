package com.deyu.stupidgameone.monster;

import com.deyu.stupidgameone.exception.NoMonsterException;

/**
 * Created by huangeyu on 15/3/24.
 */
public class MonsterFactory implements MonsterCreater{

    @Override
    public Monster createLowLevelMonster(LowLevelMonsterEnum lowLevelMonsterEnum) {

        String name = lowLevelMonsterEnum.getName();
        int HP = lowLevelMonsterEnum.getHP();
        int Speed = lowLevelMonsterEnum.getSpeed();

        if(lowLevelMonsterEnum==null) {
            throw new NoMonsterException();
        }
            return new EasyMonster(name,HP,Speed);
    }
}
