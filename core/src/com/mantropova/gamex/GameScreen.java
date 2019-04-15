package com.mantropova.gamex;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mantropova.gamex.model.Beaver;

import javax.xml.soap.Text;

public class GameScreen implements Screen {

    private Texture beaverTexture;
    private SpriteBatch batch;
    private Beaver beaver;
    private OrthographicCamera camera;

    @Override
    public void show() {
        batch = new SpriteBatch();
        beaverTexture = new Texture(Gdx.files.internal("Images/mole.png"));
        beaverTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        beaver = new Beaver(beaverTexture, 0, 0, 2f, 2f * 1f);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        beaver.draw(batch);
        batch.end();

    }

    @Override
    public void resize(int width, int height) {
        //Соотношение ширины и высоты(сторон)
        float aspectRatio = (float) height/width;
        camera = new OrthographicCamera(20f, 20f * aspectRatio);
        camera.zoom = 0.9f;
        camera.update();
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
