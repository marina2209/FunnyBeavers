package com.mantropova.gamex.screens;

/**
 * Created by Antropova Marina on 21.04.2019.
 */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mantropova.gamex.helpers.AssetsLoader;
import com.mantropova.gamex.objects.Background;
import com.mantropova.gamex.helpers.XMLparser;

class LevelScreen implements Screen {

    private Stage stage;

    LevelScreen(final String weapon) {

        stage = new Stage(new ScreenViewport());
        Background background = new Background();
        background.setPosition(0f, 0f);
        stage.addActor(background);

        XMLparser parseLevels = new XMLparser();
        Array<String> levels = parseLevels.XMLparseLevels();
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = AssetsLoader.getGame().levels;
        Table table = new Table();
        table.row().pad(20f).width(200f).height(50f);
        table.center();
        table.setFillParent(true);

        for(int i = 0; i < levels.size; i++) {
            final String cur_level = levels.get(i);
            TextButton level = new TextButton(cur_level + " level", AssetsLoader.getGame().skin);
            level.addListener(new ClickListener() {
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    return true;
                }

                @Override
                public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                    AssetsLoader.getGame().setScreen(new PlayScreen(cur_level, weapon));
                    dispose();
                }
            });
            table.add(level).width(400f).height(100f);
            if ((i+1) % 5.0 != 0)
                table.row().pad(20f).width(200f).height(50f);
        }
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
        AssetsLoader.getGame().dispose();
    }
}