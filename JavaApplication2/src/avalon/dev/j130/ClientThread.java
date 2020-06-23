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
    
    private MainForm mainForm;
    
    boolean isNewMessage = false;
    
    
    public ClientThread(MainForm mainForm){
        super();
        this.mainForm = mainForm;
        run();
        
    }

    @Override
    public void run() {
            
        
        try (Socket socket = new Socket(InetAddress.getLocalHost(), 7_020)){
            try (ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream()); 
                        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream())){
                mainForm.setLogs("Server: " + socket.getInetAddress() + ":" + socket.getPort() + " connected.");
                  
                while(true){
                    if(isNewMessage){
                        
                        oos.writeObject("Hello");
                        isNewMessage = false;
                        
                        Object[] o = (Object[]) ois.readObject();
                        mainForm.setLogs(o[0].toString());
                        mainForm.setLogs(o[1].toString());
                        
                    }
                }
              }
        } catch (Exception ex) {
                mainForm.setLogs(ex.getMessage());
        }
        
    }

    public void setIsNewMessage(boolean isNewMessage) {
        this.isNewMessage = isNewMessage;
    }
   
}
