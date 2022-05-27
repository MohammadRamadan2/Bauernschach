package Bauerschach;


/**
 * <p> Class for the attributes of the stones</p>
 * used to save the main attributes of basically all stones, anything that applies
 * for all the pieces like color, position should be saved here.
 * @author Mohammad Ramadan
 */
public class Piece {
    /**X coordination of the target position*/
    public int position_x;
    /**Y coordination of the target position*/
    public int position_y;
    /**X coordination of the current position*/
    public int current_x;
    /**Y coordination of the current position*/
    public int current_y;

    /** Color of the stone, given as String*/
    public String Farbe;
    /** true if the first move is different from next ones (applies to the pawns in legal state, 
     * or to own-rules game ) */
    public boolean firstMove;

    
    /**
     * <p> Getter-Method for the current x coordination</p>
     * @return the current x coordination*/
    public int getCurrentX() {
        return current_x;
    }
    /**
     * <p> Getter-Method for the current y coordination</p>
     * @return the current y coordination*/
    public int getCurrentY() {
        return current_y;
    }
    /**
     * <p> Setter-Method for the current x coordination</p>
     * @param x the new current x coordination
     */
    public void setCurrentX(int x) {
        current_x = x;
    }
    /**
     * <p> Setter-Method for the current y coordination</p>
     * @param y the new current y coordination
     */
    public void setCurrentY(int y) {
        current_y = y;
    }

    /**
     * <p> Setter-Method for the {@link Piece#firstMove}</p>
     * @param firstMove_ the new value for {@link Piece#firstMove}
     */
    public void setFirstMove(boolean firstMove_) {
        firstMove = firstMove_;
    } 
    
    /**
     * <p> Getter-Method for the {@link Piece#firstMove}</p>
     * @return true if firstMove is enabled, false on otherwise
     */
    public boolean getFirstMove() {
        return this.firstMove;
    }

    /**
     * <p> Getter-Method for the x coordination of the target position</p>
     * @return x coordination of the target position
     */
    public int getPositionX(){
        return position_x;
    }
    /**
     * <p> Getter-Method for the y coordination of the target position</p>
     * @return y coordination of the target position
     */
    public int getPositionY(){
        return position_y;
    }

    /**
     * <p> Setter-Method for the target x coordination</p>
     * @param x the new current x coordination
     */
    public void setPositionX(int x){
        position_x = x;
    }
    /**
     * <p> Setter-Method for the target y coordination</p>
     * @param y the new current y coordination
     */
    public void setPositionY(int y){
        position_y = y;
    }
}