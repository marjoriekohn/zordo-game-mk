package com.zordo.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;
import com.zordo.game.LegendOfZordo;

public class PlayScreen implements Screen{
	
	Rectangle player;
	private Texture playerTexture;
	private SpriteBatch batch;
	
	final LegendOfZordo game;

	public PlayScreen(final LegendOfZordo game) {
		this.game = game;
		player = new Rectangle();
		player.x = 10;
		player.y = 10;
		
		playerTexture = new Texture("badlogic.jpg");
		
	}
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		System.out.print("hello, welcome to playscreen");
		
	}

	@Override
	public void render(float delta) {
		ScreenUtils.clear(Color.BLACK);
		
		batch = new SpriteBatch();
		
		batch.begin();
		batch.draw(playerTexture, player.x,player.y);
		batch.end();
		
		if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			player.x += 500 * Gdx.graphics.getDeltaTime();
		} else if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			player.x -= 500 * Gdx.graphics.getDeltaTime();
		} else if(Gdx.input.isKeyPressed(Input.Keys.UP)) {
			player.y += 500 * Gdx.graphics.getDeltaTime();
		} else if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
			player.y -= 500 * Gdx.graphics.getDeltaTime();
		}
		
		

	}

	@Override
	public void resize(int width, int height) {
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
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
