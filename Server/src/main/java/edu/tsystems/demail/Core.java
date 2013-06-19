package edu.tsystems.demail;

import edu.tsystems.demail.DTO.BaseDTO;
import edu.tsystems.demail.DTO.LoginDTO;
import edu.tsystems.demail.DTO.RegDTO;
import edu.tsystems.demail.DTO.UserDTO;
import edu.tsystems.demail.services.UserService;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;

/**
 * Author: Ivan Pastukh
 * Date: 18.06.13
 * Time: 23:28
 */

public class Core extends Thread {
    private Socket socket;
//
    public Core(Socket socket) {
        this.socket = socket;
        this.start();
        }
    @Override
    public void run() {
        System.out.println("Connection accepted");
        ObjectInputStream in = null;
        ObjectOutputStream out = null;
        try {
            while (true){
                in = new ObjectInputStream(socket.getInputStream());
                out = new ObjectOutputStream(socket.getOutputStream());

                String key = (String)in.readObject();
                if (key.equals("LOGIN")){
                    boolean isLogin = false;
                    LoginDTO loginDTO = new LoginDTO();
                    loginDTO = (LoginDTO)in.readObject();
                    System.out.println("LOGIN ->" + loginDTO.getLogin());
                    UserService userService = new UserService();
                    UserDTO userDTO = userService.login(loginDTO);
                    if (userDTO != null) isLogin = true;
                    if (isLogin) {
                        out.writeObject("TRUE");
                        out.writeObject(userDTO);
                        out.flush();
                        System.out.println(loginDTO.getLogin() + " logined.");
                    } else out.writeObject("FALSE");
                }

                if (key.equals("REG")){

                    RegDTO regDTO = new RegDTO();
                    regDTO = (RegDTO)in.readObject();
                    System.out.println("Registering ->" + regDTO.getFirstname());
                    UserService userService = new UserService();
                    UserDTO userDTO = userService.createNewUser(regDTO);
                    System.out.println(userDTO + " registered");

//                    System.out.println("create defaul folder for mail box " + mailBoxDTO.getMailbox());
//                    FolderService folderService = new FolderService();
//                    folderService.createDefaultFolderForMailBoxId(mailBoxDTO);
                }
//                if (user != null){
//                    System.out.println((UserDTO)user.getLogin());
//                    System.exit(0);
//                }
                else if (key.equals("EXIT")){
                    System.out.println("EXIT");
                    System.exit(0);
                }
            }
        }
        catch(SocketException ex){
            System.out.println("Client is disconnected!");
        }catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        finally {
            System.exit(0);
        }
    }
}
