
package chatserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import message.ChatMessage;

/**
 *
 * @author Ohjelmistokehitys
 */
public class ChatServer {

    static ArrayList<ServerClientBackEnd> clients = new ArrayList();
    
    public static void main(String[] args) {
        
        try {
            // Start the server to listen port 3010
            ServerSocket server = new ServerSocket(3010);
        
            // Start to listen and wait conections
            while(true){
                // Wait here the client
                Socket temp = server.accept();
                ServerClientBackEnd backEnd = new ServerClientBackEnd(temp);
                clients.add(backEnd);
                Thread t = new Thread(backEnd);
                t.setDaemon(true);
                t.start();
            }
        } catch (IOException ex){
            ex.printStackTrace();
        }
       
    }
    
    public static void broadcastMessage(ChatMessage cm){
        
        for(ServerClientBackEnd temp: clients){
            temp.sendMessage(cm);
        }
    }
}
