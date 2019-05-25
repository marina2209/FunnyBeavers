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
    private Sprite imgDead;
    public Timer timer = new Timer();
    private int currentHealth;
    public boolean isDead = true;
    private Sound sound1;
    private Player player;
    private int damage;
    private int health;

    public Mole(float ourX, float ourY, final Player player, int health, int damage,
                final boolean isMusicOn) {
        this.player = player;
        this.damage = damage;
        this.health = health;
        currentHealth = health;
        AssetsLoader assets = AssetsLoader.getInstance();
        if (assets.moleDead == null)
            throw new NullPointerException("moleDead texture");
        Texture imgDead_texture = assets.moleDead;
        imgDead = new Sprite(imgDead_texture);
        imgDead.setSize((float) Gdx.graphics.getHeight() * 15 / 100,
                        (float) Gdx.graphics.getHeight() * 15 / 100);
        if (assets.moleAlive == null)
            throw new NullPointerException("moleAlive texture");
        Texture imgAlive_texture = assets.moleAlive;
        imgAlive = new Sprite(imgAlive_texture);
        imgAlive.setSize((float) Gdx.graphics.getHeight() * 15 / 100,
                         (float) Gdx.graphics.getHeight() * 15 / 100);
        img = imgDead;
        sound1 = assets.sound1;

        addListener(new  ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                currentHealth -= player.attack();
                if (isMusicOn && (sound1 != null)) {
                    sound1.play();
                    sound1.setVolume(1, 1f);
                }
                return true;
            }
        });
        setTouchable(Touchable.disabled);
    }

    @Override
    public void setBounds(float x, float y, float width, float height) {
        super.setBounds(x, y, width, height);
        this.imgAlive.setPosition(x, y);
        this.imgDead.setPosition(x, y);
    }

    void escaping() {
        isDead = true;
        player.getHurted(damage);
    }

    @Override
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
            player.moleKilled++;
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