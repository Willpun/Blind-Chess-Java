package com.billy.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.billy.gamehelpers.AssetLoader;
import com.billy.gameobjects.ChessPiece;
import com.billy.gameobjects.ChessPiece.User;
import com.billy.gameobjects.GameBoard;
import com.billy.gameui.Button;
import com.billy.gameui.VolumeControl;
import com.billy.gameworld.GameWorld.GameState;

public class GameRenderer {

	private GameWorld myWorld;
	private OrthographicCamera cam;
	private ShapeRenderer shapeRenderer;
	private SpriteBatch batcher;
	
	private GameBoard board;
	private ChessPiece[][] chessPiece;
	private Button resumeButton, quitButton, settingButton, soundsOn, soundsOff;
	
	private Button pvpButton, soloButton, aboutButton;
	
	long startTime = 0;
	boolean drawMainMenuAlready = false;
	boolean drawBan = false, drawQi = false, drawVer = false, drawL1 = false;
	
	public GameRenderer(GameWorld world) {
		myWorld = world;
		board = myWorld.getBoard();
		chessPiece = myWorld.getChessPiece();
		resumeButton = myWorld.getResumeButton();
		quitButton = myWorld.getQuitButton();
		settingButton = myWorld.getSettingButton();
		soundsOn = myWorld.getSoundsOn();
		soundsOff = myWorld.getSoundsOff();
		
		pvpButton = myWorld.getPvpButton();
		soloButton = myWorld.getSoloButton();
		aboutButton = myWorld.getAboutButton();
		
		cam = new OrthographicCamera();
		cam.setToOrtho(true, myWorld.getGameWidth(), myWorld.getGameHeight());
		
		batcher = new SpriteBatch();
		batcher.setProjectionMatrix(cam.combined);
		
		shapeRenderer = new ShapeRenderer();
		shapeRenderer.setProjectionMatrix(cam.combined);
		
	}

	public void render(float runTime) {
		
        // Fill the entire screen with black, to prevent potential flickering.
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        shapeRenderer.begin(ShapeType.Filled);
        // Draw Background color
        shapeRenderer.setColor(0 / 255.0f, 0 / 255.0f, 0 / 255.0f, 1);
        shapeRenderer.rect(0, 0, myWorld.getGameWidth(), myWorld.getGameHeight());
        shapeRenderer.end();
        
        
        batcher.begin();

        if (myWorld.gameState == GameState.RUNNING || myWorld.gameState == GameState.SUBMENU) {
        	batcher.draw(AssetLoader.board, board.getX(), board.getY(), board.getBoardWidth(), board.getBoardHeight());
           	batcher.enableBlending();
        	drawTheRunningGame(runTime);
	
        	if (myWorld.getJudge().isGameOver()) {
        		myWorld.getJudge().drawGameOver(batcher);
        	}
      
        	if (myWorld.gameState == GameState.SUBMENU) {
            	drawSubMenu();
            }
        	
          	batcher.disableBlending();
          	
        } else if (myWorld.gameState == GameState.MAINMENU) {
        	drawMainMenu(runTime);
        } else if (myWorld.gameState == GameState.ABOUT) {
        	batcher.draw(AssetLoader.intro, 0, 0, myWorld.getGameWidth(), myWorld.getGameHeight());
        }

        batcher.end();
        
        if (myWorld.gameState != GameState.MAINMENU) {
        	drawMainMenuAlready = drawBan = drawQi = drawVer = drawL1 = false;
        	startTime = 0;
        }
	}
	
	public void drawMainMenu(float runTime) {
		float w = myWorld.getGameWidth();
		float h = myWorld.getGameHeight();

    	batcher.draw(AssetLoader.l2, 0, 0, w, h);  	
    	
    	batcher.enableBlending();

		if (!drawMainMenuAlready) {

			if (startTime <= 0 && !drawBan) {
				startTime = System.currentTimeMillis();
			} else if (!drawBan && (System.currentTimeMillis() - startTime) >= 500) {
				drawBan = true;
				startTime = 0;
				AssetLoader.capture.play(VolumeControl.volume);
			} else if (startTime <= 0 && !drawQi && drawBan) {
				startTime = System.currentTimeMillis();
			} else if (drawBan && !drawQi && (System.currentTimeMillis() - startTime) >= 500) {
				drawQi = true;
				startTime = 0;
				AssetLoader.capture.play(VolumeControl.volume);
			} else if (startTime <= 0 && !drawVer && drawQi && drawBan) {
				startTime = System.currentTimeMillis();
			} else if (drawBan && drawQi && !drawVer && (System.currentTimeMillis() - startTime) >= 500) {
				drawVer = true;
				startTime = 0;
				AssetLoader.capture.play(VolumeControl.volume);
			} else if (startTime <= 0 && !drawL1 && drawVer && drawQi && drawBan) {
				startTime = System.currentTimeMillis();
			} else if (drawBan && drawQi && drawVer && !drawL1 && (System.currentTimeMillis() - startTime) >= 500) {
				drawL1 = true;
				startTime = 0;
				drawMainMenuAlready = true;
				AssetLoader.capture.play(VolumeControl.volume);
			}
		}
    	
		if (drawBan)
			batcher.draw(AssetLoader.ban, 0, 0, w, h);
		if (drawQi)
			batcher.draw(AssetLoader.qi, 0, 0, w, h);
		if (drawVer)
			batcher.draw(AssetLoader.ver, 0, 0, w, h);
		if (drawL1)
			batcher.draw(AssetLoader.l1, 0, 0, w, h);
    	
    	
		soloButton.draw(batcher);
		pvpButton.draw(batcher);
		aboutButton.draw(batcher);
		batcher.disableBlending();
		
	}
	
	public void drawSubMenu() {	

		batcher.draw(AssetLoader.blur, 0, 0, myWorld.getGameWidth(), myWorld.getGameHeight());
		
		resumeButton.draw(batcher);
		quitButton.draw(batcher);
		if (VolumeControl.volume() == 1.0f)
			soundsOn.draw(batcher);
		else if (VolumeControl.volume() == 0.0f)
			soundsOff.draw(batcher);
	}
	
	public void drawTheRunningGame(float runTime) {
		
        for (int i = 0; i < 4; i++)
        	for (int j = 0; j < 8; j++) {      		
        		if (!chessPiece[i][j].isPressed()) {
        			switch (chessPiece[i][j].getSurface()) {
        				case BACK:
                			batcher.draw(AssetLoader.pbAnimation.getKeyFrame(runTime), chessPiece[i][j].getX(), chessPiece[i][j].getY(),
                            		chessPiece[i][j].getWidth(), chessPiece[i][j].getHeight());
                			break;
        				case FRONT:
        					if (chessPiece[i][j].getUser() == User.P2) {
        					batcher.draw(AssetLoader.blue[chessPiece[i][j].getNumber() - 1], chessPiece[i][j].getX(), chessPiece[i][j].getY(),
                            		chessPiece[i][j].getWidth(), chessPiece[i][j].getHeight());
        					} else if (chessPiece[i][j].getUser() == User.P1) {
               					batcher.draw(AssetLoader.red[chessPiece[i][j].getNumber() - 1], chessPiece[i][j].getX(), chessPiece[i][j].getY(),
                                		chessPiece[i][j].getWidth(), chessPiece[i][j].getHeight());
        					}
        					break;
                		default:
                			break;
        			
        			}
        				
        		}
        	}
      
        for (int i = 0; i < 4; i++)
        	for (int j = 0; j < 8; j++) {      		
        		if (chessPiece[i][j].isPressed()) {
        			float w = chessPiece[i][j].getWidth();
        			float h = chessPiece[i][j].getHeight();
        			switch (chessPiece[i][j].getSurface()) {
        				case BACK:
        					batcher.draw(AssetLoader.pbAnimation.getKeyFrame(runTime), chessPiece[i][j].getX() - (w / 8f), chessPiece[i][j].getY() - (h / 8f),
                        		w * 1.25f, h * 1.25f);
        					break;
        				case FRONT:
        					if (chessPiece[i][j].getUser() == User.P2) {
        					batcher.draw(AssetLoader.blue[chessPiece[i][j].getNumber() - 1], chessPiece[i][j].getX() - (w / 8f), chessPiece[i][j].getY() - (h / 8f),
        							w * 1.25f, h * 1.25f);
        					} else if (chessPiece[i][j].getUser() == User.P1) {
               					batcher.draw(AssetLoader.red[chessPiece[i][j].getNumber() - 1], chessPiece[i][j].getX() - (w / 8f), chessPiece[i][j].getY() - (h / 8f),
               							w * 1.25f, h * 1.25f);
        					}
        					break;
            			default:
            				break;
        			}

        		}
        	}
        
		settingButton.draw(batcher);
        myWorld.getJudge().drawGameTurnCounter(batcher);
	}
	
}
