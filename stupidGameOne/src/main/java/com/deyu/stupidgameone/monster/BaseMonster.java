package com.deyu.stupidgameone.monster;

/**
 * Created by huangeyu on 15/3/23.
 */
public abstract class BaseMonster implements Monster{

    protected final String Name;
    protected MonsterListener Listener;
    protected int FaceImageId;
    protected int HP;
    protected int Speed;
    protected int Width;
    protected int Height;
    protected MonsterLocation location;

    public BaseMonster(String name ){
        this.Name = name ;
    }

    public void setListener(MonsterListener listener) {
        Listener = listener;
    }

    protected boolean isAlive(){
        return HP>0;
    }

    protected void onDead(){
        if(Listener!=null)Listener.OnDead(this);
    }

    protected abstract String getSay();

    @Override
    public String say() {
        return getSay();
    }
    @Override
    public int getImageRes() {
        return FaceImageId;
    }
    @Override
    public void setLocation(MonsterLocation location) {
        this.location = location;
    }
    @Override
    public MonsterLocation getLocation() {
        return location;
    }
    @Override
    public void feelHurt(int damage) {
        HP -= damage;
        if(HP<0)HP=0;
    }
    @Override
    public void setSize(int width, int height) {
        this.Width = width;
        this.Height = height;
    }

    @Override
    public int getWidth() {
        return Width;
    }

    @Override
    public int getHeight() {
        return Height;
    }
}
