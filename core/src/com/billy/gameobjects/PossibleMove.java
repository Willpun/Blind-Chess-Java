package com.billy.gameobjects;

public class PossibleMove {
	private int i, j, target_i, target_j;
	private int rating;
	
	public PossibleMove() {
		
	}
	
	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}

	public int getJ() {
		return j;
	}

	public void setJ(int j) {
		this.j = j;
	}

	public int getTarget_i() {
		return target_i;
	}

	public void setTarget_i(int target_i) {
		this.target_i = target_i;
	}

	public int getTarget_j() {
		return target_j;
	}

	public void setTarget_j(int target_j) {
		this.target_j = target_j;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}
}
