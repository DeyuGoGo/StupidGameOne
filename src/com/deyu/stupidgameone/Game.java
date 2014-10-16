package com.deyu.stupidgameone;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Game extends Activity {
	Button button;
	TextView sayTV;
	// RelativeLayout relativeLayout;
	CountDownTimer Timer;
	Handler handler;
	Boolean Countin = true;
	TextView TV;
	private Bitmap mOrgImage;
	int gonextstep = 0;
	int wherego = 1, lastgo = 1;
	int x, y;
	Context mContext;

	private int winwidth , winheight;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 設定為全螢幕
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		mContext = this;
		setContentView(R.layout.activity_game);
		  DisplayMetrics metrics = new DisplayMetrics();  
		  getWindowManager().getDefaultDisplay().getMetrics(metrics);
		  winwidth = metrics.widthPixels;
		  winheight = metrics.heightPixels;
		findview();
		gamebegin();
		// startdia();

	}

	void gamebegin() {
		AlertDialog.Builder builder = new Builder(Game.this);

		builder.setMessage("每一關只有五秒的時間懂嗎");
		builder.setTitle("用點擊消滅罪惡");
		builder.setPositiveButton("準備好了",
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub

						startdia();
						dialog.dismiss();

					}

				});
		builder.setCancelable(false);
		builder.create().show();
	}

	void startdia() {
		gonextstep = 0;
		GameInfo.GameStage++;
		AlertDialog.Builder builder = new Builder(Game.this);

		builder.setMessage("現在是第" + String.valueOf(GameInfo.GameStage) + "關");
		builder.setTitle("加油");
		builder.setPositiveButton("開始", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub

				start();
				dialog.dismiss();

			}

		});
		builder.setCancelable(false);
		builder.create().show();

	}

	void start() {
		newMon();
		handler = new Handler();
		makewheretogo();
		// findview();
		alwaysrun();
		countdown1();
		Timer.start();
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();

		handler.removeCallbacks(gGoRunnable);

		Timer.cancel();

		finish();
	}

	void findview() {
		sayTV = (TextView) findViewById(R.id.say);
		TV = (TextView) findViewById(R.id.textView1);
	}

	void makewheretogo() {

		wherego = (int) (Math.random() * 8 + 1); // 數值範圍 : 1 ~ 8

	}

	void shouldgoRight() {
		wherego = (int) (Math.random() * 5 + 1);
	}

	void shouldgoLeft() {
		wherego = (int) (Math.random() * 5);
		if (wherego == 0) {
			wherego = 1;
		} else {
			wherego = wherego + 4;
		}
	}

	void shouldgoUp() {
		wherego = (int) (Math.random() * 5 + 1);
		if (wherego > 3) {
			wherego = wherego + 3;
		}
	}

	void shouldgoDown() {
		wherego = (int) (Math.random() * 5 + 1);
		wherego = wherego + 2;
	}

	void alwaysrun() {
		handler.postDelayed(gGoRunnable, 3);
	}

	void newMon() {
		button = (Button) findViewById(R.id.button1);
		sayTV.setVisibility(View.VISIBLE);
		button.setVisibility(View.VISIBLE);
		button.setBackgroundResource(R.drawable.ic);
		button.setHeight(50);
		button.setWidth(50);
		x = (int) (Math.random() * (winwidth - button.getWidth()));
		y = (int) ((Math.random() * (winheight - button.getHeight())));
		button.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_DOWN) { // 按下的時候
					sayTV.setVisibility(View.INVISIBLE);
					button.setVisibility(View.INVISIBLE);
					startdia();
					Timer.cancel();
					GameInfo.GameSpeed++;
				}
				return false;
			}
		});
	}

	private Runnable gGoRunnable = new Runnable() {
		@Override
		public void run() {
			try {
				if (Countin) {
					RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) button
							.getLayoutParams();
					RelativeLayout.LayoutParams layoutParams1 = (RelativeLayout.LayoutParams) sayTV
							.getLayoutParams();
					// 每次往左移進10dp
					// 再把Layout的參數放回 TextView
					if (lastgo != wherego) {
						if (wherego == 1) {
							button.setBackgroundResource(R.drawable.ic5);
						}
						if (wherego == 2) {
							button.setBackgroundResource(R.drawable.ic4);
						}
						if (wherego == 3) {
							button.setBackgroundResource(R.drawable.ic3);
						}
						if (wherego == 4) {
							button.setBackgroundResource(R.drawable.ic2);
						}
						if (wherego == 5) {
							button.setBackgroundResource(R.drawable.ic1);
						}
						if (wherego == 6) {
							button.setBackgroundResource(R.drawable.ic8);
						}
						if (wherego == 7) {
							button.setBackgroundResource(R.drawable.ic7);
						}
						if (wherego == 8) {
							button.setBackgroundResource(R.drawable.ic6);
						}
					}
					lastgo = wherego;
					if (wherego == 2 || wherego == 3 || wherego == 4) {
						x = x + GameInfo.GameSpeed;
					}

					if (wherego == 8 || wherego == 7 || wherego == 6) {
						x = x - GameInfo.GameSpeed;
					}
					if (wherego == 8 || wherego == 1 || wherego == 2) {
						y = y + GameInfo.GameSpeed;
					}
					if (wherego == 6 || wherego == 5 || wherego == 4) {
						y = y - GameInfo.GameSpeed;
					}
					// layoutParams.leftMargin =x;
					// layoutParams.topMargin=y;
					float xx = (x / getResources().getDisplayMetrics().density);
					float yy = (y / getResources().getDisplayMetrics().density);
					layoutParams.leftMargin = x;
					layoutParams.topMargin = y;
					int sayw = sayTV.getWidth();
					int btnw = button.getWidth();
					int sum = sayw - btnw;
					layoutParams1.leftMargin = x - (sum / 2);
					layoutParams1.topMargin = y - sayTV.getHeight();
					button.setLayoutParams(layoutParams);
					sayTV.setLayoutParams(layoutParams1);

					// Log.e("wherego",
					// String.valueOf((getResources().getDisplayMetrics().density)));

					alwaysrun();
					if (x > (winwidth - button.getWidth())) {
						shouldgoLeft();
					}
					if (x < 0) {
						shouldgoRight();
					}
					if (y > (winheight - button.getHeight())) {
						shouldgoDown();

					}
					if (y < 0) {
						shouldgoUp();

					}
				} else {

				}

			} catch (Exception e) {
				Log.e("error", e.toString());
			}

		}
	};

	void talktouser() {
		int sayint = (int) (Math.random() * 8 + 1); // 數值範圍 : 1
		switch (sayint) {
		case 1:
			sayTV.setText("加油好嗎？");
			break;
		case 2:
			sayTV.setText("我可是等級" + String.valueOf(GameInfo.GameStage) + "的蟑螂");
			break;
		case 3:
			sayTV.setText("你到底行不行拉");
			break;
		case 4:
			sayTV.setText("笨蛋～");
			break;
		case 5:
			sayTV.setText("不行不要勉強拉");
			break;
		case 6:
			sayTV.setText("我已經慢慢走了");
			break;
		case 7:
			sayTV.setText("呀呼～");
			break;
		case 8:
			sayTV.setText("你好像有點弱弱的");
			break;

		default:
			sayTV.setText("登冷～");
			break;
		}

	}

	public void countdown1() {
		Timer = new CountDownTimer(5000, 1000) {
			@Override
			public void onFinish() {
				Intent it = new Intent();
				it.setClass(mContext, Finish.class);
				startActivity(it);
				finish();
			}

			@Override
			public void onTick(long millisUntilFinished) {
				{
					talktouser();
				}
				TV.setText("剩餘秒數:" + millisUntilFinished / 1000);
			}
		};
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.game, menu);
		return true;
	}

}
