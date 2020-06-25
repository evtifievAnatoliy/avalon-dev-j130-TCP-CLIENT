/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avalon.dev.j130;

import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author eag
 */
public class ReadMessage extends Thread{
    Socket socket;
    ObjectInputStream ois;
    MainForm mainForm;
    
    
    public ReadMessage(Socket socket, ObjectInputStream ois, MainForm mainForm) {
        super();
        this.socket = socket;
        this.ois = ois;
        this.mainForm = mainForm;
    }

    @Override
    public void run() {
        try {
            int i=0;                      
            while(true){
                sleep(1000);
                System.out.println("Thread Read " + i);
                Object[] o = (Object[]) ois.readObject();
                System.out.println("Thread Read !!! " + o.toString());
                mainForm.setLogs(o[1].toString() + "   " + o[0].toString());
                i++;
            }
        } catch (Exception ex) {
           mainForm.setLogs("Thread Read Error: " + ex.getMessage() + " Please close your program!!!");
           
           
        } //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
