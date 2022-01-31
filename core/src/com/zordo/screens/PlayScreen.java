package com.zordo.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;
import com.zordo.game.LegendOfZordo;

public class PlayScreen implements Screen{
	
	Rectangle player;
	private Sprite playerTexture;
	private SpriteBatch batch;
	private TextureAtlas atlas;
	private TextureRegion background;
	private Texture backgroundTexture;
	
	private Boolean flippedLeft;
	private Boolean flippedRight;
	
	OrthographicCamera camera;
	
	final LegendOfZordo game;

	public PlayScreen(final LegendOfZordo game) {
		this.game = game;
		player = new Rectangle();
		player.x = 10;
		player.y = 10;
		
		flippedLeft = false;
		flippedRight = true;
		
		atlas = new TextureAtlas("link-sprites.txt");
		playerTexture = new Sprite(atlas.createSprite("link-standing-0"));
		background = new TextureRegion();
		backgroundTexture = new Texture("background_32.png");
		background.setTexture(backgroundTexture);
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800,400);
		
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
		batch.setProjectionMatrix(camera.combined);
		
		batch.begin();
		batch.draw(backgroundTexture,0,0,800,480);
		batch.draw(playerTexture, player.x,player.y);

		
		if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			if(!flippedRight) {
				playerTexture.flip(true, false);
				flippedRight = true;
				flippedLeft = false;
			}
			player.x += 500 * Gdx.graphics.getDeltaTime();
		} else if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			if(!flippedLeft) {
				playerTexture.flip(true, false);
				flippedRight = false;
				flippedLeft = true;
			}
			player.x -= 500 * Gdx.graphics.getDeltaTime();
		} else if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
			player.y += 1000 * Gdx.graphics.getDeltaTime();
		}
		
		if(player.y > 10) {
			player.y -= 25 * Gdx.graphics.getDeltaTime();
		}

		batch.end();
		camera.update();
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
