package com.zordo.character;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Linko {	
	Rectangle linkoCollider;
	Boolean flippedRight;
	Boolean jumping;
	Boolean playerControlled;
	Boolean stepping;
	
	int health;
	Sprite[] hearts;
		
	Animation<Sprite> runningRightAnimation;
	Animation<Sprite> runningLeftAnimation;
	Animation<Sprite> walkingRightAnimation;
	Animation<Sprite> walkingLeftAnimation;
	Animation<Sprite> animation;
	
	Sprite[] runningRightFrames;
	Sprite[] runningLeftFrames;
	Sprite[] walkingRightFrames;
	Sprite[] walkingLeftFrames;
	Sprite standRight;
	Sprite standLeft;
	Sprite jumpRight;
	Sprite jumpLeft;
	
	int jumps = 0;
	float jump2 = 0;
	
	float elapsed;
	float elapsed2;

	// accessing sprite assets from the directory files
	String standuri = "link-standing-sprites/";
	String runuri = "link-running-sprites/";
	String jumpuri = "link-jumping-sprites/";
	String heartsuri = "hearts/";
		
	public Linko(Boolean playerControlled) {
		this.playerControlled = playerControlled;
		linkoCollider = new Rectangle();
		linkoCollider.x = 10;
		linkoCollider.y = 10;
		linkoCollider.height = 25;
		linkoCollider.width = 15;
		
		health = 4;
		hearts = new Sprite[6];
		
		
		// heart textures
		for(int i = 0; i < 5; i++) {
			hearts[i] = new Sprite(new Texture(heartsuri + "heart-" + i + ".png"));
			hearts[i].scale(10);
		}
		
		// orientation and jumping status
		flippedRight = true;
		jumping = false;
		stepping = false;
		
		// animation objects
		runningRightFrames = new Sprite[6];
		runningLeftFrames = new Sprite[6];
		walkingRightFrames = new Sprite[4];
		walkingLeftFrames = new Sprite[4];
		
		// standing and jumping sprite creation
		standRight = new Sprite(new Texture(standuri + "link-standing-0.png"));
		standLeft = new Sprite(standRight);
		jumpRight = new Sprite(new Texture(jumpuri + "link-jumping-0.png"));
		jumpLeft = new Sprite(jumpRight);
		standLeft.flip(true, false);
		jumpLeft.flip(true, false);
		
		// running animation construction
		int j = 0;
		for(int i = 0; i < 6; i++) {
			runningRightFrames[i] = new Sprite(new Texture(runuri + "link-running-" + i + ".png"));
			runningLeftFrames[i] = new Sprite(new Texture(runuri + "link-running-" + i + ".png"));
			if( i != 2 && i != 5) {
				System.out.println("adding walk frame: " + j);
				walkingRightFrames[j] = new Sprite(new Texture(runuri + "link-running-" + i + ".png"));
				walkingLeftFrames[j] = new Sprite(new Texture(runuri + "link-running-" + i + ".png"));
				walkingLeftFrames[j].flip(true, false);
				j++;
			}
			runningLeftFrames[i].flip(true, false);
		}

		// setting the animations
		runningRightAnimation = new Animation<Sprite>(1f/4f, runningRightFrames);
		runningLeftAnimation = new Animation<Sprite>(1f/4f, runningLeftFrames);
		walkingRightAnimation = new Animation<Sprite>(1f/4f, walkingRightFrames);
		walkingLeftAnimation = new Animation<Sprite>(1f/4f, walkingLeftFrames);
		
		animation = walkingRightAnimation;
	}
	
	public void move(SpriteBatch batch) {
		elapsed += Gdx.graphics.getDeltaTime();
		stepping = true;

		if(playerControlled) {
			if(!Gdx.input.isKeyPressed(Input.Keys.LEFT) && !Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
				stepping = false;
				if(this.getFlippedRight() && !this.getJumping()) {
					this.setStandRight(this.getStandRight());
					batch.draw(this.getStandRight(), this.getLinkoCollider().x, this.getLinkoCollider().y);
				} else if (!this.getJumping()) {
					batch.draw(this.getStandLeft(), this.getLinkoCollider().x, this.getLinkoCollider().y);
				}
			} 
			if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
				if(!this.getFlippedRight()) {
					this.setFlippedRight(true);;
				}
				if(!this.getJumping()){
					animation = this.getWalkingRightAnimation();
				}
				if(!Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
					this.getLinkoCollider().x += 100 * Gdx.graphics.getDeltaTime();
				} else {
					animation = this.getRunningRightAnimation();
					this.getLinkoCollider().x += 150 * Gdx.graphics.getDeltaTime();
				}
			} else if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
				if(this.getFlippedRight()) {
					this.setFlippedRight(false);
				}
				if(!this.getJumping()){
					animation = this.getRunningLeftAnimation();
				}
				if(!Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
					this.getLinkoCollider().x -= 100 * Gdx.graphics.getDeltaTime();
				} else {
					this.getLinkoCollider().x -= 150 * Gdx.graphics.getDeltaTime();
				}
			} 
			
			if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE) && jumps < 2) {
				this.setJumping(true);
				if(jumps == 0) {
					this.getLinkoCollider().y += (100 * Gdx.graphics.getDeltaTime()) + 75;
					if(Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
						this.getLinkoCollider().y += 50;
					}
					jump2 = this.getLinkoCollider().y * 1.5f;
					System.out.println("jump 1: " + this.getLinkoCollider().y);
				} else {
					System.out.println("jump 2 start: " + this.getLinkoCollider().y);
					this.getLinkoCollider().y = jump2;
					System.out.println("jump 2: " + this.getLinkoCollider().y);
				}
				jumps++;
			} 
			
			if(this.getJumping()) {
				stepping = false;
				jumpRender(batch);
			}
			
			if(this.getLinkoCollider().y > 11) {
				this.getLinkoCollider().y -= 120 * Gdx.graphics.getDeltaTime();
			} else if (this.getLinkoCollider().y < 11) {
				jumps = 0;
				this.setJumping(false);
			}
		} else {
			if(!this.getJumping()){
				animation = this.getRunningLeftAnimation();
			}
				this.getLinkoCollider().x -= 100 * Gdx.graphics.getDeltaTime();
		}
		if(stepping) {
			batch.draw(animation.getKeyFrame(elapsed,true), this.getLinkoCollider().x,this.getLinkoCollider().y);
		}
	}
	
	private void jumpRender(SpriteBatch batch) {
		if(this.getFlippedRight()) {
			batch.draw(this.getJumpRight(), this.getLinkoCollider().x, this.getLinkoCollider().y);
		} else {
			batch.draw(this.getJumpLeft(), this.getLinkoCollider().x, this.getLinkoCollider().y);
		}
	}
	
	public void healthRender(SpriteBatch batch) {
		switch (this.health) {
			case 4: batch.draw(hearts[4], 10, 250);
				break;
			case 3: batch.draw(hearts[3], 10,250);
				break;
			case 2: batch.draw(hearts[2], 10,250);
				break;
			case 1: batch.draw(hearts[1], 10,250);
				break;
			case 0: batch.draw(hearts[0], 10,250);
				break;
			default: batch.draw(hearts[0], 10,250);
				break;
		}
	}
	
	public void setLinkoCollider(Rectangle linkoCollider) {
		this.linkoCollider = linkoCollider;
	}
	
	public void setLinkoCollider(float x, float y) {
		this.linkoCollider.x = x;
		this.linkoCollider.y = y;
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
	
	public void setWalkingRightAnimation(Animation<Sprite> walkingRightAnimation) {
		this.walkingRightAnimation = walkingRightAnimation;
	}
	
	public Animation<Sprite> getWalkingRightAnimation () {
		return this.walkingRightAnimation;
	}
	
	public void setWalkingLeftAnimation(Animation<Sprite> walkingLeftAnimation) {
		this.walkingLeftAnimation = walkingLeftAnimation;
	}
	
	public Animation<Sprite> getWalkingLeftAnimation () {
		return this.walkingLeftAnimation;
	}
	
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
	
	public void setHealth(int health) {
		this.health = health;
	}
	
	public int getHealth() {
		return this.health;
	}
}
