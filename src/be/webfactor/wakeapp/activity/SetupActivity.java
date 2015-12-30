package be.webfactor.wakeapp.activity;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import be.webfactor.wakeapp.R;
import be.webfactor.wakeapp.constants.TimeConstants;
import be.webfactor.wakeapp.fragment.TimePickerFragment;

import java.text.DateFormat;
import java.util.Calendar;

public class SetupActivity extends Activity {

	private static final Calendar DEFAULT_TIME;

	static {
		DEFAULT_TIME = Calendar.getInstance();
		DEFAULT_TIME.set(Calendar.HOUR_OF_DAY, TimeConstants.DEFAULT_HOUR);
		DEFAULT_TIME.set(Calendar.MINUTE, TimeConstants.DEFAULT_MINUTE);
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

		Bundle args = new Bundle();
		args.putSerializable(TimeConstants.TIME_PARAM_NAME, time);
		newFragment.setArguments(args);

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
		Calendar now = Calendar.getInstance();

		time.set(Calendar.DAY_OF_YEAR, now.get(Calendar.DAY_OF_YEAR));
		if (time.getTime().before(now.getTime())) {
			time.add(Calendar.DAY_OF_YEAR, 1);
		}
		Log.e("Configured time: ", time.getTime().toString());
		Log.e("Current time: ", now.getTime().toString());

		Intent intent = new Intent(this, MainActivity.class);
		intent.putExtra(TimeConstants.TIME_PARAM_NAME, time);
		startActivity(intent);
	}
}
