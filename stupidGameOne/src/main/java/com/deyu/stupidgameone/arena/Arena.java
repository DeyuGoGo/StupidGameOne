package com.deyu.stupidgameone.arena;

import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;

/**
 * Created by huangeyu on 15/3/24.
 */
public abstract class Arena extends SurfaceView implements ArenaReporterCenter{

    protected int ArenaHeight , ArenaWidth;
    protected ArrayList<ArenaReporter> mArenaReporters = new ArrayList<ArenaReporter>();
    protected SurfaceHolder holder;
    protected HandlerThread mHandlerThread  = null ;
    protected Handler nonUiHandler = null;

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
        initNonUiThread();
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

    protected void win(){
        for(ArenaReporter ar : mArenaReporters){
            ar.Win();;
        }
    }

    protected void lose(){
        for(ArenaReporter ar : mArenaReporters){
            ar.lose();
        }
    }

    protected void initNonUiThread(){
        if(mHandlerThread == null){
            mHandlerThread = new HandlerThread(getClass().getSimpleName()+"DEYU");
            mHandlerThread.start();
        }
        if(nonUiHandler==null) nonUiHandler = new Handler(mHandlerThread.getLooper());
    }

    protected void closenonUIThread(){
        if(nonUiHandler!= null) {
            nonUiHandler = null;
        }
        if(mHandlerThread != null) {
            mHandlerThread.interrupt();
            mHandlerThread = null;
        }
    }
}
