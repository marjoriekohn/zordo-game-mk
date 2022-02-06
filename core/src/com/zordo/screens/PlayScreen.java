package com.zordo.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.zordo.character.Linko;
import com.zordo.game.LegendOfZordo;

public class PlayScreen implements Screen{

	private SpriteBatch batch;
	private TextureRegion background;
	private Texture backgroundTexture;
	
	OrthographicCamera camera;
	
	final LegendOfZordo game;
	Linko linko;
	Linko linko2;
	Linko linko3;
	
	public PlayScreen(final LegendOfZordo game) {
		this.game = game;

		// setting the background texture
		background = new TextureRegion();
		backgroundTexture = new Texture("background_32.png");
		background.setTexture(backgroundTexture);
		
		// setting the camera
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800,400);
		
		linko = new Linko(true);
		linko2 = new Linko(false);
		linko3 = new Linko(false);
	
		linko2.setLinkoCollider(400, 10);
		linko3.setLinkoCollider(400,100);
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
		
		linko.move(batch);
		linko2.move(batch);
		linko3.move(batch);
		
		if(linko.getLinkoCollider().overlaps(linko2.getLinkoCollider())) {
			linko2.setLinkoCollider(500,10);
			if(linko.getHealth() > 0) {
				linko.setHealth(linko.getHealth()-1);
			}
		}
		if(linko.getLinkoCollider().overlaps(linko3.getLinkoCollider())) {
			linko3.setLinkoCollider(500, 100);
		}
		
		linko.healthRender(batch);
		
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
