package com.mantropova.gamex.objects.weapons;

import com.mantropova.gamex.helpers.AssetsLoader;
import com.mantropova.gamex.objects.Player;

/**
 * Created by Antropova Marina on 12.05.2019.
 */


public class Shovel extends Weapon {

    private Player player;

    public Shovel(Player player) {
        AssetsLoader assets = AssetsLoader.getInstance();
        this.player = player;
        damage = assets.getPrefs().getInteger("ShovelDamage");
        level = assets.getPrefs().getInteger("ShovelLevel");
    }

    @Override
    public int attack() {
        return damage * level;
    }

    @Override
    public void extraAttack() {
        if (player.health < 100) player.health += damage / 5;
    }
}
