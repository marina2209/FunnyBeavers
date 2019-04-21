package com.mantropova.gamex.helpers;

/**
 * Created by Antropova Marina on 21.04.2019.
 */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;

public class AssetsLoader {
    public Texture moleAlive;
    public Texture moleDead;
    public Texture background;
    public Texture dirtgrass;
    public Texture mushroomred;
    public Texture mushroomwhite;
    public Texture mushroombrown;
    public Texture pine;
    public Texture oak;
    public Music music;
    public Sound sound1;
    public Sound sound2;
    public Texture buttonon;
    public Texture buttonoff;
    public Texture pauseButton;
    public Texture mark;

    private void load() {
        moleAlive = new Texture(Gdx.files.internal("images/mole.png"));
        moleDead = new Texture(Gdx.files.internal("images/hole.png"));
        background = new Texture(Gdx.files.internal("images/collage.jpg"));
        dirtgrass = new Texture(Gdx.files.internal("images/dirt_grass.png"));
        mushroomred = new Texture(Gdx.files.internal("images/mushroom_red.png"));
        mushroomwhite = new Texture(Gdx.files.internal("images/mushroom_tan.png"));
        mushroombrown = new Texture(Gdx.files.internal("images/mushroom_brown.png"));
        pine = new Texture(Gdx.files.internal("images/tree_pine.png"));
        oak = new Texture(Gdx.files.internal("images/tree_oak.png"));
        music = Gdx.audio.newMusic(Gdx.files.internal("music/music1.ogg"));
        sound1 = Gdx.audio.newSound(Gdx.files.internal("music/sound1.wav"));
        sound2 = Gdx.audio.newSound(Gdx.files.internal("music/sound2.mp3"));
        buttonoff = new Texture(Gdx.files.internal("images/ButtonOff.png"));
        buttonon = new Texture(Gdx.files.internal("images/ButtonOn.png"));
        pauseButton = new Texture(Gdx.files.internal("images/ButtonOn.png"));
        mark = new Texture(Gdx.files.internal("images/mark.png"));

    }
}