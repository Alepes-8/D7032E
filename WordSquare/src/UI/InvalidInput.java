/**
 *  The InvalidInput class is called upon when a printout is needed regarind invalid 
 *  inputs, such as when inserting a wrongful player number. This class is created to
 *  localise the outputs for easier access and for future additions.
 * 
 *  @author Alex Peschel
 */
package UI;

import java.io.ObjectOutputStream;

public class InvalidInput {
    
    public void InvalidAlternative(){
        System.out.println("Invalid input: Use one of the following alternatives \n");
    }

    public void InvalidLanguage(){
        System.out.println("Invalid input: Language does not exist. Write one of the numbers to the left of the existing languages");
    }

    public void InvalidBoardSize(){
        System.out.println("Invalid input: A none possible size for a board. Use the format row/coloumn where neither is smaller than 1 or larger than 26");
    }

    public void InvalidPlayerCount(int part){
        if(part == 1){
            System.out.println("Invalid input: Needs to be a number larger than 0");
        }else if(part == 2){
            System.out.println("Invalid input: Needs to be a number equal or larger than 0");
        }
    }

    public void InvalidPlacement(boolean online, ObjectOutputStream outToClient){
        String message = "Invalid input: The input needs to be with in the board and with the format [letter][number]. a1, b3,c3 for exemple";
        if(online) {
            try {outToClient.writeObject(message);} catch (Exception e) {}
        } else{
            System.out.println(message);                
        }
    }

    public void InvalidLetter(boolean online, ObjectOutputStream outToClient){
        String message = "Invalid input: for the language you have pick this letter is invalid. I can only be one letter and not more";
        if(online) {
            try {outToClient.writeObject(message);} catch (Exception e) {}
        }else{
            System.out.println(message);                
        }
    }
}
