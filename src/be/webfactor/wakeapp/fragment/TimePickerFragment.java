package be.webfactor.wakeapp.fragment;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.TimePicker;
import be.webfactor.wakeapp.activity.SetupActivity;
import be.webfactor.wakeapp.constants.TimeConstants;

public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

	public Dialog onCreateDialog(Bundle savedInstanceState) {
		int hour = getArguments().getInt(TimeConstants.HOUR_PARAM_NAME);
		int minute = getArguments().getInt(TimeConstants.MINUTE_PARAM_NAME);

		return new TimePickerDialog(getActivity(), this, hour, minute, DateFormat.is24HourFormat(getActivity()));
	}

	public void onTimeSet(TimePicker timePicker, int hour, int minute) {
		SetupActivity activity = (SetupActivity) getActivity();
		activity.setTime(hour, minute);
	}
}
