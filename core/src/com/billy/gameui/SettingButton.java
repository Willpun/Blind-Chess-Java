package com.billy.gameui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class SettingButton extends Button{

	public SettingButton(float screenWidth, float screenHeight, float oldWidth,
			float oldHeight, TextureRegion buttonUp, TextureRegion mouseOver) {
		super(screenWidth, screenHeight, oldWidth, oldHeight, buttonUp, mouseOver);
		setWidth(screenWidth / 12);
		setHeight(getWidth());
	}
	
	@Override
    public void draw(SpriteBatch batcher) {
        if (isMouseOver) {        	
            batcher.draw(mouseOver, x, y, width, height);
        } else
            batcher.draw(buttonUp, x, y, width, height);
    }

}
