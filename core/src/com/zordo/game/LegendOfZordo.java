package com.zordo.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Sound;
import com.zordo.screens.MainScreen;
import com.zordo.screens.TitleScreen;

public class LegendOfZordo extends Game {
	private Game game;
	TitleScreen ts = new TitleScreen();
	MainScreen ms = new MainScreen();
	int state = 0;
	Sound sound;	
	
	public LegendOfZordo() {
		game = this;
	}
	
	@Override
	public void create () {
		game.setScreen(ts);
		sound = Gdx.audio.newSound(Gdx.files.internal("GLASS.WAV"));
	}

    @Override
    public void render () {
    	super.render();
    	if(Gdx.input.isKeyPressed(Keys.ENTER)) {
    		if( state < 1 ) {
    			sound.play();
    		} 
    		state++;
    	}
    	if( state == 1 ) {
    		game.setScreen(ms);
    	}
    }
	
	@Override
	public void dispose () {
	}
}

