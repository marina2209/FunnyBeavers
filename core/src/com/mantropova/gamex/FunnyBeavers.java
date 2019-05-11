package com.mantropova.gamex;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.mantropova.gamex.helpers.AssetsLoader;
import com.mantropova.gamex.screens.MainMenuScreen;

public class FunnyBeavers extends Game {

	public Skin skin = null;
    private Screen gameScreen;

	@Override
	public void create() {
		AssetsLoader.setGame(this);
		gameScreen = new MainMenuScreen();
		setScreen(gameScreen);
	}
}
