package com.billy.gameobjects;

import com.badlogic.gdx.math.Rectangle;

public class ChessPiece {

	private float x, y, width, height;
	
	private Rectangle bounds;
	private boolean isPressed;

	private float oldX, oldY;
	
	public enum User {
		P1, P2, COM, UNDEFINED
	}

	public enum Surface {
		FRONT, BACK, UNDEFINED
	}
	
	private int number;  // The strength of the chess piece. Pieces with equal or higher number capture the others.

	private Surface surface;
	private User user;
	
	public ChessPiece() {
			bounds = new Rectangle();
			isPressed = false;
			surface = Surface.UNDEFINED;
			user = User.UNDEFINED;
			number = 0;
	}

	ChessPiece (ChessPiece c) {

		x = c.x;
		y = c.y;
		width = c.width;
		height = c.height;
		bounds = c.bounds;
		isPressed = c.isPressed;
		oldX = c.oldX;
		oldY = c.oldY;
		number = c.number;
		surface = c.surface;
		user = c.user;
		
	}
	
	public void setPosition(float boardX, float boardY, float boardWidth, float boardHeight, int i, int j) {
		x = boardX + (boardWidth * i * 17 / 67);
		y = boardY + (boardHeight * j * 17 / 135);
		width = (boardWidth * 64 / 67) / 4;
		height = (boardHeight * 128 / 135) / 8;
		bounds.set(x, y, width, height);
		
	}
	
	public boolean isItThisChessPiece(int screenX, int screenY) {
		return bounds.contains(screenX, screenY);
	}
	
    public boolean isTouchDown(int screenX, int screenY) {

        if (isItThisChessPiece(screenX, screenY)) {
            isPressed = true;
            oldX = x;
            oldY = y;
            return true;
        }
        return false;
    }
	
    public boolean toOldPosition() {
    	
    	if (isPressed) {
    		x = oldX;
    		y = oldY;
    		isPressed = false;
    		return true;
    	}
    	return false;
    }
    
	public boolean isPressed() {
		return isPressed;
	}
    
	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
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

	public Surface getSurface() {
		return surface;
	}

	public void setSurface(Surface surface) {
		this.surface = surface;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
//		if (this.user == User.UNDEFINED)
	//		this.surface = Surface.UNDEFINED;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}
}
