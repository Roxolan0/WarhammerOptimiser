package com.roxolan.warhammeroptimiserapp;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
//import android.support.v7.widget.GridLayout;
import android.view.Menu;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;

public class ActionCards extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_action_cards);

		LinearLayout layoutActionCards = (LinearLayout) findViewById(R.id.layoutActionCards);
		ArrayList<String> cardNames = AppFileManager.getAllCardNames(this);

		for (int i = 0; i < cardNames.size(); ++i) {
			// TextView textCardName = new TextView(this) ;
			// textCardName.setText(cardNames.get(i) + ":");
			// textCardName.setLayoutParams(new LayoutParams(
			// LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
			// layoutActionCards.addView(textCardName);

			String cardName = cardNames.get(i);
			CheckBox checkBoxCard = new CheckBox(this);
			checkBoxCard.setText(cardName);
			checkBoxCard.setChecked(((ApplicationExtension) getApplication())
					.getCharacter().hasActionCard(cardName));
			checkBoxCard.setLayoutParams(new LayoutParams(
					LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
			layoutActionCards.addView(checkBoxCard);
			checkBoxCard
					.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener() {
						@Override
						public void onCheckedChanged(CompoundButton button,
								boolean isChecked) {
							if (isChecked) {
								((ApplicationExtension) getApplication())
										.getCharacter().addActionCard(
												button.getText().toString());
							} else {
								((ApplicationExtension) getApplication())
										.getCharacter().removeActionCard(
												button.getText().toString());
							}
						}
					});
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.action_cards, menu);
		return true;
	}

}
