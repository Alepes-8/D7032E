/**
 *  When a player wishes to have more than one player within the game, sepeate from bots, then
 *  then this class will be called upon. A class which settings up a server of a client to connect 
 *  to.
 * 
 *  @author Alex Peschel
 */
package network;

import java.util.ArrayList;
import player.AbstractPlayer;
import player.Player;
import java.io.*;
import java.net.*;

public class Server {
    private ServerSocket aSocket;

    /**
     * The AddConnection methords calls on
     *      - AbstractPlayer, to create a new player with connection information.
     * 
     * @return, new list with the extra player.
     */
    public ArrayList<AbstractPlayer> AddConnection(ArrayList<AbstractPlayer> playerList, int[][] settings) {
        int bots = settings[1][0];
        int players = settings[0][0];
        try{
            aSocket = new ServerSocket(2048);
            for(int i = bots+1 ; i<players+bots; i++){
                Socket connectionSocket = aSocket.accept();
                ObjectInputStream inFromClient = new ObjectInputStream(connectionSocket.getInputStream());
                ObjectOutputStream outToClient = new ObjectOutputStream(connectionSocket.getOutputStream());
                playerList.add(new Player(i, connectionSocket, inFromClient, outToClient)); //add an online client
                System.out.println("Connected to player " + i);
                outToClient.writeObject("You connected to the server as player " + i + "\n");
            }
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
        return playerList;
    }
}
