/** 
 *  The class manage the sorting of an array in ascending order. This is so that the 
 *  when the game is over the players placement will be written out in an ascending order.
 * 
 *  @author Alex Peschel 
 */
package tools;

import java.util.ArrayList;
import java.util.Collections;
import player.AbstractPlayer;

public class SortArray {

    /**
     * Use a select-sort to sort the list  
     */ 
    public ArrayList<AbstractPlayer> Sorting (ArrayList<AbstractPlayer> playerList){
        for(int pointer = 0 ; pointer <playerList.size() ; pointer++){
            for(int check = pointer ;  check < playerList.size() ; check++){
                if(playerList.get(pointer).CompareTo(playerList.get(check)) >= 0 ){
                    Collections.swap(playerList, pointer, check);                   
                }
            }
        }
        return playerList;
    } 
}
