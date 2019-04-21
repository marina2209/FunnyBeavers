package com.mantropova.gamex.objects;

/**
 * Created by Antropova Marina on 22.04.2019.
 */

import com.badlogic.gdx.Gdx;

public class Player {

    public int health;
    public int attack;
    public int moleKilled;

    public Player(String weaponType) {
        moleKilled = 0;
    }


    public void getHurted(int amount) {
        health -= amount;
        Gdx.app.log("CorrentHealth", Integer.toString(health));
    }

    public int attack() {
        return attack;
    }
}