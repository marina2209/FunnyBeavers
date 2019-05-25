package com.mantropova.gamex.screens;

/**
 * Created by Antropova Marina on 19.04.2019.
 */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mantropova.gamex.helpers.AssetsLoader;
import com.mantropova.gamex.objects.Background;
import com.mantropova.gamex.objects.Level;
import com.mantropova.gamex.objects.Mole;

public class PlayScreen implements Screen {
    private Level level;
    private Stage stage;
    private boolean flag;
    private Label.LabelStyle labelStyle;
    private Label scoreLabel;
    private Label healthLabel;
    private Sound sound2;
    private Music music;
    private TextButton pauseButton;
    private Table pauseTable;
    private boolean isMusicOn;
    private String numOfLevel;
    private String weapon;

    PlayScreen(String numOfLevel, String weapon) {
        this.numOfLevel = numOfLevel;
        this.weapon = weapon;
        pauseTable = pauseGameActor();

        AssetsLoader assets = AssetsLoader.getInstance();
        isMusicOn = assets.getPrefs().getBoolean("Music");
        music = assets.music;
        if (isMusicOn && (music != null)) {
            music.play();
            music.setVolume(0.5f);
        }
        sound2 = assets.sound2;

        int currentAmountDeath = 0;

        labelStyle = new Label.LabelStyle();
        labelStyle.font = AssetsLoader.getGame().font;

        pauseButton = new TextButton("pause", AssetsLoader.getGame().skin);
        pauseButton.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                pause();
            }
        });


        level = new Level(numOfLevel, weapon);
        flag = true;

        //Batch batch = new SpriteBatch();
        stage = new Stage();
        Background background = new Background();
        background.setPosition(0f, 0f);
        stage.addActor(background);
        for (Mole mole : level.molesArray)
            stage.addActor(mole);
        Table oneMoreTable = new Table();
        oneMoreTable.setFillParent(true);
        oneMoreTable.add(pauseButton);
        oneMoreTable.right().bottom();
        stage.addActor(oneMoreTable);

        healthLabel = new Label("HP: " + level.getPlayerHealth(), labelStyle);
        Table healthTable = new Table();
        healthTable.setFillParent(true);
        healthTable.add(healthLabel);
        healthTable.top().left();
        stage.addActor(healthTable);

        scoreLabel = new Label("Score: " + currentAmountDeath, labelStyle);
        Table scoreTable = new Table();
        scoreTable.setFillParent(true);
        scoreTable.add(scoreLabel);
        scoreTable.bottom().left();
        stage.addActor(scoreTable);

        pauseTable.setVisible(false);
        stage.addActor(pauseTable);
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void show() {
    }

    @Override
    public void pause() {
        level.pause();
        pauseTable.setVisible(true);
    }

    private Table pauseGameActor() {
        if (isMusicOn) {
            if (music != null) music.pause();
            if (sound2!= null) sound2.play();
        }

        final Table table = new Table();
        table.setFillParent(true);

        TextButton retry = new TextButton("  Continue  ", AssetsLoader.getGame().skin);
        retry.addListener(new ClickListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                level.continueFromPause();
                table.setVisible(false);
            }
        });

        TextButton mainMenu = new TextButton(" Main menu ", AssetsLoader.getGame().skin);
        mainMenu.addListener(new ClickListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                AssetsLoader.getGame().setScreen(new MainMenuScreen());
                if (sound2 != null) sound2.stop();
                dispose();
            }
        });
        table.add(retry);
        table.row();
        table.add(mainMenu);
        table.center();
        table.setFillParent(true);
        return table;
    }

    @Override
    public void hide() {
    }

    @Override
    public void resize(int width, int height) {
    }

    private Table endGameActor() {
        if (isMusicOn) {
            if (music != null) music.pause();
            if (sound2!= null) sound2.play();
        }

        Table table = new Table();
        table.setFillParent(true);

        TextButton retry = new TextButton("Retry", AssetsLoader.getGame().skin);
        retry.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                AssetsLoader.getGame().setScreen(new PlayScreen(numOfLevel, weapon));
                if (sound2 != null) sound2.pause();
                dispose();
            }
        });
        TextButton mainMenu = new TextButton("Main menu", AssetsLoader.getGame().skin);
        mainMenu.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                AssetsLoader.getGame().setScreen(new MainMenuScreen());
                if (sound2 != null) sound2.stop();
                dispose();
            }
        });
        Label score = new Label("Your score: " + level.player.moleKilled , labelStyle);
        Label money = new Label(
                "Your money: " + level.player.moleKilled * Integer.parseInt(numOfLevel), labelStyle);

        table.add(score);
        table.row();
        table.add(money);
        table.row();
        table.add(retry).width(550f);
        table.add(mainMenu).width(550f);
        table.center();
        table.setFillParent(true);
        return table;
    }

    @Override
    public void render(float delta) {
        if (flag) {
            if (level.isGameOver) {
                level.gameOver();
                pauseButton.setVisible(false);
                stage.addActor(endGameActor());
                flag = false;
            } else {
                int currentAmountDeath = level.update(delta);
                scoreLabel.setText("Score: " + currentAmountDeath);
                healthLabel.setText("HP: " + level.getPlayerHealth());
                stage.act(delta);
                stage.draw();
            }
        } else {
            stage.act(delta);
            stage.draw();
        }

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