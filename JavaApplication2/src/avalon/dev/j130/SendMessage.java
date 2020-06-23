/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avalon.dev.j130;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import static java.lang.Thread.sleep;
import java.net.InetAddress;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author eag
 */
public class SendMessage extends Thread{
    Socket socket;
    String newMessage;
    ObjectOutputStream oos;

    public SendMessage(Socket socket, ObjectOutputStream oos) {
        super();
        this.socket = socket;
        this.oos = oos;
    }

    @Override
    public void run() {
        try {
            int i=0;                      
            while(true){
                sleep(1000);
                /*synchronized(this){
                    wait();
                }*/
                System.out.println("Thread Send " + i + ":" + newMessage);
                if(newMessage != null){
                    oos.writeObject(newMessage);
                    newMessage = null;
                }
                i++;
            }
        } catch (Exception ex) {
           System.out.println(ex.getMessage());
                
        } //To change body of generated methods, choose Tools | Templates.
    }

    public void setNewMessage(String newMessage) {
        this.newMessage = newMessage;
    }

    
    
}
