package com.mantropova.gamex;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.mantropova.gamex.screens.MainMenuScreen;

public class FunnyBeavers extends Game {

	private Screen gameScreen;

	@Override
	public void create() {
		gameScreen = new MainMenuScreen(this);
		setScreen(gameScreen);
	}
}
