package com.deyu.stupidgameone.arena;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;

import com.deyu.stupidgameone.monster.ArenaMonster;
import com.deyu.stupidgameone.monster.ArenaMonsterCreater;
import com.deyu.stupidgameone.monster.ArenaMonsterFactory;
import com.deyu.stupidgameone.monster.LowLevelMonsterEnum;
import com.deyu.stupidgameone.monster.Monster;
import com.deyu.stupidgameone.monster.MonsterFace;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

/**
 * Created by huangeyu on 15/3/26.
 */
public abstract class BaseBattleArena extends Arena implements BattleArena{
    protected ArenaMonsterCreater mMonsterFactory ;
    protected ArrayList<ArenaMonster> Monsters = new ArrayList<ArenaMonster>();
    protected ArrayList<MonsterFace> mMonsterFaces = new ArrayList<MonsterFace>();
    private CountDownLatch latch ;
    public BaseBattleArena(Context context) {
        super(context);
    }

    public BaseBattleArena(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BaseBattleArena(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void init() {
        super.init();
        mMonsterFactory = new ArenaMonsterFactory(getContext());
    }

    @Override
    public void addLowLevelMonster(LowLevelMonsterEnum lowLevelMonsterEnum) {
        ArenaMonster monster = mMonsterFactory.createArenaMonster(lowLevelMonsterEnum);
        Monsters.add(monster);
        addMonsetFace(monster);
    }

    @Override
    public void start() {
        nonUiHandler.post(startDrawRunable);
    }

    @Override
    public void stop() {

    }

    @Override
    protected SurfaceHolder.Callback getSurfaceHolderCallback() {
        return new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {

            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {

            }
        };
    }

    protected void addMonsetFace(Monster monster){
        int monsterImageRes = monster.getImageRes();
        for(MonsterFace face : mMonsterFaces){
            if(face.faceRes == monsterImageRes)return;
        }
        Bitmap bmp = BitmapFactory.decodeResource(getResources(), monsterImageRes);
        mMonsterFaces.add(new MonsterFace(monsterImageRes , bmp));
    }

    protected MonsterFace getMonsterFace(Monster monster ){
        int monsterImageRes = monster.getImageRes();
        for(MonsterFace face : mMonsterFaces){
            if(face.faceRes == monsterImageRes)return face;
        }
        return null;
    }

    private void drawMonsters(final Canvas canvas){
        latch = new CountDownLatch(Monsters.size());
                    for(final ArenaMonster monster: Monsters){
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                DrawMonster(monster, canvas);
                            }
                        }).start();
                    }
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    private void moveMonsters(){
        for(ArenaMonster monster: Monsters){
            monster.move(ArenaWidth , ArenaHeight);
        }
    }
    private void initMonsters(){
        for(ArenaMonster monster: Monsters){
            initMonsterSize(monster);
            initMonsterLocation(monster);
        }
    }
    private void initMonsterLocation(ArenaMonster monster){
        int x = (int) (Math.random() * (ArenaWidth - monster.getWidth()));
        int y = (int) (Math.random() * (ArenaHeight - monster.getHeight()));
        monster.setLocation(new ArenaLocationInfo(x, y, 1));
    }
    private void initMonsterSize(ArenaMonster monster ){
        MonsterFace f = getMonsterFace(monster);
        monster.setSize(f.getWidth(), f.getHeight());
    }

    private void DrawMonster(ArenaMonster monster, Canvas canvas){
        MonsterFace f = getMonsterFace(monster);
        ArenaLocationInfo location = monster.getLocation();
        Bitmap b = getMoveFace(f, location.getRunWhere());
        canvas.drawBitmap(b , location.getX() , location.getY() ,null );
        latch.countDown();
    }
    private Bitmap getMoveFace(MonsterFace face, int GoToWhere){
        return face.faceBitmap[GoToWhere-1];
    }
    private Runnable startDrawRunable = new Runnable() {
        @Override
        public void run() {
            Canvas c = null ;
            initMonsters();
            try {
                c = holder.lockCanvas();
                synchronized (holder){
                    drawMonsters(c);
                }
            }finally {
                if(c != null)
                    holder.unlockCanvasAndPost(c);
                postDelayed(ContinueRunDrawRanable , 10);
            }
        }
    };
    private Runnable ContinueRunDrawRanable = new Runnable() {
        @Override
        public void run() {
            Canvas c = null ;
            try {
                c = holder.lockCanvas();
                c.drawColor(Color.WHITE);
                synchronized (holder){
                    moveMonsters();
                    drawMonsters(c);
                }
            }finally {
                if(c != null)
                    holder.unlockCanvasAndPost(c);
                postDelayed(this,10);
            }
        }
    };

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.d("DEYU" , "MotionEvent.ACTION_DOWN ");
                Log.d("DEYU" , "event.getRawX() : " + event.getRawX() + " event.getRawY() : " + event.getRawY());
                hit(event.getRawX(), event.getRawY());
                break;
        }
        return super.onTouchEvent(event);
    }


    protected void hit(float x ,float y){
        for(ArenaMonster monster : Monsters){
            boolean beHit = checkHitMonster(x , y , monster);
            if(!beHit)continue;
            Log.d("Deyu"  , "feelHurt(10)");
            monster.feelHurt(10);
        }
    }
    protected boolean checkHitMonster(float x , float y , ArenaMonster monster){
        ArenaLocationInfo l = monster.getLocation();
        int width = monster.getWidth();
        int height = monster.getHeight();
        if(x < l.getX()) return false;
        if(x > (l.getX()+width)) return false;
        if(y < l.getY()) return false;
        if(y > (l.getY()+height))return false;
        return true;
    }

}
