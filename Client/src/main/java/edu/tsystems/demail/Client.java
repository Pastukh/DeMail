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
    public static void sendTask(String key, BaseDTO baseDTO) throws IOException {
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(socket.getOutputStream());

            out.writeObject(key);
            out.writeObject(baseDTO);

            out.flush();

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            //System.exit(0);
        }
    }

    public static BaseDTO getAnswer()
    {
        ObjectInputStream in = null;
        try {
            in = new ObjectInputStream(socket.getInputStream());
            String key = (String)in.readObject();
            if (key.equals("TRUE")){
                return  (BaseDTO)in.readObject();
            }
            else return null;
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return null;
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