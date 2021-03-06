package com.deyu.stupidgameone.monster;

import android.graphics.Bitmap;
import android.graphics.Matrix;

/**
 * Created by huangeyu on 15/3/26.
 */
public class MonsterFace {

    public final int faceRes;
    public final Bitmap[] faceBitmap;
    public int width;
    public int height;

    public MonsterFace(int faceRes, Bitmap facebitmap) {
        this.faceRes = faceRes;
        this.faceBitmap = new Bitmap[8];
        this.width = (int) (facebitmap.getWidth() * 0.4);
        this.height = (int) (facebitmap.getHeight() * 0.4);
        initFaces(facebitmap);
    }

    private void initFaces(Bitmap faceBitmap) {
        for (int i = 0; i < 8; i++) {
            Matrix vMatrix = new Matrix();
            vMatrix.setRotate(45 * i);
            Bitmap vB1 = Bitmap.createScaledBitmap(faceBitmap, width, height, true);
            Bitmap vB2 = Bitmap.createBitmap(vB1
                    , 0
                    , 0
                    , vB1.getWidth()   // 寬度
                    , vB1.getHeight()  // 高度
                    , vMatrix
                    , true
            );
            this.faceBitmap[i] = vB2;
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
