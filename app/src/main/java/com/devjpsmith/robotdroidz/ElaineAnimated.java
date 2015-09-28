package com.devjpsmith.robotdroidz;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

/**
 * Created by James on 9/27/2015.
 */
public class ElaineAnimated {

    private static final String TAG = "ElaineAnimated";

    private Bitmap bitmap;      // the animation sequence
    private Rect sourceRect;    // the rectangle to be drawn from the animation btimap

    private  int frameNr;       // number of frames in the animation
    private int currentFrame;   // the current frame
    private long frameTicker;   // the time of the last frame update
    private int framePeriod;    // milliseconds between each frame update (1000/fps);

    private int spriteWidth;    // the width of the sprite to calculate the cut-out rect
    private int spriteHeight;   // the height of the sprite to calculate the cut-out rect
    private int x;              // the X Coordinate of the object (top left of the image)
    private int y;              // the Y Coordinate of the object (top left of the image)

    private Speed speed;

    public int getX(){
        return this.x;
    }

    public void setX(int x){
        this.x = x;
    }

    public void setY(int y){
        this.y = y;
    }

    public int getY(){
        return this.y;
    }

    public ElaineAnimated(Bitmap bitmap, int x, int y, int width, int height, int fps, int frameCount){
        this.bitmap = bitmap;
        this.x = x;
        this.y = y;
        currentFrame = 0;
        frameNr = frameCount;
        spriteWidth = bitmap.getWidth() / frameCount;
        spriteHeight = bitmap.getHeight();
        sourceRect = new Rect(0,0, spriteWidth, spriteHeight);
        framePeriod = 1000 / fps;
        frameTicker = 0l;
        this.speed = new Speed();
        this.speed.setXv(1F);
        this.speed.setYv(0F);
    }

    public void update(long gameTime){
        this.x += (int)(this.speed.getXv() * this.speed.getxDirection());
        this.y += (int)(this.speed.getYv() * this.speed.getyDirection());

        if (gameTime > frameTicker + framePeriod){
            frameTicker = gameTime;
            // increment the frame
            currentFrame++;
            if (currentFrame >= frameNr){
                currentFrame = 0;
            }
        }
        // define the rectangle to cut-out sprite
        this.sourceRect.left = currentFrame * spriteWidth;
        this.sourceRect.right = this.sourceRect.left + spriteWidth;
    }

    public void draw(Canvas canvas){
        // where to draw the sprite
        Rect destRect = new Rect(getX(), getY(), getX() + spriteWidth, getY() + spriteHeight);
        canvas.drawBitmap(bitmap, sourceRect, destRect, null);
    }
}
