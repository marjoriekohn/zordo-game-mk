package com.zordo.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.zordo.character.Linko;
import com.zordo.game.LegendOfZordo;

public class PlayScreen implements Screen{
	
	Rectangle other;

	private SpriteBatch batch;
	private TextureRegion background;
	private Texture backgroundTexture;
	
	OrthographicCamera camera;

	float elapsed;
	float elapsed2;
	
	final LegendOfZordo game;
	Linko linko;
	Linko otherLinko;
	
	int jumps;
	float jump2;

	public PlayScreen(final LegendOfZordo game) {
		this.game = game;

		other = new Rectangle();
		other.x = 300;
		other.y = 10;
		other.width = 10;
		other.height = 10;

		// setting the background texture
		background = new TextureRegion();
		backgroundTexture = new Texture("background_32.png");
		background.setTexture(backgroundTexture);
		
		// setting the camera
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800,400);
		
		linko = new Linko();
		otherLinko = new Linko();	
		
		elapsed = 0;
		elapsed2 = 13.50003323654f;
		
		jumps = 0;
		jump2 = 0;
		
		otherLinko.setRunningLeftAnimation(new Animation<Sprite>(1f/6f,otherLinko.getRunningLeftFrames()));
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
		batch.draw(backgroundTexture,0,0,800,400);
		elapsed += Gdx.graphics.getDeltaTime();		
		elapsed2 += Gdx.graphics.getDeltaTime();

		if(!Gdx.input.isKeyPressed(Input.Keys.LEFT) && !Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			if(linko.getFlippedRight() && !linko.getJumping()) {
				linko.setStandRight(linko.getStandRight());
				batch.draw(linko.getStandRight(), linko.getLinkoCollider().x, linko.getLinkoCollider().y);
			} else if (!linko.getJumping()) {
				batch.draw(linko.getStandLeft(), linko.getLinkoCollider().x, linko.getLinkoCollider().y);
			}
		} 
		if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			if(!linko.getFlippedRight()) {
				linko.setFlippedRight(true);;
			}
			if(!linko.getJumping()){
				batch.draw(linko.getRunningRightAnimation().getKeyFrame(elapsed,true), linko.getLinkoCollider().x,linko.getLinkoCollider().y);
			}
			if(!Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
				linko.getLinkoCollider().x += 100 * Gdx.graphics.getDeltaTime();
			} else {
				linko.getLinkoCollider().x += 150 * Gdx.graphics.getDeltaTime();
			}
		} else if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			if(linko.getFlippedRight()) {
				linko.setFlippedRight(false);
			}
			if(!linko.getJumping()){
				batch.draw(linko.getRunningLeftAnimation().getKeyFrame(elapsed,true), linko.getLinkoCollider().x,linko.getLinkoCollider().y);
			}
			if(!Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
				linko.getLinkoCollider().x -= 100 * Gdx.graphics.getDeltaTime();
			} else {
				linko.getLinkoCollider().x -= 150 * Gdx.graphics.getDeltaTime();
			}
		} 
		
		if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE) && jumps < 2) {
			linko.setJumping(true);
			if(jumps == 0) {
				linko.getLinkoCollider().y += (100 * Gdx.graphics.getDeltaTime()) + 75;
				if(Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
					linko.getLinkoCollider().y += 50;
				}
				jump2 = linko.getLinkoCollider().y * 1.5f;
				System.out.println("jump 1: " + linko.getLinkoCollider().y);
			} else {
				System.out.println("jump 2 start: " + linko.getLinkoCollider().y);
				linko.getLinkoCollider().y = jump2;
				System.out.println("jump 2: " + linko.getLinkoCollider().y);
			}
			jumps++;
		} 
		
		if(linko.getJumping()) {
			jumpRender();
		}
		
		if(linko.getLinkoCollider().y > 11) {
			linko.getLinkoCollider().y -= 120 * Gdx.graphics.getDeltaTime();
		} else if (linko.getLinkoCollider().y < 11) {
			jumps = 0;
			linko.setJumping(false);
		}
		
		batch.draw(otherLinko.getRunningLeftAnimation().getKeyFrame(elapsed2,true), other.x, other.y);
		other.x -= 100 * Gdx.graphics.getDeltaTime();
		
		if(linko.getLinkoCollider().overlaps(other)) {
			other.x = 500;
			other.y = 10;
		}
		
		batch.end();
		camera.update();
	}

	private void jumpRender() {
		if(linko.getFlippedRight()) {
			batch.draw(linko.getJumpRight(), linko.getLinkoCollider().x, linko.getLinkoCollider().y);
		} else {
			batch.draw(linko.getJumpLeft(), linko.getLinkoCollider().x, linko.getLinkoCollider().y);
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
