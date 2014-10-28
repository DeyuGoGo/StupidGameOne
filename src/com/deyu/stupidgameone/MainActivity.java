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

public class MainActivity extends Activity {

	Button StartButton,highButton,exitButton ;
	Context mContext;
	String usernameString;
	static boolean firsttime = true;
	TextView TV1;
    DB DBH;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_main);
		mContext =this;
    	DBH = new DB(mContext);
    	firsttime();
		findXmlView();
		username();
		setButtonClick();
	}
	
	private void firsttime(){
    	DBH.open();
		if(firsttime){
			DBH.create(1);
	    	firsttime = false;
		}
    	Cursor ccc =  DBH.get(1);
		int i =  ccc.getInt(1);
		GameInfo.highest = i;
    	DBH.close();
	}
	void username(){
		switch ((int)(Math.random() * 15 + 1)) {
		case 3:
			usernameString = "�³J";
			break;
		case 4:
			usernameString = "���";
			break;
		case 5:
			usernameString = "�ǻ�";
			break;
		default:
			usernameString = "�i��";
			break;
		}
		TV1.setText(usernameString+"�A���w�A�n");
	}

	void findXmlView(){
		StartButton = (Button)findViewById(R.id.startbutton);
		highButton = (Button)findViewById(R.id.highBtn);
		exitButton=(Button)findViewById(R.id.exitbtn);
		TV1=(TextView)findViewById(R.id.textView1);
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
				// TODO Auto-generated method stub
				// TODO Auto-generated method stub
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
				Toast.makeText(mContext, "�ݨӧA���O"+usernameString+"�A�ߦw", Toast.LENGTH_SHORT).show();
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
