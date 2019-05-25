package com.mantropova.gamex;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mantropova.gamex.helpers.AssetsLoader;
import com.mantropova.gamex.screens.MainMenuScreen;
import com.badlogic.gdx.graphics.Color;

public class FunnyBeavers extends Game {

	public Skin skin = null;
	public BitmapFont font = null;
	public BitmapFont levels = null;

	@Override
	public void create() {
		AssetsLoader.setGame(this);

		FreeTypeFontGenerator generator =
				new FreeTypeFontGenerator(Gdx.files.internal("fonts/alpha_echo.ttf"));
		FreeTypeFontGenerator.FreeTypeFontParameter param =
				new FreeTypeFontGenerator.FreeTypeFontParameter();
		param.size = Gdx.graphics.getHeight() / 10;
		param.characters = FONT_CHARACTERS;
		font = generator.generateFont(param);
		param.size = Gdx.graphics.getHeight() / 10;
		levels = generator.generateFont(param);
		font.setColor(Color.WHITE);
		levels.setColor(Color.WHITE);
		generator.dispose();

		AssetsLoader assets = AssetsLoader.getInstance();
		skin = new Skin();
		skin.add("default", levels);
		skin.add("ButtonOn", assets.buttonon);
		skin.add("ButtonOff", assets.buttonoff);

		TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
		textButtonStyle.up = skin.newDrawable("ButtonOn");
		textButtonStyle.down = skin.newDrawable("ButtonOff");
		textButtonStyle.checked = skin.newDrawable("ButtonOn");
		textButtonStyle.over = skin.newDrawable("ButtonOff");
		textButtonStyle.font = skin.getFont("default");
		skin.add("default", textButtonStyle);

		setScreen(new MainMenuScreen());
	}

	@Override
	public void render() {
		super.render();
	}

	private static String FONT_CHARACTERS = "�������������������������������������Ũ��������������������������abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789][_!$%#@|\\/?-+=()*&.;,{}\"?`'<>";
}
