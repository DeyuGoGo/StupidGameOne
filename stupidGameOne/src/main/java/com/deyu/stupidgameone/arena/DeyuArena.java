package com.deyu.stupidgameone.arena;

import android.content.Context;
import android.util.AttributeSet;

import com.deyu.stupidgameone.monster.LowLevelMonster;

/**
 * Created by huangeyu on 15/3/24.
 */
public class DeyuArena extends BaseArena implements BattleArena{

    public DeyuArena(Context context) {
        super(context);
    }

    public DeyuArena(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DeyuArena(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void addLowLevelMonster(LowLevelMonster monster) {

    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }
}
