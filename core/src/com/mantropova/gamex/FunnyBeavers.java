package com.mantropova.gamex;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.mantropova.gamex.screens.MainMenuScreen;

public class FunnyBeavers extends Game {

	public Skin skin = null;
    private Screen gameScreen;

	@Override
	public void create() {
		gameScreen = new MainMenuScreen(this);
		setScreen(gameScreen);
	}
}
