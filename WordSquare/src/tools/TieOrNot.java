/**
 *  This class check if there is or isn't one winner, if their is more than one than 
 *  there will be resulting in a tie. 
 * 
 *  @author Alex Peschel
 */
package tools;

import java.util.ArrayList;
import player.AbstractPlayer;

public class TieOrNot {
    
    public boolean CheckTie(ArrayList<AbstractPlayer> playerList){
        if(playerList.size()<=1){
            return false;
        }
        else if(playerList.get(0).GetScore() == playerList.get(1).GetScore()){
            return true;
        }
        return false;
    }
}
