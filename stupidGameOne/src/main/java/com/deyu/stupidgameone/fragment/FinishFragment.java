package com.deyu.stupidgameone.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.deyu.stupidgameone.DB;
import com.deyu.stupidgameone.GameInfo;
import com.deyu.stupidgameone.MainActivity;
import com.deyu.stupidgameone.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by huangeyu on 15/4/15.
 */
public class FinishFragment extends BaseFragment {

    DB DBH;
    @BindView(R.id.finish_data)
    TextView mDataTextView;
    @BindView(R.id.finish_des)
    TextView mDescribeTextView;
    private int gameSecond;
    private int gamemillinSecond;

    @OnClick(R.id.backbtn)
    public void back() {
        Intent it = new Intent();
        it.setClass(getActivity(), MainActivity.class);
        startActivity(it);
        getActivity().finish();
    }

    @OnClick(R.id.againbtn)
    public void again() {
        changeFragment(new ArenaFragment(), false);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_arena_finish, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
    }


    @Override
    protected void initComponents() {
        DBH = new DB(getActivity());
        DBH.open();
        int Gametime = (int) GameInfo.GameTime;
        if (Gametime < GameInfo.bestTime) {
            DBH.open();
            DBH.updateGameTime(1, Gametime);
            DBH.close();
            GameInfo.highest = Gametime;
        }
        initSecond(GameInfo.GameTime);
    }

    @Override
    protected void initAction() {
    }

    @Override
    protected void initViewWithValue() {
        mDataTextView.setText("" + getTimeDataString());
        mDescribeTextView.setText(getTimeDesString());
    }

    private String getTimeDataString() {
        return gameSecond + "秒" + gamemillinSecond;
    }

    private String getTimeDesString() {
        if (gameSecond < 5) return "幹，\n你作弊吧。";
        if (gameSecond < 10) return "怎麼可能，\n你是何方神聖。";
        if (gameSecond < 15) return "好像很快，\n其實不過就這樣而已。";
        if (gameSecond < 20) return "有點廢。";
        if (gameSecond < 25) return "大概是\n用腳完的速度。";
        if (gameSecond < 30) return "你的手反應速度\n比蝸牛還慢啊。";
        return "痾，廢物";
    }

    private void initSecond(long gameTime) {
        gameSecond = (int) (gameTime / 1000);
        gamemillinSecond = (int) (gameTime % 1000);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
