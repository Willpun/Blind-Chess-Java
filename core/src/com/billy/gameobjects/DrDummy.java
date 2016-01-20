package com.billy.gameobjects;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.billy.gameobjects.ChessPiece.Surface;
import com.billy.gameobjects.ChessPiece.User;

public class DrDummy {
	
	private final int RECURSION_MAX = 2;
	
	private Random r;
	
	public int i, j, target_i, target_j, oi, oj, otarget_i, otarget_j;
	
	private int repeatCounter, moveCounter;
	
	private ArrayList<PossibleMove> possibleMoves;
	
	public DrDummy () {
		
	//	chessPiece = origChessPiece;
		
	//	ChessPiece temp = new ChessPiece(origChessPiece[0][2]);
		repeatCounter = moveCounter = 0;
		r = new Random();
		i = j = target_i = target_j = -1;
		possibleMoves = new ArrayList<PossibleMove>();
	}
	
	public void reset() {
		i = j = target_i = target_j = -1;
		repeatCounter = moveCounter = 0;
		possibleMoves.clear();
	}
	
	public int getLeft(int r, int oi, int oj, int i, int j, ChessPiece[][] c, int count) {
		if (count > RECURSION_MAX)
			return 0;
		if (i - 1 < 0)
			return 0;  // there's no left.
		if (c[i-1][j].getUser() == User.P2 || c[i-1][j].getSurface() == Surface.BACK)
			return 0;
		int o = c[oi][oj].getNumber();
		int t = c[i-1][j].getNumber();
		if (o == 7 && t == 1)
			return -12 + getRight(r, oi, oj, i-1, j, c, count+1) + getLeft(r, oi, oj, i-1, j, c, count+1) +
					getDown(r, oi, oj, i-1, j, c, count+1) + getUp(r, oi, oj, i-1, j, c, count+1);
		if (o == 1 && t == 7)
			return 12 + getRight(r, oi, oj, i-1, j, c, count+1) + getLeft(r, oi, oj, i-1, j, c, count+1) +
					getDown(r, oi, oj, i-1, j, c, count+1) + getUp(r, oi, oj, i-1, j, c, count+1);
		if (r > t)
			return t + getRight(r, oi, oj, i-1, j, c, count+1) + getLeft(r, oi, oj, i-1, j, c, count+1) +
				getDown(r, oi, oj, i-1, j, c, count+1) +	getUp(r, oi, oj, i-1, j, c, count+1);
		if (r <= t)
			return -t + getRight(r, oi, oj, i-1, j, c, count+1) + getLeft(r, oi, oj, i-1, j, c, count+1) +
				getDown(r, oi, oj, i-1, j, c, count+1) +	getUp(r, oi, oj, i-1, j, c, count+1);
		return 0;
	}
	
	public int getRight(int r, int oi, int oj, int i, int j, ChessPiece[][] c, int count) {
		if (count > RECURSION_MAX)
			return 0;
		if (i + 1 >= 4)
			return 0;  // there's no left.
		if (c[i+1][j].getUser() == User.P2 || c[i+1][j].getSurface() == Surface.BACK)
			return 0;
		int o = c[oi][oj].getNumber();
		int t = c[i+1][j].getNumber();
		if (o == 7 && t == 1)
			return -12 + getRight(r, oi, oj, i+1, j, c, count+1) + getLeft(r, oi, oj, i+1, j, c, count+1) +
					getDown(r, oi, oj, i+1, j, c, count+1) + getUp(r, oi, oj, i+1, j, c, count+1);
		if (o == 1 && t == 7)
			return 12 + getRight(r, oi, oj, i+1, j, c, count+1) + getLeft(r, oi, oj, i+1, j, c, count+1) +
					getDown(r, oi, oj, i+1, j, c, count+1) + getUp(r, oi, oj, i+1, j, c, count+1);
		if (r > t)
			return t + getRight(r, oi, oj, i+1, j, c, count+1) + getLeft(r, oi, oj, i+1, j, c, count+1) +
				getDown(r, oi, oj, i+1, j, c, count+1) + getUp(r, oi, oj, i+1, j, c, count+1);
		if (r <= t)
			return -t + getRight(r, oi, oj, i+1, j, c, count+1) + getLeft(r, oi, oj, i+1, j, c, count+1) +
				getDown(r, oi, oj, i+1, j, c, count+1) + getUp(r, oi, oj, i+1, j, c, count+1);
		return 0;
	}
	
	public int getUp(int r, int oi, int oj, int i, int j, ChessPiece[][] c, int count) {
		if (count > RECURSION_MAX)
			return 0;
		if (j - 1 < 0)
			return 0;  // there's no left.
		if (c[i][j-1].getUser() == User.P2 || c[i][j-1].getSurface() == Surface.BACK)
			return 0;
		int o = c[oi][oj].getNumber();
		int t = c[i][j-1].getNumber();
		if (o == 7 && t == 1)
			return -12 + getRight(r, oi, oj, i, j-1, c, count+1) + getLeft(r, oi, oj, i, j-1, c, count+1) +
					getDown(r, oi, oj, i, j-1, c, count+1) + getUp(r, oi, oj, i, j-1, c, count+1);
		if (o == 1 && t == 7)
			return 12 + getRight(r, oi, oj, i, j-1, c, count+1) + getLeft(r, oi, oj, i, j-1, c, count+1) +
					getDown(r, oi, oj, i, j-1, c, count+1) + getUp(r, oi, oj, i, j-1, c, count+1);
		if (r > t)
			return t + getRight(r, oi, oj, i, j-1, c, count+1) + getLeft(r, oi, oj, i, j-1, c, count+1) +
				getDown(r, oi, oj, i, j-1, c, count+1) +	getUp(r, oi, oj, i, j-1, c, count+1);
		if (r <= t)
			return -t + getRight(r, oi, oj, i, j-1, c, count+1) + getLeft(r, oi, oj, i, j-1, c, count+1) +
				getDown(r, oi, oj, i, j-1, c, count+1) +	getUp(r, oi, oj, i, j-1, c, count+1);
		return 0;
	}
	
	public int getDown(int r, int oi, int oj, int i, int j, ChessPiece[][] c, int count) {
		if (count > RECURSION_MAX)
			return 0;
		if (j + 1 >= 8)
			return 0;  // there's no left.
		if (c[i][j+1].getUser() == User.P2 || c[i][j+1].getSurface() == Surface.BACK)
			return 0;
		int o = c[oi][oj].getNumber();
		int t = c[i][j+1].getNumber();
		if (o == 7 && t == 1)
			return -12 + getRight(r, oi, oj, i, j+1, c, count+1) + getLeft(r, oi, oj, i, j+1, c, count+1) +
					getDown(r, oi, oj, i, j+1, c, count+1) + getUp(r, oi, oj, i, j+1, c, count+1);
		if (o == 1 && t == 7)
			return 12 + getRight(r, oi, oj, i, j+1, c, count+1) + getLeft(r, oi, oj, i, j+1, c, count+1) +
					getDown(r, oi, oj, i, j+1, c, count+1) + getUp(r, oi, oj, i, j+1, c, count+1);
		if (r > t)
			return t + getRight(r, oi, oj, i, j+1, c, count+1) + getLeft(r, oi, oj, i, j+1, c, count+1) +
				getDown(r, oi, oj, i, j+1, c, count+1) + getUp(r, oi, oj, i, j+1, c, count+1);
		if (r <= t)
			return -t + getRight(r, oi, oj, i, j+1, c, count+1) + getLeft(r, oi, oj, i, j+1, c, count+1) +
				getDown(r, oi, oj, i, j+1, c, count+1) + getUp(r, oi, oj, i, j+1, c, count+1);
		return 0;
	}
	
	public boolean isTargetAdjacent(int i, int j, int target_i, int target_j) {
		boolean isLeft = (i - 1 == target_i) && (j == target_j);
		boolean isRight = (i + 1 == target_i) && (j == target_j);
		boolean isUp = (i == target_i) && (j - 1 == target_j);
		boolean isDown = (i == target_i) && (j + 1 == target_j);
		return isLeft || isRight || isUp || isDown;
	}
	
	public boolean isFaceDown(int target_i, int target_j, ChessPiece[][] chessPiece) {
		return (chessPiece[target_i][target_j].getSurface() == Surface.BACK);
	}
	
	public boolean isFaceUp(int target_i, int target_j, ChessPiece[][] chessPiece) {
		return (chessPiece[target_i][target_j].getSurface() == Surface.FRONT);
	}
	
	public boolean isEmptySpace(int target_i, int target_j, ChessPiece[][] chessPiece) {
		return (chessPiece[target_i][target_j].getSurface() == Surface.UNDEFINED);
	}
	
	public boolean isP2(int target_i, int target_j, ChessPiece[][] chessPiece) {
		return (chessPiece[target_i][target_j].getUser() == User.P2);
	}
	
	public boolean isP1(int target_i, int target_j, ChessPiece[][] chessPiece) {
		return (chessPiece[target_i][target_j].getUser() == User.P1);
	}
	
	public boolean isSamePiece(int i, int j, int target_i, int target_j) {
		return (i == target_i && j == target_j);
	}
	
	public boolean isThisMovePossible(int i, int j, int target_i, int target_j, ChessPiece[][] chessPiece) {
		
//		Gdx.app.log("isMovePossible getnumber()", " "+chessPiece[target_i][target_j].getNumber());
		
		
		if (isEmptySpace(i,j, chessPiece)) { // No piece selected
			return false;
		}
		if (isP1(i, j, chessPiece) && isFaceUp(i, j, chessPiece)) { // This is not your piece
			return false;
		}		
		if (isSamePiece(i, j, target_i, target_j) && isFaceDown(i, j, chessPiece)) {  // Unknown piece, so uncover it.
			return true;
		}
		if (isP2(target_i, target_j, chessPiece)){  // You can't move to the top of other piece of your own.
			return false;
		}
		if (!isTargetAdjacent(i, j, target_i, target_j)) { // You can only move 1 square far.
			return false;
		}
		
		if (isFaceUp(i, j, chessPiece) && isFaceUp(target_i, target_j, chessPiece)) {
			if (
				(	(chessPiece[target_i][target_j].getNumber() <= chessPiece[i][j].getNumber() &&
					!(chessPiece[target_i][target_j].getNumber() == 1 && chessPiece[i][j].getNumber() == 7))
				|| 
					(chessPiece[target_i][target_j].getNumber() == 7 &&
					chessPiece[i][j].getNumber() == 1))) {
						return true;
			} 
		}

		if (isFaceUp(i, j, chessPiece) && isEmptySpace(target_i, target_j, chessPiece)) {
			if(isTargetAdjacent(i, j, target_i, target_j)) {
				return true;
			}
		}
		return false;
	}
	
	public void checkAllPossibleMoves(ChessPiece[][] chessPiece) {
		int x, y, a, b, count, r;
		
		count = 0;
		for (a=0; a<4; a++) {
			for (b=0; b<8; b++) {
				for (x=0; x<4; x++) {
					for (y=0; y<8; y++) {
						if(isThisMovePossible(x, y, a, b, chessPiece)) {						
							possibleMoves.add(new PossibleMove());
							possibleMoves.get(count).setI(x);
							possibleMoves.get(count).setJ(y);
							possibleMoves.get(count).setTarget_i(a);
							possibleMoves.get(count).setTarget_j(b);
							
							if (isSamePiece(x, y, a, b)) {// prevent AI cheating
								possibleMoves.get(count).setRating(0);
							} else {
								r = chessPiece[x][y].getNumber();
								r = getLeft(r, x, y, a, b, chessPiece, 0) + getRight(r, x, y, a, b, chessPiece, 0) +
										getUp(r, x, y, a, b, chessPiece, 0) + getDown(r, x, y, a, b, chessPiece, 0);
								possibleMoves.get(count).setRating(r + chessPiece[a][b].getNumber());
							
							}
							count++;
						}
					}
				}
			}
		}
		
		Gdx.app.log("# of possible moves in this turn for AI is", " " + possibleMoves.size());
	}
	
	public boolean findNextMove(ChessPiece[][] chessPiece) {
		
		moveCounter++;
		
		checkAllPossibleMoves(chessPiece);
		if (possibleMoves.size() <= 0) {
			return false;
		} 
	
		int index, d, secondBest;
		secondBest = index = d = 0;

		for(d = 0; d < possibleMoves.size(); d++) {
					
			if (possibleMoves.get(d).getRating() >= possibleMoves.get(secondBest).getRating()) {
				
				if (possibleMoves.get(d).getRating() >= possibleMoves.get(index).getRating()) {
					secondBest = index;
					index = d;
				}
		Gdx.app.log("index updated as:", ""+ index);		
			}

		}

		if (index == possibleMoves.size() - 1 && possibleMoves.get(index).getRating() <= 0) {		 
			index = getRandom(possibleMoves.size());
		}		
		
		if (this.oi == possibleMoves.get(index).getI() &&
			this.oj == possibleMoves.get(index).getJ() &&
			this.otarget_i == possibleMoves.get(index).getTarget_i() &&
			this.otarget_j == possibleMoves.get(index).getTarget_j() ) {
			repeatCounter++;
		}
		
		if (repeatCounter > 1) {
	//		index = getRandomWithExclusion(r, 0, possibleMoves.size() - 1, index);
			index = secondBest;
			repeatCounter = 0;
		}
		
		this.i = possibleMoves.get(index).getI();
		this.j = possibleMoves.get(index).getJ();
		this.target_i = possibleMoves.get(index).getTarget_i();
		this.target_j = possibleMoves.get(index).getTarget_j();
		possibleMoves.clear();
		
		if (moveCounter % 2 == 1) {
			this.oi = this.i;
			this.oj = this.j;
			this.otarget_i = this.target_i;
			this.otarget_j = this.target_j;
		}
		return true;	
	}

	public int getRandomWithExclusion(Random rnd, int start, int end, int... exclude) {
	    int random = start + rnd.nextInt(end - start + 1 - exclude.length);
	    for (int ex : exclude) {
	        if (random < ex) {
	            break;
	        }
	        random++;
	    }
	    return random;
	}
	
	public int getRandom(int m) {
		return r.nextInt(m);
	}

	public ArrayList<PossibleMove> getPossibleMoves() {
		return possibleMoves;
	}

	public void setPossibleMoves(ArrayList<PossibleMove> possibleMoves) {
		this.possibleMoves = possibleMoves;
	}
}
