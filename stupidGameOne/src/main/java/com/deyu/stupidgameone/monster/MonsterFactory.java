package com.deyu.stupidgameone.monster;

import android.content.Context;

import com.deyu.stupidgameone.exception.NoMonsterException;

/**
 * Created by huangeyu on 15/3/24.
 */
public class MonsterFactory implements MonsterCreater{

    protected Context mContext;
    public MonsterFactory(Context context){
        this.mContext = context;
    }
    @Override
    public Monster createLowLevelMonster(LowLevelMonsterEnum lowLevelMonsterEnum) {
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
