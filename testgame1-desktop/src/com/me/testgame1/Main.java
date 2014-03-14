package com.me.testgame1;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "testgame1";
		cfg.useGL20 = false;
		cfg.width = 500;
		cfg.height = 900;
		
		new LwjglApplication(new TestGame1(), cfg);
	}
}
