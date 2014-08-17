package edu.msmith.theflyer;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class GameImage {

    private Bitmap bitmap; // the actual bitmap
    public static int move= 1; // This is what I used to make the image move

    // Constructor for this class
    public GameImage(Bitmap bitmap) {
        this.bitmap= bitmap;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    /* Method to draw images on Canvas */
    ///public void draw(Canvas canvas) {
     //   canvas.drawBitmap(bitmap, move,5, null);
     //   move+=5;//move = move + 5
    //}
    
    
    //helicopter news
    public void helicopter_news(Canvas canvas) {
           canvas.drawBitmap(bitmap, move,5, null);
           move+=5;//move = move + 5
       }
    
    //helicopter military
    public void helicopter_military(Canvas canvas) {
        canvas.drawBitmap(bitmap, move,5, null);
        move+=5;//move = move + 5
    }
    
    //red building
    public void red_building(Canvas canvas) {
        canvas.drawBitmap(bitmap, move,5, null);
        move+=5;//move = move + 5
    }
    
    //blue building
    public void blue_building(Canvas canvas) {
        canvas.drawBitmap(bitmap, move,5, null);
        move+=5;//move = move + 5
    }
    
    //yellow building
    public void yellow_building(Canvas canvas) {
        canvas.drawBitmap(bitmap, move,5, null);
        move+=5;//move = move + 5
    }
    
    //the buildings and helicopters are the only things moving
    //characters
    public void main_character(Canvas canvas) {
        canvas.drawBitmap(bitmap, move,5, null);
        move+=5;//move = move + 5
    }
}