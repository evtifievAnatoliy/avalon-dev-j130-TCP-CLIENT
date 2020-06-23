/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avalon.dev.j130;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;


public class MainForm extends JFrame{
    
    private JTextField message;
    private JButton sentbtn;
    private JTextArea logs;
    private JButton exbtn;
    
    private String messageStr;
    private String userName;
    private boolean isNewMessage = false;
    
    public MainForm() throws IOException {
        
        super("TCP-Client");
        setBounds(300, 200, 800, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        AuthorizationModalDialog authorizationModalDialog = new AuthorizationModalDialog(this,"User authorization");
                authorizationModalDialog.setVisible(true);
                if (authorizationModalDialog.isSuccess()){
                    userName = authorizationModalDialog.getUserName();
                }
                else{
                    System.exit(0);
                }
        
        Container c = getContentPane();
        
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        message = new JTextField(50);
        JLabel lbl = new JLabel(userName + ": ");
        lbl.setLabelFor(message);
        jPanel.add(lbl);
        jPanel.add(message);
        sentbtn = new JButton("Sent");
        sentbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                messageStr = message.getText();
                isNewMessage = true;
                
            }
        });
        jPanel.add(sentbtn);
        c.add(jPanel, BorderLayout.NORTH);
        
        
        
        jPanel = new JPanel();
        jPanel.setLayout(new BorderLayout());
        logs = new JTextArea();
        jPanel.add(logs);
        c.add(new JScrollPane(jPanel));
        
        jPanel = new JPanel();
        exbtn = new JButton("Exit");
        exbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        jPanel.add(exbtn);
        c.add(jPanel, BorderLayout.SOUTH);
        
        setVisible(true);
        
    }

    public boolean isIsNewMessage() {
        return isNewMessage;
    }
    
    
    
    public void setLogs(String string) {
        SwingUtilities.invokeLater(()->{
                this.logs.append(string);
                this.logs.append("\n");
            });
        
    }

    public void setIsNewMessage(boolean isNewMessage) {
        this.isNewMessage = isNewMessage;
    }

    public String getMessage() {
        return messageStr;
    }

    public String getUserName() {
        return userName;
    }
    
    
    
    
}
