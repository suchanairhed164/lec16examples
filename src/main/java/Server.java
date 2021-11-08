import java.io.*;
import java.net.*;
public class Server {
    public static void main(String args[]) {
        try {
            ServerSocket ss = new ServerSocket(3456);
            System.out.println("Waiting...");
            Socket client=ss.accept();
            System.out.println("Connected");
            client.getOutputStream().write(10);
            client.close();
            ss.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}