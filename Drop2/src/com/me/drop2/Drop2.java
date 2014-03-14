package com.me.drop2;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
 
public class Drop2 extends Game {
 
	SpriteBatch batch;
	BitmapFont font;
 
	public void create() {
		batch = new SpriteBatch();
		// Use LibGDX's default Arial font.
		font = new BitmapFont();
		this.setScreen(new MainMenuScreen(this));
	}
 
	public void render() {
		super.render(); // important! needed for rendering a game object!
	}
 
	public void dispose() {
		batch.dispose();
		font.dispose();
	}
 
}
