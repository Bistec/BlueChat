package com.example.test;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.TypedValue;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class BlueChat_ProfileView extends Activity {
	
	private TextView NameTxt;
	private TextView DescriptionTxt;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_blue_chat__profile_view);
		
		Bundle extras = getIntent().getExtras(); 
		String Name;
		String Description;
		if (extras != null) {
		    Name = extras.getString("Name");
		    Description=extras.getString("Description");
		    // and get whatever type user account id is
		    NameTxt = (TextView)findViewById(R.id.textView1);
		    DescriptionTxt = (TextView)findViewById(R.id.textView2);
		    NameTxt.setText(Name);
		    DescriptionTxt.setText(Description);
		    }

	}	

}
