package edu.msmith.theflyer;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/** This class has all the variables and methods from the image class, but adds certain methods unique to obstacles.
 *
 * @author Marcus
 *
 */
public class ObstacleImageClass extends Image {

	/** Holds the value to move the image along the x-axis by. A negative value will move the image to the left.*/
	protected int xSpeed;

	/** Class constructor.
	 * 
	 * @param bitmap The image returned by BitmapFactory.decodeResource
	 * @param screenHeight The height of the device screen.
	 * @param screenWidth The width of the device screen.
	 * @param xInitial The starting x coordinate value of the image
	 * @param yInitial The starting y coordinate value of the image
	 * @param speed The speed the obstacle will move. Use negatives to move it to the left
	 */
	public ObstacleImageClass(Bitmap bitmap, int screenHeight, int screenWidth, int xInital, int yInitial, int speed) {
		/* This makes a call to the Image class constructor to initialize its data so that the PlayerImage can function correctly*/
		super(bitmap, screenHeight, screenWidth, xInital, yInitial);
		this.xSpeed = speed;
	}

	public ObstacleImageClass(ObstacleImageClass OIC) {
		super(OIC.getGameImage(), OIC.getScreenHeight(), OIC.getScreenWidth(), 200, 100);
		this.xSpeed= OIC.getxSpeed();
	}

	/** Methods generates a new obstacles. */
	protected ObstacleImageClass getNewObstacle(int code){
		return new ObstacleImageClass(super.getGameImage(), super.getScreenHeight(), super.getScreenWidth(), 200, 100, this.xSpeed);
	}
	
	protected int getxSpeed() {
		return xSpeed;
	}

	protected void setxSpeed(int xSpeed) {
		this.xSpeed = xSpeed;
	}
	
	/** Draws obstacle to the screen and updates its position for the next draw call with the xSpeed */
	@Override
	protected void draw(Canvas canvas){
		canvas.drawBitmap(gameImage, this.xLocation,  this.yLocation, null);
		updateXLocation(xSpeed);	
	}
	
}
