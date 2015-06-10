package com.roxolan.warhammeroptimiserapp;

import warhammerOptimiser.business.Characteristic;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import static warhammerOptimiser.business.Characteristic.*;

public class CustomiseCharacter extends Activity implements
		OnRatingBarChangeListener, OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_customise_character);

		// Character name
		EditText editTextCustomiseName = (EditText) findViewById(R.id.editTextCustomiseName);
		String characterName = ((ApplicationExtension) getApplication())
				.getCharacter().getName();
		if (characterName != "") {
			editTextCustomiseName.setText(characterName);
		} else {
			editTextCustomiseName
					.setText(getString(R.string.defaultCharacterName));
		}
		editTextCustomiseName.addTextChangedListener(new TextWatcher() {
			public void afterTextChanged(Editable s) {
				TextView editTextCustomiseName = (TextView) findViewById(R.id.editTextCustomiseName);
				String newName = editTextCustomiseName.getText().toString();
				((ApplicationExtension) getApplication()).getCharacter()
						.setName(newName);
			}

			public void beforeTextChanged(CharSequence arg0, int arg1,
					int arg2, int arg3) {
			}

			public void onTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {
			}
		});

		// Stances
		RatingBar ratingConservative = (RatingBar) findViewById(R.id.ratingConservative);
		ratingConservative.setRating(((ApplicationExtension) getApplication())
				.getCharacter().getConservativeValue());
		ratingConservative.setOnRatingBarChangeListener(this);

		RatingBar ratingReckless = (RatingBar) findViewById(R.id.ratingReckless);
		ratingReckless.setRating(((ApplicationExtension) getApplication())
				.getCharacter().getRecklessValue());
		ratingReckless.setOnRatingBarChangeListener(this);

		//Characteristics
		updateTextView(STRENGTH, R.id.textViewStrength, false);
		updateTextView(STRENGTH, R.id.textViewStrength, true);
		updateTextView(TOUGHNESS, R.id.textViewToughness, false);
		updateTextView(TOUGHNESS, R.id.textViewToughness, true);
		updateTextView(AGILITY, R.id.textViewAgility, false);
		updateTextView(AGILITY, R.id.textViewAgility, true);
		updateTextView(INTELLIGENCE, R.id.textViewIntelligence, false);
		updateTextView(INTELLIGENCE, R.id.textViewIntelligence, true);
		updateTextView(WILLPOWER, R.id.textViewWillpower, false);
		updateTextView(WILLPOWER, R.id.textViewWillpower, true);
		updateTextView(FELLOWSHIP, R.id.textViewFellowship, false);
		updateTextView(FELLOWSHIP, R.id.textViewFellowship, true);
		
		//Characteristics buttons
		Button button;
		button = (Button) findViewById(R.id.buttonMStrength);
		button.setOnClickListener(this);
		button = (Button) findViewById(R.id.buttonMToughness);
		button.setOnClickListener(this);
		button = (Button) findViewById(R.id.buttonMAgility);
		button.setOnClickListener(this);
		button = (Button) findViewById(R.id.buttonMIntelligence);
		button.setOnClickListener(this);
		button = (Button) findViewById(R.id.buttonMWillpower);
		button.setOnClickListener(this);
		button = (Button) findViewById(R.id.buttonMFellowship);
		button.setOnClickListener(this);
		button = (Button) findViewById(R.id.buttonPStrength);
		button.setOnClickListener(this);
		button = (Button) findViewById(R.id.buttonPToughness);
		button.setOnClickListener(this);
		button = (Button) findViewById(R.id.buttonPAgility);
		button.setOnClickListener(this);
		button = (Button) findViewById(R.id.buttonPIntelligence);
		button.setOnClickListener(this);
		button = (Button) findViewById(R.id.buttonPWillpower);
		button.setOnClickListener(this);
		button = (Button) findViewById(R.id.buttonPFellowship);
		button.setOnClickListener(this);
		button = (Button) findViewById(R.id.buttonMStrengthFortune);
		button.setOnClickListener(this);
		button = (Button) findViewById(R.id.buttonMToughnessFortune);
		button.setOnClickListener(this);
		button = (Button) findViewById(R.id.buttonMAgilityFortune);
		button.setOnClickListener(this);
		button = (Button) findViewById(R.id.buttonMIntelligenceFortune);
		button.setOnClickListener(this);
		button = (Button) findViewById(R.id.buttonMWillpowerFortune);
		button.setOnClickListener(this);
		button = (Button) findViewById(R.id.buttonMFellowshipFortune);
		button.setOnClickListener(this);
		button = (Button) findViewById(R.id.buttonPStrengthFortune);
		button.setOnClickListener(this);
		button = (Button) findViewById(R.id.buttonPToughnessFortune);
		button.setOnClickListener(this);
		button = (Button) findViewById(R.id.buttonPAgilityFortune);
		button.setOnClickListener(this);
		button = (Button) findViewById(R.id.buttonPIntelligenceFortune);
		button.setOnClickListener(this);
		button = (Button) findViewById(R.id.buttonPWillpowerFortune);
		button.setOnClickListener(this);
		button = (Button) findViewById(R.id.buttonPFellowshipFortune);
		button.setOnClickListener(this);
		
		//Equipment
		ArrayAdapter<CharSequence> adapter;
		
		Spinner spinMeleeWeapon = (Spinner) findViewById(R.id.spinMeleeWeapon);
		adapter = ArrayAdapter.createFromResource(this, R.array.spinMeleeWeapon, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinMeleeWeapon.setAdapter(adapter);

		Spinner spinRangedWeapon = (Spinner) findViewById(R.id.spinRangedWeapon);
		adapter = ArrayAdapter.createFromResource(this, R.array.spinRangedWeapon, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinRangedWeapon.setAdapter(adapter);

		Spinner spinOffHandWeapon = (Spinner) findViewById(R.id.spinOffHandWeapon);
		adapter = ArrayAdapter.createFromResource(this, R.array.spinMeleeWeapon, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinOffHandWeapon.setAdapter(adapter);

		Spinner spinShield = (Spinner) findViewById(R.id.spinShield);
		adapter = ArrayAdapter.createFromResource(this, R.array.spinShield, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinShield.setAdapter(adapter);
		
		Spinner spinArmour = (Spinner) findViewById(R.id.spinArmour);
		adapter = ArrayAdapter.createFromResource(this, R.array.spinArmour, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinArmour.setAdapter(adapter);
}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.customise_character, menu);
		return true;
	}

	@Override
	public void onRatingChanged(RatingBar ratingBar, float newRating,
			boolean fromUser) {
		switch (ratingBar.getId()) {
		case R.id.ratingConservative:
			((ApplicationExtension) getApplication()).getCharacter()
					.setConservativeValue((int) newRating);
			break;
		case R.id.ratingReckless:
			((ApplicationExtension) getApplication()).getCharacter()
					.setRecklessValue((int) newRating);
			break;
		}
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.buttonMStrength:
			addAndUpdate(STRENGTH, -1, R.id.textViewStrength, false); break;
		case R.id.buttonMToughness:
			addAndUpdate(TOUGHNESS, -1, R.id.textViewToughness, false); break;
		case R.id.buttonMAgility:
			addAndUpdate(AGILITY, -1, R.id.textViewAgility, false); break;
		case R.id.buttonMIntelligence:
			addAndUpdate(INTELLIGENCE, -1, R.id.textViewIntelligence, false); break;
		case R.id.buttonMWillpower:
			addAndUpdate(WILLPOWER, -1, R.id.textViewWillpower, false); break;
		case R.id.buttonMFellowship:
			addAndUpdate(FELLOWSHIP, -1, R.id.textViewFellowship, false); break;
		case R.id.buttonPStrength:
			addAndUpdate(STRENGTH, 1, R.id.textViewStrength, false); break;
		case R.id.buttonPToughness:
			addAndUpdate(TOUGHNESS, 1, R.id.textViewToughness, false); break;
		case R.id.buttonPAgility:
			addAndUpdate(AGILITY, 1, R.id.textViewAgility, false); break;
		case R.id.buttonPIntelligence:
			addAndUpdate(INTELLIGENCE, 1, R.id.textViewIntelligence, false); break;
		case R.id.buttonPWillpower:
			addAndUpdate(WILLPOWER, 1, R.id.textViewWillpower, false); break;
		case R.id.buttonPFellowship:
			addAndUpdate(FELLOWSHIP, 1, R.id.textViewFellowship, false); break;
		case R.id.buttonMStrengthFortune:
			addAndUpdate(STRENGTH, -1, R.id.textViewStrengthFortune, true); break;
		case R.id.buttonMToughnessFortune:
			addAndUpdate(TOUGHNESS, -1, R.id.textViewToughnessFortune, true); break;
		case R.id.buttonMAgilityFortune:
			addAndUpdate(AGILITY, -1, R.id.textViewAgilityFortune, true); break;
		case R.id.buttonMIntelligenceFortune:
			addAndUpdate(INTELLIGENCE, -1, R.id.textViewIntelligenceFortune, true); break;
		case R.id.buttonMWillpowerFortune:
			addAndUpdate(WILLPOWER, -1, R.id.textViewWillpowerFortune, true); break;
		case R.id.buttonMFellowshipFortune:
			addAndUpdate(FELLOWSHIP, -1, R.id.textViewFellowshipFortune, true); break;
		case R.id.buttonPStrengthFortune:
			addAndUpdate(STRENGTH, 1, R.id.textViewStrengthFortune, true); break;
		case R.id.buttonPToughnessFortune:
			addAndUpdate(TOUGHNESS, 1, R.id.textViewToughnessFortune, true); break;
		case R.id.buttonPAgilityFortune:
			addAndUpdate(AGILITY, 1, R.id.textViewAgilityFortune, true); break;
		case R.id.buttonPIntelligenceFortune:
			addAndUpdate(INTELLIGENCE, 1, R.id.textViewIntelligenceFortune, true); break;
		case R.id.buttonPWillpowerFortune:
			addAndUpdate(WILLPOWER, 1, R.id.textViewWillpowerFortune, true); break;
		case R.id.buttonPFellowshipFortune:
			addAndUpdate(FELLOWSHIP, 1, R.id.textViewFellowshipFortune, true); break;
		}
	}
	
	private void addAndUpdate(Characteristic characteristic, int toAdd, int textViewId, boolean isFortune) {
		if(isFortune) {
			((ApplicationExtension) getApplication()).getCharacter().addCharacteristicFortune(characteristic, toAdd);
		} else {
			((ApplicationExtension) getApplication()).getCharacter().addCharacteristic(characteristic, toAdd);
		}
		updateTextView(characteristic, textViewId, isFortune);
	}
	private void updateTextView(Characteristic characteristic, int textViewId, boolean isFortune) {
		int newValue;
		if(isFortune) {
			newValue = ((ApplicationExtension) getApplication()).getCharacter().getCharacteristicFortune(characteristic);
		} else {
			newValue = ((ApplicationExtension) getApplication()).getCharacter().getCharacteristic(characteristic);
		}
		TextView textView = (TextView) findViewById(textViewId);
		textView.setText(Integer.toString(newValue));
	}
	
	/** Called when the user touches the "Choose action cards" button */
	public void chooseActionCards (View view) {
	    Intent intent = new Intent(this, ActionCards.class);
		startActivity(intent);
	}
}
