package src;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Modtager {
    public static void main(String[] args) throws Exception {
        lytEfterBroadcast("Hejsa");
    }

    public static void lytEfterBroadcast(String tekst) throws Exception {
        DatagramSocket modtagerSocket = new DatagramSocket(7788);
        modtagerSocket.setBroadcast(true);
        byte[] receiveData = new byte[1024];

        System.out.println("Lytter efter broadcast.....");
        while (true) {
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            modtagerSocket.receive(receivePacket);
            String besked = new String(receivePacket.getData());
            System.out.println("Modtaget besked fra: " + receivePacket.getAddress() + ": " + besked);
            InetAddress IPAddress = receivePacket.getAddress();
            byte[] sendData = tekst.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, receivePacket.getPort());
            modtagerSocket.send(sendPacket);
        }
    }
}
