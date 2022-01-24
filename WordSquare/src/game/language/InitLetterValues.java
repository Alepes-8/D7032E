/**
 *  This class inisiates a letter point configurastion where every letter get a coresponding value.
 *  
 *  For the ease of future letter configurastion the code is stored in one file which also makes is
 *  easier when one wants to add new configurastions for the letter value. 
 * 
 *  @author Alex Peschel
 */
package game.language;

import java.util.HashMap;

public class InitLetterValues {
    private HashMap<String, Integer> TilesValue;

    public HashMap<String, Integer> InitLetterValue(int Language) {
        this.TilesValue = new HashMap<String, Integer>();
        if(Language== 0){
            EnglishValue();
        }
        return this.TilesValue;
    }

    private void EnglishValue(){
        //1-point letters
        this.TilesValue.put("E", 1);
        this.TilesValue.put("A", 1);
        this.TilesValue.put("I", 1);
        this.TilesValue.put("O", 1);
        this.TilesValue.put("N", 1);
        this.TilesValue.put("R", 1);
        this.TilesValue.put("T", 1);
        this.TilesValue.put("L", 1);
        this.TilesValue.put("S", 1);
        this.TilesValue.put("U", 1);

        //2-point letters
        this.TilesValue.put("D", 2);
        this.TilesValue.put("G", 2);

        //3-point letters
        this.TilesValue.put("B", 3);
        this.TilesValue.put("C", 3);
        this.TilesValue.put("M", 3);
        this.TilesValue.put("P", 3);

        //4-point letters
        this.TilesValue.put("F", 4);
        this.TilesValue.put("H", 4);
        this.TilesValue.put("V", 4);
        this.TilesValue.put("W", 4);
        this.TilesValue.put("Y", 4);

        //5-point letters
        this.TilesValue.put("K", 5);

        //8-point letters
        this.TilesValue.put("J", 8);
        this.TilesValue.put("X", 8);

        //10-point letters
        this.TilesValue.put("Q", 10);
        this.TilesValue.put("Z", 10);
    }
}
