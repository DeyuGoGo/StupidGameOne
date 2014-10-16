package com.deyu.stupidgameone;




import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.service.textservice.SpellCheckerService.Session;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.*;
import com.facebook.Session.StatusCallback;
import com.facebook.android.Facebook;
import com.facebook.model.*;

public class MainActivity extends Activity {

	Button StartButton,highButton,exitButton ;
	Context mContext;
	String usernameString;
	static boolean firsttime = true;
	TextView TV1;
    DB DBH;
    private Facebook  facebook;
    

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_main);
		mContext =this;
	    // start Facebook Login
    	DBH = new DB(mContext);
    	if(firsttime){
    		firsttime();
    	}
    	else{
    		twotime();
    	}
		findXmlView();
		username();
		setButtonClick();
		  com.facebook.Session.openActiveSession(this, true, new StatusCallback() {
			
			@Override
			public void call(com.facebook.Session session, SessionState state,
					Exception exception) {
				// TODO Auto-generated method stub
			    if (session.isOpened()) {

			          // make request to the /me API
			          Request.newMeRequest(session, new Request.GraphUserCallback() {

			            // callback after Graph API response with user object
			            @Override
			            public void onCompleted(GraphUser user, Response response) {
			              if (user != null) {
			                TextView welcome = (TextView) findViewById(R.id.startbutton);
			                welcome.setText("Hello " + user.getName() + "!");
			              }
			            }
			          }).executeAsync();
			        }
			      }	
		});
	}
	void goFb(){
	}
	
	void firsttime(){
		
    	DBH.open();
    	DBH.create(1);
    	Cursor ccc =  DBH.get(1);
		int i =  ccc.getInt(1);
		GameInfo.highest = i;
    	DBH.close();
    	firsttime = false;
	}
	void twotime(){
		DBH.open();
    	Cursor ccc =  DBH.get(1);
		int i =  ccc.getInt(1);
		GameInfo.highest = i;
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
