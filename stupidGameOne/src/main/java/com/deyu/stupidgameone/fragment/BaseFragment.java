package com.deyu.stupidgameone.fragment;

import android.support.v4.app.Fragment;

/**
 * Created by huangeyu on 15/3/24.
 */
public abstract class BaseFragment extends Fragment{

    protected abstract void initComponents();
    protected abstract void initAction();
    protected abstract void initViewWithValue();
}
