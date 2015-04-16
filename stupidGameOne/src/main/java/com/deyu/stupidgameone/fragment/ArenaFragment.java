package com.deyu.stupidgameone.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.deyu.stupidgameone.GameInfo;
import com.deyu.stupidgameone.R;
import com.deyu.stupidgameone.arena.ArenaReporter;
import com.deyu.stupidgameone.arena.BattleArena;
import com.deyu.stupidgameone.monster.LowLevelMonsterEnum;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by huangeyu on 15/3/24.
 */
public class ArenaFragment extends BaseFragment implements ArenaReporter {

    @InjectView(R.id.arena)
    BattleArena mBattleArena;
    @InjectView(R.id.test_text_view1)
    TextView testTextView;
    private long GameStartTime = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_arena, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.inject(this, view);
    }


    @Override
    protected void initComponents() {
        for (int i = 0; i < 20; i++) {
            mBattleArena.addLowLevelMonster(LowLevelMonsterEnum.Cockroach);
        }
        mBattleArena.RegisterRepoter(this);
    }

    @Override
    protected void initAction() {
        gamebegin();
    }

    @Override
    protected void initViewWithValue() {

    }

    private void gamebegin() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("你能不能快點～");
        builder.setTitle("用點擊消滅罪惡");
        builder.setPositiveButton("準備好了",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mBattleArena.start();
                        GameStartTime = System.currentTimeMillis();
                        dialog.dismiss();
                    }
                });
        builder.setCancelable(false);
        builder.create().show();
    }

    @Override
    public void Win() {
        GameInfo.GameTime = System.currentTimeMillis() - GameStartTime;
        changeFragment(new FinishFragment(), false);
    }

    @Override
    public void lose() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mBattleArena.stop();
        mBattleArena.UnRegisterRepoter(this);
    }
}
