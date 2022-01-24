/**
 *  This abstract class manages abstract classes for the gamemode, such as the process in
 *  the game follows. This is used by modes so that code isn't repeated unnecessarily but can be
 *  overwriten in the case where they needed another process or scoring.
 * 
 *  Having it in an abstract class makes it easier for future modifications, and one needed to implement
 *  a new feature within every game one can write it once instead of for every seperate modes class.
 * 
 *  @author Alex Peschel
 */
package game.gameMode;

import java.util.ArrayList;
import UI.BoardUI;
import game.GameSetup;
import game.gameMode.phases.PickPhase;
import game.gameMode.phases.PlacePhase;
import game.scoring.Scoring;
import player.AbstractPlayer;
import tools.FullBoard;
import tools.NextPlayer;
import tools.SortArray;
import tools.TieOrNot;

public class AbstractMode {
    protected AbstractPlayer currentPlayer;
    protected FullBoard end = new FullBoard();
    protected BoardUI boardUI = new BoardUI();
    protected Scoring scoring = new Scoring();
    protected TieOrNot checkTie = new TieOrNot();
    protected SortArray sortArray = new SortArray();
    protected NextPlayer calculatePlayer = new NextPlayer();
    protected PickPhase pickPhase = new PickPhase();
    protected PlacePhase placePhase = new PlacePhase();

    /**
     * Within GameProcess it uses and calls on functions within
     *      - PickPhase
     *      - PlacePhase
     */
    public void GameProcess(GameSetup setup, boolean scrabbleMode )  {
        currentPlayer = setup.players.get(setup.GetRandomStarter());
        while(true){
            String tempLetter;
            String letter="";
            for(AbstractPlayer player : setup.players){
                tempLetter= pickPhase.PickLetter(setup, currentPlayer, scrabbleMode, player, setup.boardList.get(player.GetPlayerID()));
                if(tempLetter!=null){ // this is so that the only letter saved is from the currentPlayer.
                    letter = tempLetter; 
                }
            }
            placePhase.PlaceLetter(setup, letter, scrabbleMode);
            this.currentPlayer = setup.players.get((calculatePlayer.DecideNextPlayer(currentPlayer.GetPlayerID(), setup.players.size())));
            if (end.EndGame(setup.boardList.get(0))){ 
                break;
            }
        }
    }

    /**
     * Will call on
     *      - Scoring, to calculate each players score
     *      - Player, to write out the board once more for each individual player.
     */
    public void CalculateScore(GameSetup setup , int mode , boolean scrabbleMode) {
        setup.players.forEach((player) -> player.SendMessage(boardUI.PrintBoard(setup.boardList.get(player.GetPlayerID()), scrabbleMode) + "\n")); 
        setup.players.forEach((player) -> player.SetScore(scoring.Score(setup.boardList.get(player.GetPlayerID()), mode, setup.language)));

        String winnerMsg;
        ArrayList<AbstractPlayer> finalPlayerList = sortArray.Sorting(setup.players);
        if(checkTie.CheckTie(finalPlayerList)){
            winnerMsg = "Winner: Tie between players, no winner \n";
        }else{
             winnerMsg = "Winner: PlayerID "+ finalPlayerList.get(0).GetPlayerID()+ ", Scores: " + finalPlayerList.get(0).GetScore() +"\n";
        }

        for(AbstractPlayer player : setup.players) {winnerMsg += "PlayerID " + player.GetPlayerID() + " Score " + player.GetScore() + "\n";}
        for(AbstractPlayer player : setup.players) {player.SendMessage(winnerMsg);}  
    }
}
