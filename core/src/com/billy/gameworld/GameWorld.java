package com.billy.gameworld;

import com.billy.gameobjects.ChessPiece;
import com.billy.gameobjects.GameBoard;
import com.billy.gameobjects.GameJudge;
import com.billy.gameobjects.GameJudge.GameMode;
import com.billy.gameui.Button;
import com.billy.gameui.SettingButton;
import com.billy.gamehelpers.*;

public class GameWorld {

	private float runTime;

	private float gameWidth, gameHeight;
	private GameBoard board;
	private ChessPiece[][] chessPiece;
	private GameJudge judge;
	
	public enum GameState {
		RUNNING, MAINMENU, SUBMENU, ABOUT
	}
    public GameState gameState;
	   
    private Button resumeButton, quitButton;
    private SettingButton settingButton;
    private Button soundsOn, soundsOff;
    
    private Button pvpButton, soloButton, aboutButton;
    
	public GameWorld(float screenWidth, float screenHeight) {
		
		setGameWidth(screenWidth);
		setGameHeight(screenHeight);

		
		board = new GameBoard(this.gameWidth, this.gameHeight);
		
		judge = new GameJudge(board, this.gameWidth / 2.0f, this.gameHeight / 2.0f);

		chessPiece = judge.getChessPiece();
		
		pvpButton = new Button(this.gameWidth, this.gameHeight, AssetLoader.pvpButton.getRegionWidth(), AssetLoader.pvpButton.getRegionHeight(),
				 AssetLoader.pvpButton, AssetLoader.mouseOver);
		pvpButton.setWidth(this.gameWidth / 2.5f);
		pvpButton.setHeight(this.gameHeight / 16f * 1.35f);
		pvpButton.init((this.gameWidth - pvpButton.getWidth()) / 2f, (this.gameHeight * 12f / 16f));
		
		soloButton = new Button(this.gameWidth, this.gameHeight, AssetLoader.soloButton.getRegionWidth(), AssetLoader.soloButton.getRegionHeight(),
				 AssetLoader.soloButton, AssetLoader.mouseOver);
		soloButton.setWidth(this.gameWidth / 2.5f);
		soloButton.setHeight(this.gameHeight / 16f * 1.35f);
		soloButton.init((this.gameWidth - soloButton.getWidth()) / 2f, (this.gameHeight * 10.5f / 16f));	
		
		aboutButton = new Button(this.gameWidth, this.gameHeight, AssetLoader.aboutButton.getRegionWidth(), AssetLoader.aboutButton.getRegionHeight(),
				 AssetLoader.aboutButton, AssetLoader.mouseOver);
		aboutButton.setWidth(this.gameWidth / 2.5f);
		aboutButton.setHeight(this.gameHeight / 16f * 1.35f);
		aboutButton.init((this.gameWidth - aboutButton.getWidth()) / 2f, (this.gameHeight * 13.5f / 16f));	
		
		resumeButton = new Button(this.gameWidth, this.gameHeight, AssetLoader.resume.getRegionWidth(), AssetLoader.resume.getRegionHeight(),
				 AssetLoader.resume, AssetLoader.mouseOver);
		resumeButton.init((this.gameWidth - resumeButton.getWidth()) / 2f, (this.gameHeight * 5f / 16f));
		
		quitButton = new Button(this.gameWidth, this.gameHeight, AssetLoader.quit.getRegionWidth(), AssetLoader.quit.getRegionHeight(),
				 AssetLoader.quit, AssetLoader.mouseOver);
		
		quitButton.init((this.gameWidth - quitButton.getWidth()) / 2f, this.gameHeight * 7f / 16f);
		
		soundsOn = new Button(this.gameWidth, this.gameHeight, AssetLoader.soundsOn.getRegionWidth(), AssetLoader.soundsOn.getRegionHeight(),
				 AssetLoader.soundsOn, AssetLoader.mouseOver);
		
		soundsOn.init((this.gameWidth - soundsOn.getWidth()) / 2f, this.gameHeight * 9f / 16f);
		
		soundsOff = new Button(this.gameWidth, this.gameHeight, AssetLoader.soundsOff.getRegionWidth(), AssetLoader.soundsOff.getRegionHeight(),
				 AssetLoader.soundsOff, AssetLoader.mouseOver);
		
		soundsOff.init((this.gameWidth - soundsOff.getWidth()) / 2f, this.gameHeight * 9f / 16f);
		
		
		settingButton = new SettingButton(this.gameWidth, this.gameHeight, AssetLoader.gear.getRegionWidth(), AssetLoader.gear.getRegionHeight(),
				 AssetLoader.gear, AssetLoader.blendedGear);
		
		settingButton.init(this.gameWidth - settingButton.getWidth(), this.gameHeight - settingButton.getHeight());
		
		
		///////////////
		judge.setupPieces();
		judge.shuffle();

		gameState = GameState.MAINMENU;
		
	}
	
	public void reset() {
		judge.reset();
		judge.setupPieces();
		judge.shuffle();
	}
	
	public void setNextGameSOLO() {
		judge.gameMode = GameMode.SOLO;
	}
	
	public void setNextGamePVP() {
		judge.gameMode = GameMode.PVP;
	}
	
	public GameJudge getJudge() {
		return judge;
	}

	public ChessPiece[][] getChessPiece() {
		return chessPiece;
	}
	
	public void update(float delta) {
		
		if(gameState == GameState.RUNNING) {
//			board.update(delta);
			judge.update(runTime);
//			Gdx.app.log("FPS", "" + 1/delta);
		}
		
	}

	public GameBoard getBoard(){
		return board;
	}

	public float getGameWidth() {
		return gameWidth;
	}

	public void setGameWidth(float gameWidth) {
		this.gameWidth = gameWidth;
	}

	public float getGameHeight() {
		return gameHeight;
	}

	public void setGameHeight(float gameHeight) {
		this.gameHeight = gameHeight;
	}

	public Button getResumeButton() {
		return resumeButton;
		
	}	
	
	public Button getQuitButton() {
		return quitButton;
	}

	public Button getSettingButton() {
		return settingButton;
	}

	public Button getSoundsOn() {
		return soundsOn;
	}
	
	public Button getSoundsOff() {
		return soundsOff;
	}

	public Button getPvpButton() {
		return pvpButton;
	}
	
	public Button getSoloButton() {
		return soloButton;
	}
	
	public Button getAboutButton() {
		return aboutButton;
	}
}
