package com.roxolan.warhammeroptimiserapp;

import static warhammerOptimiser.business.Character.SKULL;
import static warhammerOptimiser.business.Characteristic.STRENGTH;
import static warhammerOptimiser.business.Characteristic.TOUGHNESS;

import java.io.IOException;
import java.util.ArrayList;

import warhammerOptimiser.business.Character;
import warhammerOptimiser.business.ResultTypeUtility;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;
import static warhammerOptimiser.business.Optimiser.*;
import warhammerOptimiser.business.NameUtilityPair;

public class BestActionCards extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_best_action_cards);
		
		int rank = ((ApplicationExtension) getApplication()).getCharacter().getLevel();
		int buttonId = getResources().getIdentifier("toggleButtonSkull" + rank, "id", "com.roxolan.warhammeroptimiserapp");
		ToggleButton tButton = (ToggleButton) findViewById(buttonId);
		tButton.toggle();
		
		Button button;
		button = (Button) findViewById(R.id.buttonMNbCards);
		button.setOnClickListener(this);
		button = (Button) findViewById(R.id.buttonPNbCards);
		button.setOnClickListener(this);
		
		TextView textNbCards = (TextView) findViewById(R.id.textNbCards);
		textNbCards.setText("3");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.best_action_cards, menu);
		return true;
	}

	private void activateThisButtonOnly(int theButtonId) {
		for (int i = 0; i <= Character.MAX[SKULL]; ++i) {
			String aButtonName = "toggleButtonSkull" + i;
			int aButtonId = getResources().getIdentifier(aButtonName, "id",
					"com.roxolan.warhammeroptimiserapp");
			ToggleButton aButton = (ToggleButton) findViewById(aButtonId);
			if (aButtonId != theButtonId) {
				if(aButton.isChecked()) {	//not necessary on newer APIs
					aButton.toggle();
				}
			} else {
				if(!aButton.isChecked()) {
					aButton.toggle();
				}
			}
		}
	}
	
	private int getNbSkulls() {
		for (int i = 0; i <= Character.MAX[SKULL]; ++i) {
			String buttonName = "toggleButtonSkull" + i;
			int buttonId = getResources().getIdentifier(buttonName, "id",
					"com.roxolan.warhammeroptimiserapp");
			ToggleButton aButton = (ToggleButton) findViewById(buttonId);
			if(aButton.isChecked()) {
				return i;
			}
		}
		return -1;
	}
	private int getNbCards() {
		TextView textNbCards = (TextView) findViewById(R.id.textNbCards);
		return Integer.parseInt(textNbCards.getText().toString());
	}

	@Override
	public void onClick(View view) {
		TextView textNbCards = (TextView) findViewById(R.id.textNbCards);
		int curValue = Integer.parseInt(textNbCards.getText().toString());
		
		switch (view.getId()) {
		case R.id.buttonMNbCards:
			if(curValue > 0)
				textNbCards.setText(curValue - 1); 
			break;
		case R.id.buttonPNbCards:
			if(curValue < 99)
				textNbCards.setText(curValue + 1);
			break;
		}
	}

	/** Called when the user touches a skull button */
	public void skullButton(View view) {
		activateThisButtonOnly(view.getId());
	}
	
	/** Called when the user touches the "find" button */
	public void findBestActionCards(View view) {
		LinearLayout layoutActionCards = (LinearLayout) findViewById(R.id.layoutActionCards);
		Character character = ((ApplicationExtension) getApplication()).getCharacter();
		Character enemy = new Character(SKULL, getNbSkulls());
		ResultTypeUtility utility = new ResultTypeUtility();
		utility.setupDamage();
		try {
			ArrayList<NameUtilityPair> bestActionCards = bestActionCards(this, getNbCards(), character, enemy, utility);
			for(int i = 0; i < bestActionCards.size(); ++i) {
				System.out.println(bestActionCards.get(i).toString());
			}
		} catch (IOException e) {
			e.printStackTrace();
			TextView textError = new TextView(this);
			layoutActionCards.addView(textError);
		}
	}
}
