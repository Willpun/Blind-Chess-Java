package com.billy.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.billy.gamehelpers.InputHandler;
import com.billy.gameworld.GameRenderer;
import com.billy.gameworld.GameWorld;

public class GameScreen implements Screen{

	private GameWorld world;
	private GameRenderer renderer;
	private float runTime;
	
	public GameScreen() {
		float screenWidth = Gdx.graphics.getWidth();
		float screenHeight = Gdx.graphics.getHeight();
		
		Gdx.app.log("Game Res:", screenWidth + " x " + screenHeight);
		
		runTime = 0;
		
		world = new GameWorld(screenWidth, screenHeight);
		
        Gdx.input.setInputProcessor(new InputHandler(world));	
		renderer = new GameRenderer(world);
	
	}
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		runTime += delta;
		world.update(delta);
		renderer.render(runTime);
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

	public float getRunTime() {
		return runTime;
	}

	public void setRunTime(float runTime) {
		this.runTime = runTime;
	}

}
