/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avalon.dev.j130;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author eag
 */
public class Main{
    
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        
        
        MainForm mainForm = new MainForm();
        
        
        /*
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
        }*/
    }
}

