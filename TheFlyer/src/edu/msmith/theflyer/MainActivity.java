package edu.msmith.theflyer;

import java.util.ArrayList;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.widget.Button;

// Here is the most basic skeleton for a moving image I could make. 
// The news Helicopter moves diagonally towards the bottom right side of the screen

public class MainActivity extends Activity implements OnTouchListener, SurfaceHolder.Callback{
	
	private MediaPlayer ThemeMusic= new MediaPlayer();
	private Button downBut, upBut;
	private SurfaceView sv;
	private GameLoop Loop; // Seperate Thread
	private Bitmap Cloud, helicopterNews, helicopterMiliary, redBuildingLarge, blueBuildingMid, yellowBuildingSmall, character;  // The helicopter and other Images in raw form change// image//
	private Image cloudImage, helicopterNewsImage, helicopterMiliaryImage, redBuildingImage, blueBuildingImage, yellowBuildingImage, characterImage; // The helicopter in object form
	private ArrayList<Image> gameImageList= new ArrayList<Image>();
	private int screenheight, screenwidth;
	private boolean pressedUp= false;
	private CharacterMovementTask CMT;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		// This is going to link our custom canvas with the mainAcitivty one.
		setContentView(R.layout.activity_main);
		
		screenheight = getResources().getConfiguration().screenHeightDp;
		screenwidth = getResources().getConfiguration().screenWidthDp;
		
		downBut= (Button) this.findViewById(R.id.DownButton);
		upBut= (Button) this.findViewById(R.id.UpButton);
		downBut.setOnTouchListener(this);
		upBut.setOnTouchListener(this);
		sv= (SurfaceView) findViewById(R.id.surfaceView1);
		sv.getHolder().addCallback(this);
		
		ThemeMusic= MediaPlayer.create(MainActivity.this, R.raw.thememusic);
		setVolumeControlStream(AudioManager.STREAM_MUSIC);
		ThemeMusic.start();
		ThemeMusic.setLooping(true);
		
		Cloud= BitmapFactory.decodeResource(getResources(), R.drawable.cloud);
		helicopterNews= BitmapFactory.decodeResource(getResources(), R.drawable.helicopter_news_size75);
		helicopterMiliary= BitmapFactory.decodeResource(getResources(), R.drawable.helicopter_military_size75);
		redBuildingLarge= BitmapFactory.decodeResource(getResources(), R.drawable.building_red_size);
		blueBuildingMid= BitmapFactory.decodeResource(getResources(), R.drawable.building_blue_size);
		yellowBuildingSmall= BitmapFactory.decodeResource(getResources(), R.drawable.building_yellow_size);
		character= BitmapFactory.decodeResource(getResources(), R.drawable.game_guy_size25);//70

		cloudImage= new ObstacleImageClass(Cloud,screenheight,screenwidth, 1000, 250, -1);
		
		helicopterNewsImage= new ObstacleImageClass(helicopterNews,screenheight,screenwidth, 200, 50, -3 );
		helicopterMiliaryImage= new ObstacleImageClass(helicopterMiliary,screenheight,screenwidth,200, 50, -3);
		redBuildingImage= new ObstacleImageClass(redBuildingLarge,screenheight,screenwidth, 300, 100, -2);
		blueBuildingImage= new ObstacleImageClass(blueBuildingMid,screenheight,screenwidth, 300, 100, -2);
		yellowBuildingImage= new ObstacleImageClass(yellowBuildingSmall,screenheight,screenwidth,300, 100, -2);
		characterImage= new PlayerImage(character,screenheight,screenwidth, 0, 100);

		// PLAYER MUST BE THE FIRST ONE ADDED TO THE LIST
		gameImageList.add(characterImage);
		gameImageList.add(helicopterMiliaryImage);
		gameImageList.add(redBuildingImage);
		gameImageList.add(blueBuildingImage);
		gameImageList.add(yellowBuildingImage);
		gameImageList.add(helicopterNewsImage);
		
		Loop= new GameLoop(sv.getHolder(), this, gameImageList);

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

	/** While the user presses a button, an async task is run in a loop until the button is released. 
	 * <br>
	 * This provides a smooth up or down motion while the button is pressed
	 */
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.UpButton:
			switch(event.getAction()){
			case MotionEvent.ACTION_DOWN:
				if(pressedUp == false){
	                CMT= new CharacterMovementTask(characterImage,-10, this.screenheight);
	                CMT.execute();
	            }
				break;
			case MotionEvent.ACTION_UP:
				while (!pressedUp){
					pressedUp= CMT.cancel(true);
				}
				pressedUp= true;
				break;
			}
			break;
		case R.id.DownButton:
			switch(event.getAction()){
			case MotionEvent.ACTION_DOWN:
				if(pressedUp == false){
	                CMT= new CharacterMovementTask(characterImage, 10, this.screenheight);
	                CMT.execute();
	            }
				break;
			case MotionEvent.ACTION_UP:
				while (!pressedUp){
					pressedUp= CMT.cancel(true);
				}
				pressedUp= true;
				break;
			}
			break;
		}
		pressedUp= false;
		return false;
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
		ThemeMusic.start();
		Loop.setIsRunning(true);
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
			Loop= new GameLoop(sv.getHolder(), this, gameImageList);
			Loop.start();
		}	
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder sh) {
		// TODO Auto-generated method stub
		Log.d("Custom", "Surface has been demoed");
	}
	
	public void render(Canvas canvas, ArrayList<Image> renderList){
		for (int i=0; i < renderList.size(); i++){
			Log.d("Custom", "Render "+ i);
			renderList.get(i).draw(canvas);
		}
	}
}