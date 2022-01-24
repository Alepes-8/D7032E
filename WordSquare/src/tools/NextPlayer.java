/** 
 *  This class takes out the next players ID. This is so that even
 *  when we get to the last player in the list we will go back to the 
 *  first instance of the list.
 * 
 *  @author Alex Peschel 
 */
package tools;

public class NextPlayer {
    
    public int DecideNextPlayer(int ID, int size){
        if(ID+1==size){
            return 0;
        }
        return ID+1; 
    }
}
