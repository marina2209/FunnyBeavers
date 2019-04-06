package com.mantropova.gamex;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mantropova.gamex.model.Beaver;

public class GameScreen implements Screen {

    private Texture beaverTexture;
    private SpriteBatch batch;
    private Beaver beaver;

    @Override
    public void show() {
        batch = new SpriteBatch();
        beaverTexture = new Texture(Gdx.files.internal("mole.png"));
        beaver = new Beaver(beaverTexture, 0, 0, 64, 64);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        beaver.draw(batch);
        batch.end();

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        beaverTexture.dispose();
        batch.dispose();
    }
}
