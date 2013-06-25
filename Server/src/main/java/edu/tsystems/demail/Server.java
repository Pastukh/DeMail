package edu.tsystems.demail;

/**
 * Author: Ivan Pastukh
 * Date: 18.06.13
 * Time: 23:12
 */
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static edu.tsystems.demail.Client.*;

public class Server {
    public static byte clientCount;
    private static ServerSocket ss;

//    public static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");

    public static void main(String[] args) throws IOException {
        try {
            ss = new ServerSocket(3434);
            System.out.println("Server running!");
//            Client.main(args);
//            Runtime.getRuntime().exec("java.exe -jar C:/Users/B/IdeaProjects/DeMail/Client/target/Client-1.0.jar");
            EntityManager em = Persistence.createEntityManagerFactory("test").createEntityManager();

            while (true){
                Socket socket = ss.accept();
                new Core(socket);
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        finally {
           ss.close();
        }

    }
}