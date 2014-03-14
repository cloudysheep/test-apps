package com.me.drop3;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "Drop V3";
		//cfg.useGL20 = false;
		cfg.width = 800;
	    cfg.height = 480;
		
		new LwjglApplication(new Drop3(), cfg);
	}
}
