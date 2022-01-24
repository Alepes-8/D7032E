/**
 *  This class manages taking out the lines within the the file sent in and in turn 
 *  transfer it to a arraylist for future access.
 * 
 * @author Alex Peschel
 */
 package game.language;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class GetDictionary {
    private ArrayList<String> dictionary = new ArrayList<String>();;

    /**
     * Creates bufferedReader to read each line within the fileReader
     * 
     * @param fileReader: It's the current language file, such as English.txt
     */
    public ArrayList<String> GetLanguageFile(FileReader fileReader){
        BufferedReader bufferedReader;
        try{
            bufferedReader = new BufferedReader(fileReader);
            String line = null;
            while((line = bufferedReader.readLine()) != null) { //go though every line
                this.dictionary.add(line);
            }
            bufferedReader.close();
        } catch(Exception f) {
            throw new IllegalArgumentException("gameSetup/language/GetDictionary.java  The file transference failed. It may be empty or not exist");
        }
        return this.dictionary;
    }
}
