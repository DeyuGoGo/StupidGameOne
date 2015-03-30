package com.deyu.stupidgameone.monster;

import android.util.Log;

import com.deyu.stupidgameone.motion.Move;

/**
 * Created by huangeyu on 15/3/23.
 */
public class EasyMonster extends BaseMonster {
    private Move m = new Move();

    public EasyMonster(String name , int HP , int Speed) {
        super(name);
        this.HP = HP ;
        this.Speed = Speed;
    }
    public EasyMonster(String name , int HP , int Speed , int face) {
        this(name,HP,Speed);
        this.FaceImageId = face;

    }

    @Override
    protected String getSay() {
        return null;
    }

    @Override
    public void move(int ArenaW , int ArenaH) {
        moveGo();
        Log.d("DEYU" , "ArenaW  : " + ArenaW + "\n" +
                "ArenaH : " + ArenaH + "\n" +
                "location.getX() : " + location.getX() + "\n" +
                "location.getY() : " + location.getY() + "\n" +
                "location.getRunWhere() " + location.getRunWhere()
        );
        location.setRunWhere(m.getWhereToGo(ArenaW , ArenaH, location.getX() , location.getY() , Width , Height,location.getRunWhere()));
    }
    private void moveGo(){
        int wherego = location.getRunWhere();
        int x = location.getX();
        int y = location.getY();
        if (wherego == 2 || wherego == 3 || wherego == 4) {
            x = x + Speed;
        }
        if (wherego == 8 || wherego == 7 || wherego == 6) {
            x = x - Speed;
        }
        if (wherego == 8 || wherego == 1 || wherego == 2) {
            y = y - Speed;
        }
        if (wherego == 6 || wherego == 5 || wherego == 4) {
            y = y + Speed;
        }
        location.setX(x);
        location.setY(y);
    }
}
