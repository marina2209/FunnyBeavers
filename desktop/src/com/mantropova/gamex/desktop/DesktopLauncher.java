package com.mantropova.gamex.desktop;

/**
 * Created by Antropova Marina on 01.04.2019.
 */

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mantropova.gamex.Main;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.title = "FunnyBeavers";

		config.width = 1280;
		config.height = 720;
		config.x = 100;
		config.y = 100;

		new LwjglApplication(new Main(), config);
	}
}
