import java.io.*;
import java.net.*;

public class NetClient implements Runnable {
    public static final int PORT = 2500;
    public static final String HOST = "localhost";
    public static final int CLIENTS_COUNT = 5;
    public static final int READ_BUFFER_SIZE = 10;

    private String name = null;

    public static void main(String[] args) {
        String name = "name";
        for (int i=1; i<=CLIENTS_COUNT; i++) {
            NetClient client = new NetClient(name+i);
            Thread thread = new Thread(client);
            thread.start();
        }
    }

    public NetClient(String name) {
        this.name = name;
    }

    public void run() {
        char[] readed = new char[READ_BUFFER_SIZE];
        StringBuffer strBuff = new StringBuffer();
        try {
            Socket socket = new Socket(HOST, PORT);
            InputStream in = socket.getInputStream();
            InputStreamReader reader = new InputStreamReader(in);
            while (true) {
                int count = reader.read(readed, 0,
                        READ_BUFFER_SIZE);
                if (count==-1) break;
                strBuff.append(readed, 0, count);
                Thread.yield();
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("client " + name + " read : " + strBuff.toString());
    }
}