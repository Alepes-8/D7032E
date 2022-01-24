package testing;

import game.gameBoard.boardCreation.Tiles;

public class TestCheckIfEmpty {
    
    public boolean IsItEmpty(Tiles[][] board){
        for(int i = 0; i < board.length; i++){
            Tiles[] newBoard = board[i];
            for(int n = 0;  n < newBoard.length;n++){
                if(newBoard[n].letter!=""){
                    return false;
                }
            }
        }
        return true;
    }
    
}
