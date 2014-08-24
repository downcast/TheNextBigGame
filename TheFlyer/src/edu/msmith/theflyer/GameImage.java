package edu.msmith.theflyer;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class GameImage {

    private Bitmap bitmap; // the actual bitmap
    private static int x=0;//flying guy
	private static int y= 0;
	private final int CLOUDMOVE= 1; // We use these constants to control the pixel move speed of each category of images
	private final int BUILDMOVE= 2;
	private final int HELIMOVE= 3;
	public static int cloudLocationX, buildLocationX, heliLocationX= 1256; // We use these to track the x (in relation to y) location in each category of images 
	private int screenheight, screenwidth;


    // Constructor for this class
    public GameImage(Bitmap bitmap,  int screenHeight, int screenWidth) {
        this.bitmap= bitmap;
        GameImage.x= bitmap.getWidth();
        GameImage.y= bitmap.getHeight();
        this.screenheight = screenHeight;
        this.screenwidth = screenWidth;
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

	protected synchronized void setY(int y) {
		GameImage.y = y;
	}
	
	//cloud
    public void cloud(Canvas canvas) {
           canvas.drawBitmap(bitmap, GameImage.cloudLocationX,this.screenheight / 3, null);
           cloudLocationX-=this.CLOUDMOVE;//LocationX = LocationX + 5
       }
    
    
    //helicopter news
    public void helicopter_news(Canvas canvas) {
           canvas.drawBitmap(bitmap, GameImage.heliLocationX,this.screenheight / 3, null);
           GameImage.heliLocationX-=this.HELIMOVE;
       }
    
    //helicopter military
    public void helicopter_military(Canvas canvas) {
        canvas.drawBitmap(bitmap, GameImage.heliLocationX,this.screenheight / 3, null);
        GameImage.heliLocationX-=this.HELIMOVE;
    }
    
    //red building
    public void red_building(Canvas canvas) {
        canvas.drawBitmap(bitmap, GameImage.buildLocationX,this.screenheight, null);
        GameImage.buildLocationX-=this.BUILDMOVE;
    }
    
    //blue building
    public void blue_building(Canvas canvas) {
        canvas.drawBitmap(bitmap, GameImage.buildLocationX,this.screenheight, null);
        GameImage.buildLocationX-=this.BUILDMOVE;
    }
    
    //yellow building
    public void yellow_building(Canvas canvas) {
        canvas.drawBitmap(bitmap, GameImage.buildLocationX,this.screenheight, null);
        GameImage.buildLocationX-=this.BUILDMOVE;
    }
    
    public void main_character(Canvas canvas) {
        canvas.drawBitmap(bitmap, 0, GameImage.y, null);  
    }
}