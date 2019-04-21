package com.mantropova.gamex.objects;

/**
 * Created by Antropova Marina on 22.04.2019.
 */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mantropova.gamex.helpers.AssetsLoader;
import java.util.*;

public class Mole extends Actor {

    private Sprite img;
    private Sprite imgAlive;
    private Texture imgAlive_texture;
    private Sprite imgDead;
    private Texture imgDead_texture;
    public Timer timer = new Timer();
    private int currentHealth;
    public boolean isDead = true;
    private Sound sound1;
    private Player player;
    private int damage;
    private int health;

    public Mole(float ourX, float ourY, final Player player, int health, int damage) {
        this.player = player;
        this.damage = damage;
        this.health = health;
        currentHealth = health;

        imgDead = new Sprite(imgDead_texture);

        imgAlive = new Sprite(imgAlive_texture);
        imgAlive.setSize((float) Gdx.graphics.getHeight() * 15 / 100, (float) Gdx.graphics.getHeight() * 15 / 100);
        img = imgDead;

        addListener(new  ClickListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
        setTouchable(Touchable.disabled);
    }

    public void setBounds(float x, float y, float width, float height) {
        super.setBounds(x, y, width, height);
        this.imgAlive.setPosition(x, y);
        this.imgDead.setPosition(x, y);
    }

    void escaping() {
        isDead = true;
    }

    public void draw(Batch batch, float alpha) {
        if (isDead)
            this.imgDead.draw(batch);
        else
            this.imgAlive.draw(batch);
    }

    public void update(float delta) {
        if (currentHealth < 1) {
            currentHealth = health;
            isDead = true;
            setTouchable(Touchable.disabled);
            timer.cancel();
            img = imgDead;
        }
    }

    public void resurrect() {
        isDead = false;
        setTouchable(Touchable.enabled);
        timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                escaping();
            }
        }, 2500);
    }

    public Sprite getImg() {
        return this.imgAlive;
    }
}