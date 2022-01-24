/**
 *  The class player manages the actions which the players does. It manages bot the insertion of
 *  letters and the placement of said letters on the board. 
 * 
 *  Player stores informastion regarding its final score, playerID, if there is someone 
 *  online or not and its corresponding Streams. This is so what the informastion regarding
 *  the player is stored within the player themself, not externaly.
 * 
 *  @author Alex Peschel
 */
package player;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;

import IO.Scan;
import error.InputChecker;
import game.gameBoard.boardCreation.Tiles;

public class Player implements AbstractPlayer/*, Comparable<AbstractPlayer>*/{
    
    private Scan scanner = new Scan();
    private InputChecker inputChecker = new InputChecker(); 
    private ObjectInputStream inFromClient;
    private ObjectOutputStream outToClient;
    private boolean online;
    private int playerID;
    private int score;
    public Socket connection;

    public Player(int playerID, Socket connection, ObjectInputStream inFromClient, ObjectOutputStream outToClient) {
        this.inFromClient = inFromClient;
        this.outToClient = outToClient;
        this.playerID = playerID;
        if(connection==null){
            this.online = false;
        }else{
            this.online = true;            
        }
        this.connection = connection; this.inFromClient = inFromClient; this.outToClient = outToClient;
    }

    @Override
    public String PickLetter(Boolean mode, HashMap<String, Integer> language) {
        if(mode == true){  // if scrabble mode than you will have the printout of the letters value and the pick a letter
            SendMessage(Tiles.LETTER_VALUES+"\n" + "Pick a letter");
        }else{
            SendMessage("Pick a letter");
        }
        String message;
        do{
            message = ReadMessage();
        }while(!inputChecker.LetterCheck(message, language, this.online, this.outToClient)); //checks if the input is valid
        return message;
    }

    @Override
    public Tiles[][] PlaceLetter(String letter, Tiles[][] board, boolean scrabbleMode, HashMap<String, Integer> letterValue) {
        int row, coloumn;
        int value = 1;
        String theLetter = letter;
        int[] size =  new int[]{board.length, board[0].length};
        if(scrabbleMode){
            value = letterValue.get(letter);
            theLetter = letter +  " [" + value + "]";
        }
        SendMessage("Place " + theLetter + " (syntax [row column])");
        do { 
            String place;
            do{
                 place = ReadMessage().toLowerCase();
            }while(!inputChecker.PositionCheck(place, size, this.online, this.outToClient));
            String[] placement = (place.contains(" ")?place.split(" "):place.split(""));
            row = ((int) placement[0].charAt(0))-97; //ascii code for a letter
            coloumn = Integer.parseInt(placement[1]);
        } while(!board[row][coloumn].letter.equals(""));
        
        board[row][coloumn].letter = letter;
        board[row][coloumn].letterValue = value;
        return board;
    }

    @Override
    public void SendMessage(Object message) {
        if(online) {
            try {outToClient.writeObject(message);} catch (Exception e) {}
        } else{
            System.out.println(message);                
        }
    }

    @Override
    public String ReadMessage() {
        String word = ""; 
        if(online)
            try{word = (String) inFromClient.readObject();} catch (Exception e){}
        else
            try {word=scanner.Scanning();} catch(Exception e){}
        return word;
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
