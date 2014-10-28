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
		String resStage = "��"+String.valueOf(a)+"��";
		if(a<2){
			TVTOP.setText("�~�M�u��"+resStage);
			TV.setText("�u�S�ΡI");
		}
		if(a==2||a==3){
			TVTOP.setText(resStage);
			TV.setText("��O������w���H���a");
		}
//		if(a==4){
//			TV.setText(resStage+"�A�]���L�N���Ӫ���O");
//		}
		if(a>=4){
			TVTOP.setText("�A�]���L�N");
		TV.setText("��"+String.valueOf(GameInfo.GameStage)+"������O");
		}
		if(a>6){
			TVTOP.setText(resStage);
			TV.setText("�ä��ȱoź��ۺ�");
		}
		if(a>8){
			TVTOP.setText(resStage);
			TV.setText("�u��A�o�ӡA�x�x��");
		}
		if(a>12){
			TVTOP.setText(resStage);
			TV.setText("�گu�����۫H�A�A�~�M�����ҬɡC");
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
