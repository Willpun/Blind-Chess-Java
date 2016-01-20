package com.billy.gameui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.billy.gamehelpers.AssetLoader;

public class Button {

	protected float width, height, x, y;
	
	protected TextureRegion buttonUp, mouseOver;

	protected boolean isMouseOver = false;
    protected boolean isPressed = false;
	
    protected Rectangle bounds;

	public Button(float screenWidth, float screenHeight, float oldWidth, float oldHeight, TextureRegion buttonUp, TextureRegion mouseOver) {

		this.buttonUp = buttonUp;
		this.mouseOver = mouseOver;
		
		float scaleWidth = screenWidth / oldWidth;
		float scaleHeight = screenHeight / oldHeight;

		if (scaleWidth < scaleHeight) {
			scaleHeight = scaleWidth;
		} else {
			scaleWidth = scaleHeight;
		}

		this.width = oldWidth * scaleWidth * 7.0f/12.0f;
		this.height = oldHeight * scaleHeight * 0.4f;
		this.x = this. y = 1;
		
        bounds = new Rectangle(this.x, this.y, this.width, this.height);
	}
	
	public void init(float x, float y) {
		bounds.setX(x);
		bounds.setY(y);
		this.x = x;
		this.y = y;
	}

	public void setIsPressed (boolean isPressed) {
		this.isPressed = isPressed;
	}
	
	public boolean getIsPressed () {
		return this.isPressed;
	}
	
	public boolean isPressed(int screenX, int screenY) {
		if (bounds.contains(screenX, screenY)) {
			this.isPressed = true;
			return true;
		}
		return false;
	}
	
	public void setMouseOver (boolean isMouseOver) {
		this.isMouseOver = isMouseOver;
	}
	
	public boolean getMouseOver () {
		return isMouseOver;
	}
	
    public boolean isMouseOver(int screenX, int screenY) {
    	
    	if (bounds.contains(screenX, screenY) && isMouseOver == false) {
    		isMouseOver = true;
    		AssetLoader.select.play(VolumeControl.volume);
    	} else if (isMouseOver == true) {
    		isMouseOver = bounds.contains(screenX, screenY);
    	}
        return isMouseOver;
    }
	
    public void draw(SpriteBatch batcher) {
        batcher.draw(buttonUp, x, y, width, height);
        if (isMouseOver) {        	
            batcher.draw(mouseOver, x + 2, y + 2, width - 4, height / 2);
        }
    }

	public float getX() {
		return this.x;
	}

	public float getY() {
		return this.y;
	}

	public void setX(float x) {
		this.x = x;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}



}
