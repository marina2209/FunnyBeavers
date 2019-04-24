package com.mantropova.gamex.screens;

/**
 * Created by Antropova Marina on 21.04.2019.
 */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mantropova.gamex.FunnyBeavers;
import com.mantropova.gamex.objects.Background;

class LevelScreen implements Screen {

    private FunnyBeavers game;
    private Background background;
    private Stage stage;

    public LevelScreen(final FunnyBeavers game) {
        this.game = game;

        background = new Background();
        background.setPosition(0f, 0f);
        stage.addActor(background);

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
        game.dispose();
    }
}