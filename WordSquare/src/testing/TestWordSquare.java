package testing;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import error.InputChecker;
import game.GameSetup;
import game.gameBoard.boardCreation.Tiles;
import game.gameMode.phases.PlacePhase;
import game.language.Language;
import game.language.ListOfLanguages;
import game.scoring.CheckWords;
import game.scoring.Scoring;
import player.AbstractPlayer;
import tools.FullBoard;
import tools.TieOrNot;

public class TestWordSquare {
    private ListOfLanguages listOfLanguages;
    private InputChecker inputChecker;
    private String path = "E:/skola/code/WordSquare";
    private TestPlayer testPlayer;
    private Language language;
    private FullBoard fullBoard;
    private AbstractPlayer bot;
    private TestCreateBoard testFilledBoard;
    private  CheckWords checkWords;
    private Scoring scoring;

    @Before
    public void prepareTest(){
        listOfLanguages = new ListOfLanguages();
        inputChecker = new InputChecker();
        testPlayer = new TestPlayer(); 
        language = new Language(0, path);
        fullBoard = new FullBoard();
        bot = testPlayer.InitTestBot();
        testFilledBoard = new TestCreateBoard();
        checkWords = new CheckWords();
        scoring = new Scoring();


    }

    @Test
    public void enoughPlayers()  { 
        assertEquals(false, inputChecker.ValueCheck(1, "-5123", listOfLanguages));
        assertEquals(false, inputChecker.ValueCheck(1, "-1", listOfLanguages));
        assertEquals(false, inputChecker.ValueCheck(1, "0", listOfLanguages));
        assertEquals(true, inputChecker.ValueCheck(1, "1", listOfLanguages));
        assertEquals(true, inputChecker.ValueCheck(1, "5621", listOfLanguages));
        assertEquals(false, inputChecker.ValueCheck(1, "a2das-", listOfLanguages));
        assertEquals(false, inputChecker.ValueCheck(1, "", listOfLanguages));
    }

    @Test
    public void enoughBots()  { 
        assertEquals(false, inputChecker.ValueCheck(2, "-5123", listOfLanguages));
        assertEquals(false, inputChecker.ValueCheck(2, "-1", listOfLanguages));
        assertEquals(true, inputChecker.ValueCheck(2, "0", listOfLanguages));
        assertEquals(true, inputChecker.ValueCheck(2, "1", listOfLanguages));
        assertEquals(true, inputChecker.ValueCheck(2, "5621", listOfLanguages));
        assertEquals(false, inputChecker.ValueCheck(2, "a2das-", listOfLanguages));
        assertEquals(false, inputChecker.ValueCheck(2, "", listOfLanguages));
    }

    @Test
    public void boardSize() { 
        assertEquals(false, inputChecker.ValueCheck(3, "-12312/-123221", listOfLanguages));
        assertEquals(false, inputChecker.ValueCheck(3, "-1/-1", listOfLanguages));
        assertEquals(false, inputChecker.ValueCheck(3, "-1/1", listOfLanguages));
        assertEquals(false, inputChecker.ValueCheck(3, "1/-1", listOfLanguages));
        assertEquals(false, inputChecker.ValueCheck(3, "0/0", listOfLanguages));
        assertEquals(true, inputChecker.ValueCheck(3, "1/1", listOfLanguages));
        assertEquals(true, inputChecker.ValueCheck(3, "13/4", listOfLanguages));
        assertEquals(true, inputChecker.ValueCheck(3, "26/26", listOfLanguages));
        assertEquals(false, inputChecker.ValueCheck(3, "27/27", listOfLanguages));
        assertEquals(false, inputChecker.ValueCheck(3, "232/34", listOfLanguages));
        assertEquals(false, inputChecker.ValueCheck(3, "1/1/3", listOfLanguages));
        assertEquals(false, inputChecker.ValueCheck(3, "a6/5", listOfLanguages));
        assertEquals(false, inputChecker.ValueCheck(3, "3 2", listOfLanguages));
        assertEquals(false , inputChecker.ValueCheck(3, "", listOfLanguages));
    }

    @Test
    public void endAtFullBoard(){
        Tiles[][] partialBoard = testFilledBoard.GetBoard(false, 1, language, "PartiallyFull");
        assertEquals(false, fullBoard.EndGame(partialBoard));
        for(int i = 0; i< 5; i++){
            bot.PlaceLetter("A", partialBoard, false, language.GetLetterValue());
        }
        assertEquals(true, fullBoard.EndGame(partialBoard));
    }
    
    @Test
    public void CorrectBoard(){
        TestCheckIfEmpty testCheckIfEmpty = new TestCheckIfEmpty();
        TestSettings testSettings = new TestSettings();
        GameSetup gameSetup = new GameSetup(testSettings.InitiateSettings(), 1, path);
        PlacePhase placePhase = new PlacePhase();

        assertEquals(true, testCheckIfEmpty.IsItEmpty(gameSetup.boardList.get(0)));
        assertEquals(true, testCheckIfEmpty.IsItEmpty(gameSetup.boardList.get(1)));
        placePhase.PlaceLetter(gameSetup, "A", false);
        assertEquals(false, testCheckIfEmpty.IsItEmpty(gameSetup.boardList.get(0)));
        assertEquals(false, testCheckIfEmpty.IsItEmpty(gameSetup.boardList.get(1)));

        
    }

    @Test
    public void fullBoard(){ /*When the game-board is fully populated with letters the score is calculated for each player.*/
        Tiles[][] emptyBoard = testFilledBoard.GetBoard(false, 1, language, "Empty");
        assertEquals(false, fullBoard.EndGame(emptyBoard));
        
        Tiles[][] parBoard = testFilledBoard.GetBoard(false, 1, language, "PartiallyFull");
        assertEquals(false, fullBoard.EndGame(parBoard));

        Tiles[][] filledBoard = testFilledBoard.GetBoard(false, 1, language, "Full");
        assertEquals(true, fullBoard.EndGame(filledBoard));
        
    }
    
    @Test
    public void identifiedWords(){ 
        Tiles[][] filledBoard = testFilledBoard.GetBoard(false, 1, language, "Full");
        ArrayList<Tiles[]> wordArrayList = checkWords.checkWords(filledBoard, language);
        String[] wordList = new String[wordArrayList.size()];
        int position = 0 ;

        for(Tiles[] word : wordArrayList){
            String tempWord = "";
            for(int pointer = 0; pointer < word.length; pointer++){
                tempWord = tempWord + word[pointer].letter;
            }
            wordList[position] = tempWord;
            position++;
        }
        //The words ACE, ABA, APE is three different words in the premade board
        assertEquals("ACE", wordList[0]);
        assertEquals("ABA", wordList[6]);
        assertEquals("APE", wordList[8]);
    }
      
    @Test
    public void standardScoring(){
        Tiles[][] boardZero = new Tiles[1][2];
        boardZero[0][0] = new Tiles(false);
        boardZero[0][1] = new Tiles(false);
        boardZero[0][0].letter = "A";
        boardZero[0][0].letterValue = 1;
        boardZero[0][1].letter = "T";
        boardZero[0][1].letterValue = 1;
        assertEquals(0,  scoring.Score(boardZero, 1, language));

        Tiles[][] boardOne = new Tiles[1][3];
        boardOne[0][0] = new Tiles(false);
        boardOne[0][1] = new Tiles(false);
        boardOne[0][2] = new Tiles(false); 
        boardOne[0][0].letter = "E";
        boardOne[0][0].letterValue = 1;
        boardOne[0][1].letter = "A";
        boardOne[0][1].letterValue = 1;
        boardOne[0][2].letter = "T";
        boardOne[0][2].letterValue = 1;
        assertEquals(1,  scoring.Score(boardOne, 1, language));

        Tiles[][] boardThree = new Tiles[1][4];
        boardThree[0][0] = new Tiles(false);
        boardThree[0][1] = new Tiles(false);
        boardThree[0][2] = new Tiles(false); 
        boardThree[0][3] = new Tiles(false); 
        boardThree[0][0].letter = "E";
        boardThree[0][0].letterValue = 1;
        boardThree[0][1].letter = "A";
        boardThree[0][1].letterValue = 1;
        boardThree[0][2].letter = "T";
        boardThree[0][2].letterValue = 1;
        boardThree[0][3].letter = "H";
        boardThree[0][3].letterValue = 1;
        assertEquals(3,  scoring.Score(boardThree, 1, language));

        Tiles[][] boardEight = new Tiles[1][5];
        boardEight[0][0] = new Tiles(false);
        boardEight[0][1] = new Tiles(false);
        boardEight[0][2] = new Tiles(false); 
        boardEight[0][3] = new Tiles(false); 
        boardEight[0][4] = new Tiles(false); 
        boardEight[0][0].letter = "E";
        boardEight[0][0].letterValue = 1;
        boardEight[0][1].letter = "A";
        boardEight[0][1].letterValue = 1;
        boardEight[0][2].letter = "T";
        boardEight[0][2].letterValue = 1;
        boardEight[0][3].letter = "H";
        boardEight[0][3].letterValue = 1;
        boardEight[0][4].letter = "E";
        boardEight[0][4].letterValue = 1;
        assertEquals(8,  scoring.Score(boardEight, 1, language));
        
        Tiles[][] boardDubbleWord = new Tiles[2][3];
        boardDubbleWord[0][0] = new Tiles(false);
        boardDubbleWord[0][1] = new Tiles(false);
        boardDubbleWord[0][2] = new Tiles(false); 
        boardDubbleWord[0][0].letter = "E";
        boardDubbleWord[0][0].letterValue = 1;
        boardDubbleWord[0][1].letter = "A";
        boardDubbleWord[0][1].letterValue = 1;
        boardDubbleWord[0][2].letter = "T";
        boardDubbleWord[0][2].letterValue = 1;
        boardDubbleWord[1][0] = new Tiles(false);
        boardDubbleWord[1][1] = new Tiles(false);
        boardDubbleWord[1][2] = new Tiles(false); 
        boardDubbleWord[1][0].letter = "E";
        boardDubbleWord[1][0].letterValue = 1;
        boardDubbleWord[1][1].letter = "A";
        boardDubbleWord[1][1].letterValue = 1;
        boardDubbleWord[1][2].letter = "T";
        boardDubbleWord[1][2].letterValue = 1;
        assertEquals(1,  scoring.Score(boardDubbleWord, 1, language));

        Tiles[][] filledBoard = testFilledBoard.GetBoard(false, 1, language, "Full");
        assertEquals(5, scoring.Score(filledBoard, 1, language));
    }
      
    @Test
    public void scrabbleScoring(){
        Tiles[][] boardLetterValues = new Tiles[1][7];
        for(int i = 0 ; i < 7 ; i++){
            boardLetterValues[0][i] = new Tiles(true);
        }
        assertEquals(1, boardLetterValues[0][0].tileType);

        boardLetterValues[0][0].letter = "E";
        boardLetterValues[0][0].letterValue = language.GetLetterValue().get("E");
        assertEquals(1, boardLetterValues[0][0].letterValue);
        boardLetterValues[0][1].letter = "D";
        boardLetterValues[0][1].letterValue = language.GetLetterValue().get("D");
        assertEquals(2, boardLetterValues[0][1].letterValue);
        boardLetterValues[0][2].letter = "M";
        boardLetterValues[0][2].letterValue = language.GetLetterValue().get("M");
        assertEquals(3, boardLetterValues[0][2].letterValue);
        boardLetterValues[0][3].letter = "F";
        boardLetterValues[0][3].letterValue = language.GetLetterValue().get("F");
        assertEquals(4, boardLetterValues[0][3].letterValue);
        boardLetterValues[0][4].letter = "K";
        boardLetterValues[0][4].letterValue = language.GetLetterValue().get("K");
        assertEquals(5, boardLetterValues[0][4].letterValue);
        boardLetterValues[0][5].letter = "J";
        boardLetterValues[0][5].letterValue = language.GetLetterValue().get("J");
        assertEquals(8, boardLetterValues[0][5].letterValue);
        boardLetterValues[0][6].letter = "Q";
        boardLetterValues[0][6].letterValue = language.GetLetterValue().get("Q");
        assertEquals(10, boardLetterValues[0][6].letterValue);

        Tiles[][] boardDL = new Tiles[1][2];
        boardDL[0][0] = new Tiles(true);
        boardDL[0][1] = new Tiles(true, 2);
        boardDL[0][0].letter = "A";
        boardDL[0][0].letterValue = 1;
        boardDL[0][1].letter = "T";
        boardDL[0][1].letterValue = 1;
        assertEquals(3,  scoring.Score(boardDL, 3, language));

        Tiles[][] boardTL = new Tiles[1][2];
        boardTL[0][0] = new Tiles(true);
        boardTL[0][1] = new Tiles(true, 3);
        boardTL[0][0].letter = "A";
        boardTL[0][0].letterValue = 1;
        boardTL[0][1].letter = "T";
        boardTL[0][1].letterValue = 1;
        assertEquals(4,  scoring.Score(boardTL, 3, language));

        Tiles[][] boardDW = new Tiles[1][2];
        boardDW[0][0] = new Tiles(true);
        boardDW[0][1] = new Tiles(true, 4);
        boardDW[0][0].letter = "A";
        boardDW[0][0].letterValue = 1;
        boardDW[0][1].letter = "T";
        boardDW[0][1].letterValue = 1;
        assertEquals(4,  scoring.Score(boardDW, 3, language));
        
        Tiles[][] boardTW = new Tiles[1][2];
        boardTW[0][0] = new Tiles(true);
        boardTW[0][1] = new Tiles(true, 5);
        boardTW[0][0].letter = "A";
        boardTW[0][0].letterValue = 1;
        boardTW[0][1].letter = "T";
        boardTW[0][1].letterValue = 1;
        assertEquals(6,  scoring.Score(boardTW, 3, language));
       
        Tiles[][] boardTWAndDW = new Tiles[1][2];
        boardTWAndDW[0][0] = new Tiles(true, 4);
        boardTWAndDW[0][1] = new Tiles(true, 5);
        boardTWAndDW[0][0].letter = "A";
        boardTWAndDW[0][0].letterValue = 1;
        boardTWAndDW[0][1].letter = "T";
        boardTWAndDW[0][1].letterValue = 1;
        assertEquals(12,  scoring.Score(boardTWAndDW, 3, language));

    }
      
    /**
     * It will return true if there is a tie and false if there is a winner
     */
    @Test
    public void correctWinner(){ 
        
        TieOrNot checkTie = new TieOrNot();
        ArrayList<AbstractPlayer> players = new ArrayList<AbstractPlayer>();
        players.add(testPlayer.InitTestPlayer());
        players.add(testPlayer.InitTestPlayer());

        players.get(0).SetScore(10);
        players.get(1).SetScore(10);


        assertEquals(true, checkTie.CheckTie(players));
        players.get(1).SetScore(5);
        assertEquals(false, checkTie.CheckTie(players));
    }
}
