package edu.tsystems.demail;

import edu.tsystems.demail.DTO.BaseDTO;
import edu.tsystems.demail.DTO.LoginDTO;

import java.util.List;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Properties;


/**
 * Author: Ivan Pastukh
 * Date: 18.06.13
 * Time: 23:41
 */

public class Client{
    public static Socket socket;

    public static void main(String[] args) throws IOException {

       Client.socket = new Socket("localhost", 1234);

//        LoginDTO user = new LoginDTO();
//        user.setLogin("IVAN PASTUKH");
//        user.setPassword("123");
//
//        sendTask("REG", user);

        App.main(args);
    }
    public static boolean sendTask(String key, BaseDTO baseDTO) throws IOException {
        ObjectOutputStream out = null;
        ObjectInputStream in = null;
        try {
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());

            out.writeObject(key);
            out.writeObject(baseDTO);

            out.flush();

            if (in.readObject().toString().equals("TRUE")) return true;
            else return false;
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            //System.exit(0);
        }
        return false;
    }

    public static boolean getAnswer(Properties data)
    {
        ObjectOutputStream out = null;
        ObjectInputStream in = null;
        try {
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
            out.writeObject(data);
            out.flush();
            if(in.readObject().toString().equals("true"))
                return true;
            else return false;
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public static List getList(Properties data)
    {
        ObjectInputStream in = null;
        ObjectOutputStream out = null;
        try
        {
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
            out.writeObject(data);
            out.flush();
            return (List) in.readObject();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static void sendAction(Properties data)
    {
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            out.writeObject(data);
            out.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}