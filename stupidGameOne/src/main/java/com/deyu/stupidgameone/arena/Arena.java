package com.deyu.stupidgameone.arena;

import android.content.Context;
import android.graphics.PixelFormat;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;

/**
 * Created by huangeyu on 15/3/24.
 */
public abstract class Arena extends SurfaceView implements BattleArena , ArenaReporterCenter{

    protected int ArenaHeight , ArenaWidth;
    protected ArrayList<ArenaReporter> mArenaReporters = new ArrayList<ArenaReporter>();
    protected SurfaceHolder holder;

    public Arena(Context context) {
        super(context);
        init();
    }

    public Arena(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public Arena(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }
    protected void init(){
        setZOrderOnTop(true);    // necessary
        holder = getHolder();
        holder.addCallback(getSurfaceHolderCallback());
        holder.setFormat(PixelFormat.TRANSPARENT);
    }

    protected abstract SurfaceHolder.Callback getSurfaceHolderCallback();

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        ArenaHeight = h;
        ArenaWidth = w;
    }

    @Override
    public void RegisterRepoter(ArenaReporter reporter) {
        mArenaReporters.add(reporter);
    }

    @Override
    public void UnRegisterRepoter(ArenaReporter reporter) {
        mArenaReporters.remove(reporter);
    }

}
