package Practica_19_Contraseña;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ClienteContraseña {

	public static void main(String[] args) {
		
		Scanner ent = new Scanner(System.in);
		DatagramSocket socket;
		try {
			socket = new DatagramSocket();
			InetAddress IpServidor = InetAddress.getByName("localhost"); // Obtenemos direccio desde donde nos envian
			int puerto = 8000;
			
			System.out.println("Introduce la contraseña :");
			String contraseña = ent.next();
			 
			
			 byte [] bufferSalida = contraseña.getBytes();
			
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
