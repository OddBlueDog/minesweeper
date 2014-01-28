package minesweeper;


import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * 
 * @author CandNo:22368
 * A MineFieldPanel is the main panel that contains the minefield grid and all the buttons.
 */
public class MineFieldPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private Minefield mineField;
	private MineSweeperFrame mineSweeperFrame;
	private MineTileButton[][] mineTileButtonArray;
	
	/**
	 * 
	 * @param rows amounts of rows to be in the gridlayout and minefiled
	 * @param columns amount of columns to be in the gridlayout and minefield
	 * @param mines amount of mines to be in the minefield
	 * @param mineSweeperFrame the frame that the panel is within
	 */
	public MineFieldPanel(int rows, int columns, int mines, MineSweeperFrame mineSweeperFrame) {
		this.mineSweeperFrame = mineSweeperFrame;
		this.mineTileButtonArray = new MineTileButton[rows][columns];
	    this.mineField = new Minefield(rows,columns,mines);
	    MyMouseListener mouseList = new MyMouseListener(mineField, this);
		this.setLayout(new GridLayout(rows,columns));
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < columns; j++) {
				this.add(mineTileButtonArray[i][j] = new MineTileButton(mineField.getMineTile(i,j).toString(),i, j));
				mineTileButtonArray[i][j].addMouseListener(mouseList);
				mineTileButtonArray[i][j].setPreferredSize(new Dimension(40, 40));
			}
		}
	}
	
	/**
	 * Cycles though the whole board making sure everything is up to date in display.
	 */
	public void updateGUI() {
		for(int i = 0; i < mineField.getRows(); i++) {
			for(int j = 0; j < mineField.getColumns(); j++) {
				if(mineField.getMineTile(i,j).toString() == "@") {
					ImageIcon flag = new ImageIcon(getClass().getResource("/res/flag.png"));
					mineTileButtonArray[i][j].setIcon(flag);
				} else {
					switch(mineField.getMineTile(i,j).getMinedNeighbours()) {
					case 1:
						mineTileButtonArray[i][j].setText("<html><font color = #00a2e5>" + mineField.getMineTile(i,j).toString() + "</font></html>");
						break;
					case 2:
						mineTileButtonArray[i][j].setText("<html><font color = #36c000>" + mineField.getMineTile(i,j).toString() + "</font></html>");
						break;
					case 3:
						mineTileButtonArray[i][j].setText("<html><font color = #ec0000>" + mineField.getMineTile(i,j).toString() + "</font></html>");
						break;
					case 4:
						mineTileButtonArray[i][j].setText("<html><font color = #006aa1>" + mineField.getMineTile(i,j).toString() + "</font></html>");
						break;
					case 5:
						mineTileButtonArray[i][j].setText("<html><font color = #90007d>" + mineField.getMineTile(i,j).toString() + "</font></html>");
						break;
					case 6:
						mineTileButtonArray[i][j].setText("<html><font color = #e4dc00>" + mineField.getMineTile(i,j).toString() + "</font></html>");
						break;
					case 7:
						mineTileButtonArray[i][j].setText("<html><font color = #000000>" + mineField.getMineTile(i,j).toString() + "</font></html>");
						break;
					case 8:
						mineTileButtonArray[i][j].setText("<html><font color = #767200>" + mineField.getMineTile(i,j).toString() + "</font></html>");
						break;
					default:
						break;
				}
					mineTileButtonArray[i][j].setIcon(null);
				}
				if(mineField.getMineTile(i,j).isRevealed()) {
					mineTileButtonArray[i][j].setEnabled(false);
				}
			}
		}
	}
	
	/**
	 * 
	 * @return the amount of rows in the minefield
	 */
	public int getRows() {
		return mineField.getRows();
	}
	
	/**
	 * 
	 * @return returns the amount of columns in the minefield
	 */
	public int getColumns() {
		return mineField.getColumns();
	}
	
	/**
	 * 
	 * @return returns the amount of mines in the minefield
	 */
	public int getMines() {
		return mineField.getMines();
	}
	
	/**
	 * 
	 * @return returns the minesweeperframe
	 */
	public MineSweeperFrame getMineSweeperFrame() {
		return mineSweeperFrame;
	}
}
