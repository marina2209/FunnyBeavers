package com.mantropova.gamex.screens;

/**
 * Created by Antropova Marina on 17.04.2019.
 */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;

public class SettingsScreen implements Screen {

    public SettingsScreen() {
    }

    public void resize(int width, int height) {
    }

    public void show() {
    }

    public void hide() {
    }

    public void pause() {
    }

    public void render(float delta) {
        Gdx.gl.glClearColor(1f, 1f, 1f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    public void resume() {
    }

    public void dispose() {
    }
}