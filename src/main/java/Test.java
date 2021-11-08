import java.io.IOException;
import java.net.*;

public class Test {
    try{
        DatagramSocket s = null;
        try {
            s = new DatagramSocket();
        } catch (SocketException socketException) {
            socketException.printStackTrace();
        }
        byte data[]={1, 2, 3};
        InetAddress addr =
                null;
        try {
            addr = InetAddress.getByName("localhost");
        } catch (UnknownHostException unknownHostException) {
            unknownHostException.printStackTrace();
        }
        DatagramPacket p =
                new DatagramPacket(data, 3, addr, 3456);
        try {
            s.send(p);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        System.out.println("Datagram sent");
        s.close();
    } catch (SocketException e) {
        e.printStackTrace();
    } catch (UnknownHostException e1) {
        e1.printStackTrace();
    } catch (
    IOException e2) {
        e2.printStackTrace();
    }
}
