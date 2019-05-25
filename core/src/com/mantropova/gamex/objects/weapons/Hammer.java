package com.mantropova.gamex.objects.weapons;

import com.mantropova.gamex.helpers.AssetsLoader;
import com.mantropova.gamex.objects.Player;

/**
 * Created by Antropova Marina on 12.05.2019.
 */

public class Hammer extends  Weapon {

    public Hammer(Player player) {
        AssetsLoader assets = AssetsLoader.getInstance();
        damage = assets.getPrefs().getInteger("HammerDamage");
        level = assets.getPrefs().getInteger("HammerLevel");
    }

    @Override
    public int attack() {
        return damage * level;
    }

    @Override
    public void extraAttack() {
    }
}