package com.mantropova.gamex.objects;

/**
 * Created by Antropova Marina on 21.04.2019.
 */

import com.badlogic.gdx.utils.Array;
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

    public Level(String level) {
        this.level = Integer.parseInt(level);
        coins = new Coins();

        timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                setMoleAlive(random);
            }
        }, delay, delay);

    }

    public int update(float delta) {
        return 1;
    }

    public void gameOver() {
        timer.cancel();
    }

    public void pause() {
        timer.cancel();
    }

    public void continueFromPause() {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                setMoleAlive(random);
            }
        }, delay, delay);
    }

    private void setMoleAlive(Random rand) {
        int num = rand.nextInt(molesArray.size - 1);
        // TODO
    }

    public int getPlayerHealth() {
        return 1;
    }
}

