/**
 *  The class takes a board in and in turn sends it to coresponding places so that a score can be 
 *  returned. 
 * 
 *  @author Alex Peschel
 */
package game.scoring;

import game.gameBoard.boardCreation.Tiles;
import game.language.Language;


public class Scoring {
    private CalculateScore calculateScore = new CalculateScore();
    private CheckWords checkWords = new CheckWords();
    /**
    * Will call create and call on functions within
    *      - CheckWords. to get every word within the board compared with a dictionary
    *      - CalculateScore. get the score corolating to the specific gamemode
    */
    public int Score(Tiles[][] board, int mode, Language language){
        int score = calculateScore.GetScore(checkWords.checkWords(board, language), mode, language);
        return score;
    }
}
