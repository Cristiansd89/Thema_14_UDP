package Practica_01_UDP_Gonzalo;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class TEnviaUDP {

    View view;

    public TEnviaUDP(String mensaje, String host, int puerto, View view) {
        this.view = view;
        byte[] array = mensaje.getBytes();

        try {
            DatagramSocket datagramSocket = new DatagramSocket();
            DatagramPacket datagramPacket = new DatagramPacket(array, array.length, InetAddress.getByName(host), puerto);
            datagramSocket.send(datagramPacket);
            this.view.textArea1.setText("");
            datagramSocket.close();
        }
        catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }




}
