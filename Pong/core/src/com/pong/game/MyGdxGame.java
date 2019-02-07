package com.pong.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.pong.game.states.GameStateManager;
import com.pong.game.states.MenyState;

public class MyGdxGame extends ApplicationAdapter {
	public static final int WIDTH = 1000;
	public static final int HEIGHT = 400;

	public static final String TITLE = "Pong";
	private SpriteBatch batch;
	Texture img;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		Gdx.gl.glClearColor(1, 0, 0, 1);
		GameStateManager.getInstance().setState(new MenyState());
	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		GameStateManager.getInstance().update(Gdx.graphics.getDeltaTime());
		GameStateManager.getInstance().render(batch);

		/*batch.begin();
		batch.draw(img, 0, 0);
		batch.end();*/
	}
	
	@Override
	public void dispose () {

	}
}
