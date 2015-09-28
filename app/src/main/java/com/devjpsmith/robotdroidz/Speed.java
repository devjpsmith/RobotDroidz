package com.devjpsmith.robotdroidz;

/**
 * Created by James on 9/27/2015.
 */
public class Speed {

    private static final String TAG = "Speed";

    public static final int DIRECTION_RIGHT = 1;
    public static final int DIRECTION_LEFT = -1;
    public static final int DIRECTION_UP = -1;
    public static final int DIRECTION_DOWN = 1;

    private float xv = 1;   // velocity value on the X axis
    private float yv = 1;   // velocity value on the Y axis

    private int xDirection = DIRECTION_RIGHT;
    private int yDirection = DIRECTION_DOWN;

    public Speed(){
        this.xv = 1;
        this.yv = 1;
    }

    public Speed(float xv, float yv){
        this.xv = xv;
        this.yv = yv;
    }

    public float getXv(){
        return this.xv;
    }

    public void setXv(float xv){
        this.xv = xv;
    }

    public float getYv(){
        return this.yv;
    }

    public void setYv(float yv){
        this.yv = yv;
    }

    public int getxDirection(){
        return this.xDirection;
    }

    public void setxDirection(int xDirection){
        this.xDirection = xDirection;
    }

    public int getyDirection(){
        return  this.yDirection;
    }

    public void setyDirection(int yDirection){
        this.yDirection = yDirection;
    }

    // change direction on the X axis
    public void toggleXDirection(){
        xDirection = xDirection * -1;
    }

    // change direction on the xAxis
    public void toggleYDirection(){
        yDirection = yDirection * -1;
    }
}
