package com.mantropova.gamex.screens;

/**
 * Created by Antropova Marina on 19.04.2019.
 */

import com.badlogic.gdx.Screen;
import com.mantropova.gamex.FunnyBeavers;

public class PlayScreen implements Screen {

    private FunnyBeavers game;

    public PlayScreen(FunnyBeavers game) {
        this.game = game;
    }

    public void show() {
    }

    public void pause() {
    }

    public void hide() {
    }

    public void resize(int width, int height) {
    }

    public void render(float delta) {
    }

    public void resume() {
    }

    public void dispose() {
        game.dispose();
    }
}