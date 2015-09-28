package com.devjpsmith.robotdroidz;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by James on 9/27/2015.
 */
public class Droid {

    private static final String TAG = "Droid";

    private Bitmap bitmap; // the actual bitmap
    private boolean touched; // if droid is touched(picked up)
    private int x;
    private int y;

    public Droid(Bitmap bitmap, int x, int y){
        this.bitmap = bitmap;
        this.x = x;
        this.y = y;
        this.speed = new Speed();
        this.speed.setXv(2F);
        this.speed.setYv(2F);
    }

    public Speed speed;

    public Speed getSpeed(){
        return this.speed;
    }

    public void update(){
        if (!touched){
            x += (int)(speed.getXv() * speed.getxDirection());
            y += (int)(speed.getYv() * speed.getyDirection());
        }
    }

    public Bitmap getBitmap(){
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap){
        this.bitmap = bitmap;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public void setX(int x){
        this.x = x;
    }

    public void setY(int y){
        this.y = y;
    }

    public boolean isTouched(){
        return touched;
    }

    public void setTouched(boolean touched){
        this.touched = touched;
    }

    public void draw(Canvas canvas){
        canvas.drawBitmap(bitmap, x - (bitmap.getWidth() / 2), y - (bitmap.getHeight() / 2), null);
    }

    public void handleActionDown(int eventX, int eventY){
        if (eventX >= (x - bitmap.getWidth()/2) && (eventX <= (x + bitmap.getWidth()/2))){
            if (eventY >= (y - bitmap.getHeight()/2) && (y <= (y + bitmap.getHeight()/2))){
                // droid touched
                setTouched(true);
            }
            else {
                setTouched(false);
            }
        }
        else {
            setTouched(false);
        }
    }
}
