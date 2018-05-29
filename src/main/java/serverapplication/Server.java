/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverapplication;

import com.emaraic.ObjectRecognition.Recognizer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Andrew
 */
public class Server {
    private static final int PORT = 8100;
    private ServerSocket serverSocket;
    private boolean running = false;
    private final Recognizer recognizer=new Recognizer();
    
    public static void main(String[] args)  {
         
        Server server = new Server();
        server.init();
        server.waitForClients(); 
    }
 
    public void setRunning(boolean running) {
        this.running = running;
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }
	
    public void stop() throws IOException {
        this.running = false;
    }

    private void init() {
        try {
            this.serverSocket=new ServerSocket(PORT);
            this.running=true;
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void waitForClients() {
       
       while(this.running)
       {
           try {
               Socket socket=this.serverSocket.accept();
               new ClientThread(this,socket,this.recognizer).start();
           } catch (IOException ex) {
               Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
           }
       }
    }
}
