package com.deyu.stupidgameone.arena;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;

import com.deyu.stupidgameone.monster.LowLevelMonsterEnum;
import com.deyu.stupidgameone.monster.Monster;
import com.deyu.stupidgameone.monster.MonsterCreater;
import com.deyu.stupidgameone.monster.MonsterFace;
import com.deyu.stupidgameone.monster.MonsterFactory;

import java.util.ArrayList;

/**
 * Created by huangeyu on 15/3/26.
 */
public abstract class BaseBattleArena extends Arena implements BattleArena{
    protected MonsterCreater mMonsterFactory ;
    protected ArrayList<Monster> Monsters = new ArrayList<Monster>();
    protected ArrayList<MonsterFace> mMonsterFaces = new ArrayList<MonsterFace>();

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

    protected Bitmap getMonsterFace(Monster monster){
        int monsterImageRes = monster.getImageRes();
        for(MonsterFace face : mMonsterFaces){
            if(face.faceRes == monsterImageRes)return face.faceBitmap;
        }
        return null;
    }

    private void drawMonsters(Canvas canvas){
        for(Monster monster: Monsters){
            drawMonster(monster, canvas);
        }
    }
    private void drawMonster(Monster monster , Canvas canvas){
        Bitmap b = getMonsterFace(monster);
        Log.d("DEYU ", "ArenaWidth : " + ArenaWidth + " ArenaHeight : " + ArenaHeight);
        int x = (int) (Math.random() * (ArenaWidth - b.getWidth()));
        int y = (int) (Math.random() * (ArenaHeight - b.getHeight()));
        canvas.drawBitmap(b , x , y ,null );
    }

    private void startDraw(){
        Canvas c = null ;
        try {
            c = holder.lockCanvas();
            synchronized (holder){
                draw(c);
            }
        }finally {
            if(c != null)
             holder.unlockCanvasAndPost(c);
        }

    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        drawMonsters(canvas);
    }
    private Runnable startDrawRunable = new Runnable() {
        @Override
        public void run() {
            Canvas c = null ;
            try {
                c = holder.lockCanvas();
                synchronized (holder){
                    draw(c);
                }
            }finally {
                if(c != null)
                    holder.unlockCanvasAndPost(c);
            }
        }
    };

}
