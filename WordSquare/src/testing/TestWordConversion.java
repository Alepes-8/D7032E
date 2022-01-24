package testing;

import java.util.ArrayList;

import game.gameBoard.boardCreation.Tiles;

public class TestWordConversion {
    private ArrayList<String> tempStorage;

    public ArrayList<String> getWords(ArrayList<Tiles[]> words){
        for(Tiles[] word : words){
            String tempWord = "";
            for(int pointer = 0 ; pointer < word.length ; pointer++){
                tempWord = tempWord + word[pointer].letter;
            }
            tempStorage.add(tempWord);
        }
        return tempStorage;
    }
}
