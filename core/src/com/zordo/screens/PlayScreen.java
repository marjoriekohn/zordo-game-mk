package com.zordo.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.utils.ScreenUtils;
import com.zordo.character.Linko;
import com.zordo.environment.Platform;
import com.zordo.game.LegendOfZordo;

public class PlayScreen implements Screen{

	private SpriteBatch batch;
	private TextureRegion background;
	private Texture backgroundTexture;
	
	OrthographicCamera camera;
	
	final LegendOfZordo game;
	Linko linko;
	Linko linko2;
	
	private Platform boundaryleft;
	private Platform boundaryright;
	private Platform boundarybottom;
	private Platform boundarytop;
	
	
	public PlayScreen(final LegendOfZordo game) {
		this.game = game;

		
		this.boundaryleft = new Platform(10,1080);
		this.boundaryleft.setCoordinates(0,0);
		this.boundaryright = new Platform(10,1080);
		this.boundaryright.setCoordinates(790,0);
		this.boundarytop = new Platform(800,10);
		this.boundarytop.setCoordinates(0,390);
		
		ScreenUtils.clear(0, 0, 0.2f, 1);

		// setting the background texture
		background = new TextureRegion();
		backgroundTexture = new Texture("background_32.png");
		background.setTexture(backgroundTexture);
		
		// setting the camera
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800,400);
		
		linko = new Linko(true);
		linko2 = new Linko(false);
	
		linko2.setLinkoCollider(400, 10);
	}
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		
    	Gdx.gl20.glClearColor(0, 0, 0.2f, 0.0f);
    	Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
    	
		batch = new SpriteBatch();
		batch.setProjectionMatrix(camera.combined);
		
		batch.begin();
		batch.draw(backgroundTexture,0,0,1920,1080);
		
		boundaryleft.render(batch);
		boundaryright.render(batch);
		boundarytop.render(batch);
		
		// mostly every screen
		linko.move(batch,camera);
		
		// every screen
		linko.collisionWithPlatform();
		if(linko.getLinkoCollider().overlaps(this.boundaryleft)) {
			linko.setLinkoCollider(this.boundaryleft.getX() + 10, linko.getLinkoCollider().y);
		}
		
		if(linko.getLinkoCollider().overlaps(this.boundaryright)) {
			linko.setLinkoCollider(this.boundaryright.getX() - 25, linko.getLinkoCollider().y);
		}
		
		// every screen
		if(linko.health <= 0) {
			BitmapFont font = new BitmapFont();
			font.draw(batch, "GAME OVER", 400, 200);
		}

		
		if(linko2 != null) {
			linko2.move(batch,camera);
			
			if(linko.getLinkoCollider().overlaps(linko2.getLinkoCollider())) {
				linko2.setLinkoCollider(500,10);
				linko2.damage();
				linko.damage();
			}
			
			if(linko2.hearts.isEmpty()) {
				linko2 = null;
				System.gc();
			}
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
		batch.dispose();
	}

}
