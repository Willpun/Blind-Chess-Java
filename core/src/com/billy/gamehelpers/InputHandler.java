package com.billy.gamehelpers;

import com.badlogic.gdx.InputProcessor;
import com.billy.gameobjects.ChessPiece;
import com.billy.gameobjects.GameJudge;
import com.billy.gameui.VolumeControl;
import com.billy.gameworld.GameWorld;
import com.billy.gameworld.GameWorld.GameState;

public class InputHandler implements InputProcessor{

	ChessPiece[][] chessPiece;
	GameWorld myWorld;
	GameJudge judge;
	
	private int i, j;
	
	public InputHandler (GameWorld world) {
		
		myWorld = world;
		judge = world.getJudge();
		chessPiece = myWorld.getChessPiece();
		i = j = -1;
	}
	
	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
	
		if (myWorld.gameState == GameState.RUNNING && myWorld.getSettingButton().isMouseOver(screenX, screenY) == true) {
			return myWorld.getSettingButton().isPressed(screenX, screenY);
		}
		if (myWorld.gameState == GameState.RUNNING && myWorld.getSettingButton().isMouseOver(screenX, screenY) == false) {
			for (int i = 0; i < 4; i++)
				for (int j = 0; j < 8; j++) {
					if (chessPiece[i][j].isTouchDown(screenX, screenY)) {
						this.i = i;
						this.j = j;
						myWorld.getSettingButton().setIsPressed(false);
						return true;
					}
				}
		} else if (myWorld.gameState == GameState.SUBMENU) {
				if (!myWorld.getResumeButton().isMouseOver(screenX, screenY))
				 if(!myWorld.getQuitButton().isMouseOver(screenX, screenY))
					if(!(VolumeControl.volume() == 1.0f ? myWorld.getSoundsOn().isMouseOver(screenX, screenY) :
						myWorld.getSoundsOff().isMouseOver(screenX, screenY))) {						
						// do nothing
					}
				return myWorld.getResumeButton().isPressed(screenX, screenY) || 
						myWorld.getQuitButton().isPressed(screenX, screenY) ||
						(VolumeControl.volume() == 1.0f ? myWorld.getSoundsOn().isPressed(screenX, screenY) :
						myWorld.getSoundsOff().isPressed(screenX, screenY));
				
		} else if (myWorld.gameState == GameState.MAINMENU) {
			if (!myWorld.getSoloButton().isMouseOver(screenX, screenY))
				if (!myWorld.getPvpButton().isMouseOver(screenX, screenY))
					if (!myWorld.getAboutButton().isMouseOver(screenX, screenY)) {
						// do nothing
					}
			return myWorld.getSoloButton().isPressed(screenX, screenY) ||
					myWorld.getPvpButton().isPressed(screenX, screenY) ||
					myWorld.getAboutButton().isPressed(screenX, screenY);
		}
		
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		if (myWorld.gameState == GameState.ABOUT) {
			myWorld.gameState = GameState.MAINMENU;
			return true;
		}
		
		if (myWorld.gameState == GameState.RUNNING && judge.isGameOver()) {
			myWorld.gameState = GameState.MAINMENU;
			myWorld.reset();
			return true;
		}
		
		if (myWorld.gameState == GameState.RUNNING && myWorld.getSettingButton().isMouseOver(screenX, screenY) == false) {
			if (i >= 0 && j >= 0)
				if(judge.isValidMove(screenX, screenY, i, j)) {
					return true;
				}
		}
		if (myWorld.gameState == GameState.RUNNING && myWorld.getSettingButton().isMouseOver(screenX, screenY) == true ) {
			if (i >= 0 && j >= 0) {
				chessPiece[i][j].toOldPosition();
			}
			if (myWorld.getSettingButton().getIsPressed()) {
				myWorld.gameState = GameState.SUBMENU;
				myWorld.getSettingButton().setMouseOver(false);
				myWorld.getSettingButton().setIsPressed(false);
				AssetLoader.changes.play(VolumeControl.volume);
				return true;
			}
		}
		if (myWorld.gameState == GameState.SUBMENU && myWorld.getResumeButton().isMouseOver(screenX, screenY) == true) {
			if (myWorld.getResumeButton().getIsPressed()) {
				myWorld.gameState = GameState.RUNNING;
				myWorld.getResumeButton().setMouseOver(false);
				myWorld.getResumeButton().setIsPressed(false);
				AssetLoader.changes.play(VolumeControl.volume);
				return true;
			}
		}
		if (myWorld.gameState == GameState.SUBMENU && myWorld.getQuitButton().isMouseOver(screenX, screenY) == true) {
			if (myWorld.getQuitButton().getIsPressed()) {
				myWorld.gameState = GameState.MAINMENU;
				myWorld.getQuitButton().setMouseOver(false);
				myWorld.getQuitButton().setIsPressed(false);
				AssetLoader.changes.play(VolumeControl.volume);
				
				myWorld.reset();
				
				return true;
			}
		}
		if (myWorld.gameState == GameState.SUBMENU && VolumeControl.volume() == 0.0f && myWorld.getSoundsOff().isMouseOver(screenX, screenY) == true) {		
			if (myWorld.getSoundsOff().getIsPressed()) {
				myWorld.getSoundsOff().setMouseOver(false);
				myWorld.getSoundsOff().setIsPressed(false);
				VolumeControl.enableSounds();
				AssetLoader.changes.play(VolumeControl.volume);
				myWorld.getSoundsOn().setMouseOver(true);
				return true;
			}
		}
		if (myWorld.gameState == GameState.SUBMENU && VolumeControl.volume() == 1.0f && myWorld.getSoundsOn().isMouseOver(screenX, screenY) == true) {
			if (myWorld.getSoundsOn().getIsPressed()) {
				myWorld.getSoundsOn().setMouseOver(false);
				myWorld.getSoundsOn().setIsPressed(false);
				VolumeControl.disableSounds();
				myWorld.getSoundsOff().setMouseOver(true);
				return true;
			}
		}

		if (myWorld.gameState == GameState.MAINMENU && myWorld.getPvpButton().isMouseOver(screenX, screenY) == true) {
			if (myWorld.getPvpButton().getIsPressed()) {
				myWorld.gameState = GameState.RUNNING;
				myWorld.getPvpButton().setMouseOver(false);
				myWorld.getPvpButton().setIsPressed(false);
				AssetLoader.capture.play(VolumeControl.volume);
				
				myWorld.setNextGamePVP();
				
				return true;
			}
		}	
		if (myWorld.gameState == GameState.MAINMENU && myWorld.getSoloButton().isMouseOver(screenX, screenY) == true) {
			if (myWorld.getSoloButton().getIsPressed()) {
				myWorld.gameState = GameState.RUNNING;
				myWorld.getSoloButton().setMouseOver(false);
				myWorld.getSoloButton().setIsPressed(false);
				AssetLoader.capture.play(VolumeControl.volume);
				
				myWorld.setNextGameSOLO();
				
				return true;
			}
		}
		if (myWorld.gameState == GameState.MAINMENU && myWorld.getAboutButton().isMouseOver(screenX, screenY) == true) {
			if (myWorld.getAboutButton().getIsPressed()) {
				myWorld.gameState = GameState.ABOUT;
				myWorld.getAboutButton().setMouseOver(false);
				myWorld.getAboutButton().setIsPressed(false);
				AssetLoader.changes.play(VolumeControl.volume);			
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		
		if (myWorld.gameState == GameState.RUNNING)
			if (i >= 0 && j >= 0)
				if (chessPiece[i][j].isPressed()) {
					chessPiece[i][j].setX( screenX - (chessPiece[i][j].getWidth() / 2));
					chessPiece[i][j].setY( screenY - (chessPiece[i][j].getHeight() / 2));
					return true;			
				}
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		
		if (myWorld.gameState == GameState.SUBMENU) {
			return myWorld.getResumeButton().isMouseOver(screenX, screenY) || 
					myWorld.getQuitButton().isMouseOver(screenX, screenY) ||
					(VolumeControl.volume() == 1.0f ? myWorld.getSoundsOn().isMouseOver(screenX, screenY) :
					myWorld.getSoundsOff().isMouseOver(screenX, screenY));
		} else if (myWorld.gameState == GameState.RUNNING) {
			return myWorld.getSettingButton().isMouseOver(screenX, screenY);
		} else if (myWorld.gameState == GameState.MAINMENU) {
			return myWorld.getAboutButton().isMouseOver(screenX, screenY) ||
					myWorld.getSoloButton().isMouseOver(screenX, screenY) ||
					myWorld.getPvpButton().isMouseOver(screenX, screenY);
		}
		
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
