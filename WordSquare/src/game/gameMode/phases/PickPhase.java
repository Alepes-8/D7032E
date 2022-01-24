/**
 * This class manages getting the letter input from the right person in questions and send that letter back,
 * though it will always print out a board to the player.
 * 
 * @author Alex Peschel
 */
package game.gameMode.phases;

import UI.BoardUI;
import game.GameSetup;
import game.gameBoard.boardCreation.Tiles;
import player.AbstractPlayer;


public class PickPhase {
    BoardUI boardUI = new BoardUI();

    public String PickLetter(GameSetup setup, AbstractPlayer currentPlayer, Boolean scrabbleMode, AbstractPlayer player, Tiles[][] board){
        if(currentPlayer == player){
            // sends to the player the message of the boardUI and the string
            player.SendMessage(boardUI.PrintBoard(board, scrabbleMode) + "\nWaiting for a letter to be picked...\n");
            String letter = currentPlayer.PickLetter(scrabbleMode , setup.language.GetLetterValue()).toUpperCase(); 
            return letter;
        }else{
            player.SendMessage(boardUI.PrintBoard(board, scrabbleMode) + "\nWaiting for a letter to be picked...\n");
        }
        return null;        
    }  
}
