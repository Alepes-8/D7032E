/**
 *  If the player wish to have a scrabble board with randomised placements of the special 
 *  tiles and then a flexible board which can be any size within the wished specifiks, this class 
 *  will manage that assigment. With the larger size the corresponding amount of special 
 *  tiles will be created, instead of having the same amount of tiles no matter the size.
 * 
 *  @author Alex Peschel
 */
package game.gameBoard.boardCreation;

import java.util.Random;

public class RandomScrabbleBoard {
    
    public Tiles[][] CreateRandomScrabBoard(Tiles[][] board, int[] boardSize){
        int Clength, Rlength;
        Rlength = boardSize[0];
        Clength = boardSize[1];
        int[] tileTypes = GetTileTypes(boardSize);
        for(int tile : tileTypes) {
            Random rnd = new Random();
            int r, c;
            do {
                r = rnd.nextInt(Rlength); 
                c = rnd.nextInt(Clength);
            } while(board[r][c].tileType!=Tiles.RL);
            board[r][c].tileType = tile;
        }
        return board;
    }

    /**
     * 
     * The calculation regarding how many special tiles there should be comes 
     * from a predetermained one in PreScrabble. Here there is for exemple 3 DL 
     * tiles within 25 tiles leading to 12% will be DL tiles
     * 
     * @return a list of every special tile to be placed out on the board
     */
    private int[] GetTileTypes(int[] size){
        int blockCount = size[0] * size[1];
        int DLAmount = (int) Math.floor(blockCount * 0.12);
        int TLAmount = (int) Math.floor(blockCount * 0.08);
        int DWAmount = (int) Math.floor(blockCount * 0.12);
        int TWAmount = (int) Math.floor(blockCount * 0.04);
        int amountOfTiles = DLAmount + TLAmount +DWAmount + TWAmount;
        int[] tilesTypes = new int[amountOfTiles];
        tilesTypes = AddTiles(tilesTypes, DLAmount, Tiles.DL, 0);
        tilesTypes = AddTiles(tilesTypes, TLAmount, Tiles.TL, DLAmount);
        tilesTypes = AddTiles(tilesTypes, DWAmount, Tiles.DW, DLAmount+TLAmount);
        tilesTypes = AddTiles(tilesTypes, TWAmount, Tiles.TW, DLAmount+TLAmount+DWAmount);
        return tilesTypes;
    }

    private int[] AddTiles(int[] list, int amount, int type, int position){
        for(int i = 0; i < amount ; i++){
            list[position] = type;
            position = position + 1;
        }
        return list;
    }
}
