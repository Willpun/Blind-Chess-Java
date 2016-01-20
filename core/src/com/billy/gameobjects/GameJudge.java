package com.billy.gameobjects;

import java.util.Random;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.billy.gamehelpers.AssetLoader;
import com.billy.gameobjects.ChessPiece;
import com.billy.gameobjects.ChessPiece.Surface;
import com.billy.gameobjects.ChessPiece.User;
import com.billy.gameui.VolumeControl;

public class GameJudge {
	
	GlyphLayout layout = new GlyphLayout(); // Obviously stick this in a field to avoid allocation each frame.	
	
	private float midPointX, midPointY;   // mid-point of the screen
	
	private ChessPiece[][] chessPiece;
	private GameBoard board;
	
	private int target_i, target_j;
	
	private int countP1, countP2, gameTurn, lastCaptureTurn;
		
	private DrDummy dummy;
	
	long startTime;
	
	public enum GameMode {
		SOLO, PVP
	}
	public GameMode gameMode = GameMode.SOLO;
	
	boolean isPlayed = false;
	
	public GameJudge (GameBoard board, float midPointX, float midPointY) {
		
		countP1 = countP2 = 16;
		
		this.midPointX = midPointX;
		this.midPointY = midPointY;
		this.board = board;
		gameTurn = 1;
		
		startTime = lastCaptureTurn = 0;
		
		chessPiece = new ChessPiece[4][8];
	
		for(int i = 0; i < 4; i++)
			for(int j = 0; j < 8; j++) {
				chessPiece[i][j] = new ChessPiece();
			}

		dummy = new DrDummy();	
		
	}
	
	public void reset() {
		gameTurn = 1;
		startTime = lastCaptureTurn = 0;
		countP1 = countP2 = 16;
		isPlayed = false;
		dummy.reset();
	}
	
	public boolean isGameOver() {
		if (countP1 <= 0 || countP2 <= 0 || isStalemate()) {
			return true;
		}
		return false;
	}
	
	public boolean isStalemate() {
		if (gameTurn - lastCaptureTurn >= 50)
			return true;
		
		return false;
	}
	
	public void drawGameOver(SpriteBatch batcher) {

  //      AssetLoader.font.getData().setScale(0.75f, -0.75f);
		AssetLoader.font.setColor(Color.ORANGE);		
	
		if (isStalemate()) {
			layout.setText(AssetLoader.font, "Draw");
			AssetLoader.font.draw(batcher, layout, midPointX - layout.width/2.0f, midPointY - 32);			
		}else if (countP1 <= 0 && gameMode == GameMode.SOLO) {
			layout.setText(AssetLoader.font, "Game Over");
			AssetLoader.font.draw(batcher, layout, midPointX - layout.width/2.0f, midPointY - 32);
		} else if (countP2 <= 0 && gameMode == GameMode.SOLO) {
			layout.setText(AssetLoader.font, "You win!");
			AssetLoader.font.draw(batcher, layout, midPointX - layout.width/2.0f, midPointY - 32);	
		} else if (countP1 <= 0 && gameMode == GameMode.PVP) {
			layout.setText(AssetLoader.font, "2nd Player wins!");
			AssetLoader.font.draw(batcher, layout, midPointX - layout.width/2.0f, midPointY - 32);				
		} else if (countP2 <= 0 && gameMode == GameMode.PVP) {
			layout.setText(AssetLoader.font, "1st Player wins!");
			AssetLoader.font.draw(batcher, layout, midPointX - layout.width/2.0f, midPointY - 32);	
		}
//		AssetLoader.font.getData().setScale(0.35f, -0.35f);		

		if (!isPlayed && isGameOver()) {
			AssetLoader.capture.stop();
			AssetLoader.move.stop();
			AssetLoader.game_over.play(VolumeControl.volume);
			isPlayed = true;
		}
	}
	
	public void drawGameTurnCounter(SpriteBatch batcher) {
		layout.setText(AssetLoader.font, "Turn");

		if (gameTurn % 2 == 1) {
			AssetLoader.font.setColor(255/255.0f, 50/255.0f, 0/255.0f, 1);
			AssetLoader.font.draw(batcher, layout, midPointX - layout.width * 3/4, 2);
		} else {
			AssetLoader.font.setColor(0/255.0f, 100/255.0f, 255/255.0f, 1);
			AssetLoader.font.draw(batcher, layout, midPointX - layout.width * 3/4, 2);
		}
		
		layout.setText(AssetLoader.font, " " + gameTurn);
		AssetLoader.font.draw(batcher, "  " + gameTurn, midPointX, 2);
	}

	public void update(float delta) {

	    if(getWhoseTurn() == User.P2 && gameMode == GameMode.SOLO && !isGameOver()) {
	    	if (startTime <= 0) {
	    		startTime = System.currentTimeMillis();
	    	} else if ((System.currentTimeMillis() - startTime) >= 1000  ){
	    		if (dummy.findNextMove(chessPiece)) {
		    		if(isValidMoveForAI(dummy.target_i, dummy.target_j, dummy.i, dummy.j)) {
		    			startTime = 0;
		    		}
		    	}	
	    	}
		}
	    
	}
	
	public ChessPiece.User getWhoseTurn() {
		if ((gameTurn % 2) == 1)
			return User.P1;
		else
			return User.P2;
	}
	
	public boolean locateTargetChessPiece(int screenX, int screenY) {
		target_i = -1;
		target_j = -1;
		for(int i = 0; i < 4; i++)
			for(int j = 0; j < 8; j++)
				if (chessPiece[i][j].isItThisChessPiece(screenX, screenY)) {
					target_i = i;
					target_j = j;
					return true;
				}
		return false;
	}
	
	public boolean isTargetAdjacentPiece(int i, int j) {
		boolean isLeft = (i - 1 == target_i) && (j == target_j);
		boolean isRight = (i + 1 == target_i) && (j == target_j);
		boolean isUp = (i == target_i) && (j - 1 == target_j);
		boolean isDown = (i == target_i) && (j + 1 == target_j);
		return isLeft || isRight || isUp || isDown;
	}
	
	public boolean isTargetAdjacentPieceForAI(int target_i, int target_j, int i, int j) {
		boolean isLeft = (i - 1 == target_i) && (j == target_j);
		boolean isRight = (i + 1 == target_i) && (j == target_j);
		boolean isUp = (i == target_i) && (j - 1 == target_j);
		boolean isDown = (i == target_i) && (j + 1 == target_j);
		return isLeft || isRight || isUp || isDown;
	}
	
	public boolean capture(int screenX, int screenY, int i, int j) {
		
		if (chessPiece[i][j].getUser() == User.P1)
			countP2--;
		else countP1--;		
		
		chessPiece[target_i][target_j].setNumber(chessPiece[i][j].getNumber());
		chessPiece[target_i][target_j].setUser(
				chessPiece[target_i][target_j].getUser() == User.P1 ? User.P2 : User.P1);
				
		chessPiece[i][j].setUser(User.UNDEFINED);
		chessPiece[i][j].setSurface(Surface.UNDEFINED);
		chessPiece[i][j].setNumber(0);
		chessPiece[i][j].toOldPosition();
		
		AssetLoader.capture.play(VolumeControl.volume);
		
		lastCaptureTurn = gameTurn;
		return true;
	}

	public boolean captureForAI(int target_i, int target_j, int i, int j) {
		
		if (chessPiece[i][j].getUser() == User.P1)
			countP2--;
		else countP1--;		
		
		chessPiece[target_i][target_j].setNumber(chessPiece[i][j].getNumber());
		chessPiece[target_i][target_j].setUser(
				chessPiece[target_i][target_j].getUser() == User.P1 ? User.P2 : User.P1);
				
		chessPiece[i][j].setUser(User.UNDEFINED);
		chessPiece[i][j].setSurface(Surface.UNDEFINED);
		chessPiece[i][j].setNumber(0);
//		chessPiece[i][j].toOldPosition();
		
		AssetLoader.capture.play(VolumeControl.volume);
		
		lastCaptureTurn = gameTurn;
		return true;
	}

	
	public boolean isValidMove(int screenX, int screenY, int i, int j) {

//		Gdx.app.log("Turn:", "" + gameTurn);
		
		if (getWhoseTurn() == User.P2 && gameMode == GameMode.SOLO) {
			chessPiece[i][j].toOldPosition();
			AssetLoader.invalid.play(VolumeControl.volume);
			return false;
		}
		if (chessPiece[i][j].isPressed()) {
			
			if (chessPiece[i][j].getSurface() == ChessPiece.Surface.FRONT &&
					getWhoseTurn() == chessPiece[i][j].getUser()) {
				
				if (locateTargetChessPiece(screenX, screenY)) {
					
					if (chessPiece[target_i][target_j].getSurface() == Surface.FRONT) {
						
						if (isTargetAdjacentPiece(i, j)) {
							
							if ((chessPiece[i][j].getUser() == User.P1 &&
									chessPiece[target_i][target_j].getUser() == User.P2)
								||	(chessPiece[i][j].getUser() == User.P2 &&
									chessPiece[target_i][target_j].getUser() == User.P1))
							{
									if (
										(	(chessPiece[target_i][target_j].getNumber() <= chessPiece[i][j].getNumber() &&
											!(chessPiece[target_i][target_j].getNumber() == 1 && chessPiece[i][j].getNumber() == 7))
											|| 
											(chessPiece[target_i][target_j].getNumber() == 7 &&
													chessPiece[i][j].getNumber() == 1)))
									{	
										gameTurn++;
										return capture(screenX, screenY, i, j);
									}
							}
						}
					} else if (isTargetAdjacentPiece(i, j)) {
						if (chessPiece[target_i][target_j].getUser() == User.UNDEFINED &&
								chessPiece[i][j].getUser() != User.UNDEFINED) {
//			Gdx.app.log("Judge is:", "checking valid move");
							
							chessPiece[target_i][target_j].setNumber(chessPiece[i][j].getNumber());
							chessPiece[target_i][target_j].setUser(chessPiece[i][j].getUser());
							chessPiece[target_i][target_j].setSurface(chessPiece[i][j].getSurface());
							chessPiece[i][j].setUser(User.UNDEFINED);
							chessPiece[i][j].setSurface(Surface.UNDEFINED);
							chessPiece[i][j].setNumber(0);
							chessPiece[i][j].toOldPosition();
							
							gameTurn++;
							
							AssetLoader.move.play(VolumeControl.volume);
							return true;
						}
					}
				}
			} else if (chessPiece[i][j].getSurface() == ChessPiece.Surface.BACK) {
				if (locateTargetChessPiece(screenX, screenY)) {
					if (target_i == i && target_j == j) {
						chessPiece[i][j].setSurface(Surface.FRONT);
						chessPiece[i][j].toOldPosition();
						
						gameTurn++;
						
						AssetLoader.uncover.play(VolumeControl.volume);
						return true;
					}
				}
			}
		}
		
		// invalid move
		chessPiece[i][j].toOldPosition();
		AssetLoader.invalid.play(VolumeControl.volume);
		return false;
	}
	
	public boolean isValidMoveForAI(int target_i, int target_j, int i, int j) {

//		Gdx.app.log("Turn:", "" + gameTurn);
		
		if (getWhoseTurn() == User.P2 && gameMode == GameMode.SOLO) {
			
			if (chessPiece[i][j].getSurface() == ChessPiece.Surface.FRONT &&
					chessPiece[i][j].getUser() == User.P2) {
				
				if (true) {
					
					if (chessPiece[target_i][target_j].getSurface() == Surface.FRONT) {
						
						if (isTargetAdjacentPieceForAI(target_i, target_j, i, j)) {
							
							if ((chessPiece[i][j].getUser() == User.P1 &&
									chessPiece[target_i][target_j].getUser() == User.P2)
								||	(chessPiece[i][j].getUser() == User.P2 &&
									chessPiece[target_i][target_j].getUser() == User.P1))
							{
									if (
										(	(chessPiece[target_i][target_j].getNumber() <= chessPiece[i][j].getNumber() &&
											!(chessPiece[target_i][target_j].getNumber() == 1 && chessPiece[i][j].getNumber() == 7))
											|| 
											(chessPiece[target_i][target_j].getNumber() == 7 &&
													chessPiece[i][j].getNumber() == 1)))
									{	
										gameTurn++;
										
										return captureForAI(target_i, target_j, i, j);
									}
							}
						}
					} else if (isTargetAdjacentPieceForAI(target_i, target_j, i, j)) {
						if (chessPiece[target_i][target_j].getUser() == User.UNDEFINED &&
								chessPiece[i][j].getUser() != User.UNDEFINED) {
//		Gdx.app.log("Judge is:", "checking valid move for AI");
							
							chessPiece[target_i][target_j].setNumber(chessPiece[i][j].getNumber());
							chessPiece[target_i][target_j].setUser(chessPiece[i][j].getUser());
							chessPiece[target_i][target_j].setSurface(chessPiece[i][j].getSurface());
							chessPiece[i][j].setUser(User.UNDEFINED);
							chessPiece[i][j].setSurface(Surface.UNDEFINED);
							chessPiece[i][j].setNumber(0);
//							chessPiece[i][j].toOldPosition();
							
							gameTurn++;
							
							AssetLoader.move.play(VolumeControl.volume);
							return true;
						}
					}
				}
			} else if (chessPiece[i][j].getSurface() == ChessPiece.Surface.BACK) {
				if (true) {
					if (target_i == i && target_j == j) {
						chessPiece[i][j].setSurface(Surface.FRONT);
//						chessPiece[i][j].toOldPosition();
						
						gameTurn++;
						
						AssetLoader.uncover.play(VolumeControl.volume);
						return true;
					}
				}
			}
		}
		
		return false;
	}
	
	public ChessPiece[][] getChessPiece() {
		return chessPiece;
	}
	
	public void shuffle() {
		
	    Random random = new Random();

	    for (int i = chessPiece.length - 1; i > 0; i--) {
	        for (int j = chessPiece[i].length - 1; j > 0; j--) {
	            int m = random.nextInt(i + 1);
	            int n = random.nextInt(j + 1);

	            ChessPiece temp = chessPiece[i][j];
	            chessPiece[i][j] = chessPiece[m][n];
	            chessPiece[m][n] = temp;
	        }
	    }
		for(int i = 0; i < 4; i++)
			for(int j = 0; j < 8; j++) {
				chessPiece[i][j].setPosition(
						board.getX(), board.getY(),
						board.getBoardWidth(), board.getBoardHeight(),
						i, j);
			}
	}

	
	// setup the Board with Chess Pieces
	public void setupPieces() {
		// setup each chess piece. There will be a total of 32 pieces; each player will have 16 pieces.
		countP1 = countP2 = 16;
				
		for(int i = 0; i < 2; i++)
			for(int j = 0; j < 8; j++) {
				chessPiece[i][j].setUser(User.P1);
				chessPiece[i][j].setSurface(Surface.BACK);
			}		
		for(int i = 2; i < 4; i++)
			for(int j = 0; j < 8; j++) {
				chessPiece[i][j].setUser(User.P2);
				chessPiece[i][j].setSurface(Surface.BACK);
			}
		
		// assigning numbers to the pieces for P1
		chessPiece[0][0].setNumber(7);
		chessPiece[0][1].setNumber(6);
		chessPiece[0][2].setNumber(6);
		chessPiece[0][3].setNumber(5);
		chessPiece[0][4].setNumber(5);
		chessPiece[0][5].setNumber(4);
		chessPiece[0][6].setNumber(4);
		chessPiece[0][7].setNumber(3);
		chessPiece[1][0].setNumber(3);
		chessPiece[1][1].setNumber(2);
		chessPiece[1][2].setNumber(2);
		chessPiece[1][3].setNumber(1);
		chessPiece[1][4].setNumber(1);
		chessPiece[1][5].setNumber(1);
		chessPiece[1][6].setNumber(1);
		chessPiece[1][7].setNumber(1);
		
		// assigning numbers to the pieces for P2
		chessPiece[2][0].setNumber(7);
		chessPiece[2][1].setNumber(6);
		chessPiece[2][2].setNumber(6);
		chessPiece[2][3].setNumber(5);
		chessPiece[2][4].setNumber(5);
		chessPiece[2][5].setNumber(4);
		chessPiece[2][6].setNumber(4);
		chessPiece[2][7].setNumber(3);
		chessPiece[3][0].setNumber(3);
		chessPiece[3][1].setNumber(2);
		chessPiece[3][2].setNumber(2);
		chessPiece[3][3].setNumber(1);
		chessPiece[3][4].setNumber(1);
		chessPiece[3][5].setNumber(1);
		chessPiece[3][6].setNumber(1);
		chessPiece[3][7].setNumber(1);
		
	}

	public int getCountP1() {
		return countP1;
	}

	public void setCountP1(int countP1) {
		this.countP1 = countP1;
	}

	public int getCountP2() {
		return countP2;
	}

	public void setCountP2(int countP2) {
		this.countP2 = countP2;
	}

}
