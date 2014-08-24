package edu.msmith.theflyer;

import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Log;
import android.view.SurfaceHolder;

public class GameLoop extends Thread implements Runnable{

	// Allows access to the surface
	private SurfaceHolder surfaceHolder;
	private MainActivity mainActivity;
	protected Canvas canvas;
	// This or similar vari we can use to end the game
	private boolean isRunning= true;
	private final int FPS= 33;	// We use to control the overall render rate of the game. 33 represents 30 fps

	public GameLoop (SurfaceHolder sh, MainActivity ma){
		super();
		this.surfaceHolder= sh;
		this.mainActivity= ma;
	}

	public void run(){
		while (this.isRunning){
			Log.d("Custom", "GameLoop is running");
			try {
				// Not 100% sure, but it seems to get the current canvas, as in, what the user sees.
				canvas= this.surfaceHolder.lockCanvas();
				// Left a link on trello explain what this does. Basically, it keep multiple threads from using this specific set of code
				synchronized(this.surfaceHolder){
					// This stops the program from throwing a unresponsive program error. Error caused when the user pushes the home button in the
					// middle of the app
					if (this.canvas != null){
						// This clears the screen by drawing the background color
						canvas.drawColor(Color.BLUE);
						// This ultimately makes its way down to the GameImage obj who actually draws the heli
						this.mainActivity.render(canvas);
						// This is what unlocks the canvas and outputs what we drew to the user.
						this.surfaceHolder.unlockCanvasAndPost(canvas);
					}
				}
				// Makes the game wait, I would like to have this handled by a handler if that is possible
				GameLoop.sleep(this.FPS, 0);
				// Use the timing stuff from java to count when the stuff should execute to hopefully be able to stop the thread.
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

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
