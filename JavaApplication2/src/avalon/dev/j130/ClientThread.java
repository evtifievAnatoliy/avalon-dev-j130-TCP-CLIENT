/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avalon.dev.j130;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ClientThread extends Thread{
    private Socket clientSock;
    private String clientHostPost;
    private MainForm mainForm;
    
        
    public ClientThread(Socket clientSock, MainForm mainForm){
        super();
        this.clientSock = clientSock;
        this.mainForm = mainForm;
        
    }

    @Override
    public void run() {
            
        
        try (Socket socket = new Socket(InetAddress.getLocalHost(), 7_020)){
            try (ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream()); 
                        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream()); 
                        Scanner sc = new Scanner(System.in)){
                  
                while(true){
                    System.out.printf("Enter a line: ");
                    String line = sc.nextLine();
                    oos.writeObject(line);
                  
                    Object[] o = (Object[]) ois.readObject();
                    System.out.println(o[0]);
                    System.out.println(o[1]);
                    System.out.println();
                }
              }
        } catch (Exception ex) {
                
        }
        
        /*
        
        clientHostPost = String.format("%s,%s", clientSock.getInetAddress(), clientSock.getPort());
        mainForm.setLogs(clientHostPost + " connected.");
        try(ObjectOutputStream oos = new ObjectOutputStream(clientSock.getOutputStream());
                ObjectInputStream ois = new ObjectInputStream(clientSock.getInputStream())){
            while(true){
                String line = (String) ois.readObject();
                Date d = new Date();
                mainForm.setLogs(clientHostPost + ", " +  line + ", " + d);
                   
                oos.writeObject(new Object[]{line, d});
            }
        } catch (Exception ex) {
                
        }
            mainForm.setLogs(clientHostPost + " disconnected.");
            */
        }

   
}
