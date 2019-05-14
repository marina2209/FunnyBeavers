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
        try {
            XmlReader.Element root = (new XmlReader()).parse(Gdx.files.internal("xml/"+strLevel+".xml"));
            this.moleAttack = Integer.parseInt(root.getChildrenByName("attack").get(0).getText());
            this.moleHealth = Integer.parseInt(root.getChildrenByName("health").get(0).getText());
            this.delay = Long.parseLong(root.getChildrenByName("delay").get(0).getText());
            Array<XmlReader.Element> xml_pos = root.getChildByName("positions").getChildrenByName("position");
            for (XmlReader.Element el : xml_pos) {
                this.starsPos.add(new float[]{ Float.parseFloat(el.getAttribute("x")),
                        Float.parseFloat(el.getAttribute("y"))});
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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
