package com.deyu.stupidgameone.motion;

/**
 * Created by huangeyu on 15/3/28.
 */
public class Move {

    private static Move ourInstance = new Move();

    private Move() {
    }

    public static Move getInstance() {
        return ourInstance;
    }

    public native int init();

    public native int getWhereToGo(int w, int h, int x, int y, int imgw, int imgh, int whereToGo);

    static {
        System.loadLibrary("Move");
    }
}
