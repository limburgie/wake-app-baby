package be.webfactor.wakeapp.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import be.webfactor.wakeapp.R;

public class MainActivity extends Activity {

	private int current = R.drawable.night;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setContentView(R.layout.main);

		final ImageView image = (ImageView) findViewById(R.id.image);
		image.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				if (current == R.drawable.day) {
					current = R.drawable.night;
				} else {
					current = R.drawable.day;
				}
				image.setImageResource(current);
			}
		});
	}
}
