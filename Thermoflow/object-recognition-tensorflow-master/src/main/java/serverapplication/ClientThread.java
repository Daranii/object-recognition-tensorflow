/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverapplication;

import com.emaraic.ObjectRecognition.Recognizer;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Andrew
 */
public class ClientThread extends Thread {
    private final Socket socket ;
    private final Server server;
    private final Recognizer recognizer;

    ClientThread(Server GameServer,Socket socket, Recognizer recognizer) {
      this.socket=socket;
      this.server=GameServer;
      this.recognizer=recognizer;
    }
     // Create the constructor that receives a reference to the server and to the client socket
    @Override
    public void run() {
        try {

            BufferedImage img = ImageIO.read(socket.getInputStream());
            String currentPath = new File("").getAbsolutePath() + "image.jpg";
            ImageIO.write(img, "jpg", new File(currentPath));

            String response=this.recognizer.analyse(currentPath);
            System.out.println(response);
            
            PrintWriter out = new PrintWriter(socket.getOutputStream()); //server -> client stream
            out.println(response);
            out.flush();
            socket.close(); //... usse try-catch-finally to handle the exceptions!
        } catch (IOException ex) {
            Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    private String execute(String message) {
        
        
        System.out.println( "Server received message " + message);
        return "Server received message " + message;
        

    }
}
