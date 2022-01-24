/** 
 *  AbstractPlayer is created to set an abstract class for players and bots to follow,
 *  so that there is defined functionalities. 
 *  
 *  @author Alex Peschel 
 */
package player;

import java.util.HashMap;

import game.gameBoard.boardCreation.Tiles;

public interface AbstractPlayer  {

    public String PickLetter(Boolean mode, HashMap<String, Integer> language);
    public Tiles[][] PlaceLetter(String letter, Tiles[][] board, boolean scrabbleMode, HashMap<String, Integer> letterValue);
    public void SendMessage(Object message);
    public String ReadMessage();
    public int GetPlayerID();
    public int GetScore();
    public void SetScore(int score);
    /**
     * Used to compare scores between the AbstractPlayer input and the called upon AbstractPlayer.
     */
    public int CompareTo(AbstractPlayer abstractPlayer);
}
