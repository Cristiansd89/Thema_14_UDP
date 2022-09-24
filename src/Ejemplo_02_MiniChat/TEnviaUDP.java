package Ejemplo_02_MiniChat;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.concurrent.ThreadFactory;

public class TEnviaUDP  {
	
	String mensaje;
	int tamMen;
	String hostDestino;
	int puerto;
	
	
	public TEnviaUDP(String mensaje,int tamMen, int puerto) {
		this.mensaje= mensaje;
		this.tamMen = 512;
		this.hostDestino = "localhost";
		
	}
	
	
	public static void envia (String mensaje, int tamMen, String  hostDestino, int puerto) {
		
		
		
	
		
		try {
			
			DatagramSocket socketUDP = new DatagramSocket(); //Establecemos Conexion
			byte [] buffer = new byte[tamMen];
			buffer = mensaje.getBytes(); // Transformamos el mensaje en un Array de bytes
			InetAddress direccion = InetAddress.getByName(hostDestino); // Obtenemos direccio desde donde nos envian
			DatagramPacket paqueteEnvio = new DatagramPacket(buffer, buffer.length, direccion ,puerto);
			socketUDP.send(paqueteEnvio);
			
			
			
			
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
