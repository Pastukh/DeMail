package edu.tsystems.demail;

import edu.tsystems.demail.DTO.LoginDTO;
import edu.tsystems.demail.DTO.UserDTO;
import edu.tsystems.demail.model.UserDAO;

import javax.swing.*;
import java.awt.event.*;

/**
 * Created with IntelliJ IDEA.
 * User: B
 * Date: 06.06.13
 * Time: 14:21
 * To change this template use File | Settings | File Templates.
 */
class Presentation
{
    private JButton button1;
    private JPanel panel1;
    private JTextField textField1;
    private JTextField textField2;
    private JLabel ConStatus;
    private JLabel label1;
    public JFrame frame;

    public Presentation() {
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserService userService = new UserService();
                LoginDTO loginDTO = new LoginDTO();
                UserDTO userDTO = new UserDTO();
                loginDTO.setLogin(textField1.getText());
                loginDTO.setPassword(textField2.getText());
                userDTO = userService.login(loginDTO);
                if (userDTO!=null) ConStatus.setText(userDTO.getFirstname() + userDTO.getLastname() + " is connected"); //JOptionPane.showMessageDialog(frame,userDTO.getLastname());
            }
        });

    }


    public void start () {
        createUIComponents();
    }

    private void createUIComponents() {
        frame = new JFrame("DeMail");
        frame.setContentPane(new Presentation().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
