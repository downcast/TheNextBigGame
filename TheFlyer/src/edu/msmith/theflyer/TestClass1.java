package edu.msmith.theflyer;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/** This is the view of the MainActivity like mainActivity.xml. Just setContentView to this class.
 * 
 * @author Marcus and Sherdon
 *
 */
public class TestClass1 extends SurfaceView implements SurfaceHolder.Callback{

	private GameLoop Loop; // Seperate Thread
	private Bitmap helicopterNews, helicopterMiliary, redBuildingLarge, blueBuildingMid, yellowBuildingSmall, character;  // The helicopter and other Images in raw form change// image//
	private GameImage helicopterNewsImage, helicopterMiliaryImage, redBuildingImage, blueBuildingImage, yellowBuildingImage, characterImage; // The helicopter in object form
	
	public TestClass1(Context context) {
		super(context);
		//intercept callback events
		getHolder().addCallback(this);
		
		// Load the image into game
		//image= BitmapFactory.decodeResource(getResources(), R.drawable.helicopter_military_big2);
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
		
		Loop= new GameLoop(getHolder(), this);
		// Allows the callbacks to work
		this.setFocusable(true);
		
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub
		Log.d("Custom", "Surface has been changed");
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		Log.d("Custom", "Surface has been created");
		Loop.setIsRunning(true);
		Loop.start();
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		Log.d("Custom", "Surface has been demoed");
	}
	
	public void render(Canvas canvas){
		//gameImage.draw(canvas);
		helicopterNewsImage.helicopter_news(canvas);
		helicopterMiliaryImage.helicopter_military(canvas);
		redBuildingImage.red_building(canvas);
		blueBuildingImage.blue_building(canvas);
		yellowBuildingImage.yellow_building(canvas);
		characterImage.main_character(canvas);
	}
}
