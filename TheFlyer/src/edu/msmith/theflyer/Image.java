package edu.msmith.theflyer;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/** This class is the parent of all game images used in the game. It holds all the common functions that all images will need to function
 *
 * @author Marcus
 *
 */
public abstract class Image {

	/** This is the actually image */
	protected Bitmap gameImage;
	protected int screenHeight;
	protected int screenWidth;
	/** Holds the canvas x-coordinate location of the image. */
	protected int xLocation;
	/** Holds the canvas y-coordinate location of the image. */
	protected int yLocation;
	protected int imageWidth;
	protected int imageHeight;

	/** Superclass constructor for all images loaded into the game
	 * 
	 * @param bitmap The image returned by BitmapFactory.decodeResource
	 * @param screenHeight The height of the device screen.
	 * @param screenWidth The width of the device screen.
	 * @param xInitial The starting x coordinate value of the image
	 * @param yInitial The starting y coordinate value of the image
	 */
	public Image(Bitmap bitmap, int screenHeight, int screenWidth, int xInitial, int yInitial) {
		this.gameImage= bitmap;
		this.screenHeight = screenHeight;
		this.screenWidth = screenWidth;
		this.xLocation= xInitial;
		this.yLocation= yInitial;
		this.imageHeight= this.gameImage.getHeight();
		this.imageWidth= this.gameImage.getWidth();
	}

	protected int getxLocation() {
		return xLocation;
	}
	
	/** Used to change the x-coordinate location of the image. This function should be used to choose a custom location 
	 * for an image. To update the x-axis by a set number, use the updateX function instead.
	 * @param xLocation The horizontal location an image will be when the system renders the image
	 */
	protected synchronized void setxLocation(int xLocation) {
		this.xLocation = xLocation;
	}
	
	/** Used to update the horizontal location of an image. Use this method in a loop to take current xLocation and add value to it.
	 * 
	 * @param value Amount that the current xLocation should increase by (+10) or decrease by (-10)
	 */
	protected void updateXLocation(int value){
		this.setxLocation(this.getxLocation()+value);
	}

	protected int getyLocation() {
		return yLocation;
	}

	/**Used to change the y-coordinate location of the image. This function should be used to choose a custom location for an image. 
	 * To update the y-axis by a set number, use the updateY function instead.
	 * 
	 * @param yLocation The vertical location an image will be when the system renders the image
	 */
	protected synchronized void setyLocation(int yLocation) {
		this.yLocation = yLocation;
	}

	/** Used to update the vertical location of an image. Use this method in a loop to take current yLocation and add value to it.
	 * 
	 * @param value Amount that the current yLocation should increase by (+10) or decrease by (-10)
	 */
	protected void updateYLocation(int value){
		this.setyLocation(this.getyLocation()+value);
	}
	
	/** Returns the width (x) of the object's bitmap */
	protected int getImageWidth() {
		return this.imageWidth;
	}

	/** Returns the width (y) of the object's bitmap */
	protected int getImageHeight() {
		return this.imageHeight;
	}

	/** Returns the right-most x value of the image (right edge).*/
	protected int getImageXEdge(){
		return (this.getxLocation()+this.getImageWidth());
	}
	
	/** Returns the bottom-most y value of the image (bottom edge). */
	protected int getImageYEdge(){
		return (this.getyLocation()+this.getImageHeight());
	}
	
	/** Used to draw objects to the screen.
	 * <br>
	 * Each image type will have its own version of the draw method.
	 * @param canvas
	 */
	protected abstract void draw(Canvas canvas);
	
}
