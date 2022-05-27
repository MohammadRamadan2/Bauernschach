package Bauerschach;

import java.util.*;

/**
 * <p> The class has been orginally created for {@link Shell} </p>
 *
 * the class has been mostly used in the console interface, some of the 
 * functions might still be useful, however try to ignore them when possible.
 * Still-used Functions are the ones to save the temporary variable of the target
 * and current position of the stones, attributes likes {@link PawnChess#arL_black}
 * and {@link PawnChess#arL_white} could be moved in the future for cleaner version.
 * @author Mohammad Ramadan
 * @version 1.0
 */
public class PawnChess extends Piece{
    /** ArrayList for the white stones */
    public ArrayList<Piece> arL_white = new ArrayList<>();
    /** ArrayList for the black stones */
    public ArrayList<Piece> arL_black = new ArrayList<>();
    /** 2-dim Array for the board */
    public String stoneOnLayout[][] = new String[9][9];
    /** Temporary variable for the current x position of a stone*/
    public int currTempX;
    /** Temporary variable for the current y position of a stone*/
    public int currTempY;
    /** Temporary variable for the target x position of a stone*/
    public int targTempX;
    /** Temporary variable for the target y position of a stone*/
    public int targTempY;
    /** Name of player 1 */
    public String p1_name;
    /** Name of player 2 */
    public String p2_name;
    
    /**
    * <p> Setter-Method  for  {@link PawnChess#currTempX}</p> 
    * @param x the new value for {@link PawnChess#currTempX}
    */
    public void setCurrentTempx(int x){
        currTempX = x;
    }
    /**
    * <p> Setter-Method  for  {@link PawnChess#currTempY}</p> 
    * @param y the new value for {@link PawnChess#currTempY}
    */
    public void setCurrentTempy(int y){
        currTempY = y;
    }

    /**
    * <p> Setter-Method  for  {@link PawnChess#targTempX}</p> 
    * @param x the new value for {@link PawnChess#targTempX}
    */
    public void setTargTempx(int x){
        targTempX = x;
    }
    /**
    * <p> Setter-Method  for  {@link PawnChess#targTempY}</p> 
    * @param y the new value for {@link PawnChess#targTempY}
    */
    public void setTargTempy(int y){
        targTempY = y;
    }
    
    /**
    * <p> Getter-Method  for  {@link PawnChess#currTempX}</p> 
    * @return the value of currTempX
    */
    public int getCurrTempX(){
        return currTempX;
    }
    /**
    * <p> Getter-Method  for  {@link PawnChess#currTempY}</p> 
    * @return the value of currTempY
    */
    public int getCurrTempY(){
        return currTempY;
    }

    /**
    * <p> Getter-Method  for  {@link PawnChess#targTempX}</p> 
    * @return the value of targTempX
    */
    public int getTargTempX(){
        return targTempX;
    }
    /**
    * <p> Getter-Method  for  {@link PawnChess#targTempY}</p> 
    * @return the value of targTempY
    */
    public int getTargTempY(){
        return targTempY;
    }
        
    /**
    * <p> Function to install the board on the console</p>
    * @deprecated 
    * 
    * the function is deprecated due to the newer interface!
    */
    public String[][] initBoard(){
    	for (int x = 1 ; x < 9 ; x++)
    	{
            System.out.print(x + " ");
            for (int y = 1; y < 9 ; y++)
            {
                if (x == 2) {
                    stoneOnLayout[x][y] = " B";
                }
                else if (x == 7) {
                    stoneOnLayout[x][y] = " W";
                }
                else { 
                    stoneOnLayout[x][y] = " -";
                }
                System.out.print(stoneOnLayout[x][y]);
            }
            System.out.println();
    	}  		
    	return stoneOnLayout;
    }
    
    /**
    * <p> Show the board on the console</p>
    * @deprecated 
    * 
    * the function is deprecated due to the newer interface!
    */
    public void showBoard() {
        for (int x = 1 ; x < 9 ; x++)
        {
            System.out.print(x + " ");
            for (int y = 1; y < 9 ; y++)
            {
                System.out.print(stoneOnLayout[x][y]);
            }
            System.out.println();
        }
    }
    
    /**
    * <p> Set the new position for the selected stone</p>
    * @deprecated 
    * 
    * the function is deprecated due to the newer interface!
    */
    public void setOnThisPosition(String[][] stoneOnLayout_,int x, int y, String value){
        setCurrentX(x);
        setCurrentY(y);
        stoneOnLayout_[x][y] = value;
        stoneOnLayout[x][y] = value;
    }
    
    /**
    * <p> Set the start position for all the stones</p>
    * @deprecated 
    * 
    * the function is deprecated due to the newer interface!
    */
    public void setStartPosition(){
        for (int i = 0; i <8 ; i++){
            Piece ph_figur = new Piece();
            ph_figur.setFirstMove(true);

            arL_black.add(ph_figur);
            arL_white.add(ph_figur);

            arL_white.get(i).setCurrentX(7);
            arL_white.get(i).setFirstMove(true);
            arL_black.get(i).setCurrentX(2);          
        }
    }
        /**
        * <p> Set the start position for all the stones version 2</p>
        * @deprecated 
        * 
        * the function is deprecated due to the newer interface!
        */
        public void setStartPositionGUI(){
        for (int i = 0; i <8 ; i++){
            Piece phFigur = new Piece();
            phFigur.setFirstMove(true);

            arL_black.add(phFigur);
            arL_white.add(phFigur);

            arL_white.get(i).setCurrentX(6);
            arL_white.get(i).setFirstMove(true);
            arL_black.get(i).setCurrentX(1);          
        }
    }
    
}
