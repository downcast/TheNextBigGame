package edu.msmith.theflyer;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class PlayerImage extends Image {

	/** PlayerImage Class constructor
	 * 
	 * @param bitmap The image returned by BitmapFactory.decodeResource
	 * @param screenHeight The height of the device screen.
	 * @param screenWidth The width of the device screen.
	 * @param xInitial The starting x coordinate value of the image
	 * @param yInitial The starting y coordinate value of the image
	 */
	public PlayerImage(Bitmap bitmap, int screenHeight, int screenWidth, int xInital, int yInitial) {
		/* This makes a call to the Image class constructor to initialize its data so that the PlayerImage can function correctly*/
		super(bitmap, screenHeight, screenWidth, xInital, yInitial);
		
	}
	
	/** Draws the character to the screen but does not update its position for the next draw. This is handled by the CharacterMovementTask */
	@Override
	protected void draw(Canvas canvas){
		canvas.drawBitmap(gameImage, this.xLocation,  this.yLocation, null);
	
	}
}
