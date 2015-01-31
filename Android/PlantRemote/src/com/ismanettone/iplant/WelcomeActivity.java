/**
 * This is the welcome activity, which it appears if the ip value hasn't been set
 * @author giacomobellazzi
 */
package com.ismanettone.iplant;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class WelcomeActivity extends Activity {
	
	private ImageButton button;
	private ImageButton start;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);
		button = (ImageButton) findViewById(R.id.imageButton1);
		start = (ImageButton) findViewById(R.id.imageButton2);
		buttonActionSet();
		buttonActionStart();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.welcome, menu);
		return true;
	}
	
	private void buttonActionSet(){
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(WelcomeActivity.this,Prefs.class);
		    	startActivity(intent);
				
			}
		});
	}
	
	private void buttonActionStart(){
		start.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(WelcomeActivity.this,MainActivity.class);
		    	startActivity(intent);
			}
		});
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
