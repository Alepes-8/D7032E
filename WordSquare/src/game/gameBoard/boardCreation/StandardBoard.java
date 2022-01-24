/**
 *  The class StandardBoard manages the creation of a normal board with no special tiles or anthing else.
 * 
 *  @author Alex Peschel
 */
package game.gameBoard.boardCreation;

public class StandardBoard {
    
    public Tiles[][] CreateStandardBoard(Tiles[][] board, int[] boardSize, boolean scrabValue){
        for(int i = 0; i < board.length; i++){
            Tiles[] tiles = board[i];
            for(int n = 0; n < tiles.length ; n++){
                tiles[n] = new Tiles(scrabValue);
            }
        }
        return board;
    }
}
