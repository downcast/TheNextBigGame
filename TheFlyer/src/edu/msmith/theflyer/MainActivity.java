package edu.msmith.theflyer;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;

// Here is the most basic skeleton for a moving image I could make. 
// The news Helicopter moves diagonally towards the bottom right side of the screen

public class MainActivity extends Activity implements OnClickListener, SurfaceHolder.Callback{
	
	private MediaPlayer ThemeMusic= new MediaPlayer();
	private Button but;
	private static int count= 0;
	private SurfaceView sv;
	private GameLoop Loop; // Seperate Thread
	private Bitmap Cloud, helicopterNews, helicopterMiliary, redBuildingLarge, blueBuildingMid, yellowBuildingSmall, character;  // The helicopter and other Images in raw form change// image//
	private GameImage cloudImage, helicopterNewsImage, helicopterMiliaryImage, redBuildingImage, blueBuildingImage, yellowBuildingImage, characterImage; // The helicopter in object form
	private int screenheight, screenwidth;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		// This is going to link our custom canvas with the mainAcitivty one.
		setContentView(R.layout.activity_main);
		
		screenheight = getResources().getConfiguration().screenHeightDp;
		screenwidth = getResources().getConfiguration().screenWidthDp;
		
		but= (Button) this.findViewById(R.id.button1);
		but.setOnClickListener(this);
		sv= (SurfaceView) findViewById(R.id.surfaceView1);
		sv.getHolder().addCallback(this);
		
		ThemeMusic= MediaPlayer.create(MainActivity.this, R.raw.thememusic);
		setVolumeControlStream(AudioManager.STREAM_MUSIC);
		ThemeMusic.start();
		
		Cloud= BitmapFactory.decodeResource(getResources(), R.drawable.cloud);
		helicopterNews= BitmapFactory.decodeResource(getResources(), R.drawable.helicopter_news_size75);
		helicopterMiliary= BitmapFactory.decodeResource(getResources(), R.drawable.helicopter_military_size75);
		redBuildingLarge= BitmapFactory.decodeResource(getResources(), R.drawable.building_red_size);
		blueBuildingMid= BitmapFactory.decodeResource(getResources(), R.drawable.building_blue_size);
		yellowBuildingSmall= BitmapFactory.decodeResource(getResources(), R.drawable.building_yellow_size);
		character= BitmapFactory.decodeResource(getResources(), R.drawable.game_guy_size25);//70

		cloudImage= new GameImage(Cloud);
		helicopterNewsImage= new GameImage(helicopterNews);
		helicopterMiliaryImage= new GameImage(helicopterMiliary);
		redBuildingImage= new GameImage(redBuildingLarge);
		blueBuildingImage= new GameImage(blueBuildingMid);
		yellowBuildingImage= new GameImage(yellowBuildingSmall);
		characterImage= new GameImage(character);

		Loop= new GameLoop(sv.getHolder(), this);

	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		// Called when the home button is pressed
		Log.d("Custom", "Activity Paused");
		super.onPause();
		ThemeMusic.pause();
		Loop.setIsRunning(false);
	}

	@Override
	public void onClick(View v) {
		// Here we will control the character movements; Possibly move control to a while pressed instead to get a fluid motion
		Log.d("Custom", "On Click");
		characterImage.setY(count);
		count+=10;
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		Log.d("Custom", "Activity Destroy");
		super.onDestroy();
	}

	@Override
	protected void onRestart() {
		// Called when the user comes back to the game before creating the surface. WE restart the music.
		Log.d("Custom", "Activity Restart");
		super.onRestart();
		ThemeMusic.start();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		Log.d("Custom", "Activity Resume");
		super.onResume();
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		Log.d("Custom", "Activity Started");
		super.onStart();
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		Log.d("Custom", "Activity Stopped");
		super.onStop();
	}

	@Override
	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		Log.d("Custom", "Surface has been changed");
	
	}

	@Override
	public void surfaceCreated(SurfaceHolder arg0) {
		// When the user comes back to the game this function is called again. So we test the state of the thread to determine whether
		// has been started. When the thread is terminated, we reinitialize the thread and start it.
		Log.d("Custom", "Surface has been created");
		if (Loop.getState()== Thread.State.NEW){
			Log.d("Custom", "New Thread");
			Loop.start();
		}else{
			Log.d("Custom", Loop.getState().toString());
			Loop= new GameLoop(sv.getHolder(), this);
			Loop.start();
		}	
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder sh) {
		// TODO Auto-generated method stub
		Log.d("Custom", "Surface has been demoed");
	}
	
	public void render(Canvas canvas){
		cloudImage.cloud(canvas);
		helicopterNewsImage.helicopter_news(canvas);
		helicopterMiliaryImage.helicopter_military(canvas);
		redBuildingImage.red_building(canvas);
		blueBuildingImage.blue_building(canvas);
		yellowBuildingImage.yellow_building(canvas);
		characterImage.main_character(canvas);
	}


}