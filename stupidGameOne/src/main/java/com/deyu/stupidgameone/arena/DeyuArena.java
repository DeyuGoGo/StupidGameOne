package com.deyu.stupidgameone.arena;

import android.content.Context;
import android.util.AttributeSet;

import com.deyu.stupidgameone.monster.BaseMonster;
import com.deyu.stupidgameone.monster.MonsterListener;

/**
 * Created by huangeyu on 15/3/24.
 */
public class DeyuArena extends BaseBattleArena {

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

}
