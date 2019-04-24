package com.mantropova.gamex.objects;

/**
 * Created by Antropova Marina on 24.04.2019.
 */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mantropova.gamex.helpers.AssetsLoader;


public class Background extends Actor {
    private Texture backgroundTexture;
    private Sprite backgroundSprite;
    private Texture mushroomr;
    private Sprite mushroomrSprite;
    private Texture mushroomb;
    private Sprite mushroombSprite;
    private Texture mushroomw;
    private Sprite mushroomwSprite;
    private Texture pine;
    private Sprite pineSprite;
    private Texture oak;
    private Sprite oakSprite;
    private AssetsLoader assets = new AssetsLoader();
    int screenWidth = Gdx.graphics.getWidth();
    int screenHeight = Gdx.graphics.getHeight();

    public Background() {
        backgroundTexture = assets.background;
        backgroundSprite = new Sprite(backgroundTexture, screenWidth, screenHeight);
        mushroomr = assets.mushroomred;
        mushroomrSprite = new Sprite(mushroomr);
        mushroomb = assets.mushroombrown;
        mushroombSprite = new Sprite(mushroomb);
        mushroomw = assets.mushroomwhite;
        mushroomwSprite = new Sprite(mushroomw);
        pine = assets.pine;
        pineSprite = new Sprite(pine);
        oak = assets.oak;
        oakSprite = new Sprite(oak);
    }

    @Override
    public void draw(Batch batch, float alpha) {
        backgroundSprite.draw(batch);
        float x = -75f;
        while (x < screenWidth){
            mushroomrSprite.draw(batch);
            mushroomrSprite.setPosition(x + 50f, 65f);
            x += 50f;
            mushroombSprite.draw(batch);
            mushroombSprite.setPosition(x + 50f, 65f);
            x += 50f;
            mushroomwSprite.draw(batch);
            mushroomwSprite.setPosition(x + 50f, 65f);
            x += 50f;
        }
        int y = screenHeight;
        while (y - pine.getHeight() > 100f) {
            y -= pine.getHeight();
            pineSprite.setPosition(20f, y);
            pineSprite.draw(batch);
            oakSprite.setPosition(screenWidth - assets.oak.getWidth() - 20f, y);
            oakSprite.draw(batch);
        }
    }
}
