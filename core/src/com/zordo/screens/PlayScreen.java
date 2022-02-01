package com.zordo.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.zordo.game.LegendOfZordo;

public class PlayScreen implements Screen{
	
	Rectangle player;

	private SpriteBatch batch;
	private TextureRegion background;
	private Texture backgroundTexture;
	
	private Boolean flippedRight;
	
	OrthographicCamera camera;
	
	Animation<Sprite> animation;
	Animation<Sprite> runningRightAnimation;
	Animation<Sprite> runningLeftAnimation;
	
	Sprite[] runningRightFrames;
	Sprite[] runningLeftFrames;
	Sprite standRight;
	Sprite standLeft;
	
	float elapsed;
	
	final LegendOfZordo game;

	public PlayScreen(final LegendOfZordo game) {
		this.game = game;
		player = new Rectangle();
		player.x = 10;
		player.y = 10;
		
		flippedRight = true;
		
		runningRightFrames = new Sprite[6];
		runningLeftFrames = new Sprite[6];
		
		String standuri = "link-standing-sprites/";
		String runuri = "link-running-sprites/";
		
		standRight = new Sprite(new Texture(standuri + "link-standing-0.png"));
		standLeft = new Sprite(new Texture(standuri + "link-standing-0.png"));
		standLeft.flip(true, false);
		
		for(int i = 0; i < 6; i++) {
			runningRightFrames[i] = new Sprite(new Texture(runuri + "link-running-" + i + ".png"));
			runningLeftFrames[i] = new Sprite(new Texture(runuri + "link-running-" + i + ".png"));
			runningLeftFrames[i].flip(true, false);
		}

		runningRightAnimation = new Animation<Sprite>(1f/4f, runningRightFrames);
		runningLeftAnimation = new Animation<Sprite>(1f/4f, runningLeftFrames);
		animation = runningRightAnimation;
		
		background = new TextureRegion();
		backgroundTexture = new Texture("background_32.png");
		background.setTexture(backgroundTexture);
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800,400);
		
	}
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		batch = new SpriteBatch();
		batch.setProjectionMatrix(camera.combined);
		
		batch.begin();
		batch.draw(backgroundTexture,0,0,800,480);


		if(!Gdx.input.isKeyPressed(Input.Keys.LEFT) && !Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			elapsed += Gdx.graphics.getDeltaTime();
			if(flippedRight) {
				batch.draw(standRight, player.x, player.y);
			} else {
				batch.draw(standLeft, player.x, player.y);
			}
		} else { 
			elapsed += Gdx.graphics.getDeltaTime();
			if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
				if(!flippedRight) {
					flippedRight = true;
				}
				animation = runningRightAnimation;
				player.x += 200 * Gdx.graphics.getDeltaTime();
			} else if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
				if(flippedRight) {
					flippedRight = false;
				}
				animation = runningLeftAnimation;
				player.x -= 200 * Gdx.graphics.getDeltaTime();
			} 
			batch.draw(animation.getKeyFrame(elapsed,true), player.x,player.y);
		}
		
		if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
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
