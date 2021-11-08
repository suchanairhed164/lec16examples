import java.io.*;
import java.net.*;
public class Client {
    public static void main(String args[]) {
        try {
            Socket s = new Socket("localhost", 3456);
            InputStream is = s.getInputStream();
            System.out.println("Read: "+is.read());
            s.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}