package minesweeper; 

public class Minefield {
	private MineTile[][] minefield;

	private int rows, columns, numMines;

	public Minefield(int rows, int columns, int numMines) {
		this.rows = rows;
		this.columns = columns;
		this.numMines = numMines;
		minefield = new MineTile[rows][columns];
		// We must fill out the array with objects this time, as opposed to arrays
		// of primitive values.
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				minefield[i][j] = new MineTile();
			}
		}
		populateMines(numMines); 
	}

	public int getRows() {
		return rows;
	}

	public int getColumns() {
		return columns;
	}
	
	public int getMines() {
		return numMines;
	}

	public void mineSquare(int row, int column) {
		minefield[row][column].toggleMined();
		for (int i = row - 1; i <= row + 1; i++) {
			for (int j = column - 1; j <= column + 1; j++) {
				if (i >= 0 && j >= 0 && i < minefield.length
						&& j < minefield[i].length) {
					minefield[i][j].incMinedNeighbours();
				}

			}
		}
	}

	public String toString() {
		String s = "";
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				s += minefield[i][j];

			}
			s += "\n";
		}

		return s;
	}

	public void printMinefield() {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				System.out.print(minefield[i][j]);
			}
			System.out.println();
		}
	}

	private void populateMines(int num) {
		int created = 0;
		while (created < num) {
			int row = (int) (Math.random() * this.rows);
			int col = (int) (Math.random() * this.columns);
			if (!minefield[row][col].isMined() && !(row == 0 && col == 0)) {
				mineSquare(row, col);
				created++;
			}

		}
	}



	public void mark(int row, int column) {
		this.minefield[row][column].toggleMarked();

	}

	private void checkReveal(int row, int column) {
		// walk round the outside of the current tile
		for (int i = row - 1; i <= row + 1; i++) {
			for (int j = column - 1; j <= column + 1; j++) {
				// Now that you've seen one approach to creating bounds in v1,
				// here we roll the check on bounds inside the loop
				if (i >= 0 && j >= 0 && i < minefield.length
						&& j < minefield[i].length) {
					// Not the one just shown...
					if (!(i == row && j == column)) {
						// If its not already revealed - recursion termination condition
						if (!minefield[i][j].isRevealed()) {
							// Note that this is the check on ending the recursion
							minefield[i][j].setRevealed(true);
							if (minefield[i][j].getMinedNeighbours() == 0) {
								// recurse around here to see what also needs to be revealed
								checkReveal(i, j);
							}
						}
					}
				}
			}
		}

	}

	public boolean step(int row, int column) {
		if (row >= 0 && row < getRows() && column >= 0 && column < getColumns()) {
			if (minefield[row][column].isMined()) {
				// The BOOM return
				return false;
			} else {
				// Otherwise reveal...
				minefield[row][column].setRevealed(true);
				// ...and show any other tiles needed
				if (minefield[row][column].getMinedNeighbours() == 0) {
					checkReveal(row, column);
				}
			}

		}
		return true;

	}

	public boolean areAllMinesFound() {
		// Check all tiles for mined tiles marked and only mined tiles marked
		for (int i = 0; i < this.rows; i++) {
			for (int j = 0; j < this.columns; j++) {
				if ((minefield[i][j].isMined() && !minefield[i][j].isMarked())
						|| (!minefield[i][j].isMined() && minefield[i][j]
								.isMarked())) {
					return false;
				}
			}
		}
		return true;
	}

	public MineTile getMineTile(int row, int col) {
		return minefield[row][col];
	}
	
	public MineTile[][] getMineTileArray() {
		return minefield;
	}
	
}
