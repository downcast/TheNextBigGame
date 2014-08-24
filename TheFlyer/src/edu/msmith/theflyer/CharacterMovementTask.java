package edu.msmith.theflyer;

import android.os.AsyncTask;
import android.util.Log;

/**
 * Class is used to make the character move up and down fluidly
 * @author Marcus
 */
public class CharacterMovementTask extends AsyncTask<Void, Void, Void>{
	
	private GameImage charaImage;
	private int value;
	/** A higher value means the character moves slower */
	private final int MOVESPEED= 25;
	
	public CharacterMovementTask(GameImage characterImage, int move){
		this.charaImage= characterImage;
		this.value= move;
	}
	
	@Override
	protected Void doInBackground(Void... ag0) {
		// TODO Auto-generated method stub
		while (!this.isCancelled()){
			Log.d("Custom", "Chara Move");
			this.charaImage.setY(charaImage.getY()+this.value);
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
