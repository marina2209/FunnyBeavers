package com.mantropova.gamex.objects;

/**
 * Created by Antropova Marina on 22.04.2019.
 */

import com.badlogic.gdx.Gdx;
import com.mantropova.gamex.objects.armour.Armour;
import com.mantropova.gamex.objects.armour.Barrel;
import com.mantropova.gamex.objects.weapons.Hammer;
import com.mantropova.gamex.objects.weapons.Shovel;
import com.mantropova.gamex.objects.weapons.Weapon;

public class Player {

    public int health;
    public int attack;
    public int moleKilled;
    private Armour armour;
    private Weapon weapon;

    public Player(String weaponType) {
        if (weaponType.equals("Hammer")) {
            weapon = new Hammer(this);
        } else if (weaponType.equals("Shovel")) {
            weapon = new Shovel(this);
        } else {
            weapon = new Shovel(this);
        }
        armour = new Barrel(this);
        health = 100 + armour.getMoreHealth();
        attack = weapon.attack() + armour.getMoreAttack();

        moleKilled = 0;
    }


    public void getHurted(int amount) {
        health -= amount;
        Gdx.app.log("CorrentHealth", Integer.toString(health));
    }

    public int attack() {
        weapon.extraAttack();
        return attack;
    }
}