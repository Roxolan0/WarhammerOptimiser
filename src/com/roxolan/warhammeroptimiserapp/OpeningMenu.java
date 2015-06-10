package com.roxolan.warhammeroptimiserapp;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import warhammerOptimiser.business.Character;
import warhammerOptimiser.business.Optimiser;
import warhammerOptimiser.business.ResultTypeUtility;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class OpeningMenu extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_opening_menu);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.opening_menu, menu);
		return true;
	}

	@Override
	public void onResume() {
		super.onResume();
		TextView textName = (TextView) findViewById(R.id.textName);
		String characterName = ((ApplicationExtension) getApplication())
				.getCharacter().getName();
		if (characterName != "") {
			textName.setText(characterName);
		} else {
			textName.setText(getString(R.string.defaultCharacterName));
		}
	}

	/** Called when the user touches the "Quick character creation" button */
	public void create(View view) {
		Intent intent = new Intent(this, QuickCharacterCreation.class);
		startActivity(intent);
	}

	/** Called when the user touches the "Customise character" button */
	public void customise(View view) {
		Intent intent = new Intent(this, CustomiseCharacter.class);
		startActivity(intent);
	}

	/** Called when the user touches the "Optimise character" button */
	public void optimise(View view) {
		Intent intent = new Intent(this, Optimisations.class);
		startActivity(intent);
	}
}
