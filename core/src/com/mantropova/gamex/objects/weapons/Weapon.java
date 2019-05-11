package com.mantropova.gamex.objects.weapons;

/**
 * Created by Antropova Marina on 12.05.2019.
 */

public abstract class Weapon{
    int damage;
    int level;

    public abstract int attack();
    public abstract void extraAttack();
}
