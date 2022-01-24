/**
 *  This class is assigened to get a certain file within dictionary depending on the language 
 *  set in the settings.
 * 
 * @author Alex Peschel
 */
package game.language;

import java.io.File;
import java.io.FileReader;

public class GetFileReader {
    private FileReader fileReader;

    public FileReader GetFile(int language, String path, ListOfLanguages listOfLanguages){
        String filePath = "";
        if(path != null){
            filePath = path;
        }else{
            filePath = new File("").getAbsolutePath(); //get the absolute path to the src folder
        }
        try{
            filePath = filePath + "/src/game/language/dictionarys/"; 
            fileReader = new FileReader(filePath + listOfLanguages.GetList().get(language) + ".txt"); 
        } catch(Exception e) {
            throw new IllegalArgumentException("gameSetup/language/GetFileReader.java  something is wrong with finding the file");
        }
        return fileReader;
    }
}
