import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPBroadcast {

    public static void main(String[] args) {

    }

    public void udsendBroadcast(String t) throws IOException {
        DatagramSocket serverSocket = new DatagramSocket(9876);
        byte[] receiveData = new byte[1024];
        byte[] sendData = new byte[1024];
        while (true) {
            DatagramPacket receivePacket = new DatagramPacket(receiveData,
                    receiveData.length);
            serverSocket.receive(receivePacket);
            String sentence = new String(receivePacket.getData());
        }
    }
}
