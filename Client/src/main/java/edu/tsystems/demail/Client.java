package edu.tsystems.demail;


import edu.tsystems.demail.DTO.BaseDTO;
import edu.tsystems.demail.DTO.UserDTO;
import edu.tsystems.demail.ui.EnterForm;
import edu.tsystems.demail.ui.MainForm;
import edu.tsystems.demail.ui.NewMessage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;


/**
 * Author: Ivan Pastukh
 * Date: 18.06.13
 * Time: 23:41
 */

public class Client{
    public static Socket socket;

    public static void main(String[] args) throws IOException {
       Client.socket = new Socket("localhost", 3434);
//       App.main(args);
        new EnterForm().start();
//        UserDTO userDTO = new UserDTO();
//        userDTO.setId(1);
//        userDTO.setLogin("user");
//        new NewMessage().start(userDTO);
    }

    public static void sendTask(String command, BaseDTO baseDTO) throws IOException {
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(socket.getOutputStream());
            out.writeObject(command);
            out.writeObject(baseDTO);
            out.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static BaseDTO getAnswer() {
        ObjectInputStream in = null;
        String command;
        try {
            in = new ObjectInputStream(socket.getInputStream());
            command = (String)in.readObject();
            if (command.equals("OK"))
                return (BaseDTO)in.readObject();
            else if (command.equals("ERR"))
                System.out.println("ERROR! Server return ERR.");
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static List<BaseDTO> getList() {
        ObjectInputStream in = null;
        String command;
        try {
            in = new ObjectInputStream(socket.getInputStream());
            command = (String)in.readObject();
            if (command.equals("LIST"))
                return (List<BaseDTO>)in.readObject();
            else if (command.equals("ERR"))
                System.out.println("ERROR! Server return ERR.");
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

}