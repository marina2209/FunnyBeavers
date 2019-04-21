package com.mantropova.gamex.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

/**
 * Created by Antropova Marina on 22.04.2019.
 */

public class Coins{

    public int amount;

    public Coins() {
        amount = 1;
    }

    public boolean spendCoins(int amount){
        if (amount <= this.amount){
            this.amount -= amount;
            return true;
        }
        else return false;
    }

    public void addCoins(int amount){
        this.amount += amount;
    }
}
