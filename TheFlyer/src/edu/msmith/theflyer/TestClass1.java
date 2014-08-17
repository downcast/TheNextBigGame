package edu.msmith.theflyer;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/** This is the view of the MainActivity like mainActivity.xml. Just setContentView to this class.
 * 
 * @author Marcus
 *
 */
public class TestClass1 extends SurfaceView implements SurfaceHolder.Callback{

	private GameLoop Loop; // Seperate Thread
	private Bitmap image;  // The helicopter Image in raw form
	private GameImage gameImage; // The helicopter in object form
	
	public TestClass1(Activity activity) {
		super(activity);
		//intercept callback events
		getHolder().addCallback(this);
		
		// Load the image into game
		image= BitmapFactory.decodeResource(getResources(), R.drawable.testheli);
		// Attach image to the custom obj
		gameImage= new GameImage(image);
		
		//Loop= new GameLoop(getHolder(), this);
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
		gameImage.draw(canvas);
	}
}
