package edu.msmith.theflyer;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

// Here is the most basic skeleton for a moving image I could make. 
// The news Helicopter moves diagonally towards the bottom right side of the screen
public class MainActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// This is going to link our custom canvas with the mainAcitivty one.
		setContentView(new TestClass1(this));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}

/*
 * private MediaPlayer ThemeMusic= new MediaPlayer();
 * ThemeMusic= MediaPlayer.create(MainActivity.this, R.raw.theme_music_dark_tension);
		setVolumeControlStream(AudioManager.STREAM_MUSIC);
		ThemeMusic.start();
		*/
