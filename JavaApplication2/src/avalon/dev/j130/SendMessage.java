/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avalon.dev.j130;

import java.io.ObjectOutputStream;
import java.net.Socket;


public class SendMessage extends Thread{
    private Socket socket;
    private String newMessage;
    private Object monitor = new Object();

    public SendMessage(Socket socket) {
        super();
        this.socket = socket;
    }

    @Override
    public void run() {
        try (ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream())){
            int i=0;                      
            while(true){
                //sleep(500);
                
                System.out.println("Thread Send " + i + ":" + newMessage);
                if(newMessage != null){
                    oos.writeObject(newMessage);
                    newMessage = null;
                }
                i++;
                synchronized(monitor){
                    monitor.wait();
                }
            }
        } catch (Exception ex) {
           System.out.println("Thread Send Error: " + ex.getMessage());
           
           
        } //To change body of generated methods, choose Tools | Templates.
    }

    public void setNewMessage(String newMessage){
        this.newMessage = newMessage;
        try{
            synchronized(monitor){
                    monitor.notifyAll();
            }   
        }
        catch(Exception ex){
            System.out.println("Thread Send notify Error: " + ex.getMessage());
        }
                
    }

    
    
}
