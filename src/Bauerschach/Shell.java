package Bauerschach;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


/**
 * <p> The class has been created for console interface </p>
 * 
 * @deprecated
 * the main class for the console interface which has been deactivated.
 * The class has been replaced with {@link Game} for more modern Interface
 * @author Mohammad Ramadan
 * @version 1.0
 */
public class Shell extends GUI{
    public String p1_name;
    public String p2_name;
    public boolean keepWaiting = true;
    
    public void setWait(boolean w){
        keepWaiting = w;
    }
    
    public boolean getWait(){
        return keepWaiting;
    }
    
    public Piece onUpdate(boolean is_white, String[][] stoneOnLayout) {
        int newX, newY, currX, currY;
        String testWith;
        Piece p = new Piece();
        Scanner scanner = new Scanner(System.in);

        System.out.println("currX ?");
        currX = scanner.nextInt();

        System.out.println("currY ?");
        currY = scanner.nextInt();

        System.out.println("newX ?");
        newX = scanner.nextInt();

        System.out.println("newY ?");
        newY = scanner.nextInt();

        if (is_white)
            testWith = " B";
        else 
            testWith = " W";

        if (currY -1 == newY && stoneOnLayout[newX][newY].equals(testWith) || 
                currY +1 == newY && stoneOnLayout[newX][newY].equals(testWith) ||
                currY == newY && stoneOnLayout[newX][newY].equals(" -") ) {

            p.setPositionX(newX);
            p.setPositionY(newY);

            p.setCurrentY(currY);
            p.setCurrentX(currX);
        }
        else {
            System.out.println("Error: Y value might be too hight / low!");
            return null;
        }
        return p;
    }

    public void test(){
        System.out.println(p1_name);
        System.out.println(p1_name);
    }
    // JFrame, java swing
    // extends JFrame
    // as layout is Grid
     
    public static void main_(String[] args) 
    {
        String _stoneOnLayout[][] = new String[9][9];
        boolean gameOver = false;
        boolean is_white = true;
        int blackFCount = 8;
        int whiteFCount = 8;
        
        PawnChess b = new PawnChess();
        Pawn stone = new Pawn();
        Piece f = new Piece(); 
        
        Shell s = new Shell();
        ShellInter si = s.new ShellInter();
        NameWin nw = new NameWin();
        NameWin.NameWinInteractive nWinInter = nw.new NameWinInteractive();
        GUI gui = new GUI();
        //GUI.GUIInteractive guiInter = gui.new GUIInteractive();
        
        si.run();
        return;
/*
        //s.onStart();
        _stoneOnLayout = b.initBoard();
        b.setStartPosition();
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                nw.setVisible(true);
            }
        });
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException ex) {
            Logger.getLogger(Shell.class.getName()).log(Level.SEVERE, null, ex);
        }
        do{
        ;
        }while (nw.isVisible() == true);
        
        
        gui.setVisible(true);
        
        String[] ss = nWinInter.getNames();
        s.p1_name = ss[0];
        s.p2_name = ss[1];
        System.out.println(s.p1_name);
        System.out.println(s.p2_name);
        
        while (gameOver == false) {
            if (is_white)
                System.out.println("Spieler 1 (Weiss):");
            else
                System.out.println("Spieler 2 (Schwarz):");
            
            //do{
            //; //wait till click events!
            //}while (guiInter.getCurrTempX() <= 0 && guiInter.getTargTempX() <= 0);
            
            f = s.onUpdate(is_white, _stoneOnLayout);
            f = si.onUpdateGUI(is_white, _stoneOnLayout);
            if (f == null)
                continue;

            if (stone.moveStone(f, is_white) == false)
                continue;

            if (is_white) { //Start the changes of GUI
                if (f.position_x == b.arL_black.get(f.position_y).getCurrentX() && f.position_y == b.arL_black.get(f.position_y ).getCurrentY() )
                {
                    stone.removeStone(b.arL_black.get(f.position_y ));
                    blackFCount--;
                }
                b.stoneOnLayout[f.current_x][f.current_y] = " -";
                b.arL_white.get(f.position_y).setCurrentX(f.position_x);
                b.arL_white.get(f.position_y).setCurrentY(f.position_y);
                b.stoneOnLayout[f.position_x][f.position_y] = " W";
                b.arL_white.get(f.position_y).setFirstMove(false);
            }

            // Black stone has been moved
            else if (is_white == false) { //Start the changes of GUI
                if (f.position_x == b.arL_white.get(f.position_y ).getCurrentX() && f.position_y == b.arL_white.get(f.position_y ).getCurrentY() )
                {
                    stone.removeStone(b.arL_white.get(f.position_y ));
                    whiteFCount--;
                }
                b.stoneOnLayout[f.current_x][f.current_y] = " -";
                b.arL_black.get(f.position_y).setCurrentX(f.position_x);
                b.arL_black.get(f.position_y).setCurrentY(f.position_y);
                b.stoneOnLayout[f.position_x][f.position_y] = " B";
                b.arL_black.get(f.position_y).setFirstMove(false);
            }
            
            // Remove the curr icon AND make icon in targPos
            b.showBoard();
            if (whiteFCount == 0 || blackFCount == 0)
                gameOver = true;
            is_white = !is_white;
        }
        System.out.println("Spiel ist vorbei!");

        if (whiteFCount > blackFCount)
            System.out.println("Gewinner ist Spieler 1 !!!");

        else if (whiteFCount < blackFCount)
            System.out.println("Gewinner ist Spieler 2 !!!");

        //System.out.println("Programm Ende in 3 sec");
        //TimeUnit.SECONDS.sleep(3);
        return;	
*/
    }

    public void setP1Name(String name){
        p1_name = name;   
    }
    
    public void setP2Name(String name){
        p2_name = name;   
    }

    public void onStart() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bitte geben Sie name den Spieler 1");
        p1_name = scanner.next();

        System.out.println("Bitte geben Sie name den Spieler 2");
        p2_name = scanner.next();
        //scanner.close();
    }
    
    
/**
 * 
 * @deprecated
 * Inner class of {@link Shell} which is deprecated, therefore is {@link  ShellInter}
 * also deprecated
 * @author Mohammad Ramadan
 * @version 1.0
 */
    public class ShellInter extends GUI{    
        
        public String[][] getCurrentPos(){
            return guiInter.stoneOnLayout;
        }
        public String[][] getTargetPos(){
            return guiInter.stoneOnLayout;
        }
       
        public Piece onUpdateGUI(boolean is_white, String[][] stoneOnLayout) {
            int newX, newY, currX, currY;
            String testWith;
            //Bauernschach b = new PawnChess();
            Piece f = new Piece();
            currX = guiInter.currTempX;
            currY = guiInter.currTempY;
            newX = guiInter.targTempX;
            newY = guiInter.targTempY;

            if (is_white)
               testWith = " B";
            else 
               testWith = " W";

            if (currY -1 == newY && stoneOnLayout[newX][newY].equals(testWith) || 
                   currY +1 == newY && stoneOnLayout[newX][newY].equals(testWith) ||
                   currY == newY && stoneOnLayout[newX][newY].equals(" -") ) {

                f.setPositionX(newX);
                f.setPositionY(newY);

                f.setCurrentY(currY);
                f.setCurrentX(currX);
            }
            else {
                JOptionPane.showMessageDialog(null,"Error: Y value might be too hight / low!", "Error" ,JOptionPane.ERROR_MESSAGE);
                System.out.println("Error: Y value might be too hight / low!"); 
                f = null; // mean Error!
            }
            guiInter.currTempX = -1;
            guiInter.currTempY = -1;
            guiInter.targTempX = -1;
            guiInter.targTempY = -1;
            return f;
       }
        
        public void run (){
            String _stoneOnLayout[][] = new String[9][9];
            boolean gameOver = false;
            boolean is_white = true;
            int blackFCount = 8;
            int whiteFCount = 8;

            //Bauernschach b = new PawnChess();
            Pawn stone = new Pawn();
            Piece f = new Piece(); 

            Shell s = new Shell();
            NameWin nw = new NameWin();
            NameWin.NameWinInteractive nWinInter = nw.new NameWinInteractive();
            //GUI gui = new GUI();
            //GUI.GUIInteractive guiInter = gui.new GUIInteractive();
            
            //s.onStart();
            _stoneOnLayout = guiInter.initBoard();
            guiInter.setStartPosition();

            java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                    nw.setVisible(true);
                }
            });
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException ex) {
                Logger.getLogger(Shell.class.getName()).log(Level.SEVERE, null, ex);
            }
            do{
            ;
            }while (nw.isVisible() == true);

            String[] ss = nWinInter.getNames();
            s.p1_name = ss[0];
            s.p2_name = ss[1];
            System.out.println(s.p1_name);
            System.out.println(s.p2_name);

            while (gameOver == false) {
                if (is_white)
                    System.out.println("Spieler 1 (Weiss):");
                else
                    System.out.println("Spieler 2 (Schwarz):");

                
                java.awt.EventQueue.invokeLater(new Runnable() {
                    public void run() {
                        new GUI().setVisible(true);
                    }
                });
                
                do{
                ; //wait till click events!
                }while (guiInter.getCurrTempX() <= 0 && guiInter.getTargTempX() <= 0);

                f = s.onUpdate(is_white, _stoneOnLayout);
                f = onUpdateGUI(is_white, _stoneOnLayout);
                if (f == null)
                    continue;

                if (stone.moveStone(f, is_white) == false)
                    continue;

                if (is_white) { //Start the changes of GUI
                    if (f.position_x == guiInter.arL_black.get(f.position_y).getCurrentX() && f.position_y == guiInter.arL_black.get(f.position_y ).getCurrentY() )
                    {
                        stone.removeStone(guiInter.arL_black.get(f.position_y ));
                        blackFCount--;
                    }
                    guiInter.stoneOnLayout[f.current_x][f.current_y] = " -";
                    guiInter.arL_white.get(f.position_y).setCurrentX(f.position_x);
                    guiInter.arL_white.get(f.position_y).setCurrentY(f.position_y);
                    guiInter.stoneOnLayout[f.position_x][f.position_y] = " W";
                    guiInter.arL_white.get(f.position_y).setFirstMove(false);
                }

                // Black stone has been moved
                else if (is_white == false) { //Start the changes of GUI
                    if (f.position_x == guiInter.arL_white.get(f.position_y ).getCurrentX() && f.position_y == guiInter.arL_white.get(f.position_y ).getCurrentY() )
                    {
                        stone.removeStone(guiInter.arL_white.get(f.position_y ));
                        whiteFCount--;
                    }
                    guiInter.stoneOnLayout[f.current_x][f.current_y] = " -";
                    guiInter.arL_black.get(f.position_y).setCurrentX(f.position_x);
                    guiInter.arL_black.get(f.position_y).setCurrentY(f.position_y);
                    guiInter.stoneOnLayout[f.position_x][f.position_y] = " B";
                    guiInter.arL_black.get(f.position_y).setFirstMove(false);
                }

                // Remove the curr icon AND make icon in targPos
                guiInter.showBoard();
                if (whiteFCount == 0 || blackFCount == 0)
                    gameOver = true;
                is_white = !is_white;
            }
            System.out.println("Spiel ist vorbei!");

            if (whiteFCount > blackFCount)
                System.out.println("Gewinner ist Spieler 1 !!!");

            else if (whiteFCount < blackFCount)
                System.out.println("Gewinner ist Spieler 2 !!!");

            //System.out.println("Programm Ende in 3 sec");
            //TimeUnit.SECONDS.sleep(3);
            return;	
        }
        
    }
}