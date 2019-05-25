package com.mantropova.gamex.objects;

/**
 * Created by Antropova Marina on 21.04.2019.
 */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.mantropova.gamex.helpers.AssetsLoader;
import com.mantropova.gamex.helpers.XMLparser;

import java.util.*;

public class Level {

    public boolean isGameOver = false;
    public Array<Mole> molesArray;
    public long delay = 1;
    private Random random = new Random();
    private Timer timer;
    public Player player;
    private Coins coins;
    private int level;

    public Level(String level, String weapon) {
        this.level = Integer.parseInt(level);
        player = new Player(weapon);
        coins = new Coins();

        XMLparser xml = new XMLparser();
        List<float[]> posArray = xml.getPos(level);

        delay = xml.getDelay();
        timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                setMoleAlive(random);
            }
        }, delay, delay);

        AssetsLoader assets = AssetsLoader.getInstance();

        int i;
        molesArray = new Array<Mole>();
        for (i = 0; i < posArray.size(); i++) {
            molesArray.add(new Mole(posArray.get(i)[0], posArray.get(i)[1],
                                    player, xml.getHealth(), xml.getAttack(),
                                    assets.getPrefs().getBoolean("Music")));
        }

        i = 0;
        for (Mole mole : molesArray) {
            mole.setBounds(
                    Gdx.graphics.getWidth() * posArray.get(i)[0] / 100,
                    Gdx.graphics.getHeight() * posArray.get(i)[1] / 100 - mole.getImg().getHeight() / 2,
                    mole.getImg().getWidth(),
                    mole.getImg().getHeight()
            );
            i++;
        }

    }

    public int update(float delta) {
        if (player.health < 1) isGameOver = true;
        for (Mole mole : molesArray)
            mole.update(delta);
        return player.moleKilled;
    }

    public void gameOver() {
        timer.cancel();
        for (Mole mole : molesArray) {
            mole.timer = new Timer();
        }
        coins.addCoins(player.moleKilled * level);
    }

    public void pause() {
        timer.cancel();
        for (Mole mole : molesArray) {
            mole.timer.cancel();
        }
    }

    public void continueFromPause() {
        timer = new Timer();
        for (Mole mole : molesArray) {
            mole.timer = new Timer();
        }
        timer.schedule(new TimerTask() {
            public void run() {
                setMoleAlive(random);
            }
        }, delay, delay);
    }

    private void setMoleAlive(Random rand) {
        int num = rand.nextInt(molesArray.size - 1);
        if (molesArray.get(num).isDead) {
            molesArray.get(num).resurrect();
        }
    }

    public int getPlayerHealth() {
        return (player.health > 0) ? player.health : 0;
    }
}

