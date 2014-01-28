package minesweeper;

import javax.swing.JButton;

/**
 * 
 * @author CandNo: 22368
 * represents a MineTitleButton within the minefield.
 */

public class MineTileButton extends JButton {
	private static final long serialVersionUID = 1L;
	private int row;
	private int column;
	public MineTileButton(String text, int row, int column) {
		this.setText(text);
		this.row = row;
		this.column = column;
	}

	/**
	 * 
	 * @return the row of the MineTileButton
	 */
	public int getRow() {
		return row;
	}

	/**
	 * 
	 * @return the column of the MineTileButton
	 */
	public int getColumn() {
		return column;
	}
	

}
