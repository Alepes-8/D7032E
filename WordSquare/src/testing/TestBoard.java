package testing;

import game.gameBoard.Board;
import game.gameBoard.boardCreation.Tiles;

public class TestBoard {
    private Board createBoard = new Board();
    
    public Tiles[][] TestBoardInit(boolean scrabbleValue, int mode){
        int[] size = new int[]{5,5};
        return createBoard.setup(size, scrabbleValue, mode);
    }
}
