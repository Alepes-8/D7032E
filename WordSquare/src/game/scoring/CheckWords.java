/**
 *  This is a general class which takes out every word within the board, even
 *  words shorter than 3 when playing standard. This is so that it can be used by more than just one class.
 *  Than the words are stored and sent back for the scoring to be calculated. The words are taken out according
 *  to the already determined dictionary.
 *  
 *  @author Alex Peschel
 */
package game.scoring;

import java.util.ArrayList;

import game.gameBoard.boardCreation.Tiles;
import game.language.Language;

public class CheckWords {
    
    public ArrayList<Tiles[]> checkWords(Tiles[][] board, Language language) {
        ArrayList<Tiles[]> words = new ArrayList<Tiles[]>();

        //Check all possible combinations for each row
        for(Tiles[] columns : board) {
            for(int col=0; col<columns.length; col++) {
                ArrayList<Tiles> possibleWord = new ArrayList<Tiles>();
                String speledOut = new String();
                
                for(int i=col; i<columns.length; i++) {
                    speledOut = speledOut + columns[i].letter; // Spell out so that i can be compared with dictionary
                    possibleWord.add(columns[i]);   
                    String aWord = speledOut.replace("[", "").replace("]", "").replace(", ", "");
                    if(language.GetDictionary().contains(aWord)){
                        words.add(possibleWord.toArray(new Tiles[possibleWord.size()]));
                    }
                }
            }
        }
        
        //Check all possible combinations for each column
        for(int col = 0; col<board[0].length; col++) {
            Tiles[] rows = new Tiles[board.length];
            for(int row=0; row<rows.length; row++) {
                rows[row] = board[row][col];
            }
            for(int row=0; row<rows.length; row++) {
                ArrayList<Tiles> possibleWord = new ArrayList<Tiles>();
                String speledOut = new String();

                for(int i=row; i<rows.length; i++) {
                    possibleWord.add(rows[i]);
                    speledOut = speledOut + rows[i].letter;                    
                    String aWord = speledOut.replace("[", "").replace("]", "").replace(", ", "");                    
                    if(language.GetDictionary().contains(aWord)){
                        words.add(possibleWord.toArray(new Tiles[possibleWord.size()]));
                    }                                    
                }
            }
        }

        //Check downwards diagonals from left to right
        for(int col=0; col<board[0].length; col++) {
            for(int row=board.length-1; row>=0; row--) { //start bottom-left
                ArrayList<Tiles> possibleWord = new ArrayList<Tiles>();
                int r=row, c=col;
                String speledOut = new String();

                while(r<board.length && c<board[0].length) {
                    possibleWord.add(board[r][c]);
                    speledOut = speledOut + board[r][c].letter;                    
                    String aWord = speledOut.replace("[", "").replace("]", "").replace(", ", "");
                    if(language.GetDictionary().contains(aWord)) {
                        words.add(possibleWord.toArray(new Tiles[possibleWord.size()]));
                    }
                    r++; c++;
                }
            }
        }
        return words;
    }
}
