/**
 *  This class is made to create the players board, firstly a Then the normal board without 
 *  extra special tiles so that it can if needed build either a pre-Built 5x5 scrabble board, or
 *  a self detemained size board where it has randomized placements for the scrabbleMode.
 * 
 *  It is located  in one place so that one can add new type of creation board with ease.
 * 
 *  @author Alex Peschel
 */
package game.gameBoard;

import game.gameBoard.boardCreation.PreScrabbleBoard;
import game.gameBoard.boardCreation.RandomScrabbleBoard;
import game.gameBoard.boardCreation.StandardBoard;
import game.gameBoard.boardCreation.Tiles;

public class Board {
    private StandardBoard standardBoard = new StandardBoard();
    private PreScrabbleBoard preScrabbleBoard = new PreScrabbleBoard();
    private RandomScrabbleBoard randomScrabbleBoard = new RandomScrabbleBoard();
                    
    public Tiles[][] setup(int[] boardSize, boolean scrabValue, int mode) {
        Tiles[][] board = new Tiles[boardSize[0]][boardSize[1]];
        
        board = standardBoard.CreateStandardBoard(board, boardSize, scrabValue);
        if(scrabValue == true){
            if(mode == 3){
                board = preScrabbleBoard.CreatPreScrabbleBoard(board);
            }else if(mode==4){
                board = randomScrabbleBoard.CreateRandomScrabBoard(board, boardSize);
            }
        }
        return board;
    }
}
