package practica_01_UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class TRecibeUDP  {
	int puerto;
	int tamMens;
	
	public TRecibeUDP (int puerto,int tamMens) {
		this.puerto = puerto;
		this.tamMens = tamMens;
	}
	
	public void recibe(int puerto, int tamMens) {
		
		try {
			
			DatagramSocket socket = new DatagramSocket(puerto);
			byte [] buffer = new byte[tamMens];
			
			DatagramPacket paqueteRecibido = new DatagramPacket(buffer, tamMens); // Flujo para recibir bytes
			socket.receive(paqueteRecibido); 
			
			String mensaje = new String(paqueteRecibido.getData()); // transformamos  los bytes en un mensaje
			
			
			
			
		} catch (SocketException e) {

			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
