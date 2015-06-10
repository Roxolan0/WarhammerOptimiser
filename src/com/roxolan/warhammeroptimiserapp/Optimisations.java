package com.roxolan.warhammeroptimiserapp;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class Optimisations extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_optimisations);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.optimisations, menu);
		return true;
	}
	
	/** Called when the user touches the "Average damage" button */
	public void averageDamage (View view) {
	    Intent intent = new Intent(this, AverageDamage.class);
	    startActivity(intent);
	}

	/** Called when the user touches the "Best action cards" button */
	public void bestActionCards (View view) {
	    Intent intent = new Intent(this, BestActionCards.class);
	    startActivity(intent);
	}
}
