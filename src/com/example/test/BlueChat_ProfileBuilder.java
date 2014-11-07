package com.example.test;


import com.example.test.R;

import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class BlueChat_ProfileBuilder extends Activity {

	private static Bitmap Image =null;
	private static Bitmap rotateImage = null;
	private ImageView imageView;
	private static final int GALLERY = 1;
	private Uri imageuri;
	String picturePath;
	EditText description;
	EditText name;
	public static final int RESULT_GALLERY = 0;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_blue_chat__profile_builder);
		Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/Prisma.otf");
        TextView BlueChat = (TextView) findViewById(R.id.textView1);
        BlueChat.setTypeface(tf);
        BlueChat.setText("Build Your Profile");
        BlueChat.setTextSize(TypedValue.COMPLEX_UNIT_SP,50);
        imageView = (ImageView) findViewById(R.id.imageView1);
        description= (EditText) findViewById(R.id.editText1);
        name = (EditText) findViewById(R.id.editText2);
	}	
@Override
protected void onResume()
{
	super.onResume();
	SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
	
	int i = prefs.getInt("ProfilePhoto",-1);
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
	
	//Click function for profile image
	public void onClick(View v) {
		Intent i = new Intent(getApplicationContext(), AvatarPicker.class);
		 startActivity(i);
		 	//Toast toast = Toast.makeText(getApplicationContext(), "this", Toast.LENGTH_SHORT);
	    	
				 
		 
		}
	
	public void onClick2(View v)
	{
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
		SharedPreferences.Editor editor = prefs.edit();
		editor.putString("Name",name.getText().toString());
		editor.commit();
		editor.putString("Description", description.getText().toString());
		editor.commit();
		
		//Remove this to see printer
		Intent intent = new Intent(getApplicationContext(),BluetoothChat.class);
    	startActivity(intent);
   
	}
	
	public void print(View v)
	{
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
		
		String name = prefs.getString("Description","No Name");
		name+="\n"+prefs.getString("Name","No Name");
		name+="\n"+prefs .getString("ProfileURI", picturePath);
		Toast toast = Toast.makeText(getApplicationContext(), name, Toast.LENGTH_LONG);
		toast.show();
		
	}
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
	    super.onActivityResult(requestCode, resultCode, data);

	    if(requestCode==RESULT_GALLERY)
	    {
	    	if(resultCode==RESULT_OK)
	    	{
	    		
	    		imageuri=data.getData();
	    		
	    		  String[] filePathColumn = { MediaStore.Images.Media.DATA };
	    		  
	    		  Cursor cursor = getContentResolver().query(imageuri,filePathColumn, null, null, null);
	              
	               
	                cursor.moveToFirst();

	                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
	                picturePath = cursor.getString(columnIndex);
	                cursor.close();

	               ImageView imageView = (ImageView) findViewById(R.id.imageView1);
	                imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));
	                imageView.getLayoutParams().height = 500;
	                imageView.getLayoutParams().width = 500;
	    		
	           
	    		
	    		
	    	}
	    	 
	    }
	 
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.blue_chat__profile_builder, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
	/*	 int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
		*/
		return true;
	}
}
