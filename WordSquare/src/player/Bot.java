/**
 *  Implementing the abstract class AbstractPlayer for its functions, the Bot class is 
 *  created when a automated player is needed. So that a player can play all alone. 
 *  {@link #PickLetter()} and {@link #PlaceLetter()} uses randomised values to pick 
 *  and place letters.  
 * 
 *  The bot player stores it's final score and playerID in it which can be accessed
 *  with the help of {@link #getScore()} and {@link #getPlayerID()}. This is so that
 *  the information regarding the bot is stored within it self.
 * 
 *  @author Alex Peschel
 */
package player;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Random;

import game.gameBoard.boardCreation.Tiles;

public class Bot implements AbstractPlayer/*,  Comparable<AbstractPlayer>*/{
    
    private ObjectOutputStream outToClient;
    private boolean online;
    private int playerID;
    private int score;
    public Socket connection;
    
    public Bot(int playerID, Socket connection, ObjectInputStream inFromClient, ObjectOutputStream outToClient){
        this.outToClient = outToClient;
        this.playerID = playerID;
        if(connection==null){
            this.online = false;
        }else{
            this.online = true;            
        }
        this.connection = connection; this.outToClient = outToClient;
    }

    @Override
    public String PickLetter(Boolean mode, HashMap<String, Integer> language) {
        Random rnd = new Random();
        int theLetter = rnd.nextInt(26);
        return ""+((char) (theLetter+65));
    }

    @Override
    public Tiles[][] PlaceLetter(String letter, Tiles[][] board, boolean scrabbleMode, HashMap<String, Integer> letterValue) {
        Random rnd = new Random();
        int row, coloumn;
        int value = 1;
        if(scrabbleMode){
            value = letterValue.get(letter);
        }
        do {
            row = rnd.nextInt(board.length);
            rnd = new Random();
            coloumn = rnd.nextInt(board[0].length);
        } while(!board[row][coloumn].letter.equals(""));
        
        board[row][coloumn].letter = letter;
        board[row][coloumn].letterValue = value;
        return board;
    }

    @Override
    public void SendMessage(Object message) {
        if(online) {
            try {outToClient.writeObject(message);} catch (Exception e) {}
        }           
    }

    @Override
    public String ReadMessage() {
        return null;
    }

    @Override
    public int GetPlayerID() {
        return playerID;
    }

    @Override
    public int GetScore() {
        return this.score;
    }

    @Override
    public void SetScore(int score) {
        this.score = score;        
    }

    @Override
    public int CompareTo(AbstractPlayer o) {
        return o.GetScore() - this.score;
    }
}
