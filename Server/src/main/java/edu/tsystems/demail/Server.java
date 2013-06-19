package edu.tsystems.demail;

/**
 * Author: Ivan Pastukh
 * Date: 18.06.13
 * Time: 23:12
 */
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Server {
    private static ServerSocket ss;

    public static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");

    public static void main(String[] args) throws IOException {
        try {
            ss = new ServerSocket(1234);
            System.out.println("Server is started!");
            System.out.println("Waiting a client....");
            while (true)
            {
                Socket socket = null;
                while(socket == null){
                    socket = ss.accept();
                }
                new Protocol(socket);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        finally {
           ss.close();
        }
    }
}