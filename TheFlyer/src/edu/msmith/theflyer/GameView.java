package edu.msmith.theflyer;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class GameView extends View implements OnTouchListener{

	private GameLoop Loop; // Seperate Thread
	private Bitmap image;  // The helicopter Image in raw form
	private GameImage gameImage; // The helicopter in object form
	private static int move=0;
	private static int low=0;
	public Canvas canvas= null;
	
	public GameView(Context context, AttributeSet attrs) {
		super(context, attrs);

		// Load the image into game
		image= BitmapFactory.decodeResource(getResources(), R.drawable.testheli);
		// Attach image to the custom obj
		gameImage= new GameImage(image);
		
		//intercept callback events
		//((SurfaceHolder) getHandler()).addCallback(this);

		
		// Allows the callbacks to work
		//this.setFocusable(true);
		this.setOnTouchListener(this);
		
	}
	
	@Override
	protected void onDraw(Canvas canvas){
		//if (this.canvas == null)
			this.canvas= canvas;
		this.canvas.drawBitmap(image, move, 5, null);
		move+=1;
	}
	
	protected void customOnDraw(){
		Log.d("Custom", "Custom Draw");
		///this.canvas.drawBitmap(image, 5, low, null);
		//low+=5;
	}
	
	public boolean onTouch(View view, MotionEvent event){
		Log.d("Custom", "Motion");
		invalidate();
		return true;
	}
}
