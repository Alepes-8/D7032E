/**
 *  A class created for the standard gamemode. to prevent repeating code it is extending the abstractmode.
 *  Having the standard gamemode localised gives the ease for future modifications. 
 * 
 * @author Alex Peschel
 */
package game.gameMode.modes;

import game.GameSetup;
import game.gameMode.AbstractMode;

public class Standard extends AbstractMode{
    private boolean scrabbleMode = false;

    public Standard(GameSetup setup, int convertedMode){
        GameProcess(setup, scrabbleMode);
        CalculateScore(setup, convertedMode, scrabbleMode);
    } 
}
