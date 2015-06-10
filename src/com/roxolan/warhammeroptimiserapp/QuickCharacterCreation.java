package com.roxolan.warhammeroptimiserapp;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import warhammerOptimiser.business.Character;
import warhammerOptimiser.business.Characteristic;
import warhammerOptimiser.business.Stance;

public class QuickCharacterCreation extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_quick_character_creation);
		
		Spinner spinRank = (Spinner) findViewById(R.id.spinRank);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.spinRank, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinRank.setAdapter(adapter);

		Spinner spinMainCharacteristic = (Spinner) findViewById(R.id.spinMainCharacteristic);
		adapter = ArrayAdapter.createFromResource(this, R.array.spinMainCharacteristic, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinMainCharacteristic.setAdapter(adapter);
		
		Spinner spinMainStance = (Spinner) findViewById(R.id.spinMainStance);
		adapter = ArrayAdapter.createFromResource(this, R.array.spinMainStance, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinMainStance.setAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.quick_character_creation, menu);
		return true;
	}
	
	private int getRank() throws UnsupportedOperationException {
		Spinner spinRank = (Spinner) findViewById(R.id.spinRank);
		String rank = spinRank.getSelectedItem().toString();
		if(rank == getString(R.string.rank0)) {
			return 0;
		} else if(rank == getString(R.string.rank1)) {
			return 1;
		} else if(rank == getString(R.string.rank2)) {
			return 2;
		} else if(rank == getString(R.string.rank3)) {
			return 3;
		} else {
			throw new UnsupportedOperationException("Unknown rank");
		}
	}
	private Characteristic getMainCharacteristic() throws UnsupportedOperationException{
		Spinner spinMainCharacteristic = (Spinner) findViewById(R.id.spinMainCharacteristic);
		String characteristic = spinMainCharacteristic.getSelectedItem().toString();
		if(characteristic == getString(R.string.strength)) {
			return Characteristic.STRENGTH;
		} else if(characteristic == getString(R.string.toughness)) {
			return Characteristic.TOUGHNESS;
		} else if(characteristic == getString(R.string.agility)) {
			return Characteristic.AGILITY;
		} else if(characteristic == getString(R.string.intelligence)) {
			return Characteristic.INTELLIGENCE;
		} else if(characteristic == getString(R.string.willpower)) {
			return Characteristic.WILLPOWER;
		} else if(characteristic == getString(R.string.fellowship)) {
			return Characteristic.FELLOWSHIP;
		} else if(characteristic == getString(R.string.versatile)) {
			return Characteristic.VERSATILE;
		} else if(characteristic == getString(R.string.allNonLegal)) {
			return Characteristic.ALL;
		} else if(characteristic == getString(R.string.noneNonLegal)) {
			return Characteristic.NONE;
		} else {
			throw new UnsupportedOperationException("Unknown rank");
		}
	}
	private Stance getMainStance() throws UnsupportedOperationException{
		Spinner spinMainStance = (Spinner) findViewById(R.id.spinMainStance);
		String stance = spinMainStance.getSelectedItem().toString();
		if(stance == getString(R.string.conservative)) {
			return Stance.CONSERVATIVE;
		} else if(stance == getString(R.string.reckless)) {
			return Stance.RECKLESS;
		} else if(stance == getString(R.string.versatile)) {
			return Stance.VERSATILE;
		} else if(stance == getString(R.string.bothNonLegal)) {
			return Stance.ALL;
		} else if(stance == getString(R.string.neitherNonLegal)) {
			return Stance.NONE;
		} else {
			throw new UnsupportedOperationException("Unknown stance");
		}
	}
	private String getName() {
		EditText editTextName = (EditText) findViewById(R.id.editTextName);
		if(editTextName.getText().toString() == "") {
			return getString(R.string.anonymous);
		} else {
			return editTextName.getText().toString();
		}
	}
	
	private void setupQuickCharacter() {
		((ApplicationExtension)getApplication()).getCharacter().setupQuickCharacter(getName(), getRank(), getMainCharacteristic(), getMainStance());
	}
	
	/** Called when the user touches the "Create" button */
	public void confirmCreation (View view) {
		setupQuickCharacter();
	    Intent intent = new Intent(this, OpeningMenu.class);
		startActivity(intent);
	}
}
