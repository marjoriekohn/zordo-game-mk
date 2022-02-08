package com.zordo.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.zordo.screens.PlayScreen;
import com.zordo.screens.TitleScreen;

public class LegendOfZordo extends Game {
		
	@Override
	public void create () {
		Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());
		this.setScreen(new TitleScreen(this));
	}

    @Override
    public void render () {
    	super.render();
    }
	
	@Override
	public void dispose () {
		super.dispose();
	}
}

