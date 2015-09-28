package com.devjpsmith.robotdroidz; /**
 * Created by James on 9/26/2015.
 */

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.devjpsmith.robotdroidz.MainThread;

public class MainGamePanel extends SurfaceView implements SurfaceHolder.Callback {
    private static final String TAG = "com.devjpsmith.robotdroidz.MainGamePanel";

    private MainThread thread;
    private Droid droid;
    private ElaineAnimated elaine;
    private String avgFps;

    public void setAvgFps(String avgFps){
        this.avgFps = avgFps;
    }

    private void displayFps(Canvas canvas, String fps){
        if (canvas != null && fps != null){
            Paint paint = new Paint();
            paint.setARGB(255, 255, 255, 255);
            canvas.drawText(fps, this.getWidth() - 50, 20, paint);
        }
    }

    public MainGamePanel(Context c) {
        super(c);
        // add the callback (this) to the holder to intercept events
        getHolder().addCallback(this);

        // create droid and load bitmap
        droid = new Droid(BitmapFactory.decodeResource(getResources(), R.drawable.droid_1), 50, 50);
        elaine = new ElaineAnimated(BitmapFactory.decodeResource(getResources(), R.drawable.walk_elaine)
        ,10 , 50    // initial position
        ,30, 47     // width and height of the sprite
        ,5, 5);     // FPS and number of frames in the animation

        // create the game loop
        thread = new MainThread(getHolder(), this);

        // make the game panel focusable so it can handle events
        setFocusable(true);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height){

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder){
        thread.setRunning(true);
        thread.start();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder){
        boolean retry = true;
        while (retry){
            try {
                thread.join();
                retry = false;
            }
            catch (InterruptedException e){
                // try again shutting down the thread
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        if (event.getAction() == MotionEvent.ACTION_DOWN){
            // delegating event handling to the droid
            droid.handleActionDown((int)event.getX(), (int)event.getY());

            // check if in the lower part of the screen we exit
            if (event.getY() > getHeight() - 50){
                thread.setRunning(false);
                ((Activity)getContext()).finish();
            }
            else {
                Log.d(TAG, "Coords: x=" + event.getX() + " y=" + event.getY());
            }
        }
        if (event.getAction() == MotionEvent.ACTION_MOVE){
            // the gestures
            if (droid.isTouched()){
                // the droid was picked up and is being dragged
                droid.setX((int)event.getX());
                droid.setY((int) event.getY());;
            }
        }
        if (event.getAction() == MotionEvent.ACTION_UP){
            // touch was released
            if (droid.isTouched()){
                droid.setTouched(false);
            }
        }

        return true;
    }

    public void update(){
        // check collision with the right wall if heading right
        if (droid.getSpeed().getxDirection() == Speed.DIRECTION_RIGHT
                && droid.getX() + droid.getBitmap().getWidth() / 2 >= getWidth()){
            droid.getSpeed().toggleXDirection();
        }
        // check collision with the left wall if heading left
        if (droid.getSpeed().getxDirection() == Speed.DIRECTION_LEFT
                && droid.getX() - droid.getBitmap().getWidth() / 2 <= 0){
            droid.getSpeed().toggleXDirection();
        }
        // check collision with the bottom wall if heading down
        if (droid.getSpeed().getyDirection() == Speed.DIRECTION_DOWN
                && droid.getY() + droid.getBitmap().getHeight()/ 2 >= getHeight()){
            droid.getSpeed().toggleYDirection();
        }
        // check collision with the top wall if heading up
        if (droid.getSpeed().getyDirection() == Speed.DIRECTION_UP
                && droid.getY() - droid.getBitmap().getHeight() / 2 <= 0){
            droid.getSpeed().toggleYDirection();
        }
        droid.update();
        elaine.update(this.thread.getGameTimer());
    }


    //@Override
    protected void render(Canvas canvas){
        canvas.drawColor(Color.BLACK);
        droid.draw(canvas);
        elaine.draw(canvas);
        displayFps(canvas, avgFps);
    }
}

