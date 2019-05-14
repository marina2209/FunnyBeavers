package com.mantropova.gamex.helpers;

/**
 * Created by Antropova Marina on 14.05.2019.
 */

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.XmlReader;
import java.util.*;


public class XMLparser {

    private List<float[]> starsPos = new ArrayList<float[]>();

    private long delay = 1;
    private int moleHealth = 1;
    private int moleAttack = 1;

    public Array<String> XMLparseLevels(){
        Array<String> levels = new Array<String>();
        Array<Integer> int_levels = new Array<Integer>();

        FileHandle dirHandle;

        if (Gdx.app.getType() == ApplicationType.Android) {
            dirHandle = Gdx.files.internal("xml");
        } else {
            dirHandle = Gdx.files.internal(System.getProperty("user.dir") + "/xml");
        }

        for (FileHandle entry : dirHandle.list()) {
            levels.add(entry.name().split(".xml")[0]);
        }

        for (int i = 0; i < levels.size; i++ ) {
            int_levels.add(Integer.parseInt(levels.get(i)));
        }
        int_levels.sort();
        levels.clear();

        for (int i = 0; i < int_levels.size; i++) {
            levels.add(int_levels.get(i).toString());
        }
        return levels;
    }

    public List<float[]> getPos(String strLevel) {
        delay = 1;
        moleAttack = 1;
        moleHealth = 1;
        return this.starsPos;
    }

    public long getDelay() {
        return this.delay;
    }

    public int getHealth() {
        return moleHealth;
    }
    public int getAttack() {
        return moleAttack;
    }
}
