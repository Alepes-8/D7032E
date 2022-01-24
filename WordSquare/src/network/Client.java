/**
 *  The client class manages the connection to a server. This is used when one wants to 
 *  join someone elses game.
 * 
 *  @author Alex Peschel
 */
package network;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import IO.Scan;

public class Client {
    private Scan scan = new Scan();

    public void Clienter(String ipAddress) {
        try {
            Socket aSocket = new Socket(ipAddress, 2048);
            ObjectOutputStream outToServer = new ObjectOutputStream(aSocket.getOutputStream());
            ObjectInputStream inFromServer = new ObjectInputStream(aSocket.getInputStream());
            String nextMessage = "";
            
            //loop handling the game for the connected player
            while(!nextMessage.contains("Winner")){
                nextMessage = (String) inFromServer.readObject();
                System.out.println(nextMessage);
                if(nextMessage.contains("Pick") || nextMessage.contains("Place")) {
                    outToServer.writeObject(scan.Scanning());
                } 
            }  
            aSocket.close();
        } catch (Exception e) {
        }
        
    }
    
}
