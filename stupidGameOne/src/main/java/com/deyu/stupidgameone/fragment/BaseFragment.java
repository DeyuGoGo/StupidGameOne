package com.deyu.stupidgameone.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import com.deyu.stupidgameone.R;
import com.deyu.stupidgameone.arena.BattleArena;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by huangeyu on 15/3/24.
 */
public abstract class BaseFragment extends Fragment{
    @InjectView(R.id.arena) BattleArena mBattleArena;
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.inject(view);
    }

    protected abstract void initComponents();
    protected abstract void initAction();
    protected abstract void initViewWithValue();
}
