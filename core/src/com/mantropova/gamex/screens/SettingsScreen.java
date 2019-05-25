package com.mantropova.gamex.screens;

/**
 * Created by Antropova Marina on 17.04.2019.
 */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mantropova.gamex.helpers.AssetsLoader;
import com.mantropova.gamex.objects.Background;

public class SettingsScreen implements Screen {

    private Stage stage;
    private AssetsLoader assets = AssetsLoader.getInstance();

    SettingsScreen(final Music musicPlayScreen) {
        stage = new Stage(new ScreenViewport());
        Background background = new Background();
        background.setPosition(0f, 0f);
        stage.addActor(background);

        Table table = new Table();
        table.setFillParent(true);

        final TextButton music = new TextButton(
                "Music: " + (assets.getPrefs().getBoolean("Music") ? "on" : "off"),
                AssetsLoader.getGame().skin);

        music.addListener(new ClickListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                boolean flag = assets.getPrefs().getBoolean("Music");
                if (!flag) {
                    music.setText("Music: on");
                    assets.getPrefs().putBoolean("Music", true);
                    if (musicPlayScreen != null)
                        musicPlayScreen.play();
                } else {
                    music.setText("Music: off");
                    assets.getPrefs().putBoolean("Music", false);
                    if (musicPlayScreen != null)
                        musicPlayScreen.pause();
                }
                assets.getPrefs().flush();
            }
        });

        TextButton newGame = new TextButton("New game", AssetsLoader.getGame().skin);
        newGame.addListener(new ClickListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                assets.getPrefs().putInteger("Coins", 0);
                assets.getPrefs().putInteger("HammerDamage", 20);
                assets.getPrefs().putInteger("HammerLevel", 1);
                assets.getPrefs().putInteger("ShovelDamage", 20);
                assets.getPrefs().putInteger("ShovelLevel", 1);
                assets.getPrefs().flush();
            }
        });

        TextButton back = new TextButton("Back", AssetsLoader.getGame().skin);
        back.addListener(new ClickListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                AssetsLoader.getGame().setScreen(new MainMenuScreen());
                dispose();
            }
        });

        table.add(music).width(400f).height(100f);
        table.row().height(100f);
        table.add(newGame).width(400f);
        table.row().height(100f);
        table.add(back).width(400f);
        table.center();

        stage.addActor(table);
        Gdx.input.setInputProcessor(stage);
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
    public void render(float delta) {
        Gdx.gl.glClearColor(1f, 1f, 1f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
    }
}