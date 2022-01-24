/** 
 *  This class manages the games different types of inputs and checks if the input is valid according 
 *  to the games specifics. Here we check the input occurring in {@link #GameHandler} and {@link #Player}.
 *  
 *  Returning the value true in the case where the input is valid, and false in anything fails. Then thowing 
 *  a string saying what may be wrong to the user.
 * 
 *  @author Alex Peschel 
 */
package error;

import UI.InvalidInput;
import game.language.ListOfLanguages;

import java.io.ObjectOutputStream;
import java.util.HashMap;
import tools.StringToInt;

/**
 *  If somethings isn't correct within the critieras the function will 
 *  call on invalidInput for a representative error message
 */
public class InputChecker {
    private InvalidInput invalidInput = new InvalidInput();
     
    public boolean ValueCheck(int changePart, String newValue, ListOfLanguages listOfLanguages){  //used to check the value of an input for settings
        if(changePart == 1 || changePart == 2){
            if(newValue.matches("[0-9]+")){
                int change = StringToInt.ConvertString(newValue);
                if(changePart == 1 && change >= 1){
                    return true;
                }else if(changePart == 2 && change>=0){
                    return true;
                }  
            }
            invalidInput.InvalidPlayerCount(changePart);
        }else if(changePart == 3){
            if(newValue.contains("/")){
                String[] partialInput = newValue.split("/");
                if(partialInput.length == 2){
                    if(partialInput[0].matches("[0-9]+") &&  partialInput[1].matches("[0-9]+")){
                        if( StringToInt.ConvertString(partialInput[0]) >= 1 && StringToInt.ConvertString(partialInput[1]) >= 1 &&
                            26 >= StringToInt.ConvertString(partialInput[0]) && 26 >= StringToInt.ConvertString(partialInput[1])){
                            return true;
                        }
                    }
                }
            }
            invalidInput.InvalidBoardSize();
        }else if(changePart == 4){
            if(listOfLanguages.GetList().get(StringToInt.ConvertString(newValue)) != null){
                return true;
            }
            invalidInput.InvalidLanguage();
        }
        return false;
    }

    public boolean PositionCheck(String position , int [] size, boolean online, ObjectOutputStream outToClient){
        int row, coloumn;
        if(position.length() == 2){
            String[] placement = (position.contains(" ")?position.split(" "):position.split(""));
            if(placement[0].matches("[a-zA-Z]+") && placement[1].matches("[0-9]+")){
                row = ((int) placement[0].charAt(0))-97; //ascii code for a
                coloumn = Integer.parseInt(placement[1]);
                if(size[0]-1 >= row && size[1]-1 >= coloumn ){
                    return true;
                }
            }
        }
        invalidInput.InvalidPlacement(online, outToClient);
        return false;
    }

    public boolean LetterCheck(String letter, HashMap<String, Integer> letterList, boolean online, ObjectOutputStream outToClient){
        if(letter.length() == 1){
            
        }
        invalidInput.InvalidLetter(online, outToClient);
        return false;
    }
}
