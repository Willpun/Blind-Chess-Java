package com.billy.games;

import com.badlogic.gdx.Game;
import com.billy.gamehelpers.AssetLoader;
import com.billy.screens.SplashScreen;

public class BlindChess extends Game {

	@Override
	public void create() {
		AssetLoader.load();
		setScreen(new SplashScreen(this));	
	}
	
	@Override
	public void dispose() {
		super.dispose();
		AssetLoader.dispose();
	}
	
}
