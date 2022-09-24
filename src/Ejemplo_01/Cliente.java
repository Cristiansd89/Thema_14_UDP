package Ejemplo_01;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Cliente {

	public static void main(String[] args) {

		
		final int PUERTO = 5000;
		byte [] buffer = new byte[1024];
		
		
		try {
			InetAddress direccion = InetAddress.getByName("localhost"); // Obtenemos direccio desde donde nos envian
			
			
			DatagramSocket socketUDP = new DatagramSocket(); //Establecemos conexion
			
	
			String mensaje = " Hola mundo desde cLiente";
			
			buffer = mensaje.getBytes(); // transformamos el mensaje
			
			DatagramPacket enviarPaquete = new DatagramPacket(buffer,buffer.length, direccion ,PUERTO);  ///Comprimimos el paquete
			
			System.out.println("Envio el datagrama");
			socketUDP.send(enviarPaquete);
			
			
			
			/**
			 * Flujo para recicibir paquetes 
			 */
			System.out.println("Cliente esperando Contestacion");
			DatagramPacket recibirPaquete = new DatagramPacket(buffer, buffer.length);
			
			socketUDP.receive(recibirPaquete);
			
			mensaje = new String(recibirPaquete.getData()); // Transformamos los bytes en un strign para porder leer los
			System.out.println(mensaje);
			
			socketUDP.close(); // Cerramos Conexion
			
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
