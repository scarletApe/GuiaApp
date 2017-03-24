package com.marmar.guiapp;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class AgradecimientosFragment extends Fragment {

	public AgradecimientosFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_agradecimientos,
				container, false);

		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
//				final Intent mainIntent = new Intent(LaunchActivity.this,
//						HomeActivity.class);
//				LaunchActivity.this.startActivity(mainIntent);
//				LaunchActivity.this.finish();
				System.exit(0);
			}
		}, 5000);

		return rootView;
	}
}