package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

public class Sender {
    public static void main(String[] args) throws Exception {
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
        udsendBroadcast(inFromUser.readLine());
    }

    public static void udsendBroadcast(String tekst) throws Exception {
        DatagramSocket senderSocket = new DatagramSocket();
        senderSocket.setBroadcast(true);
        InetAddress IPAddress = InetAddress.getByName("255.255.255.255");
        byte[] sendData = tekst.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 7788);

        boolean modtagetSvar = false;
        for (int i = 0; i < 3 && !modtagetSvar; i++) {
            System.out.println("Sender...." + tekst);
            senderSocket.send(sendPacket);

            senderSocket.setSoTimeout(2000);
            try {
                byte[] receiveData = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                senderSocket.receive(receivePacket);
                String svar = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println("Svar modtaget fra: " + receivePacket.getAddress() + ": " + svar);
                modtagetSvar = true;
            } catch (SocketTimeoutException e) {
                System.out.println("intet svar");
            }
        }
    }
}
