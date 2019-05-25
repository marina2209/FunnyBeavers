package com.mantropova.gamex.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

/**
 * Created by Antropova Marina on 22.04.2019.
 */

public class Coins{

    public int amount;
    private Preferences pref = Gdx.app.getPreferences("FunnyBeavers");

    public Coins() {
        amount = pref.getInteger("Coins");
    }

    public boolean spendCoins(int amount){
        if (amount <= this.amount){
            this.amount -= amount;
            pref.putInteger("Coins", this.amount);
            pref.flush();
            return true;
        }
        else return false;
    }

    public void addCoins(int amount){
        this.amount += amount;
        pref.putInteger("Coins", this.amount);
        pref.flush();
    }
}
