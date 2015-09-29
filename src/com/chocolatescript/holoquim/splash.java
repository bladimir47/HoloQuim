package com.chocolatescript.holoquim;

import java.util.Timer;
import java.util.TimerTask;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class splash extends Activity{

	private static final long SPLASH_SCREEN_DELAY = 3000;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		
		TimerTask task = new TimerTask() {
            @Override
            public void run() {
 
            	Intent mainIntent = new Intent().setClass( splash.this, MainActivity.class);
                startActivity(mainIntent);
                finish();
                
                
                
                
            }
        };
 
        Timer timer = new Timer();
        timer.schedule(task, SPLASH_SCREEN_DELAY);
	}
	
	
}
