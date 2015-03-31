package com.deyu.stupidgameone.arena;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.SurfaceHolder;

import com.deyu.stupidgameone.monster.LowLevelMonsterEnum;
import com.deyu.stupidgameone.monster.Monster;
import com.deyu.stupidgameone.monster.MonsterCreater;
import com.deyu.stupidgameone.monster.MonsterFace;
import com.deyu.stupidgameone.monster.MonsterFactory;
import com.deyu.stupidgameone.monster.MonsterLocation;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

/**
 * Created by huangeyu on 15/3/26.
 */
public abstract class BaseBattleArena extends Arena implements BattleArena{
    protected MonsterCreater mMonsterFactory ;
    protected ArrayList<Monster> Monsters = new ArrayList<Monster>();
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
        mMonsterFactory = new MonsterFactory(getContext());
    }

    @Override
    public void addLowLevelMonster(LowLevelMonsterEnum lowLevelMonsterEnum) {
        Monster monster = mMonsterFactory.createLowLevelMonster(lowLevelMonsterEnum);
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
                    for(final Monster monster: Monsters){
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
        for(Monster monster: Monsters){
            monster.move(ArenaWidth , ArenaHeight);
        }
    }
    private void initMonsters(){
        for(Monster monster: Monsters){
            initMonsterSize(monster);
            initMonsterLocation(monster);
        }
    }
    private void initMonsterLocation(Monster monster){
        int x = (int) (Math.random() * (ArenaWidth - monster.getWidth()));
        int y = (int) (Math.random() * (ArenaHeight - monster.getHeight()));
        monster.setLocation(new MonsterLocation(x ,y ,1));
    }
    private void initMonsterSize(Monster monster ){
        MonsterFace f = getMonsterFace(monster);
        monster.setSize(f.getWidth(),f.getHeight());
    }

    private void DrawMonster(Monster monster, Canvas canvas){
        MonsterFace f = getMonsterFace(monster);
        MonsterLocation location = monster.getLocation();
        Bitmap b = getMoveFace(f , location.getRunWhere());
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
                postDelayed(ContinueRunDrawRanable , 2);
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
                postDelayed(this,2);
            }
        }
    };
}
