package com.deyu.stupidgameone.arena;

/**
 * Created by huangeyu on 15/3/31.
 */
public class ArenaLocationInfo {
    int x;
    int y;
    int RunWhere;

    public ArenaLocationInfo(int x, int y, int RunWhere) {
        this.x = x;
        this.y = y;
        this.RunWhere = RunWhere;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getRunWhere() {
        return RunWhere;
    }

    public void setRunWhere(int runWhere) {
        RunWhere = runWhere;
    }
}
