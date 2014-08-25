package edu.msmith.theflyer;

import android.os.AsyncTask;

/**
 * Class is used to make the character move up and down fluidly
 * @author Marcus
 */
public class CharacterMovementTask extends AsyncTask<Void, Void, Void>{
	
	private Image charaImage;
	private int value;
	/** A higher value means the character moves slower */
	private final int MOVESPEED= 25;
	
	public CharacterMovementTask(Image characterImage, int move){
		this.charaImage= characterImage;
		this.value= move;
	}
	
	@Override
	protected Void doInBackground(Void... ag0) {
		// TODO Auto-generated method stub
		while (!this.isCancelled()){
			this.charaImage.updateYLocation(value);
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
