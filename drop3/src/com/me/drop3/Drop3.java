package com.me.drop3;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
 
public class Drop3 extends Game {
 
  SpriteBatch batch;
  	BitmapFont font;
	//AssetManager manager = new AssetManager();
 
	public void create() {
		batch = new SpriteBatch();
		//manager.load("data/Oxygen.otf", BitmapFont.class);
		//font = manager.get("data/Oxygen.otf", BitmapFont.class);
		
		// Use LibGDX's default Arial font.
		font = new BitmapFont();
		this.setScreen(new MainMenuScreen(this));
	}
 
	public void render() {
		//if(manager.update()) {
	         // we are done loading, let's move to another screen!
	    //}

	    // display loading information
	    //float progress = manager.getProgress();
	    //manager.finishLoading();
		
		super.render(); // important!
	}
 
	public void dispose() {
		batch.dispose();
		font.dispose();
		//manager.dispose();
	}
 
}
