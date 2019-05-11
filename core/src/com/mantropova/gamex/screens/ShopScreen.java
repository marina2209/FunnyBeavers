package com.mantropova.gamex.screens;

/**
 * Created by Antropova Marina on 20.04.2019.
 */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mantropova.gamex.FunnyBeavers;
import com.mantropova.gamex.helpers.AssetsLoader;
import com.mantropova.gamex.objects.Background;
import com.mantropova.gamex.objects.Coins;

public class ShopScreen implements Screen {

    private Stage stage;
    private TextButton hammer;
    private TextButton shovel;
    private TextButton shovelLevelUp;
    private TextButton hammerLevelUp;
    private TextButton markHammer;
    private TextButton markShovel;
    private Label moneyLabel;
    private Coins coins = new Coins();
    private String weaponType = "";

    public ShopScreen(final FunnyBeavers game) {

        stage = new Stage(new ScreenViewport());
        Skin skin = new Skin();
        //skin.add("default", game.levels);
        AssetsLoader assets = AssetsLoader.getInstance();
        skin.add("ButtonOn", assets.buttonon);
        skin.add("ButtonOff", assets.buttonoff);
        skin.add("Mark", assets.mark);
        Background background = new Background();
        background.setPosition(0f, 0f);
        stage.addActor(background);

        TextButton.TextButtonStyle markButton = new TextButton.TextButtonStyle();
        markButton.up = skin.newDrawable("Mark");
        markButton.down = skin.newDrawable("Mark");
        markButton.checked = skin.newDrawable("Mark");
        markButton.over = skin.newDrawable("Mark");
        markButton.font = skin.getFont("default");
        skin.add("Mark", markButton);

        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = skin.newDrawable("ButtonOn");
        textButtonStyle.down = skin.newDrawable("ButtonOn");
        textButtonStyle.checked = skin.newDrawable("ButtonOn");
        textButtonStyle.over = skin.newDrawable("ButtonOn");
        textButtonStyle.font = skin.getFont("default");
        skin.add("default", textButtonStyle);

        markHammer = new TextButton("", markButton);
        markShovel = new TextButton("", markButton);
        markHammer.setVisible(true);
        markShovel.setVisible(false);
        Table table = new Table();
        table.setFillParent(true);

        int curLev = 1 ; //pref.getInteger("HammerLevel"); TODO
        hammer = new TextButton("Hammer, lvl: " + 1 /* TODO pref.getInteger("HammerLevel")*/, skin);
        hammer.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                weaponType = "Hammer";
                markHammer.setVisible(true);
                if (markShovel.isVisible()) markShovel.setVisible(false);
            }
        });
        int currentCost = curLev * 2;

        hammerLevelUp = new TextButton(Integer.toString(currentCost), skin);
        hammerLevelUp.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                int curLev = 1;
                int currentCost = curLev * 2;
                if (coins.spendCoins(currentCost)) {
                    curLev++;
                    currentCost = curLev * 2;
                    hammerLevelUp.setText(Integer.toString(currentCost));
                    currentCost = curLev * 2;
                    hammerLevelUp.setText(Integer.toString(currentCost));
                    hammer.setText("Hammer, lvl: " + curLev);
                }
            }
        });
        curLev = 1;
        shovel = new TextButton("Shovel, lvl: " + curLev, skin);
        shovel.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                weaponType = "Shovel";
                markShovel.setVisible(true);
                if (markHammer.isVisible()) markHammer.setVisible(false);
            }
        });
        currentCost = curLev * 3;
        shovelLevelUp = new TextButton(Integer.toString(currentCost), skin);
        shovelLevelUp.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                int curLev = 1;
                int currentCost = curLev * 3;
                if (coins.spendCoins(currentCost)) {
                    curLev++;
                    currentCost = curLev * 3;
                    shovelLevelUp.setText(Integer.toString(currentCost));
                    shovel.setText("Shovel, lvl: " + curLev);
                }
            }
        });
        table.row().height(100f);
        table.add(markHammer).width(75f);
        table.add(hammer).width(550f);
        table.add(hammerLevelUp).width(200f);
        table.row().height(100f);
        table.add(markShovel).width(75f);
        table.add(shovel).width(550f);
        table.add(shovelLevelUp).width(200f);
        table.center();
        stage.addActor(table);

        Table table2 = new Table();
        table2.setFillParent(true);

        TextButton play = new TextButton("GO", skin);
        play.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new LevelScreen(game));
            }
        });

        table2.row().height(100f);
        table2.add(play).width(100f);
        table2.bottom().right();
        stage.addActor(table2);

        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.fontColor = Color.WHITE;
        moneyLabel = new Label("MONEY: " + coins.amount, labelStyle);
        Table moneyTable = new Table();
        moneyTable.add(moneyLabel);
        moneyTable.right().top();
        moneyTable.setFillParent(true);
        stage.addActor(moneyLabel);

        Gdx.input.setInputProcessor(stage);
        Gdx.input.setCatchBackKey(true);
    }


    public void render(float delta) {
        Gdx.gl.glClearColor(1f, 1f, 1f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        moneyLabel.setText("MONEY: " + coins.amount);
        stage.act(delta);
        stage.draw();
    }

    public void resize(int width, int height) {
    }

    public void show() {
    }

    public void hide() {
    }

    public void pause() {
    }

    public void resume() {
    }

    public void dispose() {
        stage.dispose();
    }
}