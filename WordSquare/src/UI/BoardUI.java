/**
 *  This class manages the printout of the board, giving the player a visual 
 *  understanding of their game. When neeeded the board will be colorful representing
 *  special tiles, such as when playing the gamemode Scrabble. 
 * 
 *  @author Alex Peschel
 */
package UI;

import game.gameBoard.boardCreation.Tiles;

public class BoardUI {
        private static final String REGULAR = "\u001B[47m\u001B[30m"; //white background black text
        private static final String DOUBLE_LETTER = "\u001B[46m\u001B[30m"; //cyan background black text
        private static final String TRIPPLE_LETTER = "\u001B[42m\u001B[30m"; //green background black text
        private static final String DOUBLE_WORD = "\u001B[43m\u001B[30m"; //yellow background black text
        private static final String TRIPPLE_WORD = "\u001B[45m\u001B[30m"; //magenta background black text
        private static final String HEADING = "\u001B[44m\u001B[37m"; //blue background white text
        private static final String RESET = "\u001B[0m"; //reset to default
        
    public String PrintBoard(Tiles[][] board, Boolean scrabbleMode){
        int asciiRowCount = 97;
        String retStr = "";
        for(int i=0; i<board[0].length; i++) {
            retStr += "\t"+RESET+HEADING+"  "+i+"  ";
        }
        for(Tiles[] cols : board) {
            retStr += "\t" + RESET +"\n"+HEADING + "  "+ ((char) asciiRowCount++)+"  "; 
            for(Tiles letter : cols) {
                String coloring = "";
                
                //if statments determaining the tiles color
                if(letter.tileType == Tiles.RL) {coloring = REGULAR;}
                if(letter.tileType == Tiles.DL) {coloring = DOUBLE_LETTER;}
                if(letter.tileType == Tiles.TL) {coloring = TRIPPLE_LETTER;}
                if(letter.tileType == Tiles.DW) {coloring = DOUBLE_WORD;}
                if(letter.tileType == Tiles.TW) {coloring = TRIPPLE_WORD;}
                String letterValue = (letter.letterValue == 0)?"     ":" ["+letter.letterValue+"]";
                String theLetter = "";
                if(letter.scrabbleValues) {
                    theLetter = letter.letter+letterValue;
                } else {
                    theLetter = "  "+(letter.letter.equals("")?" ":letter.letter)+"  ";
                }
                retStr += "\t" + RESET + coloring + theLetter;
            }
        }
        retStr += "\t";
        if(scrabbleMode) {
            retStr += RESET + "\n\n\t" +REGULAR + " STD \t" + RESET+DOUBLE_LETTER + " DL  \t" + RESET+TRIPPLE_LETTER + " TL  \t" + RESET+DOUBLE_WORD + " DW  \t" + RESET+TRIPPLE_WORD + " TW  \t" + RESET;
        }
        return retStr + RESET + "\n";
    }
}
