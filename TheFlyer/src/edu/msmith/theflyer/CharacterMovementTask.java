package edu.msmith.theflyer;

import android.os.AsyncTask;

/**
 * Class is used to make the character move up and down fluidly
 * @author Marcus
 */
public class CharacterMovementTask extends AsyncTask<Void, Void, Void>{
	
	private Image charaImage;
	private int value;
	private int screenHeight;
	/** A higher value means the character moves slower */
	private final int MOVESPEED= 25;
	
	public CharacterMovementTask(Image characterImage, int move, int screenHeight){
		this.charaImage= characterImage;
		this.value= move;
		this.screenHeight= screenHeight;
	}
	
	@Override
	protected Void doInBackground(Void... ag0) {
		// TODO Auto-generated method stub
		while (!this.isCancelled()){
			if (this.charaImage.getyLocation() < 0){
				this.charaImage.setyLocation(0);
			}else if (this.charaImage.getImageYEdge() > screenHeight){
				this.charaImage.alignToLowerWall();
			} else{
				this.charaImage.updateYLocation(value);
			}
			try {
				Thread.sleep(MOVESPEED, 0);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
}
