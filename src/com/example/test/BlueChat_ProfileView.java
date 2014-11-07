package com.example.test;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.TypedValue;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class BlueChat_ProfileView extends Activity {
	
	private TextView NameTxt;
	private TextView DescriptionTxt;
	private ImageView imageView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
		
		setContentView(R.layout.activity_blue_chat__profile_view);
		
		Bundle extras = getIntent().getExtras(); 
		String Name;
		String Description;
		if (extras != null) {
		    Name = extras.getString("Name");
		    Description=extras.getString("Description");
		    // and get whatever type user account id is
		    NameTxt = (TextView)findViewById(R.id.textView3);
		    DescriptionTxt = (TextView)findViewById(R.id.textView2);
		    imageView = (ImageView)findViewById(R.id.imageView1);
		    
		    NameTxt.setText(Name);
		    DescriptionTxt.setText(Description);
		    int i=extras.getInt("ProfilePhoto");
		    if(i==0)
		    imageView.setImageResource(R.drawable.images);
		    else if(i==1)
		    imageView.setImageResource(R.drawable.one);
		    else if(i==2)
		    imageView.setImageResource(R.drawable.two);
		    else if(i==3)
		    imageView.setImageResource(R.drawable.three);
		    else if(i==4)
		    imageView.setImageResource(R.drawable.four);
		    else if(i==5)
		    imageView.setImageResource(R.drawable.five);
		    else if(i==6)
		    imageView.setImageResource(R.drawable.six);
		    
		    }

	}	

}
