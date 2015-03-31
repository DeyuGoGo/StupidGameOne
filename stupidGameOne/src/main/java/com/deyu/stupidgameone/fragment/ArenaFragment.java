package com.deyu.stupidgameone.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.deyu.stupidgameone.R;
import com.deyu.stupidgameone.arena.BattleArena;
import com.deyu.stupidgameone.monster.LowLevelMonsterEnum;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by huangeyu on 15/3/24.
 */
public class ArenaFragment extends BaseFragment{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_arena, container, false);
        return rootView;
    }

    @InjectView(R.id.arena) BattleArena mBattleArena;
    @InjectView(R.id.test_text_view1) TextView testTextView;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.inject(this ,view);
//        mBattleArena = (BattleArena)view.findViewById(R.id.arena);
        initComponents();
        initAction();
        initViewWithValue();
    }


    @Override
    protected void initComponents() {
        for(int i = 0 ; i < 10 ; i++){
            mBattleArena.addLowLevelMonster(LowLevelMonsterEnum.Cockroach);
        }
    }

    @Override
    protected void initAction() {
        Handler oo = new Handler();
        oo.postDelayed(new Runnable() {
            @Override
            public void run() {
                mBattleArena.start();

            }
        },2000);
    }

    @Override
    protected void initViewWithValue() {

    }

}
