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
public abstract class BaseArena extends RelativeLayout implements BattleArena , ArenaReporterCenter{

    protected ArrayList<Monster> Monsters = new ArrayList<Monster>();
    protected MonsterFactory mMonsterFactory ;
    protected ArrayList<ArenaReporter> mArenaReporters = new ArrayList<ArenaReporter>();
    protected int ArenaHeight , ArenaWidth;

    public BaseArena(Context context) {
        super(context);
        init();
    }

    public BaseArena(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BaseArena(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }
    protected void init(){
        mMonsterFactory = new MonsterFactory(getContext());
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        ArenaHeight = h;
        ArenaWidth = w;
    }

    @Override
    public void RegisterRepoter(ArenaReporter reporter) {
        mArenaReporters.add(reporter);
    }

    @Override
    public void UnRegisterRepoter(ArenaReporter reporter) {
        mArenaReporters.remove(reporter);
    }

    protected void checkGamePoint(){

    }


}
