package Practica_17_ContarPalabras;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class ClienteCuentaPalabras {

	
	
	
	
	public static void main(String[] args) {
		try {
			DatagramSocket socket = new DatagramSocket();
			InetAddress IpServidor = InetAddress.getByName("localhost"); // Obtenemos direccio desde donde nos envian
			int puerto = 8000;
			
			String mensajeEnvio = " jamon Queso jamon queso jamon jamon ";
			
			 byte [] bufferSalida = mensajeEnvio.getBytes();
			
			DatagramPacket paqEnvio = new DatagramPacket(bufferSalida, bufferSalida.length,IpServidor,puerto);
			System.out.println("Enviando paquete....");
			socket.send(paqEnvio);
			
			
			
		
			
			System.out.println("Esperando pequete....");
			byte [] bufferEntrada = new byte [1024];
			
			DatagramPacket paqEntrada = new DatagramPacket(bufferEntrada, bufferEntrada.length );
			socket.receive(paqEntrada);
			String mensajeRecibido = new String(paqEntrada.getData()); 
			System.out.println(mensajeRecibido);
			
			socket.close();
			
			/*
			 * No funciona Bien
			 */
			
			
			
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
