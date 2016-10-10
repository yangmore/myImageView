package com.example.myimageview;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		  setContentView(R.layout.activity_main);
	        Constans.mContext=getApplicationContext();
	        
	       MyImageView myImageView=  (MyImageView) findViewById(R.id.hairIv);
//	       Bitmap bit = BitmapFactory.decodeResource(null, R.drawable.ic_launcher);
//	       myImageView.setImageResource(R.drawable.ic_launcher);
	       
	   int width=    this.getWindowManager().getDefaultDisplay().getWidth();
	     int height=  this.getWindowManager().getDefaultDisplay().getHeight();
	     Constans.screenWidth=width;
	     Constans.screenHeigt=height;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
