/**
 *  This class is called upon when creating a new board. THe tiles class is created for every position
 *  within the board. Here the informastion regarding once tiles is localy stored for ease of access and
 *  to lessen dependecies between other classes. 
 * 
 * @author Alex Peschel
 */
package game.gameBoard.boardCreation;

public class Tiles {
    public static final int RL = 1; //regular letter
    public static final int DL = 2; //double letter
    public static final int TL = 3; //tripple letter
    public static final int DW = 4; //double word
    public static final int TW = 5; //tripple word
        
    public static final String LETTER_VALUES = "1 point:  E A I O N R T L S U, 2 points: D G, 3 points B C M P\n" + 
                                                   "4 points: F H V W Y, 5 points: K, 8 points: J X, 10 points Q Z";

    public String letter = ""; 
    public int tileType = RL;
    public int letterValue = 0; //value depends on game-mode
    public boolean scrabbleValues = false;

    public Tiles(boolean scrabbleValues) { //regular mode constructor
        this(scrabbleValues, RL);
    }
    
    public Tiles(boolean scrabbleValues, int tileType) {
        this.scrabbleValues = scrabbleValues;
        this.tileType = tileType;    
    }

}
