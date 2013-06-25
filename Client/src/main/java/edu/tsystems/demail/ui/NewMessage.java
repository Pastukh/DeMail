package edu.tsystems.demail.ui;

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import edu.tsystems.demail.Client;
import edu.tsystems.demail.DTO.BaseDTO;
import edu.tsystems.demail.DTO.FolderDTO;
import edu.tsystems.demail.DTO.MailDTO;
import edu.tsystems.demail.DTO.UserDTO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Author: Ivan Pastukh
 * Date: 24.06.13
 * Time: 1:38
 */
public class NewMessage {
    private JFrame frame;
    private JTextField textField1;
    private JPanel panel1;
    private JTextField textField2;
    private JTextPane textPane1;
    private JButton sendButton;
    private JButton cancelButton;
    private JLabel label2;
    private JLabel label1;
    private JLabel label3;
    private JButton moveMessageButton;
    private JButton deleteMessageButton;
    private JButton createFolderButton;
    private JButton deleteFolderButton;
    private UserDTO userDTO;
    private MailDTO mailDTO = new MailDTO();

    public NewMessage() {
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });
        moveMessageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });
        deleteMessageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });
        createFolderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });
        deleteFolderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
    }

//    private void deleteFolder() {
//        FolderDTO folderDTO = new FolderDTO();
//        folderDTO.setId(8);
//        System.out.println("delete folder id " + folderDTO.getId());
//        try {
//            System.out.println("try delete folder");
//            Client.sendTask("DELETE_FOLDER", folderDTO);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        BaseDTO baseDTO = Client.getAnswer(); //return null
//    }
//
//    private void createFolder() {
//        FolderDTO folderDTO = new FolderDTO();
//        folderDTO.setMailBoxId(1);
//        folderDTO.setName("TEST");
//        folderDTO.setSystem(false);
//        System.out.println(folderDTO);
//        try {
//            System.out.println("try create folder");
//            Client.sendTask("CREATE_FOLDER", folderDTO);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        BaseDTO baseDTO = Client.getAnswer(); //return null
//    }
//
//    private void deleteMessage() {
//        mailDTO.setId(1);
//        System.out.println(mailDTO);
//        try {
//            System.out.println("try delete message");
//            Client.sendTask("DELETE_MESSAGE", mailDTO);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        BaseDTO baseDTO = Client.getAnswer(); //return null
//    }
//
//    private void moveMessage() {
//        mailDTO.setId(1);
//        mailDTO.setToId(1); //hide user id
//        mailDTO.setSubject("Trash"); //hide FolderTo
//        System.out.println(mailDTO);
//        try {
//            System.out.println("try move message");
//            Client.sendTask("MOVE", mailDTO);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        BaseDTO baseDTO = Client.getAnswer(); //return null
//    }

    private void sendMessage() {
        mailDTO.setFromId(userDTO.getId());
        mailDTO.setFrom(userDTO.getLogin());
        mailDTO.setTo(textField1.getText());
        mailDTO.setSubject(textField2.getText());
        mailDTO.setBody(textPane1.getText());
        System.out.println(mailDTO);
        try {
            System.out.println("try send message");
            Client.sendTask("SEND", mailDTO);
        } catch (IOException e) {
            e.printStackTrace();
        }
        BaseDTO baseDTO = Client.getAnswer(); //return null
    }

    public void start(UserDTO userDTO) {
        this.userDTO = userDTO;
        frame = new JFrame("NewMessage");
        frame.setContentPane(this.panel1);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        panel1 = new JPanel();
        panel1.setLayout(new FormLayout("fill:201px:noGrow,left:4dlu:noGrow,fill:209px:grow", "center:max(d;4px):noGrow,top:4dlu:noGrow,center:max(d;4px):noGrow,top:4dlu:noGrow,center:d:noGrow,top:4dlu:noGrow,top:229px:grow,top:4dlu:noGrow,center:max(d;4px):noGrow"));
        panel1.setMinimumSize(new Dimension(300, 100));
        textField1 = new JTextField();
        textField1.setText("user@demail.de");
        CellConstraints cc = new CellConstraints();
        panel1.add(textField1, cc.xy(1, 5, CellConstraints.FILL, CellConstraints.DEFAULT));
        textField2 = new JTextField();
        textField2.setText("test subject");
        panel1.add(textField2, cc.xy(3, 5, CellConstraints.FILL, CellConstraints.DEFAULT));
        textPane1 = new JTextPane();
        textPane1.setText("test body text");
        panel1.add(textPane1, cc.xyw(1, 7, 3, CellConstraints.FILL, CellConstraints.FILL));
        sendButton = new JButton();
        sendButton.setText("Send");
        panel1.add(sendButton, cc.xy(1, 9, CellConstraints.FILL, CellConstraints.DEFAULT));
        cancelButton = new JButton();
        cancelButton.setText("Cancel");
        panel1.add(cancelButton, cc.xy(3, 9));
        label2 = new JLabel();
        label2.setName("label2");
        label2.setText("Addres to");
        panel1.add(label2, cc.xy(1, 3));
        label3 = new JLabel();
        label3.setName("label3");
        label3.setText("Subject");
        panel1.add(label3, cc.xy(3, 3));
        label1 = new JLabel();
        label1.setName("label1");
        label1.setText("From");
        panel1.add(label1, cc.xy(1, 1));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel1;
    }
}
