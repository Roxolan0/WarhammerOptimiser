package com.roxolan.warhammeroptimiserapp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import android.widget.ToggleButton;
import warhammerOptimiser.business.Character;
import warhammerOptimiser.business.Optimiser;
import warhammerOptimiser.business.ResultTypeUtility;
import static warhammerOptimiser.business.Character.*;
import static warhammerOptimiser.business.Optimiser.*;
import static warhammerOptimiser.business.TargetType.*;
import static warhammerOptimiser.business.ResultType.*;

public class AverageDamage extends Activity {
	// ArrayList<View> temporaryText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_average_damage);
		// temporaryText = new ArrayList<View>();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.average_damage, menu);
		return true;
	}

	@Override
	public void onResume() {
		super.onResume();
		updateActionCards();
	}

	// Called by onResume and whenever a skull button is pressed.
	private void updateActionCards() {
		cleanActionCards();
		LinearLayout layoutActionCards = (LinearLayout) findViewById(R.id.layoutActionCards);
		ArrayList<String> actionCards = ((ApplicationExtension) getApplication())
				.getCharacter().getActionCards();
		for (int i = 0; i < actionCards.size(); ++i) {
			String cardName = actionCards.get(i);
			TextView textCardName = new TextView(this);
			// temporaryText.add(textCardName);
			textCardName.setText(cardName + ":");
			textCardName.setLayoutParams(new LayoutParams(
					LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
			layoutActionCards.addView(textCardName);

			ResultTypeUtility utility = new ResultTypeUtility();
			utility.setupDamage();
			for (int j = 0; j <= Character.MAX[SKULL]; ++j) {
				String buttonName = "toggleButtonSkull" + j;
				int buttonId = getResources().getIdentifier(buttonName, "id",
						"com.roxolan.warhammeroptimiserapp");
				ToggleButton button = (ToggleButton) findViewById(buttonId);
				if (button.isChecked()) {
					TextView textAverageDamage = new TextView(this);
					textAverageDamage.setText(R.string.computing);
					textAverageDamage.invalidate();
					textAverageDamage.requestLayout();
					// temporaryText.add(textAverageDamage);
					layoutActionCards.addView(textAverageDamage);
					double averageDamage;
					Character player = ((ApplicationExtension) getApplication()).getCharacter();
					Character monster = new Character(SKULL, j);
					System.out.println("Passing card " + cardName 
							+ ", rank " + player.getLevel() + ", skull " + monster.getLevel() 
							+ ", damage utility = " + utility.getUtility(DEAL, DAMAGE));
					try {
						averageDamage = Optimiser.averageUtility(this, cardName, player, monster, utility);
						textAverageDamage.setText(j + " "
								+ getString(R.string.skulls) + ": "
								+ averageDamage);
					} catch (IOException e) {
						e.printStackTrace();
						textAverageDamage.setText(R.string.errorCardNotFound);
					}
					textAverageDamage.invalidate();
					textAverageDamage.requestLayout();
				}
			}
		}
	}

	public void updateActionCards(View view) {
		updateActionCards();
	}

	private void cleanActionCards() {
		LinearLayout layoutActionCards = (LinearLayout) findViewById(R.id.layoutActionCards);
		layoutActionCards.removeAllViews();
		// for(int i = 0; i < temporaryText.size(); ++i) {
		// ((LinearLayout) temporaryText.get(i).getParent()).rem
		// }
	}

	/** Called when the user touches the "Customise character" button */
	public void chooseActionCards(View view) {
		Intent intent = new Intent(this, ActionCards.class);
		startActivity(intent);
	}

	/** Called when the user touches the "Customise character" button */
	public void customiseCharacter(View view) {
		Intent intent = new Intent(this, CustomiseCharacter.class);
		startActivity(intent);
	}
}
