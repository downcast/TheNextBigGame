package edu.msmith.theflyer;

import java.util.ArrayList;

import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Log;
import android.view.SurfaceHolder;

public class GameLoop extends Thread implements Runnable{

	private SurfaceHolder surfaceHolder;
	private MainActivity mainActivity;
	protected Canvas canvas;
	private boolean isRunning= true;
	/** This list holds all the images that we want to draw to the screen. Images are removed from the list when the player has collided with them. PLAYER MUST BE INDEX 0*/
	private ArrayList<Image> renderImagesList;
	/**We use to control the overall render rate of the game. 33 represents 30 fps */
	private final int FPS= 33;

	public GameLoop (SurfaceHolder sh, MainActivity ma, ArrayList<Image> renderImagesList){
		super();
		this.surfaceHolder= sh;
		this.mainActivity= ma;
		this.renderImagesList= renderImagesList;
	}

	public void run(){
		while (this.isRunning){
			try {
				// Not 100% sure, but it seems to get the current canvas, as in, what the user sees.
				canvas= this.surfaceHolder.lockCanvas();
				synchronized(this.surfaceHolder){
					if (this.canvas != null){
						// This clears the screen by drawing the background color
						canvas.drawColor(Color.BLUE);
						// This ultimately makes its way down to the GameImage obj who actually draws the heli
						this.mainActivity.render(canvas, this.renderImagesList);
						// This is what unlocks the canvas and outputs what we drew to the user.
						this.surfaceHolder.unlockCanvasAndPost(canvas);
						// This is what will check to see if any objects collided with the player
						checkCollisions(1);
					}
				}
				// Makes the game wait, I would like to have this handled by a handler if that is possible
				GameLoop.sleep(this.FPS, 0);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/** Recursive function that check to see if the player has collided with the image.
	 * <br>
	 * Current only works from the top left point need revision.
	 * @param compareIndex The index of the image on the list that will be compared to the player
	 */
	private void checkCollisions(int compareIndex){
		if (compareIndex >= this.renderImagesList.size()){
			return;
		}
		else{
			// 
			if (checkSingleCollision(compareIndex)){
				Log.d("Custom", "Collided with, " + compareIndex+ " image");
			}
			checkCollisions(++compareIndex);
		}
		return;
	}

	/** Only checks collision in relation to the player
	 * 
	 * @param compareIndex The index that should be compared to the player
	 * @return Whether or no a collision was detected
	 */
	private boolean checkSingleCollision(int compareIndex) {
		// TODO Auto-generated method stub
		if (this.renderImagesList.get(0).getxLocation() < this.renderImagesList.get(compareIndex).getxLocation()){
			if (this.renderImagesList.get(compareIndex).getxLocation() < this.renderImagesList.get(0).getImageXEdge()){
				if (this.renderImagesList.get(0).getyLocation() < this.renderImagesList.get(compareIndex).getyLocation()){
					if (this.renderImagesList.get(compareIndex).getyLocation() < this.renderImagesList.get(0).getImageYEdge()){
						Log.d("Custom", "Collsion detected, removing image from render list");
						this.renderImagesList.remove(compareIndex);
						return true;
					}
				}
			}
			
		}
		return false;
	}

	public synchronized void setIsRunning(boolean bool){
		this.isRunning= bool;
	}

	protected synchronized void setSurfaceHolder(SurfaceHolder surfaceHolder) {
		this.surfaceHolder = surfaceHolder;
	}

	protected synchronized void setMainActivity(MainActivity mainActivity) {
		this.mainActivity = mainActivity;
	}
	public boolean getIsRunning(){
		return isRunning;
	}

}
