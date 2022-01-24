/**
 *  A class manageing the startup of the correct gamemode dependent on the earlier input from 
 *  GameHandler. This is centered at one place so it will be easier for future modificastions 
 *  when adding new game modes. 
 * 
 *  @auther Alex Peschel
 */
package game;

import game.gameMode.modes.Scrabble;
import game.gameMode.modes.Standard;
import tools.StringToInt;

/**
 * The Game class creates 
 *      - GameSetup
 *      - AbstractMode Standard/Scrabble
 */
public class Game {
    private GameSetup setup;

    public void RunGame(int [][] settings, String mode){
        int convertedMode = StringToInt.ConvertString(mode);
        setup = new GameSetup(settings, convertedMode, null); 

        if (convertedMode == 1){//standard game mode
            new Standard(setup, convertedMode);
        }
        else if(convertedMode == 2 || convertedMode == 3 || convertedMode == 4){//scrabbel game mode            
            new Scrabble(setup, convertedMode);
        }
    }
}
