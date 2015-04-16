package com.deyu.stupidgameone.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.deyu.stupidgameone.R;

/**
 * Created by huangeyu on 15/3/24.
 */
public abstract class BaseFragment extends Fragment {
    protected FragmentManager mFragmentManager;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
        initComponents();
        initAction();
        initViewWithValue();
    }

    private void init() {
        mFragmentManager = getActivity().getSupportFragmentManager();
    }

    protected abstract void initComponents();

    protected abstract void initAction();

    protected abstract void initViewWithValue();

    protected void changeFragment(Fragment fragment, boolean isNeedBackStack) {
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack so the user can navigate back
        transaction
                .replace(R.id.fragment_container, fragment);
        if (isNeedBackStack) transaction.addToBackStack(null);
        // Commit the transaction
        transaction.commit();
    }
}
