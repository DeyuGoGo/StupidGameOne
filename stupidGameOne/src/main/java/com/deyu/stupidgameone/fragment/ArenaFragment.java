package com.deyu.stupidgameone.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.deyu.stupidgameone.R;
import com.deyu.stupidgameone.arena.BattleArena;

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
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.inject(view);
    }


    @Override
    protected void initComponents() {
    }

    @Override
    protected void initAction() {

    }

    @Override
    protected void initViewWithValue() {

    }

}
