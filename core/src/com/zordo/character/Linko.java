package com.zordo.character;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

public class Linko {
	Rectangle linkoCollider;
	Boolean flippedRight;
	Boolean jumping;
		
	Animation<Sprite> runningRightAnimation;
	Animation<Sprite> runningLeftAnimation;
	
	Sprite[] runningRightFrames;
	Sprite[] runningLeftFrames;
	Sprite standRight;
	Sprite standLeft;
	Sprite jumpRight;
	Sprite jumpLeft;

	// accessing sprite assets from the directory files
	String standuri = "link-standing-sprites/";
	String runuri = "link-running-sprites/";
	String jumpuri = "link-jumping-sprites/";
		
	public Linko() {
		linkoCollider = new Rectangle();
		linkoCollider.x = 10;
		linkoCollider.y = 10;
		linkoCollider.height = 25;
		linkoCollider.width = 15;
		
		// orientation and jumping status
		flippedRight = true;
		jumping = false;
		
		// animation objects
		runningRightFrames = new Sprite[6];
		runningLeftFrames = new Sprite[6];
		
		
		// standing and jumping sprite creation
		standRight = new Sprite(new Texture(standuri + "link-standing-0.png"));
		standLeft = new Sprite(standRight);
		jumpRight = new Sprite(new Texture(jumpuri + "link-jumping-0.png"));
		jumpLeft = new Sprite(jumpRight);
		standLeft.flip(true, false);
		jumpLeft.flip(true, false);
		
		// running animation construction
		for(int i = 0; i < 6; i++) {
			runningRightFrames[i] = new Sprite(new Texture(runuri + "link-running-" + i + ".png"));
			runningLeftFrames[i] = new Sprite(new Texture(runuri + "link-running-" + i + ".png"));
			runningLeftFrames[i].flip(true, false);
		}

		// setting the animations
		runningRightAnimation = new Animation<Sprite>(1f/4f, runningRightFrames);
		runningLeftAnimation = new Animation<Sprite>(1f/4f, runningLeftFrames);
	}
	
	public void setLinkoCollider(Rectangle linkoCollider) {
		this.linkoCollider = linkoCollider;
	}
	
	public Rectangle getLinkoCollider() {
		return this.linkoCollider;
	}
	
	public void setFlippedRight(Boolean flip) {
		this.flippedRight = flip;
	};
	
	public Boolean getFlippedRight() {
		return this.flippedRight;
	};
	
	public void setJumping(Boolean jumping) {
		this.jumping = jumping;
	};
	
	public Boolean getJumping() {
		return this.jumping;
	};
	
	public void setRunningRightAnimation(Animation<Sprite> runningRightAnimation) {
		this.runningRightAnimation = runningRightAnimation;
	};
	
	public Animation<Sprite> getRunningRightAnimation() {
		return this.runningRightAnimation;
	};
	
	public void setRunningLeftAnimation(Animation<Sprite> runningLeftAnimation) {
		this.runningLeftAnimation = runningLeftAnimation;
	};
	
	public Animation<Sprite> getRunningLeftAnimation() {
		return this.runningLeftAnimation;
	};
	
	public void setRunningRightFrames(Sprite[] runningRightFrames) {
		this.runningRightFrames = runningRightFrames;
	};
	
	public Sprite[] getRunningRightFrames() {
		return this.runningRightFrames;
	};
	
	public void setRunningLeftFrames(Sprite[] runningLeftFrames) {
		this.runningLeftFrames = runningLeftFrames;
	};
	
	public Sprite[] getRunningLeftFrames() {
		return this.runningLeftFrames;
	};
	
	public void setStandRight(Sprite standRight) {
		this.standRight = standRight;
	};
	
	public Sprite getStandRight() {
		return this.standRight;
	};
	
	public void setStandLeft(Sprite standLeft) {
		this.standLeft = standLeft;
	};
	
	public Sprite getStandLeft() {
		return this.standLeft;
	};
	
	public void setJumpRight(Sprite jumpRight) {
		this.jumpRight = jumpRight;
	};
	
	public Sprite getJumpRight() {
		return this.jumpRight;
	};
	
	public void setJumpLeft(Sprite jumpLeft) {
		this.jumpLeft = jumpLeft;
	};
	
	public Sprite getJumpLeft() {
		return this.jumpLeft;
	};
	
	
}
