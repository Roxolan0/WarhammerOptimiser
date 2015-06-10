package com.roxolan.warhammeroptimiserapp;

import warhammerOptimiser.business.Character;
import warhammerOptimiser.business.ResultTypeUtility;
import android.app.Application;

public class ApplicationExtension extends Application {
	
	private Character character;
//	private Character quickCharacter;
	private Character enemy;
	private ResultTypeUtility utility;
	
	@Override
	public void onCreate() {
		super.onCreate();
		character = new Character();
//		quickCharacter = new Character();
		enemy = new Character();
		utility = new ResultTypeUtility();
	}
	
	public Character getCharacter() {
		return character;
	}
//	public Character getQuickCharacter() {
//		return quickCharacter;
//	}
	public Character getEnemy() {
		return enemy;
	}
	public ResultTypeUtility getUtility() {
		return utility;
	}
	
//	public void saveQuickCharacter() {
//		character = new Character(quickCharacter);
//	}
}
