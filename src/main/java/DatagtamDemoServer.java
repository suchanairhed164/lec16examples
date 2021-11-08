import java.io.*;
import java.net.*;

class DatagramDemoServer {
    public static final int PORT = 2000;
    private static final int LENGTH_RECEIVE = 1;
    private static final byte[] answer = ("received").getBytes();
    private DatagramSocket servSocket = null;
    private boolean keepRunning = true;
    public static void main(String[] args) {
        DatagramDemoServer server = new DatagramDemoServer();
        server.service();
    }

    public DatagramDemoServer() {
        try {
            servSocket = new DatagramSocket(PORT);
        } catch(SocketException e) {
            System.err.println("Unable to open socket : " + e.toString());
        }
    }
    protected void service() {
        DatagramPacket datagram;
        InetAddress clientAddr;
        int clientPort;
        byte[] data;
        while (keepRunning) {
            try {
                data = new byte[LENGTH_RECEIVE];
                datagram = new DatagramPacket(data, data.length);
                servSocket.receive(datagram);
                clientAddr = datagram.getAddress();
                clientPort = datagram.getPort();
                data = getSendData(datagram.getData());
                datagram = new DatagramPacket(data, data.length,
                        clientAddr, clientPort);
                servSocket.send(datagram);
            } catch(IOException e) {
                System.err.println("I/O Exception : " + e.toString());
            }
        }
    }
    protected byte[] getSendData(byte b[]) {
        byte[] result = new byte[b.length+answer.length];
        System.arraycopy(b, 0, result, 0, b.length);
        System.arraycopy(answer, 0, result, b.length, answer.length);
        return result;
    }
}