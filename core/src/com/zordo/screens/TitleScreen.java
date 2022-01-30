package com.zordo.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.zordo.game.GifDecoder;
import com.zordo.game.LegendOfZordo;

public class TitleScreen implements Screen {

	final LegendOfZordo game;
    public SpriteBatch batch;
    public Animation<TextureRegion> animation;
    float elapsed;
    
    OrthographicCamera camera;
    
    public TitleScreen(final LegendOfZordo game) {
    	this.game = game;
    	camera = new OrthographicCamera();
    	camera.setToOrtho(false,800,400);
    }
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
	    batch = new SpriteBatch();
	    batch.setProjectionMatrix(camera.combined);
	    animation = GifDecoder.loadGIFAnimation(Animation.PlayMode.LOOP, Gdx.files.internal("lozTitle.gif").read());		

		// TODO Auto-generated method stub
        elapsed += Gdx.graphics.getDeltaTime();
        Gdx.gl.glClearColor(1, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(animation.getKeyFrame(elapsed), 0, 0);
        batch.end();
        
        if(Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
        	this.game.setScreen(new PlayScreen());
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
		batch.dispose();
	}

}
