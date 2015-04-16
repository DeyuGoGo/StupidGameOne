package com.deyu.stupidgameone;




import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.deyu.stupidgameone.activity.ArenaActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class MainActivity extends Activity {

    Button StartButton,highButton,exitButton ;
    Context mContext;
    String usernameString;
    static boolean firsttime = true;
    TextView TV1;
    DB DBH;
    @InjectView(R.id.btn_arena) Button ArenaBtn;
    @OnClick(R.id.btn_arena)
    public void gotoArena(){
        Intent it= new Intent();
        GameInfo.GameSpeed=1;
        GameInfo.GameStage=0;
        GameInfo.GameTime=GameInfo.GameDefaultTime;
        it.setClass(MainActivity.this, ArenaActivity.class);
        startActivity(it);
        finish();
    }

    public native int getWhereDDGo(int w , int h , int x ,int y ,int imgw,int imgh);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        mContext =this;
        DBH = new DB(mContext);
        firsttime();
        findXmlView();
        username();
        setButtonClick();
    }

    private void firsttime(){
        DBH.open();
        Cursor ccc =  DBH.get(1);
        int i =  ccc.getInt(1);
        int i2 =  ccc.getInt(2) == 0 ? (int)GameInfo.GameDefaultTime : ccc.getInt(2);
        GameInfo.highest = i;
        GameInfo.bestTime = i2;
        DBH.close();
    }
    void username(){
        switch ((int)(Math.random() * 15 + 1)) {
            case 3:
                usernameString = "笨蛋";
                break;
            case 4:
                usernameString = "菜瓜";
                break;
            case 5:
                usernameString = "傳說";
                break;
            default:
                usernameString = "勇者";
                break;
        }
        TV1.setText(usernameString+"，早安你好");
    }


    void findXmlView(){
        StartButton = (Button)findViewById(R.id.startbutton);
        highButton = (Button)findViewById(R.id.highBtn);
        exitButton=(Button)findViewById(R.id.exitbtn);
        TV1=(TextView)findViewById(R.id.textView1);
        if(GameInfo.highest > 6) ArenaBtn.setVisibility(View.VISIBLE);
    }
    void setButtonClick(){
        StartButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent it= new Intent();
                GameInfo.GameSpeed=1;
                GameInfo.GameStage=0;
                it.setClass(MainActivity.this, Game.class);
                startActivity(it);
                finish();
            }
        });
        highButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent it= new Intent();
                it.setClass(MainActivity.this, HighSroce.class);
                startActivity(it);
                finish();
            }
        });
        exitButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Toast.makeText(mContext, "看來你不是"+usernameString+"，晚安", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        com.facebook.Session.getActiveSession().onActivityResult(this, requestCode, resultCode, data);
    }
}
