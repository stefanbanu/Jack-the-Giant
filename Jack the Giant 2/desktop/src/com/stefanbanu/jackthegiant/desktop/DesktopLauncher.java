package com.stefanbanu.jackthegiant.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.stefanbanu.jackthegiant.GameMain;

import utils.GameUtil;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = GameUtil.WIDTH;
		config.height = GameUtil.HEIGHT;
		new LwjglApplication(new GameMain(), config);
	}
}
