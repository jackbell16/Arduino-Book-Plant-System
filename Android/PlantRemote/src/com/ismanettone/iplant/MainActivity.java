package com.ismanettone.iplant;

import task.ConnectionTask;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class MainActivity extends Activity {
	
	private SharedPreferences settings;
	private ImageButton on;
	private ImageButton off;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		settings = PreferenceManager.getDefaultSharedPreferences(this);
		if(settings.getString("ipAddress", "")!=""){
			setContentView(R.layout.activity_main);
			on = (ImageButton) findViewById(R.id.imageButton1s);
			off = (ImageButton) findViewById(R.id.imageButton2s);
			buttonAction(on, "on",R.string.action_on);
			buttonAction(off, "off",R.string.action_off);
		}else{
			Intent intent = new Intent(MainActivity.this,WelcomeActivity.class);
	    	startActivity(intent);
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			Intent intent = new Intent(MainActivity.this,Prefs.class);
	    	startActivity(intent);
			return true;
		}
		if (id == R.id.item1) {
			Intent intent = new Intent(MainActivity.this,WelcomeActivity.class);
	    	startActivity(intent);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	private void buttonAction(ImageButton button,final String action, final int actionOn){
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ConnectionTask task = new ConnectionTask(MainActivity.this, actionOn);
				String host = settings.getString("ipAddress", "");
				task.execute(host,action);
			}
		});
	}
}
