import java.io.*;
import java.net.*;

public class NetServer {
    public static final int PORT = 2500;
    private static final int TIME_SEND_SLEEP = 100;
    private static final int COUNT_TO_SEND = 10;
    private ServerSocket servSocket;

    public static void main(String[] args) {
        NetServer server = new NetServer();
        server.go();
    }

    public NetServer() {
        try{
            servSocket = new ServerSocket(PORT);
        } catch(IOException e) {
            System.err.println("Unable to open Server Socket : " + e.toString());
        }
    }

    public void go() {

        // Класс-поток для работы с
        //подключившимся клиентом
        class Listener implements Runnable {
            Socket socket;
            public Listener(Socket aSocket) {
                socket = aSocket;
            }
            public void run() {
                try {
                    System.out.println("Listener started");
                    int count = 0;
                    OutputStream out = socket.getOutputStream();
                    OutputStreamWriter writer = new
                            OutputStreamWriter(out);
                    PrintWriter pWriter = new PrintWriter(writer);
                    while (count<COUNT_TO_SEND) {
                        count++;
                        pWriter.print(((count>1)?",":"")+ "Say" + count);
                        sleeps(TIME_SEND_SLEEP);
                    }
                    pWriter.close();
                } catch(IOException e) {
                    System.err.println("Exception : " + e.toString());
                }
            }
        }

        // Основной поток, циклически выполняющий метод accept()
        System.out.println("Server started");
        while (true) {
            try {
                Socket socket = servSocket.accept();
                Listener listener = new Listener(socket);
                Thread thread = new Thread(listener);
                thread.start();
            } catch(IOException e) {
                System.err.println("IOException : " + e.toString());
            }
        }
    }

    public void sleeps(long time) {
        try {
            Thread.sleep(time);
        } catch(InterruptedException e) {
        }
    }
}