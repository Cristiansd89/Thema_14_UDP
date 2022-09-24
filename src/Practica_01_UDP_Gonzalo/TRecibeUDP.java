package Practica_01_UDP_Gonzalo;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class TRecibeUDP {

    View view;


    public TRecibeUDP(View view, int puerto){

        this.view = view;
        byte[] buffer = new byte[512];
        try {
            DatagramSocket datagramSocket = new DatagramSocket(puerto);
            DatagramPacket datosRecibidos = new DatagramPacket(buffer, buffer.length);

            while(true){

                datagramSocket.receive(datosRecibidos);
                String mensajeRecibido = new String(datosRecibidos.getData(), 0, datosRecibidos.getLength());
                view.textArea2.append(mensajeRecibido + "\n");

            }

        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}
