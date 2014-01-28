package minesweeper;

public class MineTile {
	private boolean marked = false;
	private int minedNeighbours = 0;
	private boolean mined = false;
	private boolean revealed = false;

	
	/**
	 * 
	 * @return returns if the tile is revealed
	 */
	public boolean isRevealed() {
		return revealed;
	}
	
	/**
	 * toggles if the tile is revealed or not.
	 */
	public void toggleRevealed() {
		this.revealed = !this.revealed;
	}
	
	/**
	 * 
	 * @return returns is the tile is marked or not.
	 */
	public boolean isMarked() {
		return marked;
	}
	
	/**
	 * toggles if the tile is marked or not
	 */
	public void toggleMarked() {
		this.marked = !this.marked;
	}
	
	/**
	 * 
	 * @return returns the amount of minedneighbours
	 */
	public int getMinedNeighbours() {
		return minedNeighbours;
	}
	
	/**
	 * Increments the amount of mined neighbours
	 */
	public void incMinedNeighbours() {
		this.minedNeighbours++;
	}
	
	/**
	 * 
	 * @return returns if the tile is mined or not.
	 */
	public boolean isMined() {
		return mined;
	}
	
	/**
	 * toggles if the tile is mined or not.
	 */
	public void toggleMined() {
		this.mined = !this.mined;
	}

	/**
	 * to string method of MineTile
	 */
	public String toString() {
		// As per specification, show the current state of the tile
		if(revealed) {
			if(mined) {
				return "*";
			} else {
				return "" + minedNeighbours;
			}
		} else {
			if(marked) {
				return "@";
			} else {
				return "";
			}
		}
	}
	
	/**
	 * 
	 * @param revealed sets if the tile is revealed of not.
	 */
	public void setRevealed(boolean revealed) {
		this.revealed = revealed;
	}
	
	
}
