package edu.msmith.theflyer;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.Log;

public class GameImage {

    private Bitmap bitmap; // the actual bitmap
    private static int x=0;//flying guy
	private static int y= 0;
	private final int CLOUDMOVE= 1; // We use these constants to control the pixel move speed of each category of images
	private final int BUILDMOVE= 2;
	private final int HELIMOVE= 3;
	public static int cloudLocationX, buildLocationX, heliLocationX= 1256; // We use these to track the x (in relation to y) location in each category of images 
	private int screenheight, screenwidth;
	public boolean ready= true;

    // Constructor for this class
    public GameImage(Bitmap bitmap,  int screenHeight, int screenWidth) {
        this.bitmap= bitmap;
        GameImage.x= bitmap.getWidth();
        GameImage.y= bitmap.getHeight();
        this.screenheight = screenHeight;
        this.screenwidth = screenWidth;
    }
    
    public boolean readyRender(){
    	return this.ready;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    protected int getX() {
		return x;
	}

	protected void setX(int x) {
		GameImage.x = x;
	}
	

	protected int getY() {
		return y;
	}

	protected void setY(int y) {
		GameImage.y = y;
	}
	
    /* Method to draw images on Canvas */
    ///public void draw(Canvas canvas) {
     //   canvas.drawBitmap(bitmap, LocationX,5, null);
     //   LocationX+=5;//LocationX = LocationX + 5
    //}
	
	//cloud
    public void cloud(Canvas canvas) {
           canvas.drawBitmap(bitmap, this.cloudLocationX,this.screenheight / 3, null);
           cloudLocationX-=this.CLOUDMOVE;//LocationX = LocationX + 5
       }
    
    
    //helicopter news
    public void helicopter_news(Canvas canvas) {
           canvas.drawBitmap(bitmap, this.heliLocationX,this.screenheight / 3, null);
           this.heliLocationX-=this.HELIMOVE;//LocationX = LocationX + 5
       }
    
    //helicopter military
    public void helicopter_military(Canvas canvas) {
        canvas.drawBitmap(bitmap, this.heliLocationX,this.screenheight / 3, null);
        this.heliLocationX-=this.HELIMOVE;//LocationX = LocationX + 5
    }
    
    //red building
    public void red_building(Canvas canvas) {
        canvas.drawBitmap(bitmap, this.buildLocationX,this.screenheight, null);
        this.buildLocationX-=this.BUILDMOVE;//LocationX = LocationX + 5
    }
    
    //blue building
    public void blue_building(Canvas canvas) {
        canvas.drawBitmap(bitmap, this.buildLocationX,this.screenheight, null);
        this.buildLocationX-=this.BUILDMOVE;//LocationX = LocationX + 5
    }
    
    //yellow building
    public void yellow_building(Canvas canvas) {
        canvas.drawBitmap(bitmap, this.buildLocationX,this.screenheight, null);
        this.buildLocationX-=this.BUILDMOVE;//LocationX = LocationX + 5
    }
    
    //the buildings and helicopters are the only things moving
    //characters
    public void main_character(Canvas canvas) {
        canvas.drawBitmap(bitmap, 0, GameImage.y, null);
        Log.d("Custom", "Character drawn");
       // LocationX+=5;//LocationX = LocationX + 5
    }
}