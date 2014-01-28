package minesweeper;

import java.awt.BorderLayout;
import java.awt.Window;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
/**
 * 
 * @author CandNo:22368
 *
 *MyMouseListener is used to listen to the MineTileButton that has been pressed within the grid of the
 *minefield
 */

public class MyMouseListener implements MouseListener {

    private Minefield mineField;
    private MineFieldPanel miFP;
    private int enteredRow, enteredCol;
    
    /**
     * 
     * @param m mineField that has been passed from the mineFieldPanel
     * @param p MineFieldPanel that has been pass from the MineFieldPanel
     */
    public MyMouseListener(Minefield m, MineFieldPanel p) {
        this.mineField = m;
        this.miFP = p;
    }
    
    /**
     * Method to mark a mine on the mine field
     * @param i row to mark
     * @param j colum to mark
     */
    public void mark(int i, int j) {
        mineField.mark(i, j);
        if(mineField.areAllMinesFound()) {
            miFP.updateGUI();
            int response = JOptionPane.showConfirmDialog(null,
                    "Congratulations you completed the game. Would you like to play again?","Congratulations!", JOptionPane.YES_NO_OPTION);
             if (response == JOptionPane.NO_OPTION) {
                 endGame();
                } else if (response == JOptionPane.YES_OPTION) {
                    newGame();
                }
        }
    }
    
    /**
     * Ends the game by closing the window.
     */
    public void endGame() {
        SwingUtilities.windowForComponent(miFP).dispose();
    }
    
    /**
     * Creates a newgame, removes the old MineSweeperFrame and adds a new one.
     */
    public void newGame() {
        miFP = miFP.getMineSweeperFrame().getMiPanel();
        Window parent = SwingUtilities.windowForComponent(miFP);
        parent.remove(miFP);
        MineFieldPanel miPanel = new MineFieldPanel(mineField.getRows(),mineField.getColumns(),mineField.getMines(), miFP.getMineSweeperFrame());
        miFP.getMineSweeperFrame().setMiPanel(miPanel);
        parent.add(miPanel,BorderLayout.CENTER);
        parent.validate();

    }
    
    /**
     * 
     * @param i row that is being stepped on
     * @param j column that is being stepped on
     */
    public void step(int i, int j) {
        if(!mineField.step(i, j)) {
            int response = JOptionPane.showConfirmDialog(null,
                    "BOOM!! Game over. Would you like to play again?", "BOOM!!", JOptionPane.YES_NO_OPTION);
             if (response == JOptionPane.NO_OPTION) {
                 endGame();
                } else if (response == JOptionPane.YES_OPTION) {
                    newGame();
                }
        }
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    /**
     * Event that is triggered when the mouse is released
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        MineTileButton source = (MineTileButton)e.getSource();
        int r = source.getRow();
        int c = source.getColumn();
        if(r == enteredRow && c == enteredCol){
        
        if(e.getButton() == MouseEvent.BUTTON1) {
            this.step(r,c);
        }
        if(e.getButton() == MouseEvent.BUTTON3) {
            this.mark(r,c);
        }
        
        miFP.updateGUI();
        }
    }

    /**
     * Event that is trigger when the mouse is entered into a MineTitleButton. This is to make sure the user
     * doesn't long press and then decide to move off the square as they have made a choice they didn't want to make.
     */
    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
        MineTileButton source = (MineTileButton)e.getSource();
        enteredRow = source.getRow();
        enteredCol = source.getColumn();
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }
    
}
