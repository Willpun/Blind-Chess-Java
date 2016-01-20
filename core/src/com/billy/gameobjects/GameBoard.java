package com.billy.gameobjects;

public class GameBoard {
		
	private float boardWidth, boardHeight, x, y;
		
	public GameBoard(float screenWidth, float screenHeight) {
	
		float scaleWidth = screenWidth / 67;
		float scaleHeight = screenHeight / 135;
		
		if (scaleWidth < scaleHeight) {
			scaleHeight = scaleWidth;
		} else {
			scaleWidth = scaleHeight;
		}
		
		boardWidth = 67 * scaleWidth * 0.8f;
		boardHeight = 135 * scaleHeight * 0.8f;
		x = (screenWidth - boardWidth) * 0.5f;
		y = (screenHeight - boardHeight) * 0.5f;
	}
	
	public void update(float delta) {
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

	public float getBoardWidth() {
		return boardWidth;
	}

	public void setBoardWidth(float boardWidth) {
		this.boardWidth = boardWidth;
	}

	public float getBoardHeight() {
		return boardHeight;
	}

	public void setBoardHeight(float boardHeight) {
		this.boardHeight = boardHeight;
	}

}
