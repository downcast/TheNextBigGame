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
	private Bitmap helicopterNews, helicopterMiliary, redBuildingLarge, blueBuildingMid, yellowBuildingSmall, character;  // The helicopter and other Images in raw form change// image//
	private GameImage helicopterNewsImage, helicopterMiliaryImage, redBuildingImage, blueBuildingImage, yellowBuildingImage, characterImage; // The helicopter in object form
	private boolean run= false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		// This is going to link our custom canvas with the mainAcitivty one.
		setContentView(R.layout.activity_main);
		
		but= (Button) this.findViewById(R.id.button1);
		but.setOnClickListener(this);
		sv= (SurfaceView) findViewById(R.id.surfaceView1);
		sv.getHolder().addCallback(this);
		
		ThemeMusic= MediaPlayer.create(MainActivity.this, R.raw.thememusic);
		setVolumeControlStream(AudioManager.STREAM_MUSIC);
		ThemeMusic.start();
		
		helicopterNews= BitmapFactory.decodeResource(getResources(), R.drawable.helicopter_news_size75);
		helicopterMiliary= BitmapFactory.decodeResource(getResources(), R.drawable.helicopter_military_size75);
		redBuildingLarge= BitmapFactory.decodeResource(getResources(), R.drawable.building_red_size);
		blueBuildingMid= BitmapFactory.decodeResource(getResources(), R.drawable.building_blue_size);
		yellowBuildingSmall= BitmapFactory.decodeResource(getResources(), R.drawable.building_yellow_size);
		character= BitmapFactory.decodeResource(getResources(), R.drawable.flyer_guy_size70);
		//image= BitmapFactory.decodeResource(getResources(), R.drawable.helicopter_military_size85);
		// Attach image to the custom obj
		//gameImage= new GameImage(image);
		helicopterNewsImage= new GameImage(helicopterNews);
		helicopterMiliaryImage= new GameImage(helicopterMiliary);
		redBuildingImage= new GameImage(redBuildingLarge);
		blueBuildingImage= new GameImage(blueBuildingMid);
		yellowBuildingImage= new GameImage(yellowBuildingSmall);
		characterImage= new GameImage(character);

		Loop= new GameLoop(sv.getHolder(), this);
		// Allows the callbacks to work
		//this.setFocusable(true);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Log.d("Custom", "On Click");
		characterImage.setY(count);
		count+=10;
	}

	@Override
	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		Log.d("Custom", "Surface has been changed");
	
	}

	@Override
	public void surfaceCreated(SurfaceHolder arg0) {
		// TODO Auto-generated method stub
		Log.d("Custom", "Surface has been created");
		this.run= true;
		Loop.start();
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder arg0) {
		// TODO Auto-generated method stub
		Log.d("Custom", "Surface has been demoed");
		boolean retry= true;
		while (retry= true){
		try {
			Loop.setIsRunning(false);
			Loop.join();
			retry= false;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}	
	}
	
	public void render(Canvas canvas){
		helicopterNewsImage.helicopter_news(canvas);
		helicopterMiliaryImage.helicopter_military(canvas);
		redBuildingImage.red_building(canvas);
		blueBuildingImage.blue_building(canvas);
		yellowBuildingImage.yellow_building(canvas);
		characterImage.main_character(canvas);
	}
}