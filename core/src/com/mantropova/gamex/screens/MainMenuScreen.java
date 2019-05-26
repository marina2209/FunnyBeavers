package com.mantropova.gamex.screens;

/**
 * Created by Antropova Marina on 17.04.2019.
 */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mantropova.gamex.helpers.AssetsLoader;
import com.mantropova.gamex.objects.Background;

public class MainMenuScreen implements Screen {

    private Stage stage;

    public MainMenuScreen() {
        AssetsLoader assets = AssetsLoader.getInstance();

        final Music music = assets.music;
        if (assets.getPrefs().getBoolean("music") && (music != null)) {
            music.play();
            music.setVolume(1f);
            music.setLooping(true);
        }

        stage = new Stage(new ScreenViewport());
        Background background = new Background();
        background.setPosition(0f, 0f);
        stage.addActor(background);

        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = AssetsLoader.getGame().font;
        labelStyle.fontColor = Color.WHITE;
        Label label = new Label("KILL THE MOLE", labelStyle);
        label.setAlignment(Align.center);
        Table table = new Table();
        table.setFillParent(true);

        TextButton play = new TextButton("Play", AssetsLoader.getGame().skin);
        play.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                AssetsLoader.getGame().setScreen(new ShopScreen());
                dispose();
            }
        });

        TextButton settings = new TextButton("Settings", AssetsLoader.getGame().skin);
        settings.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                AssetsLoader.getGame().setScreen(new SettingsScreen(music));
                dispose();
            }
        });

        TextButton exit = new TextButton("Exit", AssetsLoader.getGame().skin);
        exit.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.exit();
                dispose();
            }
        });

        table.add(label).width(400f).center();
        table.row().height(100f);
        table.add(play).width(400f);
        table.row().height(100f);
        table.add(settings).width(400f);
        table.row().height(100f);
        table.add(exit).width(400f);
        stage.addActor(table);

        Gdx.input.setInputProcessor(stage);
        Gdx.input.setCatchBackKey(true);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1f, 1f, 1f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void show() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}