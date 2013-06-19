package edu.tsystems.demail;

import edu.tsystems.demail.DTO.BaseDTO;
import edu.tsystems.demail.DTO.LoginDTO;
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

public class Protocol extends Thread {
    private Socket socket;
//
    public Protocol(Socket socket) {
        this.socket = socket;
        this.start();
        }
    @Override
    public void run() {
        System.out.println("Connection accepted");
        ObjectInputStream in = null;
        try {
            while (true){
                in = new ObjectInputStream(socket.getInputStream());
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());

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
                        System.out.println(loginDTO.getLogin() + " logined.");
                    } else out.writeObject("FALSE");
                }

                if (key.equals("REG")){
                    System.out.println("KEY - > REG");
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
//
//        @Override
//        public void run() {
//            System.out.println("Client is connected. Waiting a commands...");
//            ObjectInputStream in = null;
//            Properties data;
//            try {
//                while (true) {
//                    in = new ObjectInputStream(socket.getInputStream());
//                    ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
//                    data = (Properties) in.readObject();
//                    if (data.getProperty(pp.KEY).equals(pc.REG_PROFILE))
//                    {
//                        if (RegistrationService.profileChecking(data.getProperty(pp.USERNAME), data.getProperty(pp.PASSWORD), data.getProperty(pp.FIRSTNAME), data.getProperty(pp.LASTNAME), data.getProperty(pp.PHONE), parseDate(data.getProperty(pp.BIRTHDAY))) == true)
//                            out.writeObject("true");
//                        else out.writeObject("false");
//
//                    }
//                    else if (data.getProperty(pp.KEY).equals(pc.LOG_PROFILE))
//                    {
//                        java.util.List login = LoginService.connect(data.getProperty(pp.PHONE), data.getProperty(pp.PASSWORD));
//                        if (login.get(0).equals("false"))
//                            out.writeObject(Arrays.asList("false"));
//                        else out.writeObject(login);
//                    }
//                    else if (data.getProperty(pp.KEY).equals(pc.REG_MAIL))
//                    {
//                        if (RegistrationService.mailChecking(data.getProperty(pp.USERNAME), data.getProperty(pp.PASSWORD), data.getProperty(pp.PHONE)) == true)
//                            out.writeObject("true");
//                        else out.writeObject("false");
//                    }
//                    else if (data.getProperty(pp.KEY).equals(pc.GET_MAILS)) {
//                        out.writeObject(DataDAO.getMails(data.getProperty(pp.PHONE)));
//                    }
//                    else if (data.getProperty(pp.KEY).equals(pc.DELETE_MAIL)) {
//                        DataDAO.deleteMail(data.getProperty(pp.USERNAME));
//                    }
//                    else if (data.getProperty(pp.KEY).equals(pc.GET_FOLDERS)) {
//                        out.writeObject(DataDAO.getFolders(data.getProperty(pp.USERNAME)));
//                    }
//                    else if (data.getProperty(pp.KEY).equals(pc.GET_MESSAGES)) {
//                        out.writeObject(DataDAO.getMessages(data.getProperty(pp.USERNAME), data.getProperty(pp.FOLDERNAME)));
//                    }
//                    else if (data.getProperty(pp.KEY).equals(pc.NEW_FOLDER)) {
//                        DataDAO.createFolder(data.getProperty(pp.USERNAME), data.getProperty(pp.FOLDERNAME));
//                    }
//                    else if (data.getProperty(pp.KEY).equals(pc.DELETE_FOLDER)) {
//                        DataDAO.deleteFolder(data.getProperty(pp.USERNAME), data.getProperty(pp.FOLDERNAME));
//                    }
//                    else if (data.getProperty(pp.KEY).equals(pc.RENAME_FOLDER)) {
//                        DataDAO.renameFolder(data.getProperty(pp.USERNAME), data.getProperty(pp.FOLDERNAME), data.getProperty(pp.NEWFOLDERNAME));
//                    }
//                    else if (data.getProperty(pp.KEY).equals(pc.SEND_MESSAGE))
//                    {
//                        if (DataService.userSubmiting(data.getProperty(pp.FROM), data.getProperty(pp.TO), data.getProperty(pp.SUBJECT), data.getProperty(pp.TEXT)) == true)
//                            out.writeObject("true");
//                        else
//                            out.writeObject("false");
//                    }
//                    else if (data.getProperty(pp.KEY).equals(pc.DELETE_MESSAGE)) {
//                        DataDAO.delMessage(data.getProperty(pp.ID));
//                    }
//                    else if (data.getProperty(pp.KEY).equals(pc.MOVE_MESSAGE)) {
//                        DataDAO.moveMessage(data.getProperty(pp.FOLDERNAME), data.getProperty(pp.ID));
//                    }
//                }
//            }
//            catch(EOFException ex){
//                System.out.println(socket.getLocalAddress() + ":" + socket.getLocalPort() + " client is disconnected.");
//            }catch (IOException ex) {
//                ex.printStackTrace();
//            } catch (ClassNotFoundException ex) {
//                ex.printStackTrace();
//            }
//        }
//
//        private static Date parseDate(String date) {
//            try {
//                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//                java.util.Date date1 = dateFormat.parse(date);
//
//                return new java.sql.Date(date1.getTime());
//            } catch (ParseException ex) {
//                ex.printStackTrace();
//            }
//            return null;
//        }
//    }
}
