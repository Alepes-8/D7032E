/**
 *  This class is assigned to extract the words from the desired language file into this class. Storying
 *  it localy for the ease of access and lessen the dependecies. Here the class also inisiates the lettervalues
 *  and an instance of ListOfLanguages.
 * 
 *  @author Alex Peschel
 */
package game.language;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

public class Language {
    private ArrayList<String> dictionary = new ArrayList<String>();
    private HashMap<String, Integer> LetterValue;
    private FileReader fileReader;
    private InitLetterValues InitValue = new InitLetterValues();
    private ListOfLanguages listOfLanguages = new ListOfLanguages();
    private GetDictionary getDictionary = new GetDictionary();
    private GetFileReader getFileReader = new GetFileReader();
    
    /**
     * Language creates several parts
     *      - the list of Languages(listOfLanguages.SetLanguages)
     *      - The set letter values acoriding to the decided language(InitLetterValue)
     *      - Gets the language file(GetFile)
     *      - Takes out words from the file into a arraylist(GetLanguageFile)
     * 
     * @param language: is the integer value which a language is correlated to within ListOfLanguages
     */
    public Language(int language, String path){
        listOfLanguages.SetLanguages();
        LetterValue = InitValue.InitLetterValue(language);
        fileReader = getFileReader.GetFile(language, path, listOfLanguages);
        dictionary = getDictionary.GetLanguageFile(this.fileReader);
    }

    public HashMap<String, Integer> GetLetterValue(){
        return this.LetterValue;
    }

    public ArrayList<String> GetDictionary(){
        return this.dictionary;
    }
}
