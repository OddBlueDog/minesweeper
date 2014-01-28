package minesweeper;
import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * 
 * @author CandNo:22368
 * This is the main frame that represents the game as a whole. The class is exteneded from JFrame to
 * provide all the functionaility that JFrame provides.
 */

public class MineSweeperFrame extends JFrame {

	MineFieldPanel miPanel;
	private static final long serialVersionUID = 1L;

		public MineSweeperFrame() {
			this.setTitle("Minesweeper - CandNo: 22368");
			miPanel = new MineFieldPanel(8,8,10,this);
			// creates a new menu bar and sets the JMenuBar of this to the new menuBar
			JMenuBar menuBar = new JMenuBar();
			this.setJMenuBar(menuBar);
			
			// creates the new JMenus and adds the to the menuBar
			JMenu fileMenu = new JMenu("File");
			JMenu helpMenu = new JMenu("Help");
			menuBar.add(fileMenu);
			menuBar.add(helpMenu);
			
			// creates all the JMenuItems that will be within the JMenu
			JMenuItem newItem = new JMenuItem("New");
			JMenuItem beginnerItem = new JMenuItem("Beginner");
			JMenuItem intermediateItem = new JMenuItem("Intermediate");
			JMenuItem hardItem = new JMenuItem("Hard");
			JMenuItem customItem = new JMenuItem("Custom");
			JMenuItem aboutItem = new JMenuItem("About");
			JMenuItem quitItem = new JMenuItem("Quit");
			
			//adds all the JMenuItems's to there respective JMenus
			fileMenu.add(newItem);
			fileMenu.addSeparator();
			fileMenu.add(beginnerItem);
			fileMenu.add(intermediateItem);
			fileMenu.add(hardItem);
			fileMenu.add(customItem);
			fileMenu.addSeparator();
			fileMenu.add(quitItem);
			
			helpMenu.add(aboutItem);
			
			// adds myMenuListener to all the JMenuItems
			MyMenuListener myMenuListener = new MyMenuListener(this);
			newItem.addActionListener(myMenuListener);
			beginnerItem.addActionListener(myMenuListener);
			intermediateItem.addActionListener(myMenuListener);
			hardItem.addActionListener(myMenuListener);
			customItem.addActionListener(myMenuListener);
			aboutItem.addActionListener(myMenuListener);
			quitItem.addActionListener(myMenuListener);
						
			this.getContentPane().add(miPanel,BorderLayout.CENTER);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.pack();
		}
		
		/**
		 * Causes the MineSweeperFrame to become visible
		 */
		public void showIt() {
		    this.setVisible(true);
		  }
		
		/**
		 * 
		 * @return the current MineFieldPanel that the MineSweeperFrame has
		 */
		public MineFieldPanel getMiPanel(){
			return miPanel;
		}
		
		/**
		 * 
		 * @param newPanel the new MineFieldPanel, when a new game is created, or the user changes the game
		 * settings.
		 */
		public void setMiPanel(MineFieldPanel newPanel){
			this.miPanel = newPanel;
		}
}
