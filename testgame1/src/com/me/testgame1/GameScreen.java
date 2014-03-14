package com.me.testgame1;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;

public class GameScreen implements Screen {
	final TestGame1 game;
	
	// TODO alter volume so it isn't ear-splitting...
	
	OrthographicCamera camera;
	Rectangle area1;
	Rectangle area2;
	Rectangle area3;
	Sound sound1;
	Sound sound2;
	Sound sound3;
	long lastClick = System.nanoTime();
	long sinceLastClick;
	
	public GameScreen(final TestGame1 gam) {
		this.game = gam;
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 500, 900);
		
		sound1 = Gdx.audio.newSound(Gdx.files.internal("data/startup.wav"));
		sound2 = Gdx.audio.newSound(Gdx.files.internal("data/plink.wav"));
		sound3 = Gdx.audio.newSound(Gdx.files.internal("data/pickup.wav"));
		
		area1 = new Rectangle();
		area1.x = 0;
		area1.y = 0;
		area1.width = 500;
		area1.height = 300;
		
		area2 = new Rectangle();
		area2.x = 0;
		area2.y = 300;
		area2.width = 500;
		area2.height = 300;
		
		area3 = new Rectangle();
		area3.x = 0;
		area3.y = 600;
		area3.width = 500;
		area3.height = 300;
	}

	@Override
	public void render(float delta) {
		// TODO color the background and possibly color all the different areas different colors
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		camera.update();
		
		//game.batch.setProjectionMatrix(camera.combined);
		
		if (Gdx.input.isTouched()) {
			sinceLastClick = System.nanoTime() - lastClick;
			lastClick = System.nanoTime();
			
			game.batch.begin();
			game.font.draw(game.batch, Float.toString(sinceLastClick), 20, 20);
			game.batch.end();
			
			// TODO find out where the touch is
			if (Gdx.input.getY() < 300) {
				// TODO play a certain sound
				sound1.play();
			}
			else if (Gdx.input.getY() > 300 && Gdx.input.getY() < 600) {
				// TODO play a different sound
				sound2.play();
			}
			else if (Gdx.input.getY() > 600) {
				// TODO play another sound
				sound3.play();
			}
		}
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
}
