/** 
 *  FullBoard is a class which assignment is to check if a board is full or not 
 *  then return a false value if it is emtpy and true if it isn't. 
 * 
 *  Used when the game wanna se if it should continue to the next round or end.
 *  
 *  @author Alex Peschel 
 */
package tools;

import game.gameBoard.boardCreation.Tiles;

public class FullBoard {
    
    public boolean EndGame(Tiles[][] board) {
        for(int i = 0; i < board.length; i++){
            Tiles[] newBoard = board[i];
            for(int n = 0;  n < newBoard.length;n++){
                if(newBoard[n].letter==""){
                    return false;
                }
            }
        }
        return true;
    }
}
