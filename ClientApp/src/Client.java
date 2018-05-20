   /*
    * To change this license header, choose License Headers in Project Properties.
    * To change this template file, choose Tools | Templates
    * and open the template in the editor.
    */

   import javax.imageio.ImageIO;
   import java.awt.image.BufferedImage;
   import java.io.BufferedReader;
   import java.io.File;
   import java.io.IOException;
   import java.io.InputStreamReader;
   import java.net.Socket;
   import java.net.UnknownHostException;

   /**
    * @author Andrew
    */
   public class Client {
       private final static String SERVER_ADDRESS = "127.0.0.1";
       private final static int PORT = 8100;

       public static void main(String[] args) throws IOException {
           Client client = new Client();

           //Scanner keyboard = new Scanner(System.in);
           //String request = keyboard.nextLine();

           BufferedImage img = null;
           img = ImageIO.read(new File("E:\\Facultate\\IP\\Proiect Image\\Client+Server\\Client+Server\\Thermoflow\\download.jpg"));
           client.sendRequestToServer(img);
       }

       private void sendRequestToServer(BufferedImage request) throws IOException {
           try {
               Socket socket = new Socket(Client.SERVER_ADDRESS, Client.PORT);
               //PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

               BufferedReader in = new BufferedReader(
                       new InputStreamReader(socket.getInputStream()));

               ImageIO.write(request, "bmp", socket.getOutputStream());

               String response = in.readLine();
               System.out.println(response);

           } catch (UnknownHostException e) {
               System.err.println("No server listening... " + e);
           }
       }
   }