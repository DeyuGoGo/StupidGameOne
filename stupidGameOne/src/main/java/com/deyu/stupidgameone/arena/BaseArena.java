package com.deyu.stupidgameone.arena;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.deyu.stupidgameone.monster.Monster;
import com.deyu.stupidgameone.monster.MonsterFactory;

import java.util.ArrayList;

/**
 * Created by huangeyu on 15/3/24.
 */
public abstract class BaseArena extends RelativeLayout{

    protected ArrayList<Monster> Monsters = new ArrayList<Monster>();
    protected MonsterFactory mMonsterFactory = new MonsterFactory();

    public BaseArena(Context context) {
        super(context);
    }

    public BaseArena(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BaseArena(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    protected void checkGamePoint(){

    }

}
