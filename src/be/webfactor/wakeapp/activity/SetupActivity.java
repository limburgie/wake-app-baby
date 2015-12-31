package be.webfactor.wakeapp.activity;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import be.webfactor.wakeapp.R;
import be.webfactor.wakeapp.constants.TimeConstants;
import be.webfactor.wakeapp.fragment.TimePickerFragment;

import java.text.DateFormat;
import java.util.Calendar;

public class SetupActivity extends Activity {

	private int hour = TimeConstants.DEFAULT_HOUR;
	private int minute = TimeConstants.DEFAULT_MINUTE;

	private TextView timeLabelTextView;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.setup);
		timeLabelTextView = (TextView) findViewById(R.id.time_label);

		setTimeLabel();
	}

	public void showTimePicker(View view) {
		DialogFragment newFragment = new TimePickerFragment();

		Bundle args = new Bundle();
		args.putInt(TimeConstants.HOUR_PARAM_NAME, hour);
		args.putInt(TimeConstants.MINUTE_PARAM_NAME, minute);
		newFragment.setArguments(args);

		newFragment.show(getFragmentManager(), "timePicker");
	}

	public void setTime(int hour, int minute) {
		this.hour = hour;
		this.minute = minute;

		setTimeLabel();
	}

	private void setTimeLabel() {
		DateFormat timeFormat = android.text.format.DateFormat.getTimeFormat(getApplicationContext());

		Calendar time = Calendar.getInstance();
		time.set(Calendar.HOUR_OF_DAY, hour);
		time.set(Calendar.MINUTE, minute);

		timeLabelTextView.setText(timeFormat.format(time.getTime()));
	}

	public void start(View view) {
		Calendar configured = Calendar.getInstance();
		configured.set(Calendar.HOUR_OF_DAY, hour);
		configured.set(Calendar.MINUTE, minute);
		configured.set(Calendar.SECOND, 0);

		if (configured.getTime().before(Calendar.getInstance().getTime())) {
			configured.add(Calendar.DAY_OF_YEAR, 1);
		}

		Intent intent = new Intent(this, MainActivity.class);
		intent.putExtra(TimeConstants.TIME_PARAM_NAME, configured);
		startActivity(intent);
	}
}
