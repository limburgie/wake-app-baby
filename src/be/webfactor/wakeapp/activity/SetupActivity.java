package be.webfactor.wakeapp.activity;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import be.webfactor.wakeapp.R;
import be.webfactor.wakeapp.fragment.TimePickerFragment;

import java.text.DateFormat;
import java.util.Calendar;

public class SetupActivity extends Activity {

	private static final Calendar DEFAULT_TIME;

	static {
		DEFAULT_TIME = Calendar.getInstance();
		DEFAULT_TIME.set(Calendar.HOUR_OF_DAY, 7);
		DEFAULT_TIME.set(Calendar.MINUTE, 0);
		DEFAULT_TIME.set(Calendar.SECOND, 0);
	}

	private Calendar time = DEFAULT_TIME;
	private TextView timeLabelTextView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.setup);
		timeLabelTextView = (TextView) findViewById(R.id.time_label);

		setTimeLabel();
	}

	public void showTimePicker(View view) {
		DialogFragment newFragment = new TimePickerFragment();
		newFragment.show(getFragmentManager(), "timePicker");
	}

	public void setTime(int hour, int minute) {
		time.set(Calendar.HOUR_OF_DAY, hour);
		time.set(Calendar.MINUTE, minute);

		setTimeLabel();
	}

	private void setTimeLabel() {
		DateFormat timeFormat = android.text.format.DateFormat.getTimeFormat(getApplicationContext());

		timeLabelTextView.setText(timeFormat.format(time.getTime()));
	}

	public void start(View view) {
		Intent intent = new Intent(this, MainActivity.class);
		intent.putExtra(ActivityConstants.TIME_PARAM, time);
		startActivity(intent);
	}
}
