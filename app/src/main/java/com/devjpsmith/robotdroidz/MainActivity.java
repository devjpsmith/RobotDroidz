package com.devjpsmith.robotdroidz;

import android.app.Activity;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends Activity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // requesting to turn title off
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // making it full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        // set our main game panel as the view
        setContentView(new MainGamePanel(this));
    }

    @Override
    protected void onDestroy(){
        Log.d(TAG, "Destroying...");
        super.onDestroy();
    }

    @Override
    protected void onStop(){
        Log.d(TAG, "Stopping...");
        super.onStop();
    }
}
