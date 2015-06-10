package com.roxolan.warhammeroptimiserapp;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

import android.content.Context;

public class AppFileManager {
	private AppFileManager() {
	} // Fully static class, should never be instantiated

	public static BufferedReader cardReader(Context context, String cardName)
			throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				context.getAssets().open(
						"cards/cards_melee/" + cardName + ".txt")));
		return reader;
	}
	
	public static ArrayList<String> getAllCardNames(Context context) {
		try {
			String[] cardNames = context.getAssets().list("cards/cards_melee");
			for(int i = 0; i < cardNames.length; ++i) {
				cardNames[i] = cardNames[i].substring(0, cardNames[i].length() - 4);	//Removes ".txt"
			}
			ArrayList<String> cardArray = new ArrayList<String>(Arrays.asList(cardNames));
			return cardArray;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
