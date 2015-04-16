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

import butterknife.ButterKnife;
import butterknife.InjectView;

public class HighSroce extends Activity {

	TextView SroceTV;
    @InjectView(R.id.tv_best_time)TextView BestTimeTv;
	Button backHomeButton;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		 requestWindowFeature(Window.FEATURE_NO_TITLE);
	        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
	        WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_high_sroce);
        ButterKnife.inject(this);
		findView();
		
	}

	void findView(){
		SroceTV = (TextView)findViewById(R.id.HighTV);
		SroceTV.setText("第"+String.valueOf(GameInfo.highest)+"關啦");
        if(GameInfo.highest > 6) BestTimeTv.setVisibility(View.VISIBLE);
        BestTimeTv.setText("最快" +getTimeString() +"啦");
		backHomeButton = (Button)findViewById(R.id.backHomeBtn);
		backHomeButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent it= new Intent();
				it.setClass(HighSroce.this, MainActivity.class);
				startActivity(it);
				finish();
			}
		});
	}
    private String getTimeString(){
        return GameInfo.bestTime/1000 + "秒" + GameInfo.bestTime%1000;
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.high_sroce, menu);
		return true;
	}

}
