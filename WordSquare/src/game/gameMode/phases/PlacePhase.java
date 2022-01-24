/**
 *  The PlacePhase class is here to makes sure that every player gets to place their own
 *  letter on their own board. 
 * 
 * @author Alex Peschel
 */
package game.gameMode.phases;

import game.GameSetup;
import game.gameBoard.boardCreation.Tiles;
import player.AbstractPlayer;
import tools.ThreadPool;

public class PlacePhase {
    ThreadPool threadPool = new ThreadPool();
    
    public void PlaceLetter(GameSetup setup, String letter, boolean scrabbleMode){
        threadPool.CreateThreadPool(setup.players.size());
        for(AbstractPlayer playerPlace : setup.players) {
            Runnable task = new Runnable() {
                @Override
                public void run() {
                    Tiles[][] newBoard = playerPlace.PlaceLetter(letter, setup.boardList.get(playerPlace.GetPlayerID()), scrabbleMode, setup.language.GetLetterValue());
                    setup.boardList.set(playerPlace.GetPlayerID(), newBoard);
                }
            };
             threadPool.CloseThreadPool(task);
         }
        threadPool.ShutdownThreadPool();
    }
}
