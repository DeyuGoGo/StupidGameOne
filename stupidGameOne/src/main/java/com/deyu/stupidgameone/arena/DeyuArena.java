package com.deyu.stupidgameone.arena;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.RelativeLayout;

import com.deyu.stupidgameone.monster.BaseMonster;
import com.deyu.stupidgameone.monster.LowLevelMonsterEnum;
import com.deyu.stupidgameone.monster.Monster;
import com.deyu.stupidgameone.monster.MonsterListener;

/**
 * Created by huangeyu on 15/3/24.
 */
public class DeyuArena extends BaseArena {

    private MonsterListener Listener = new MonsterListener() {
        @Override
        public void OnDead(BaseMonster whoDead) {
            Monsters.remove(whoDead);
        }
    };

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
    public void addLowLevelMonster(LowLevelMonsterEnum lowLevelMonsterEnum) {
        Monster monster = mMonsterFactory.createLowLevelMonster(lowLevelMonsterEnum);
        Monsters.add(monster);
    }

    @Override
    public void start() {
        initMonsters();
    }

    @Override
    public void stop() {

    }
    private void initMonsters(){
        for(Monster monster: Monsters){
            initMonster(monster);
        }
    }
    private void initMonster(Monster monster){
        addView(monster.getImage());
        monster.getImage().getLayoutParams().width=50;
        monster.getImage().getLayoutParams().height=50;
        Log.d("DEYU "  , "ArenaWidth : " + ArenaWidth + " ArenaHeight : " + ArenaHeight);
        int x = (int) (Math.random() * (ArenaWidth - monster.getImage().getMeasuredWidth()));
        int y = (int) (Math.random() * (ArenaHeight - monster.getImage().getMeasuredHeight()));
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams)monster.getImage().getLayoutParams();
        layoutParams.leftMargin = x;
        layoutParams.topMargin = y;
    }

}
