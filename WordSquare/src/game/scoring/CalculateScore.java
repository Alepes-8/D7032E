/**
 *  The CalculateScore class calculates the score, and depending on the game mode the 
 *  scoring would be done somewhat diffent. For the mode standard words won't count twice 
 *  while the scrabble game mode does. 
 * 
 *  The code is placed within the same file so that new scoring methords could be easily 
 *  added for differnet game mode.
 * 
 *  @author Alex Peschel
 */
package game.scoring;

import java.util.ArrayList;

import game.gameBoard.boardCreation.Tiles;
import game.language.Language;

public class CalculateScore {
    private ArrayList<String> tempStorage;

    public int GetScore(ArrayList<Tiles[]> words, int mode, Language language) {
        int score = 0;
        
        if(mode == 1) { //regular WordTiless scoring
            tempStorage = new ArrayList<String>(/*words.size()*/);
            for(Tiles[] word : words) { //score: 1, 2, 4, 6, 8, 10, etc.
                if(CheckDublicates(word)){
                    if(word.length==3){
                         score+=1;
                    }
                    else if(word.length>3) {
                        score+=(word.length-3)*2;
                    }
                }
            }
        } else {    //scrabble WordTiless scoring
            for(Tiles[] word : words) {
                int wordScore = 0;
                int wordMultiplier = 1;
                for(int i=0;i<word.length; i++) {
                    int letterMultiplier = 1;
                    if(word[i].tileType == Tiles.DL) {letterMultiplier = 2;}
                    if(word[i].tileType == Tiles.TL) {letterMultiplier = 3;}
                    wordScore += (word[i].letterValue * letterMultiplier);
                    int tileMultiplier = 1;
                    if(word[i].tileType == Tiles.DW) {tileMultiplier = 2;}
                    if(word[i].tileType == Tiles.TW) {tileMultiplier = 3;}
                    wordMultiplier = wordMultiplier * tileMultiplier;
                }
                wordScore = wordScore * wordMultiplier;
                score += wordScore;
            }            
        }
        return score;
    }
    
    private boolean CheckDublicates(Tiles[] word){
        String tempWord = "";
        for(int pointer = 0 ; pointer < word.length ; pointer++){
            tempWord = tempWord + word[pointer].letter;
        }
        for(int check = 0; check < tempStorage.size(); check++ ){ // checks if tempWord already exist
            if(tempStorage.get(check).equals(tempWord)){ 
                return false;
            }
        }
        tempStorage.add(tempWord);
        return true;
    }
}
