package edu.tsystems.demail;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Properties;
import java.io.EOFException;

/**
 * Author: Ivan Pastukh
 * Date: 18.06.13
 * Time: 23:28
 */


    public class Protocol extends Thread {

//        private Socket socket;
//        private ProtocolCommands pc;
//        private ProtocolParameters pp;
//
//        public Protocol(Socket socket) {
//            this.socket = socket;
//            this.pc = new ProtocolCommands();
//            this.pp = new ProtocolParameters();
//            this.start();
//        }
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
