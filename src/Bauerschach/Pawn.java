package Bauerschach;

import javax.swing.JOptionPane;

/**
 * <p> The class which control the pawns </p>
 * 
 * has the main moving and removing fuctions specified for the pawns, extends 
 * {@link Piece} class to change the position of the selected stone  
 * @author Mohammad Ramadan
 * @version 1.0
 */
public class Pawn extends Piece{

    /**
    * <p> Function to move the pawns on the console interface  </p>
    * 
    * @deprecated due to the newer GUI,
    * has been replaced with {@link Pawn#moveStoneGUI(Bauerschach.Piece, boolean)}
    * @author Mohammad Ramadan
    */
    public boolean moveStone(Piece selectedStone, boolean is_white){
        int allowedStep = 1;
        int firstStep = 2; 
        
        // check for firstMove
        if (is_white) {
            if (selectedStone.getCurrentX() == 7)
                selectedStone.setFirstMove(true);
        }
        else { //black
            if (selectedStone.getCurrentX() == 2)
                selectedStone.setFirstMove(true);
        }
        
        if (is_white) {
            allowedStep = -1;
            firstStep = -2;
        }
        else {//black
            allowedStep = 1;
            firstStep = 2;
        }
        
        // X Controller
    	if ((selectedStone.getCurrentX() + allowedStep) == selectedStone.position_x){
            selectedStone.setPositionX(selectedStone.position_x);
            selectedStone.setPositionY(selectedStone.position_y);	
    	}
    	else if (selectedStone.getFirstMove()){
            if (((selectedStone.getCurrentX() + firstStep) == selectedStone.position_x) &&
                    ((selectedStone.getCurrentY()) == selectedStone.position_y )){
                selectedStone.setPositionX(selectedStone.position_x);
                selectedStone.setPositionY(selectedStone.position_y);	
            }
            else {
                JOptionPane.showMessageDialog(null,"Can't move to the selected Position", "Error" ,JOptionPane.ERROR_MESSAGE);
                System.out.println("Can't move to the selected Position");
                return false;    
            }
        }
    	else {
            JOptionPane.showMessageDialog(null,"Can't move to the selected Position", "Error" ,JOptionPane.ERROR_MESSAGE);
            System.out.println("Can't move to the selected Position");
            return false;
    	}
        return true;
    }
    
    
    /**
    * <p> Function to move the stones on the modern GUI</p>
    *
    * does a couple of tests to make sure that the move is legal, works only for
    * pawns!
    * 
    * @param selectedStone the selected stone to change it's position
    * @param is_white true if the selectedStone is white, false on otherwise
    * 
    * @return true if the stone has been moved, false on otherwise
    *
    */
    public boolean moveStoneGUI(Piece selectedStone, boolean is_white){
        int allowedStep = 1;
        int firstStep = 2; 
        
        // check for firstMove
        if (is_white) {
            if (selectedStone.getCurrentY() == 6)
                selectedStone.setFirstMove(true);
        }
        else { //black
            if (selectedStone.getCurrentY() == 1)
                selectedStone.setFirstMove(true);
        }
        
        if (is_white) {
            allowedStep = -1;
            firstStep = -2;
        }
        else {//black
            allowedStep = 1;
            firstStep = 2;
        }
        
        // X Controller
    	if ((selectedStone.getCurrentY() + allowedStep) == selectedStone.position_y){
            selectedStone.setPositionX(selectedStone.position_x);
            selectedStone.setPositionY(selectedStone.position_y);	
    	}
    	else if (selectedStone.getFirstMove()){
            if (((selectedStone.getCurrentY() + firstStep) == selectedStone.position_y) &&
                    ((selectedStone.getCurrentX()) == selectedStone.position_x )){
                selectedStone.setPositionX(selectedStone.position_x);
                selectedStone.setPositionY(selectedStone.position_y);	
            }
            else {     
                JOptionPane.showMessageDialog(null,"Can't move to the selected Position", "Error" ,JOptionPane.ERROR_MESSAGE);
                System.out.println("Can't move to the selected Position");
                return false;    
            }
        }
    	else {
            JOptionPane.showMessageDialog(null,"Can't move to the selected Position", "Error" ,JOptionPane.ERROR_MESSAGE);
            System.out.println("Can't move to the selected Position");
            return false;
    	}
        return true;
    }
    
    /**
    * <p> remove the stones</p>
    * 
    * @param selectedStone the selected stone to get removed
    *
    */
    public void removeStone(Piece selectedStone)
    {
        selectedStone.setCurrentX(-1);
        selectedStone.setCurrentY(-1);
    }
    
}