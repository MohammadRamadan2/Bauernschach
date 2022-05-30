package Bauerschach;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * <p> Game is the main class of the program, used mainly for the GUI, has 
 * replaced the {@link Shell} class </p>
 * 
 * @author Mohammad Ramadan
 * @version 1.0
 */
public class Game extends PawnChess implements ActionListener {
    JFrame frame;
    private enum clickPhase_{
        CURR_PHASE,
        TARG_PHASE
    }
    private enum color_{
        WHITE,
        BLACK
    }
    private clickPhase_ clickPhase = clickPhase_.CURR_PHASE;
    private int clickCounter = 0;
    private final JPanel gui = new JPanel(new BorderLayout(3, 3));
    private boolean is_white = true;
    private int blackFCount = 8, whiteFCount = 8;
    private boolean is_over = false;
    private String winner;
    private boolean has_toolbar = false;
    private boolean keepWaiting = true;
    
    final private JButton[][] bsSquares = new JButton[8][8];
    final private Pawn stone = new Pawn();
    final private JLabel lbl_color = new JLabel("White Turn");
    final private JLabel lbl_phase = new JLabel("Selecting a stone...");
    final private JPanel chessBoard= new JPanel (new GridLayout(0, 9));// +1 for Char and Num;
    final private String userDirectory  = System.getProperty("user.dir");
    final private String blackPawnImg = userDirectory + "\\src\\Bauerschach\\Graphics\\blackPawn.png";
    final private String whitePawnImg = userDirectory + "\\src\\Bauerschach\\Graphics\\whitePawn.png";
    
    /**
    * <p> constructor of the class {@link Game} </p>
    * install the GUI when called 
    */
    Game(){
        initGui();
    }
    
    /**
    * <p> Change the value of the object {@link Game#keepWaiting} </p>
    * @param w the new value 
    */
    public void setWait(boolean w){
        keepWaiting = w;
    }
    
    /**
    * <p> Get the value of the object {@link Game#keepWaiting} </p>
    * @return the value of {@link Game#keepWaiting}
    */
    public boolean getWait(){
        return keepWaiting;
    }
    
    /**
    * <p> Set the name of the first Player </p>
    * @param name The name of the player
    */
    public void setP1Name(String name){
        p1_name = name;   
    }
    
    /**
     * <p> Set the name of the second Player </p>
     * @param name The name of the player
     */   
    public void setP2Name(String name){
        p2_name = name;   
    }
    
    /**
    * <p> Install the GUI </p>
    * mainly used for installing the GUI at the very beginning. All the Labels, 
    * Buttons and thier images will be installed in way to make the game playable.
    * this method has also been used in {@link Game#newGame()}
    */ 
    public final void initGui(){
        Insets buttonMargin = new Insets(0,0,0,0);
        String bsChar = "ABCDEFGH";
        ImageIcon icon;
        
        // Color Settings
        Color brown = new Color(204, 102, 0); 
        Color white = new Color(255, 204, 51); //it's yellow more than white...
        
        if (has_toolbar == true){
            gui.removeAll();
        }
        
        chessBoard.setBorder(new LineBorder(brown));
        setStartPosition();
        gui.add(chessBoard); //Add the JPanel to the gui

        // Creating a new JToolBar
        JToolBar tools = new JToolBar();
        JButton newGame = new JButton("New Game");
        JButton rename = new JButton("Change The Names!");

        // Adding the JToolBar to the top of the window
        gui.add(tools, BorderLayout.PAGE_START);
        
        // Adding the new tools
        tools.add(newGame); 
        tools.add(rename); 
        tools.add(lbl_color);
        tools.add(lbl_phase);

        lbl_color.setFont(lbl_color.getFont().deriveFont(lbl_color.getFont().getStyle() | Font.BOLD));

        Border border = lbl_color.getBorder();
        Border margin = new EmptyBorder(10,10,10,10);
        Border smlrMargin = new EmptyBorder(5,5,5,5);
        
        // Changing the margins of the labels
        if (has_toolbar == false){       
            lbl_color.setBorder(new CompoundBorder(border, margin));
            lbl_phase.setBorder(new CompoundBorder(border, smlrMargin));
        }
        
        // add an ActionListener for the main window
        newGame.addActionListener(new ActionListener() { 
            @Override public void actionPerformed(ActionEvent e) { 
                newGame();
            } 
        });
        
        // add an ActionListener for the names window
        rename.addActionListener(new ActionListener() { 
            @Override public void actionPerformed(ActionEvent e) { 
                rename();
            } 
        });


        // add every single buttons
        for (int i = 0; i < bsSquares.length ; i++) //y
        {
            for (int j = 0 ; j < bsSquares[i].length ; j++) //x
            {
                JButton tempBut = new JButton();
                
                tempBut.setMargin(buttonMargin);
                
                try{
                if (i == 1){ // Position for the balck pawns
                    icon = new ImageIcon( ImageIO.read( new File(blackPawnImg)));
                    tempBut.setName("B");
                }
                else if (i == 6){ // Position for the white pawns
                    icon = new ImageIcon( ImageIO.read( new File(whitePawnImg)));
                    tempBut.setName("W");
                }
                else { // Empty position
                    icon = new ImageIcon(new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));
                    tempBut.setName("-");
                }
                
                tempBut.setIcon(icon);
                }
                catch (IOException ex) {
                    Logger.getLogger(Shell.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                // Set the background color of the buttons
                if ((j % 2 == 1 && i % 2 == 1) || (j % 2 == 0 && i % 2 == 0))
                    tempBut.setBackground(white);
                else 
                    tempBut.setBackground(brown);
 
                bsSquares[j][i] = tempBut;
                bsSquares[j][i].addActionListener(this);
            }
        }
        
        chessBoard.add(new JLabel(""));
        // fill the top row
        for (int i = 0; i < 8; i++) {
            chessBoard.add(
                    new JLabel(bsChar.substring(i, i + 1),
                    SwingConstants.CENTER));
        }
        
        // Add the buttons and num on the left side
        for (int i = 0; i < 8; i++) { // y
            for (int j = 0; j < 8; j++) { // x
                switch (j) {
                    case 0:
                        chessBoard.add(new JLabel("" + (i + 1),
                                SwingConstants.CENTER));
                    default:
                        chessBoard.add(bsSquares[j][i]);
                }
            }
        }
        has_toolbar = true;
    }
    
 /**
 * <p> return {@link Game#gui} </p>
 * @return {@link Game#gui} which is the main window
 */ 
    public final JComponent getGui(){
        return gui;
    }    
    
/**
 * <p> the main stone mover </p>
 * The function will be used in {@link Game#actionPerformed} only when the player
 * give two different positions, one for the selected stone and the other one for 
 * the target position.
 * A lot of cases will be checked to see if the move is legal before applying
 * the new coordination
 * 
 * @param is_white true if the pawn is white, false on otherwise
 * @return Object from the class {@link Piece} with the new coordination
 */ 
    public Piece onUpdateGUI(boolean is_white){
        Piece f = new Piece();
        int newX, newY, currX, currY;
        newX = getTargTempX();
        newY = getTargTempY();
        currX = getCurrTempX();
        currY = getCurrTempY();
        
        // The selected square is empty
        if(clickPhase == clickPhase_.CURR_PHASE){
            if (!is_white && bsSquares[currX][currY].getName().equals("B") == false ||
                 is_white && bsSquares[currX][currY].getName().equals("W") == false){
                JOptionPane.showMessageDialog(null,"The selected square is either empty or it contains an opponent pawn!", "Error", JOptionPane.ERROR_MESSAGE);
                return null;
            }
        }
        // the wrong Pawn has been selected (white turn)
        if (is_white){
            if (bsSquares[currX][currY].getName().equals("B")){
                JOptionPane.showMessageDialog(null,"It's not your turn yet!", "Error", JOptionPane.ERROR_MESSAGE);
                System.out.println("It's not your turn yet!");
                return null;
            }
        }
        // the wrong Pawn has been selected (Black turn)
        else {
            if (bsSquares[currX][currY].getName().equals("W")){
                JOptionPane.showMessageDialog(null,"It's not your turn yet!", "Error", JOptionPane.ERROR_MESSAGE);
                System.out.println("It's not your turn yet!");
                return null;
            }
        }
        
        if (currY == 6 && is_white) // White Pawn
           f.setFirstMove(true); // Enable the first move ability
        else if (currY == 1 && is_white == false) //Black Pawn
           f.setFirstMove(true); // Enable the first move ability
        
        String testWith;
        if (is_white)
            testWith = "B";
        else 
            testWith = "W";

        // The target position is illegal
        if (currX +2 == newX || currX -2 == newX){
            if (currY +1 == newY || currY -1 == newY){
                JOptionPane.showMessageDialog(null,"this is a pawn not a knight...", "Error", JOptionPane.ERROR_MESSAGE);
                System.out.println("this is a pawn not a knight...");
            }
            else{
                JOptionPane.showMessageDialog(null,"Can't move two Steps to left / right.", "Error", JOptionPane.ERROR_MESSAGE);
                System.out.println("Can't move two Steps to left / right");
            }
            return null;
        }
        
        // The target position is illegal
        if (currY + 2 == newY && currX +1 == newX 
                || currY - 2 == newY && currX -1 == newX ){
            JOptionPane.showMessageDialog(null,"this is a pawn not a knight...", "Error",JOptionPane.ERROR_MESSAGE);
            System.out.println("this is a pawn not a knight...");
            return null;
        }
        
        // The target position MIGHT be legal
        if ( (currY -1 == newY  && bsSquares[newX][newY].getName().equals(testWith)) ||
                (currY -2 == newY && currX == newX)||
                (currY +1 == newY && bsSquares[newX][newY].getName().equals(testWith)) ||
                (currY +2 == newY && currX == newX) ||
                (currY == newY) ) {

            // The target position is illegal
            if (bsSquares[newX][newY].getName().equals("B") &&
                    currX == newX ||
                    bsSquares[newX][newY].getName().equals("W") &&
                    currX == newX )
            {
                JOptionPane.showMessageDialog(null,"Can't attack the opponent / same stone!", "Error" ,JOptionPane.ERROR_MESSAGE);
                System.out.println("Error: Can't attack the opponent / same stone!");
                return null;
            }
            // Applying the new position
            f.setPositionX(newX);
            f.setPositionY(newY);

            // Applying the current position
            f.setCurrentY(currY);
            f.setCurrentX(currX);
        }
        // The target position is surely legal
        else if ( currX == newX && currY -1 == newY && is_white && bsSquares[newX][newY].getName().equals("-") 
                ||currX == newX && currY +1 == newY && !is_white && bsSquares[newX][newY].getName().equals("-"))
        {
            // Applying the new position
            f.setPositionX(newX);
            f.setPositionY(newY);

            // Applying the current position
            f.setCurrentY(currY);
            f.setCurrentX(currX);
        }
        // The target position dosen't make sence!
        else {
            JOptionPane.showMessageDialog(null,"Values might be too hight / low!", "Error" ,JOptionPane.ERROR_MESSAGE);
            System.out.println("Error:  values might be too hight / low!");
            return null;
        }
        // Remove the values from targTempx and targTempY
        setTargTempx(-1);
        setTargTempy(-1);
        setCurrentTempx(-1);
        setCurrentTempy(-1);
        return f;
    }
    
    
/**
* <p> The main Event Handler </p>
* react on every clicks on every square, deciding when to start moving the stones
* depending on the phase, update the gui if the operation has succeeded
* @param ae needed to get the source of the action
*/ 
    @Override public void actionPerformed (ActionEvent ae){
        Piece f = new Piece();
        if (is_over)
        {
            JOptionPane.showMessageDialog(null,"Game is over! Please start a new game!", "Error" ,JOptionPane.ERROR_MESSAGE);
            System.out.println("Game is over! Please start a new game!");
            return;
        }
        for (int i = 0; i < 8; i++) { // y
            for (int j = 0; j < 8; j++) { // x
                if(ae.getSource() == this.bsSquares[j][i]){
                    switch(clickPhase)
                    {
                        case CURR_PHASE:
                            setCurrentTempx(j);
                            setCurrentTempy(i);
                            clickPhase = clickPhase_.TARG_PHASE;
                            System.out.println("curr x=" + getCurrTempX() + " y=" + getCurrTempY());
                            clickCounter++;
                            lbl_phase.setText("Selecting a new position!");
                            
                            break;

                        case TARG_PHASE: 
                            setTargTempx(j);
                            setTargTempy(i);
                            clickPhase = clickPhase.CURR_PHASE;
                            System.out.println("targ x=" + getTargTempX() + " y=" + getTargTempY());
                            clickCounter++;
                            lbl_phase.setText("Selecting a stone...");
                            break;
                    }
                    if (clickCounter == 2)
                    {
                        f = onUpdateGUI(is_white);
                        if (f == null){
                            clickCounter = 0;
                            break;
                        }

                        if (stone.moveStoneGUI(f, is_white) == false){
                            clickCounter = 0;
                            break;
                        }

                        if (is_white) { //Start the changes of GUI
                            if (f.position_x == arL_black.get(f.position_y).getCurrentX() && f.position_y == arL_black.get(f.position_y ).getCurrentY() )
                            {
                                stone.removeStone(arL_black.get(f.position_y ));
                                blackFCount--;
                            }
                            bsSquares[f.current_x][f.current_y].setName("-");
                            bsSquares[f.current_x][f.current_y].setIcon(new ImageIcon(new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB)));
                            arL_white.get(f.position_y).setCurrentX(f.position_x);
                            arL_white.get(f.position_y).setCurrentY(f.position_y);

                            bsSquares[f.position_x][f.position_y].setName("W");
                            try{
                                Icon icon = new ImageIcon( ImageIO.read( new File(whitePawnImg)));
                                bsSquares[f.position_x][f.position_y].setIcon(icon);
                            }
                            catch(IOException ex){
                                Logger.getLogger(Shell.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            arL_white.get(f.position_y).setFirstMove(false);
                        }
                        // Black stone has been moved
                        else if (is_white == false) { //Start the changes of GUI
                            if (f.position_x == arL_white.get(f.position_y ).getCurrentX() && f.position_y == arL_white.get(f.position_y ).getCurrentY() )
                            {
                                stone.removeStone(arL_white.get(f.position_y ));
                                whiteFCount--;
                            }
                            bsSquares[f.current_x][f.current_y].setName("-");
                            bsSquares[f.current_x][f.current_y].setIcon(new ImageIcon(new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB)));
                            arL_black.get(f.position_y).setCurrentX(f.position_x);
                            arL_black.get(f.position_y).setCurrentY(f.position_y);
                            
                            bsSquares[f.position_x][f.position_y].setName("B");
                            try{
                                Icon icon = new ImageIcon( ImageIO.read( new File(blackPawnImg)));
                                bsSquares[f.position_x][f.position_y].setIcon(icon);
                            }
                            catch(IOException ex){
                                Logger.getLogger(Shell.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            arL_black.get(f.position_y).setFirstMove(false);
                       }

                        if (is_white)
                            System.out.println("Spieler 1 (W)");
                        else
                            System.out.println("Spieler 2 (B)");
                        
                        if (gameOver(f) == true)
                        {
                            getWinner(f);
                        }
                        clickCounter = 0;
                        is_white = !is_white;
                        if (is_white){
                            lbl_color.setText("White Turn");
                        }
                        else{
                            lbl_color.setText("Black Turn");
                        }
                    }
                }
            }
        }
    }
    
    /**
    * <p> Call it to start a new game </p>
    * Resting the important objects for fresh new start and reinstalling the GUI
    */ 
    private void newGame(){
        int dialogResult = JOptionPane.showConfirmDialog (null,"Are you sure?", "Confirmation" ,JOptionPane.OK_CANCEL_OPTION);
        if(dialogResult == JOptionPane.OK_OPTION){
            chessBoard.removeAll();
            is_white = true;
            is_over = false;
            initGui();
            chessBoard.setVisible(true);
        }
    }
    
    /**
    * <p> Rename the Players </p>
    * Open the name window to rechange the names of the players (it's also fine to 
    * make changes on only one of them)
    */ 
    private void rename(){
        NameWin nw = new NameWin();
        NameWin.NameWinInteractive nWinInter = nw.new NameWinInteractive();
        
        nw.txtSpieler1.setText(p1_name);
        nw.txtSpieler2.setText(p2_name);
        nw.jButton1.setText("Confirm");        

        java.awt.EventQueue.invokeLater(new Runnable() {
        @Override public void run() {
            nw.setVisible(true);
        }
        });

        do{
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(Shell.class.getName()).log(Level.SEVERE, null, ex);
            }
        }while (nw.isVisible() == true);

        String[] ss = nWinInter.getNames();
        p1_name = ss[0];
        p2_name = ss[1];
    }    

    /**
    * <p> get the color of the next player</p>
    * @return String that contains the color name of the next player
    */ 
    public String getPlayer(){
        if (is_white) // white at the moment?
            return "Black"; // Black is next!
        else
            return "White";       
    }
    
    /**
    * <p> get the winner name of the game</p>
    * check if the owner of the given stone has won, inform the players using 
    * MessageDialog if the game has been finished
    * 
    * @param f the piece to check if it has reached the end line
    * @return the name of winner
    */ 
    public String getWinner(Piece f){
        if (blackFCount == 0
            || is_white && f.position_y == 0){
            JOptionPane.showMessageDialog(null," GAME OVER \nPlayer 1 is the winner!", "Information" ,JOptionPane.INFORMATION_MESSAGE);
            System.out.println("!!! Player 1 is the winner !!!");
            winner = p1_name;
        }
        else if (whiteFCount == 0
            || !is_white && f.position_y == 7){
            JOptionPane.showMessageDialog(null," GAME OVER \nPlayer 2 is the winner!", "Information" ,JOptionPane.INFORMATION_MESSAGE);
            System.out.println("!!! Player 2 is the winner !!!");
            winner = p2_name;
        }
        return winner;
    }
    
    /**
    * <p> Check if the game is over or not</p>
    * 
    * @param f the piece to check if it has reached the end line
    * @return true if the game is over, false on otherwise
    */ 
    public boolean gameOver(Piece f){
        if (blackFCount == 0 || whiteFCount == 0
        || is_white && f.position_y == 0
        || !is_white && f.position_y == 7)
        {
            System.out.println("!!! GAME OVER !!!");
            is_over = true;
            return true;
        }
        else 
            return false;
    }
    
    /**
    * <p> the main function of the game</p>
    * 
    * @param args the command line arguments
    */ 
    public static void main (String[] args){
        PawnChess bs = new PawnChess();
        
        NameWin nw = new NameWin();
        NameWin.NameWinInteractive nWinInter = nw.new NameWinInteractive();

        
        java.awt.EventQueue.invokeLater(() -> {
            nw.setVisible(true);
        });

        do{
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(Shell.class.getName()).log(Level.SEVERE, null, ex);
            }
        }while (nw.isVisible() == true);

        String[] ss = nWinInter.getNames();
        bs.p1_name = ss[0];
        bs.p2_name = ss[1];
        //Debug...
        System.out.println(bs.p1_name);
        System.out.println(bs.p2_name);
        
        java.awt.EventQueue.invokeLater(() -> {
            Game gui1 = new Game();
            JFrame frame1 = new JFrame("Chess Game");
            frame1.add(gui1.getGui());
            frame1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame1.setLocationByPlatform(true);
            frame1.pack(); // Pack all the elements in the window
            frame1.setMinimumSize(frame1.getSize());
            frame1.setVisible(true);
        });       
    }
}
