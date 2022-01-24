/**
 *  A class created for the scrabble gamemode. to prevent repeating code it is extending the abstractmode.
 *  Having the scrabble gamemode localised gives the ease for future modifications. 
 * 
 * @author Alex Peschel
 */
package game.gameMode.modes;

import game.GameSetup;
import game.gameMode.AbstractMode;

public class Scrabble extends AbstractMode{
    private boolean scrabbleMode = true;

    public Scrabble(GameSetup setup, int convertedMode){
        GameProcess(setup, scrabbleMode);
        CalculateScore(setup, convertedMode, scrabbleMode);
    }
}
