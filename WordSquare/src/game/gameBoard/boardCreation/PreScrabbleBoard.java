/**
 *  This class manages the creation of a pre determained scrabble board, for a scrabble game mode. 
 *  This is so that the board can always be the same for everyone.
 * 
 *  @author Alex Peschel
 */
package game.gameBoard.boardCreation;

public class PreScrabbleBoard {
    private int PreMadeScrabbleBoard[][] = {{Tiles.DW, Tiles.RL, Tiles.TW, Tiles.RL, Tiles.DW},
                                             {Tiles.RL, Tiles.DL, Tiles.RL, Tiles.DL, Tiles.RL},
                                             {Tiles.TL, Tiles.RL, Tiles.TW, Tiles.RL, Tiles.TL},
                                             {Tiles.RL, Tiles.DL, Tiles.RL, Tiles.DL, Tiles.RL},
                                             {Tiles.DW, Tiles.RL, Tiles.TW, Tiles.RL, Tiles.DW}};
                                             
    public Tiles[][] CreatPreScrabbleBoard(Tiles[][] board){
        int rLength,cLength;
        rLength = 5;
        cLength = 5;
        for(int r = 0; r < rLength ; r++){
            Tiles[] tiles = board[r];
            for (int c = 0; c <cLength; c++){
                tiles[c] = new Tiles(true, PreMadeScrabbleBoard[r][c]);
            }
        }
        return board;
    }
}
