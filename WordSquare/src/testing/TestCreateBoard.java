package testing;

import game.gameBoard.boardCreation.Tiles;
import game.language.Language;

public class TestCreateBoard {
    private Tiles[][] board;
    private TestBoard testBoard = new TestBoard();
    private String[][] fullBoard = {{"A", "C", "E", "D", "Q"},
                                    {"B", "P", "Q", "Q", "Q"},
                                    {"A", "C", "E", "Q", "Q"},
                                    {"Q", "Q", "Q", "Q", "Q"},
                                    {"A", "C", "E", "Q", "Q"}};

    public Tiles[][] GetBoard(Boolean scrabbleValue, int mode , Language language, String status){
        board =  testBoard.TestBoardInit(scrabbleValue, mode);
        if(status.equals("Full")){
            for(int row = 0; row < board.length; row++){
                for(int col = 0; col < board[row].length; col++){
                    board[row][col].letter = fullBoard[row][col];
                    board[row][col].letterValue = 1;
                }
            }
        }else if(status.equals("PartiallyFull")){
            for(int row = 0; row < board.length; row++){
                for(int col = 0; col < board[row].length-1; col++){
                    board[row][col].letter = fullBoard[row][col];
                    board[row][col].letterValue = 1;
                }
            }
        }
        return board;
    }
}
