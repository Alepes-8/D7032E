/** 
 *  The class Settings is made to inisiate the settings for the game, but also 
 *  give the possibility to change them. 
 * 
 *  The settings is categorized as integers, even language. This is so that i 
 *  can be easier to change language and change the setting accordingly.
 *  
 * @author Alex Peschel 
 */
package gamehandler;

import tools.StringToInt;

public class Settings {
    private int[][] settings = new int[5][2];

    public void InitiateSettings(){
        settings[0][0] = 1; //number of Player
        settings[1][0] = 1; //number of bots
        settings[2][0] = 5; //rows
        settings[2][1] = 5; //columns
        settings[3][0] = 0; //language
    }
    
    public void ChangeSettings(int change, String input){
        if(change == 3){
            int [] values = StringToInt.SplitString(input);
            settings[2][0] = values[0];
            settings[2][1] = values[1];
        }else{
            int value=StringToInt.ConvertString(input); 
            settings[change-1][0] = value;
        }
    }

    public int[][] GetSettings(){
        return settings;
    }
}
