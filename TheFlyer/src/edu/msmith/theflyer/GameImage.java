package edu.msmith.theflyer;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class GameImage {

    private Bitmap bitmap; // the actual bitmap
    public static int move= 1; // This is what I used to make the image move

    // Constructor for this class
    public GameImage(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    /* Method to draw images on Canvas */
    public void draw(Canvas canvas) {
        canvas.drawBitmap(bitmap, move,5, null);
        move+=5;
    }
}