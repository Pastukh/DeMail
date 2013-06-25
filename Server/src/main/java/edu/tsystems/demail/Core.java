package edu.tsystems.demail;

import edu.tsystems.demail.DTO.*;
import edu.tsystems.demail.services.FolderService;
import edu.tsystems.demail.services.MailBoxService;
import edu.tsystems.demail.services.MailService;
import edu.tsystems.demail.services.UserService;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

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
        System.out.println("Connection is accepted");
        Server.clientCount++;
        ObjectInputStream in = null;
        ObjectOutputStream out = null;
        BaseDTO baseDTO = null;
        List<BaseDTO> list = null;
        String command;
        try {
            while (true){
                in = new ObjectInputStream(socket.getInputStream());
                out = new ObjectOutputStream(socket.getOutputStream());

                command = (String)in.readObject();
                baseDTO = (BaseDTO)in.readObject();

                if (command.equals("LOGIN"))
                    baseDTO = setLogin((LoginDTO)baseDTO); //return userDTO

                if (command.equals("FIND_USER"))
                    baseDTO = findUser((LoginDTO)baseDTO); //return LoginDTO

                if (command.equals("REG"))
                    baseDTO = regUser((RegDTO)baseDTO);

                if (command.equals("SEND"))
                    baseDTO = sendMessage((MailDTO) baseDTO);

                if (command.equals("MOVE"))
                    baseDTO = moveMessage((MailDTO) baseDTO);

                if (command.equals("DELETE_MESSAGE"))
                    baseDTO = deleteMessage((MailDTO) baseDTO);

                if (command.equals("CREATE_FOLDER"))
                    baseDTO = createFolder((FolderDTO) baseDTO);

                if (command.equals("DELETE_FOLDER"))
                    baseDTO = deleteFolder((FolderDTO) baseDTO);

                if (command.equals("DO_FIRST_MESSAGE"))
                    baseDTO = createFirstMail((MailBoxDTO) baseDTO);//return userDTO

                if (command.equals("GET_FOLDERS")){
                    list = getFolders((UserDTO)baseDTO);
                    baseDTO = null;
                }
                if (command.equals("GET_MAILS")){
                    list = getMails((FolderDTO)baseDTO);
                    baseDTO = null;
                }
                if (command.equals("EXIT")){
                    System.out.println("EXIT");
                    System.exit(0);
                }

                if (baseDTO != null){
                    out.writeObject("OK");
                    out.writeObject(baseDTO);
                    out.flush();
                    baseDTO = null;
                } else if (list !=null){
                    out.writeObject("LIST");
                    out.writeObject(list);
                    out.flush();
                    list = null;
                    baseDTO = null;
                }
                else {
                    out.writeObject("ERR");
                    out.flush();
                }


            }
        }
        catch(SocketException ex){
            System.out.println("Client is disconnected!");
        }catch (IOException ex) {
            System.out.println("Couldn't listen to port 3434");
            System.exit(-1);
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
        ex.printStackTrace();
        }
        finally {
            if (Server.clientCount > 1) {
                byte c = --Server.clientCount;
                System.out.println("Client count -> " + c);
            } else {
                System.out.println("Server is shutdown.");
                System.exit(0);
            }

        }
    }
    private BaseDTO sendMessage(MailDTO mailDTO){
        System.out.println("send message----------------");
        new MailService().sendMail(mailDTO);
        System.out.println("send message----------------");
        return null;
    }
    private BaseDTO moveMessage(MailDTO mailDTO){
        System.out.println("move message----------------");
        new MailService().moveMailToFolder(mailDTO);
        System.out.println("move message----------------");
        return null;
    }
    private BaseDTO deleteMessage(MailDTO mailDTO){
        System.out.println("delete message----------------");
        new MailService().removeMail(mailDTO);
        System.out.println("delete message----------------");
        return null;
    }
    private BaseDTO createFolder(FolderDTO folderDTO){
        System.out.println("create folder----------------");
        new FolderService().createFolder(folderDTO);
        System.out.println("create folder----------------");
        return null;
    }
    private BaseDTO deleteFolder(FolderDTO folderDTO){
        System.out.println("delete folder----------------");
        new FolderService().removeFolder(folderDTO.getId());
        System.out.println("delete folder----------------");
        return null;
    }

    private List<BaseDTO> getMails(FolderDTO folderDTO){
        System.out.println("Start converting " + folderDTO);
        List<BaseDTO> list = new ArrayList<BaseDTO>();
        List<MailDTO> mails = new MailService().getMailByFolder(folderDTO.getId(),folderDTO.getUserId());
        for (MailDTO mail: mails){
            list.add(mail);
            System.out.println(mail);
        }
        if (mails != null) return list;
        else System.out.println("Mails does not exists!");
        return null;
    }
    private List<BaseDTO> getFolders(UserDTO userDTO){
        List<BaseDTO> list = new ArrayList<BaseDTO>();
        List<FolderDTO> folders = new FolderService().getFoldersByUserId(userDTO.getId());
        for (FolderDTO folder: folders){
            list.add(folder);
        }
        if (folders != null) return list;
            else System.out.println("Folders does not exists!");
        return null;

    }
    private UserDTO setLogin(LoginDTO loginDTO){
        UserDTO userDTO = null;
        UserService userService = new UserService();
        userDTO = userService.login(loginDTO);
        if (userDTO != null)
            System.out.println(loginDTO.getLogin() + " is logined.");

        return userDTO;
    }
    private LoginDTO findUser (LoginDTO loginDTO){
        UserService userService = new UserService();
        UserDTO userDTO = null;
        userDTO = userService.getUserByLogin(loginDTO.getLogin());

        if (userDTO != null) loginDTO.setLogin(userDTO.getLogin());
        else return null;
        System.out.println("Find user " + loginDTO.getLogin());
    return loginDTO;
    }

    private UserDTO regUser (RegDTO regDTO){
            UserService userService = new UserService();
            UserDTO userDTO = null;
            userDTO = userService.createNewUser(regDTO);

        if (userDTO != null) {
            System.out.println("User " + userDTO.getLogin() + " registered.");

            MailBoxService mailBoxService = new MailBoxService();
            MailBoxDTO mailBoxDTO = new MailBoxDTO();
            mailBoxDTO = mailBoxService.createMailBox(userDTO);
            FolderService folderService = new FolderService();
            folderService.createDefaultFolderForMailBoxId(mailBoxDTO);
            System.out.println("create defaul folder for mail box " + mailBoxDTO.getMailbox());
        }

    return userDTO;
    }

   private BaseDTO createFirstMail(MailBoxDTO mailBoxDTO) {
       System.out.println("create first mail");
       new MailService().createFirstMail(mailBoxDTO);
   return new BaseDTO();
    }
}
