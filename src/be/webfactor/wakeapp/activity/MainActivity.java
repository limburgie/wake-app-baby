package be.webfactor.wakeapp.activity;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import be.webfactor.wakeapp.R;

import java.util.Calendar;

public class MainActivity extends Activity {

	private static final String ACTION_NAME = "wake_app";

	private ImageView image;
	private BroadcastReceiver alarmReceiver;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setContentView(R.layout.main);

		image = (ImageView) findViewById(R.id.image);

		setTimer();
		registerAlarmHandler();
	}

	private void setTimer() {
		Calendar time = (Calendar) getIntent().getSerializableExtra("time");

		Intent reminderIntent = new Intent();
		reminderIntent.setAction(ACTION_NAME);
		PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, reminderIntent, 0);

		AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
		alarmManager.set(AlarmManager.RTC, time.getTimeInMillis(), pendingIntent);
	}

	private void registerAlarmHandler() {
		IntentFilter intentFilter = new IntentFilter();
		intentFilter.addAction(ACTION_NAME);

		alarmReceiver = new BroadcastReceiver() {
			@Override
			public void onReceive(Context context, Intent intent) {
				image.setImageResource(R.drawable.day);
			}
		};

		registerReceiver(alarmReceiver, intentFilter);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();

		unregisterReceiver(alarmReceiver);
	}
}
