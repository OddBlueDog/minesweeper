package minesweeper;

import java.awt.BorderLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 * 
 * @author CandNo: 22368
 * MyMenuListener is used to listen to which JMenuItem has been selected within the MineSweeperFrame.
 */
public class MyMenuListener implements ActionListener {

	private MineSweeperFrame mineSweeperFrame;
	private MineFieldPanel miFP, miPanel;
	
	/**
	 * 
	 * @param mineSweeperFrame this is the MineSweeperFrame that contains the JMenuItems that this
	 * object is listening to
	 */
	public MyMenuListener(MineSweeperFrame mineSweeperFrame) {
		this.mineSweeperFrame = mineSweeperFrame;
	}

	/**
	 * When a JMenuButton is pressed the appropirate action is carried out.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		miFP = mineSweeperFrame.getMiPanel();
		String actionCommand = e.getActionCommand();
		if(actionCommand.equals("New")) {
			newGame(miFP.getRows(),miFP.getColumns(),miFP.getMines());
		} else if(actionCommand.equals("Beginner")) {
			newGame(8,8,10);
		}
		else if(actionCommand.equals("Intermediate")) {
			newGame(16,16,40);
		}
		else if(actionCommand.equals("Hard")) {
			newGame(16,30,99);
		}
		else if(actionCommand.equals("Custom")) {
			JTextField rows = new JTextField();
			JTextField columns = new JTextField();
			JTextField mines = new JTextField();
			final JComponent[] inputs = new JComponent[] {
			                new JLabel("Rows"),
			                rows,
			                new JLabel("Columns"),
			                columns,
			                new JLabel("Mines"),
			                mines
			};
			JOptionPane.showMessageDialog(null, inputs, "Custom Game", JOptionPane.PLAIN_MESSAGE);
			int intRows = 0;
			int intColumns = 0;
			int intMines = 0;
			
			try {
				intRows = Integer.parseInt(rows.getText());
				intColumns = Integer.parseInt(columns.getText());
				intMines = Integer.parseInt(mines.getText());
	
				miPanel = new MineFieldPanel(intRows,intColumns,intMines,miFP.getMineSweeperFrame());
			} catch(NumberFormatException evt) {
				if(!(rows.getText().length() == 0 && columns.getText().length() == 0 && mines.getText().length() == 0)) {
					JOptionPane.showMessageDialog(null, "Invalid input, please input numbers only and fill out all boxes.");
				}
			} 
			if((intMines >=1 && intColumns >=1) && (intMines * intColumns > intMines)) {
				startNewGame();
			}
		}
		else if(actionCommand.equals("Quit")) {
			mineSweeperFrame.dispose();
		}
		else if(actionCommand.equals("About")) {
			JOptionPane.showMessageDialog(null, "Minesweeper Version 0.11 - CandNo: 22368");
		}
		
		
	}

	/**
	 * 
	 * @param rows the amount of rows in the new game.
	 * @param col the amount of columns in the new game.
	 * @param mine the amount of mines in the new game.
	 */
	private void newGame(int rows, int col, int mine) {
				miPanel = new MineFieldPanel(rows,col,mine,miFP.getMineSweeperFrame());
				startNewGame();
	}

	/**
	 * Starts a new game, removes the old MineFieldPanel and replaces it with the new one.
	 */
	private void startNewGame() {
		int response = JOptionPane.showConfirmDialog(null,
				"Are you sure you want to start a new game?", null, JOptionPane.YES_NO_OPTION);
		if (response == JOptionPane.YES_OPTION) {
		    	Window parent = SwingUtilities.windowForComponent(miFP);
				parent.remove(miFP);
				mineSweeperFrame.setMiPanel(miPanel);
				parent.add(miPanel,BorderLayout.CENTER);
				parent.validate();
				 mineSweeperFrame.pack();
		    }
	}

}