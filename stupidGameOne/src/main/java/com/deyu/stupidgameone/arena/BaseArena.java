package com.deyu.stupidgameone.arena;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.deyu.stupidgameone.monster.LowLevelMonster;

import java.util.ArrayList;

/**
 * Created by huangeyu on 15/3/24.
 */
public abstract class BaseArena extends RelativeLayout{

    protected ArrayList<LowLevelMonster> Monsters = new ArrayList<LowLevelMonster>();

    public BaseArena(Context context) {
        super(context);
    }

    public BaseArena(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BaseArena(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

}
