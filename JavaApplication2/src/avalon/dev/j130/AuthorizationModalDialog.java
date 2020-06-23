/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avalon.dev.j130;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class AuthorizationModalDialog extends JDialog{
    
    private JPanel controlPane;
    private JTextField userNameTextField;
    
    private boolean okPressed;
    private String userName;
    
    public AuthorizationModalDialog(Frame owner, String title){
        super(owner, title, true);
        setBounds(350, 250, 400, 400);
        
        controlPane = new JPanel();
        controlPane.setLayout(new BoxLayout(controlPane, BoxLayout.Y_AXIS));
        userNameTextField = new JTextField(20);
        JLabel lbl = new JLabel("Enter user name: ");
        lbl.setLabelFor(userNameTextField);
        controlPane.add(lbl);
        controlPane.add(userNameTextField);
        add(controlPane, BorderLayout.CENTER);
        
        JPanel botton = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton btnOk = new JButton("OK");
        botton.add(btnOk);
        btnOk.addActionListener(e ->{
            okPressed = true;
            userName = userNameTextField.getText();
            
            setVisible(false);
        });
        
        JButton btnCancel = new JButton("Cancel");
        botton.add(btnCancel);
        btnCancel.addActionListener(e ->{
            okPressed = false;
            setVisible(false);
        });
        
        add(botton, BorderLayout.SOUTH);
        pack();

    }

    public String getUserName() {
        return userName;
    }
    
    protected JPanel getControlsPane(){
        return controlPane;
    }
    
    public boolean isSuccess(){
        return okPressed;
    }
    
    
    
}
