package com.drmantaray.blockbuster.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.drmantaray.blockbuster.BlockBuster;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "BlockBuster";
		config.width = 480;
		config.height = 560;
		new LwjglApplication(new BlockBuster(), config);
	}
}
