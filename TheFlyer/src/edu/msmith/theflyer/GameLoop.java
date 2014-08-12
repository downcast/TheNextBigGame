package edu.msmith.theflyer;

import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Log;
import android.view.SurfaceHolder;

public class GameLoop extends Thread implements Runnable{
	
	// Allows access to the surface
	private SurfaceHolder surfaceHolder;
	private TestClass1 testClass;
	private Canvas canvas;
	
	// This or similar vari we can use to end the game
	private boolean isRunning;
	
	public GameLoop (SurfaceHolder sh, TestClass1 tc){
		super();
		this.surfaceHolder= sh;
		this.testClass= tc;
	}
	
	public void run(){
		// I got a null pointer exception one time after the surface was destroyed, so I put in this null check. I havent tested it yet.
		while (isRunning= true && this.surfaceHolder != null){
		Log.d("Custom", "GameLoop is running");
		try {
			// Not 100% sure, but it seems to get the current canvas, as in, what the user sees.
			canvas= this.surfaceHolder.lockCanvas();
			// Left a link on trello explain what this does. Basically, it keep multiple threads from using this specific set of code
			synchronized(this.surfaceHolder){
				// This clears the screen by drawing the background color
				canvas.drawColor(Color.BLACK);
				// This ultimately makes its way down to the GameImage obj who actually draws the heli
				this.testClass.render(canvas);
				// This is what unlocks the canvas and outputs what we drew to the user.
				this.surfaceHolder.unlockCanvasAndPost(canvas);
			}
			// Makes the game wait, I would like to have this handled by a handler if that is possible
			GameLoop.sleep(1000, 0);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		}
	}
	
	public void setIsRunning(boolean bool){
		this.isRunning= bool;
	}
	
	public boolean getIsRunning(){
		return isRunning;
	}
}
