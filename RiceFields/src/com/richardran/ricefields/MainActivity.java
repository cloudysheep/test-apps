package com.richardran.ricefields;

import com.richardran.ricefields.MainSurface;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class MainActivity extends Activity {

	private MainSurface mySurfaceView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
	    mySurfaceView = new MainSurface(this);
	    setContentView(mySurfaceView);		 
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		mySurfaceView.onResumeMySurfaceView();
	 }
	 
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		mySurfaceView.onPauseMySurfaceView();
	}

}
