package com.deyu.stupidgameone.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

/**
 * Created by huangeyu on 15/3/24.
 */
public abstract class BaseFragment extends Fragment{

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    protected abstract void initComponents();
    protected abstract void initAction();
    protected abstract void initViewWithValue();
}
