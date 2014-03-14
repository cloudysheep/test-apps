package com.me.drop2;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.me.drop2.Drop2;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
	    cfg.title = "Drop";
	    cfg.width = 800;
	    cfg.height = 480;
	    new LwjglApplication(new Drop2(), cfg);
	}
}
