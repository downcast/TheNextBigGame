package edu.msmith.theflyer;

import java.util.ArrayList;
import java.util.Random;

import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Log;
import android.view.SurfaceHolder;

public class GameLoop extends Thread implements Runnable{

	private SurfaceHolder surfaceHolder;
	private MainActivity mainActivity;
	protected Canvas canvas;
	private boolean isRunning= true;
	/** This list holds all the images that we want to draw to the screen. This list can have duplicates of specific ObstacleImageClass objects.
	 * <h1>Ex:</h1> It is acceptable to have three red buildings and two helicopter in the list. Images are removed from the list when the player has collided with them. 
	 * <h1>PLAYER MUST BE INDEX 0 </h1>*/
	private ArrayList<Image> renderImagesList= new ArrayList<Image>();
	/** This list holds each type of image used in the game. Unlike the renderImageList, only one type of ObstacleImageClass should be in this list.
	 * <h1> Ex:</h1> It is acceptable to have ONE red building, ONE yellow building, ONE military helicopter.
	 *<h1>PLAYER MUST BE INDEX 0 </h1>*/
	private ArrayList<Image> gameImageList;
	/**We use to control the overall render rate of the game. 33 represents 30 fps */
	private final int FPS= 33;
	private Random randSpawnIndex= new Random();
	/** Used with the spawn method to determine if a obstacle should spawn */
	private long start= 0;

	public GameLoop (SurfaceHolder sh, MainActivity ma, ArrayList<Image> gameImagesList){
		super();
		this.surfaceHolder= sh;
		this.mainActivity= ma;
		this.gameImageList= gameImagesList;
		// Sets the player to the 0 index
		this.renderImagesList.add(this.gameImageList.get(0));
		this.start= System.currentTimeMillis();
	}

	public void run(){
		while (this.isRunning){
			try {
				if (System.currentTimeMillis()- start >= 2000){
					Log.d("Custom", "Spawn"+ (System.currentTimeMillis()- start));
					objectSpawn();
					this.start= System.currentTimeMillis();
				}
				// Not 100% sure, but it seems to get the current canvas, as in, what the user sees.
				canvas= this.surfaceHolder.lockCanvas();
				synchronized(this.surfaceHolder){
					if (this.canvas != null){
						// Removes images that have moved off the screen
						renderImageListCheck();
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
				// Makes the game wait
				GameLoop.sleep(this.FPS, 0);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void renderImageListCheck() {
		for (int i=0; i < renderImagesList.size(); i++){
			while (this.renderImagesList.get(i).getxLocation() < 0){
				this.renderImagesList.remove(i);
				Log.d("Custom", "ImageCheck reset");
				i--;
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
		if (this.renderImagesList.get(0).getImageXEdge() >= this.renderImagesList.get(compareIndex).getxLocation()){
			if (this.renderImagesList.get(0).getImageXEdge() <= this.renderImagesList.get(compareIndex).getImageXEdge()){
				if(this.renderImagesList.get(0).getyLocation() >= this.renderImagesList.get(compareIndex).getyLocation()){
					if (this.renderImagesList.get(0).getyLocation() <= this.renderImagesList.get(compareIndex).getImageYEdge()){
						Log.d("Custom", "Player Collsion detected, removing image from render list");
						this.renderImagesList.remove(compareIndex);
						//this.renderImagesList.remove(o);
						return true;
						//}
					}
				}else if (this.renderImagesList.get(0).getImageYEdge() >= this.renderImagesList.get(compareIndex).getyLocation()){
					if (this.renderImagesList.get(0).getImageYEdge() <= this.renderImagesList.get(compareIndex).getImageYEdge()){
						Log.d("Custom", "Player Collsion detected, removing image from render list");
						this.renderImagesList.remove(compareIndex);
						//this.renderImagesList.remove(o);
						return true;
					}
				}
			}
		}
		if (this.renderImagesList.get(0).getxLocation() < this.renderImagesList.get(compareIndex).getxLocation()){
			if (this.renderImagesList.get(compareIndex).getxLocation() < this.renderImagesList.get(0).getImageXEdge()){
				if (this.renderImagesList.get(0).getyLocation() < this.renderImagesList.get(compareIndex).getyLocation()){
					if (this.renderImagesList.get(compareIndex).getyLocation() < this.renderImagesList.get(0).getImageYEdge()){
						Log.d("Custom", "Building Collsion detected, removing image from render list");
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

	/** Method adds a random obstacle to the renderImageList. 
	 * Method chooses a random index in the range of the gameImageList and adds that obstacle to the render list.*/
	private void objectSpawn() {
		int spawnIndex= this.randSpawnIndex.nextInt(this.gameImageList.size());
		if (spawnIndex == 0){
			spawnIndex++;
		}
		this.renderImagesList.add(new ObstacleImageClass((ObstacleImageClass)this.gameImageList.get(spawnIndex)));
		Log.d("Custom", "Spawning "+ spawnIndex);
	}

}
