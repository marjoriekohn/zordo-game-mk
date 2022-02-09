package com.zordo.environment;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;

public class Platform extends Rectangle {

	private static final long serialVersionUID = 1L;

	private int width;
	private int height;
	
	public Platform(int width, int height) {
		super();
		super.setWidth(width);
		super.setHeight(height);
		this.width = width;
		this.height = height;
	}
	
	public void setCoordinates(int x, int y) {
		super.setX(x);
		super.setY(y);
	}
	
	public float getX() {
		return super.getX();
	}
	
	public float getY() {
		return super.getY();
	}
	
	public void render(ShapeRenderer shapeRenderer) {

		 
//		 shapeRenderer.begin(ShapeType.Filled);
//		 shapeRenderer.setColor(0, 0, 0.2f, 1);
//		 shapeRenderer.rect(this.getX(), this.getY(), this.width, this.height);
//		 shapeRenderer.end();
	}
}
