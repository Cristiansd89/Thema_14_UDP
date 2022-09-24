package Ejemplo_02_MiniChat;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Observable;

public class Servidor extends Observable implements Runnable {
	

	
	public Servidor (int puerto) {
		
	}
	@Override
	public void run() {
		
	
		final int PUERTO = 5000;
		byte [] buffer = new byte[502];
		
		
		try {
			System.out.println("Iniciado el servidor :");
			DatagramSocket sockecUDP = new DatagramSocket(PUERTO); // Establece la conexion con el puerto
			
			while(true) {
				/*
				 * para recibir paquetes Creamos un DatagramPacket y le pasado el buffer y su tamaño
				 * Viene todo lo necesario para la conexion
				 */
				DatagramPacket paqueteRecib = new DatagramPacket(buffer, buffer.length); 
				sockecUDP.receive(paqueteRecib);// con esto recicibimos los paquetes
				
				
				String mensaje = new String(paqueteRecib.getData()); // Transformamos el paquete de bytes en un String  para poder mostralo por pantalla
				System.out.println(mensaje);
				
				/*
				 * Con esto sacamos la direccion que ha contactado con nosotros
				 */
				int puertoCliente = paqueteRecib.getPort();
				InetAddress direccion = paqueteRecib.getAddress();
				
				/**
				 * 
				 * Para Enviar paquete
				 */
				
				
			
				mensaje = "HOla mundo";
				buffer = mensaje.getBytes(); // Transformamos el mensaje en bytes para poder enviarlo
				
				
				/*
				 * Para enviar paquete mandamos el buffer, su tamañ, direccion y puerto al que enviamos
				 */
				
				DatagramPacket enviarPaquete = new DatagramPacket(buffer, buffer.length, direccion, PUERTO);
				
				sockecUDP.send(enviarPaquete); // Enviamos el paquete
			}
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
