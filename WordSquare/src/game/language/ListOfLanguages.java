/**
 *  ListOfLanguages creates a list of the available languages and in turn gives each of the a 
 *  correspong number. The number represent their possition, this is to ease the extraction of 
 *  informastion, as well as to easier find the desired language acording to onces number.
 * 
 *  This is used by the language file and the printout of settings.
 * 
 *  @author Alex Peschel
 */
package game.language;

import java.util.LinkedHashMap;

public class ListOfLanguages {
    private LinkedHashMap<Integer, String> avaliableLanguages;

    /**
     * inserting new languages insert it with a new number corrolating to it's position.
     */
    public void SetLanguages(){
        avaliableLanguages = new LinkedHashMap<Integer, String>();
        this.avaliableLanguages.put( 0 ,"English");
    }

    public LinkedHashMap<Integer, String> GetList(){
        return avaliableLanguages;
    }
}
