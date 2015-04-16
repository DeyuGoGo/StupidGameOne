package com.deyu.stupidgameone.monster;

import com.deyu.stupidgameone.arena.ArenaLocation;
import com.deyu.stupidgameone.arena.ArenaLocationInfo;
import com.deyu.stupidgameone.motion.Move;

/**
 * Created by huangeyu on 15/3/23.
 */
public abstract class ArenaMonster extends BaseMonster implements ArenaLocation, NoisyMonster {
    protected ArenaLocationInfo location;
    private Move m;

    public ArenaMonster(String name, int HP, int Speed) {
        super(name);
        this.HP = HP;
        this.Speed = Speed;
        m = Move.getInstance();
    }

    public ArenaMonster(String name, int HP, int Speed, int face) {
        this(name, HP, Speed);
        this.FaceImageId = face;

    }

    @Override
    public void move(int ArenaW, int ArenaH) {
        moveGo();
//        Log.d("DEYU" , "ArenaW  : " + ArenaW + "\n" +
//                "ArenaH : " + ArenaH + "\n" +
//                "location.getX() : " + location.getX() + "\n" +
//                "location.getY() : " + location.getY() + "\n" +
//                "location.getRunWhere() " + location.getRunWhere()
//        );
        location.setRunWhere(m.getWhereToGo(ArenaW, ArenaH, location.getX(), location.getY(), Width, Height, location.getRunWhere()));
    }

    private void moveGo() {
        int wherego = location.getRunWhere();
        int x = location.getX();
        int y = location.getY();
        int randomspeed = (int) (Math.random() * Speed);
        if (wherego == 2 || wherego == 3 || wherego == 4) {
            x = x + randomspeed;
        }
        if (wherego == 8 || wherego == 7 || wherego == 6) {
            x = x - randomspeed;
        }
        if (wherego == 8 || wherego == 1 || wherego == 2) {
            y = y - randomspeed;
        }
        if (wherego == 6 || wherego == 5 || wherego == 4) {
            y = y + randomspeed;
        }
        location.setX(x);
        location.setY(y);
    }

    @Override
    public ArenaLocationInfo getLocation() {
        return location;
    }

    @Override
    public void setLocation(ArenaLocationInfo location) {
        this.location = location;
    }

    @Override
    public String say() {
        return getSay();
    }

    @Override
    protected String getSay() {
        return "打我啊笨蛋";
    }
}
