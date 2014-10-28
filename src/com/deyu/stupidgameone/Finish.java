package com.deyu.stupidgameone;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class Finish extends Activity {

	TextView TV,TVTOP;
	Button againbtn,backButton ;
    DB DBH;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_finish);
    	DBH = new DB(this);
	findview();	
	btnview();
	}
	void findview(){
		TV = (TextView)findViewById(R.id.finish);
		TVTOP=(TextView)findViewById(R.id.finish1);
		int a = GameInfo.GameStage;
		if(a>GameInfo.highest){
			DBH.open();
			DBH.update(1, a);
			DBH.close();
			
			GameInfo.highest=a;
		}
		String resStage = "第"+String.valueOf(a)+"關";
		if(a<2){
			TVTOP.setText("居然只有"+resStage);
			TV.setText("真沒用！");
		}
		if(a==2||a==3){
			TVTOP.setText(resStage);
			TV.setText("算是反應遲鈍的人類吧");
		}
//		if(a==4){
//			TV.setText(resStage+"你也不過就楊承的實力");
//		}
		if(a>=4){
			TVTOP.setText("你也不過就");
		TV.setText("第"+String.valueOf(GameInfo.GameStage)+"關的實力");
		}
		if(a>6){
			TVTOP.setText(resStage);
			TV.setText("並不值得驕矜自滿");
		}
		if(a>8){
			TVTOP.setText(resStage);
			TV.setText("哎喲，這個，屌屌的");
		}
		if(a>12){
			TVTOP.setText(resStage);
			TV.setText("我真不敢相信，你居然有此境界。");
		}
	}
	void btnview(){
		againbtn = (Button)findViewById(R.id.againbtn);
		backButton = (Button)findViewById(R.id.backbtn);
		againbtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent it= new Intent();
				GameInfo.GameSpeed=1;
				GameInfo.GameStage=0;
				it.setClass(Finish.this, Game.class);
				startActivity(it);
				finish();
			
			}
		});
		backButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent it= new Intent();
				it.setClass(Finish.this, MainActivity.class);
				startActivity(it);
				finish();
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.finish, menu);
		return true;
	}

}
