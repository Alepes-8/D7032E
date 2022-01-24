/**
 *  The SettingMenu takes the different responces regarding settings in the menu into
 *  one place. This way the code is easier to understand regarding what you can suspect 
 *  getting with every input and then edit it if needed.
 * 
 *  Dividing it into two part makes it easier calling it.
 * 
 *  @author Alex Peschel
 */
package UI;

import game.language.ListOfLanguages;

public class SettingsUI {
    
    public void SettingMenu( int[][] settings , ListOfLanguages language){
        System.out.println("****************************************************************\n" +
                                    " Current settings:\n" +
                                    "   [1]  Number of players: " + settings[0][0] + "\n"+
                                    "   [2]  Number of bots: " + settings[1][0] + "\n"+
                                    "   [3]  Board size (rows/columns): " + settings[2][0] + "/" + settings[2][1] + "\n" +
                                    "   [4]  Language: "+ language.GetList().get(settings[3][0]) + "\n" + 
                                    "   [!]  Return to the main menu" + "\n" +
                                       "****************************************************************\n");
    }

    public void ChangeText(String option , ListOfLanguages listOfLanguages){
        if(option.equals("1")){
            System.out.println("Enter the number of players: ");
        }
        if(option.equals("2")){
            System.out.println("Enter the number of bots: ");
        }
        if(option.equals("3")){
            System.out.println("Enter new board size (rows/columns): ");
        }
        if(option.equals("4")) {
            System.out.println("Enter the decired language(using the numbers to the left)");
            for(int i = 0;  i < listOfLanguages.GetList().size() ; i++){
                System.out.println("[" + i +"] " + listOfLanguages.GetList().get(i));
            }
        }
        if(option.equals("5")){
            System.out.println("Back to main menu");
        }
    }
}
