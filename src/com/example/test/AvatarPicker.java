package com.example.test;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class AvatarPicker extends Activity {

	// User Choice;  Default is 0
	int userChoice = 0;
	
	final String TAG = "AvatarPicker";
	
	// Grab a handle on the Image Views for comparison
	
	ImageView imageViewOne ;
	ImageView imageViewTwo;
	ImageView imageViewThree;
	ImageView imageViewFour;
	ImageView imageViewFive;
	ImageView imageViewSix;
	SharedPreferences prefs;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blue_chat__avatar_picker);
        
        prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
    	final SharedPreferences.Editor editor = prefs.edit();
        
        
        imageViewOne = (ImageView)findViewById(R.id.imageView1);
    	imageViewTwo = (ImageView)findViewById(R.id.imageView2);
    	imageViewThree = (ImageView)findViewById(R.id.imageView3);
    	imageViewFour = (ImageView)findViewById(R.id.imageView4);
    	imageViewFive = (ImageView)findViewById(R.id.imageView5);
    	imageViewSix = (ImageView)findViewById(R.id.imageView6);
        

        imageViewOne.setOnClickListener(new View.OnClickListener() {

        	@Override
        	public void onClick(View v) {
        		userChoice = 1;
        		Log.i(TAG, "userChoice is: " + userChoice);
        		
        		editor.putInt("ProfilePhoto",userChoice);
        		editor.commit();
        		finish();
        		
        	}
        });
            
        imageViewTwo.setOnClickListener(new View.OnClickListener() {

        	@Override
        	public void onClick(View v) {
        		userChoice = 2;
        		Log.i(TAG, "userChoice is: " + userChoice);
        		editor.putInt("ProfilePhoto",userChoice);
        		editor.commit();
        		finish();

        	}
        });
        
        imageViewThree.setOnClickListener(new View.OnClickListener() {

        	@Override
        	public void onClick(View v) {
        		userChoice = 3;
        		Log.i(TAG, "userChoice is: " + userChoice);
        		editor.putInt("ProfilePhoto",userChoice);
        		editor.commit();
        		finish();
        	}
        });
        imageViewFour.setOnClickListener(new View.OnClickListener() {

        	@Override
        	public void onClick(View v) {
        		userChoice = 4;
        		Log.i(TAG, "userChoice is: " + userChoice);
        		editor.putInt("ProfilePhoto",userChoice);
        		editor.commit();
        		finish();

        	}
        });
            
        imageViewFive.setOnClickListener(new View.OnClickListener() {

        	@Override
        	public void onClick(View v) {
        		userChoice = 5;
        		Log.i(TAG, "userChoice is: " + userChoice);
        		editor.putInt("ProfilePhoto",userChoice);
        		editor.commit();
        		finish();

        	}
        });
        
        imageViewSix.setOnClickListener(new View.OnClickListener() {

        	@Override
        	public void onClick(View v) {
        		userChoice = 6;
        		editor.putInt("ProfilePhoto",userChoice);
        		editor.commit();
        		Log.i(TAG, "userChoice is: " + userChoice);
        		finish();

        	}
        });       
       
    }
    
    
    
    /**
     * @todo:   Store userChoice in an Intent and return to the 
     *  previous Activity
     */
    
    
    
    
    
}
