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



public class ClientController {
    
    private MainForm mainForm;
    
    Socket socket;
    ObjectOutputStream oos;
    ObjectInputStream ois;
    SendMessage sendMessage;
    ReadMessage readMessage;
    
        
    String clientName;
    String newMessage = null;
    
    
    public ClientController(MainForm mainForm, String clientName) throws UnknownHostException, IOException, InterruptedException{
        this.mainForm = mainForm;
        
        try{
        socket = new Socket(InetAddress.getLocalHost(), 7_020);
            ObjectOutputStream oos = null;// = new ObjectOutputStream(socket.getOutputStream()); 
            ObjectInputStream ois = null; //new ObjectInputStream(socket.getInputStream());
            mainForm.setLogs("Server: " + socket.getInetAddress() + ":" + socket.getPort() + " connected.");
            
            sendMessage = new SendMessage(socket);
            sendMessage.start();
                        
            readMessage = new ReadMessage(socket, mainForm);
            readMessage.start();
        }
        catch (Exception ex){
            mainForm.setLogs("Connection with server Error!!! " + "Please close your program!!!");
        }
        
    }

    

    public void setNewMessage(String newMessage) {
        this.newMessage = newMessage;
        sendMessage.setNewMessage(newMessage);
        
    }

    public String getNewMessage() {
        return newMessage;
    }

    public void closeConnection() throws IOException {
        this.socket.close();
    }
    
    
}
