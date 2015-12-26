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
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class SetupActivity extends Activity {

	private static final DateFormat FORMAT = new SimpleDateFormat("HH:mm");

	private Calendar time = Calendar.getInstance();
	private TextView timeLabelTextView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.setup);

		timeLabelTextView = (TextView) findViewById(R.id.time_label);

		setTimeLabel();
	}

	private void setTimeLabel() {
		timeLabelTextView.setText(FORMAT.format(time.getTime()));
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

	public void start(View view) {
		Intent intent = new Intent(this, MainActivity.class);
		intent.putExtra("time", time);
		startActivity(intent);
	}
}
