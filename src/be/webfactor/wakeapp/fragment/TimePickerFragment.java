package be.webfactor.wakeapp.fragment;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.TimePicker;
import be.webfactor.wakeapp.activity.SetupActivity;
import be.webfactor.wakeapp.constants.TimeConstants;

import java.util.Calendar;

public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		final Calendar c = (Calendar) getArguments().getSerializable(TimeConstants.TIME_PARAM_NAME);
		int hour = c.get(Calendar.HOUR_OF_DAY);
		int minute = c.get(Calendar.MINUTE);

		return new TimePickerDialog(getActivity(), this, hour, minute, DateFormat.is24HourFormat(getActivity()));
	}

	@Override
	public void onTimeSet(TimePicker timePicker, int hour, int minute) {
		SetupActivity activity = (SetupActivity) getActivity();
		activity.setTime(hour, minute);
	}
}
