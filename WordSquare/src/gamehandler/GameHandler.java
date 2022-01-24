/** 
 *  GameHandlers assignment is to handle the users input and in turn navigate to; settings, so it can be changed 
 *  or inisiated: correct UI, for printing the different menus: and lastly inisate the correct gamemode 
 *  for the player.
 * 
 *  The whole system and game starts up in {@link #main(String argv[])}.
 * 
 *  @author Alex Peschel 
 */
package gamehandler;

import UI.*;
import IO.*;
import game.Game;
import network.Client;
import tools.StringToInt;
import error.InputChecker;
import game.language.ListOfLanguages;


public class GameHandler {
    private Game game = new Game();
    private Scan scaning = new Scan();
    private Client client = new Client();
    private Settings settings = new Settings();
    private SettingsUI settingUI = new SettingsUI(); 
    private MainMenuUI mainMenuUI = new MainMenuUI();
    private InputChecker checkInput = new InputChecker();
    private InvalidInput invalidInput = new InvalidInput();
    private ListOfLanguages listOfLanguages = new ListOfLanguages();
    
    public static void main(String argv[]){
        GameHandler start = new GameHandler(argv);
    }
    
    /**
     * The GameHandler function uses several different classes for it to do the iuntended assignment
     *      - Settings, to inisiate and change settings
     *      - InputChecker, to see if the input given is valid or not acording to the set criterias
     *      - MainMenuUI, to print the main manu
     *      - SettingsUI, to print the settings menus and description
     *      - Game, to start and run the game
     *      - ListOfLanguages, to be able to print out the spelled out language
     *      - Scan, to scan the players input
     *      - Client, if a player wishes to join someonce game.
     *      - InvalidInput, to print out to the user what is wrong with their input
     * 
     */
    public GameHandler(String argv[]) {
        settings.InitiateSettings();
        boolean newGame = true;
        listOfLanguages.SetLanguages();

        while(true){
            mainMenuUI.MainMenu(settings.GetSettings(), newGame, listOfLanguages.GetList().get(settings.GetSettings()[3][0]));
            newGame = false;
            String menuChoice = scaning.Scanning();

            if (menuChoice.equals("1") || menuChoice.equals("2") || menuChoice.equals("3") || menuChoice.equals("4")){ //start the game
                game.RunGame(settings.GetSettings(), menuChoice);
            }

            else if(menuChoice.equals("5")){ // go to setting 
                while(true){
                    settingUI.SettingMenu(settings.GetSettings(), listOfLanguages);
                    String change = scaning.Scanning();
                    settingUI.ChangeText(change, listOfLanguages);
                    
                    if(change.equals("1") || change.equals("2") || change.equals("3") || change.equals("4")){ //change either players, bot, size, or language
                        String changeValue = scaning.Scanning();
                        if(checkInput.ValueCheck(StringToInt.ConvertString(change), changeValue, listOfLanguages)){
                            settings.ChangeSettings(StringToInt.ConvertString(change), changeValue);
                            continue;
                        }
                    }
                    else if(change.equals("!")){ //leave the settings menu
                        break;  
                    }else{
                        invalidInput.InvalidAlternative();
                    }
                }    
            }
            else if (menuChoice.equals("6")){   //go to client and connect to someonce game
                try {
                    client.Clienter("127.0.0.1");            
                } catch (Exception e){}
            }
            else if (menuChoice.equals("!")){
                break;
            }else{
                invalidInput.InvalidAlternative();
            } 
        }
    }
}
