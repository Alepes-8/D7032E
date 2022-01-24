/**
 *  The GetSetup class manages the startup of most of the needed components for the game to work, such as 
 *  every player and bot according to the set settings. But also inisiate the language and server.
 *  This is so that the such actions is centered in on place and that the game has the needed components.
 * 
 *  This is then used by the different gameModes where the informastion is extracted and used. We create it
 *  all here independelty of the gameMode so that it will be easier to modify and less dependent on one another.   
 * 
 *  @author Alex Peschel
 */
package game;

import player.*;
import java.util.Random;

import game.gameBoard.*;
import game.gameBoard.boardCreation.Tiles;
import game.language.Language;
import network.Server;

import java.util.ArrayList;

/**
 *  GameSetup will create 
 *      - Players
 *      - Board
 *      - Language(meaning the needed dictionary, letterValue, and InitLanguageList)
 */
public class GameSetup {
    public ArrayList<AbstractPlayer> players = new ArrayList<AbstractPlayer>();
    private Board boardSetup = new Board();
    private Tiles[][] board;
    private int mode;
    private int randomStarter;
    public Language language;
    public ArrayList<Tiles[][]> boardList;
    
    /**
     * GameSetup is a function which which uses
     *      - AbstractPlayer, to create the player instances
     *      - Board, creates the desired board
     *      - Language, inisiate and set the language for the game
     * 
     * @param path: is only not null when doing tests. it will help find the 
     * correct path to the directorys package
     */
    public GameSetup(int[][] settings, int mode, String path){
        int numPlayers = settings[0][0];
        int numBots = settings[1][0];
        int[] boardSize = settings[2] ;
        this.mode = mode;

        board = CorrectBoard(boardSize,  mode); 
        language =  new Language(settings[3][0], path); 
        for(int i=0; i<numBots; i++) {
            players.add(new Bot(i, null, null, null));    
        }
        if(numPlayers > 0){ // exist to assist testing, should never otherwise be below 1.
            players.add(new Player(numBots, null, null, null));
        }
        if(numPlayers>1){
            Server server = new Server();
            this.players = server.AddConnection(players, settings);
        }
            
        Random rnd = new Random();
        this.randomStarter = rnd.nextInt(players.size());
        SetBoardList(boardSize,players.size());
    }

    /**
     * Creates a list where the players boards can be stored
     */
    private void SetBoardList(int[] boardSize, int boardAmount){
        this.boardList = new ArrayList<Tiles[][]>(boardAmount);
        for (int i = 0 ; i < boardAmount; i++){
            this.boardList.add(CorrectBoard(boardSize,  mode));
        }
    }

    private Tiles[][] CorrectBoard(int[] boardSize , int mode){
        if(mode == 1 || mode == 2){
            board = boardSetup.setup(boardSize, false , mode);
        }else if(mode == 3 || mode == 4){
            board = boardSetup.setup(boardSize, true , mode);
        }
        return board;
    }

    
    public int GetRandomStarter(){
        return this.randomStarter;
    }
}
