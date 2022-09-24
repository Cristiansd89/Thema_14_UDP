package Practica_08_CuantasLetras;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.Buffer;

public class ClienteCuenta {

	 private static DatagramSocket socket;
	
	public static void main(String[] args) {
		
		String mensaje = "Hola que tal soy el chico de las poesias";
		
		enviarString(mensaje);
		
		String paquete = recibir();
		
		System.out.println(paquete);
		socket.close();
	}
	
	
	public static void enviarString(String cadena) {
		
		try {
			 socket =  new DatagramSocket();
			InetAddress IpServidor = InetAddress.getLocalHost();
			int puerto = 8000;
			
			
			
			byte [] bufferSalida = cadena.getBytes();
			DatagramPacket paqEnvio = new DatagramPacket(bufferSalida, bufferSalida.length,IpServidor,puerto);
			System.out.println("Enviando paquete....");
			socket.send(paqEnvio);
			


			
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
	
	public static String recibir() {
		
		String mensaje = null;
		
		
		
		try {
			socket = new DatagramSocket();
			byte [] bufferEntrada = new byte [1024];
			System.out.println("Esperando pequete....");
			
			DatagramPacket paqEntrada = new DatagramPacket(bufferEntrada, bufferEntrada.length );
			socket.receive(paqEntrada);
			mensaje = new String(paqEntrada.getData()); 
			System.out.println(mensaje);
			
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return mensaje;
		
	}

}
