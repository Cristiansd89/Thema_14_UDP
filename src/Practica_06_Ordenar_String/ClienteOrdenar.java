package Practica_06_Ordenar_String;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.SocketException;
import java.net.UnknownHostException;

public class ClienteOrdenar {

	public static void main(String[] args) {
		
		
		
		try {
			DatagramSocket Socket = new DatagramSocket();
			InetAddress IpServidor = InetAddress.getLocalHost(); // Solicitamos La Ip
			int puerto = 8000;
			
			String [] palabras = {"Veronica", "Rebeca", "Beatriz", "Sofia","Ana"};
			
			/*
			 * TRansformamos el Array en bytes
			 */
			
			ByteArrayOutputStream transEnvio = new ByteArrayOutputStream();	
			ObjectOutputStream flujoSalida = new ObjectOutputStream(transEnvio);
			flujoSalida.writeObject(palabras);
			
			
			byte [] bufferSalida = transEnvio.toByteArray(); //Sacamo el tamaño del paquete
			System.out.println("Enviando paquete");
			DatagramPacket paqueteEnvio = new DatagramPacket(bufferSalida, bufferSalida.length,IpServidor,  puerto);
			Socket.send(paqueteEnvio); //Orden Envio
			
			/*
			 * Recepcion del Array
			 * 
			 */
			
			System.out.println("Entra un  Paquete");
			byte bufferEntrada [] = new byte[1024];
			
			DatagramPacket paqueteEnt = new DatagramPacket(bufferEntrada, bufferEntrada.length);
			Socket.receive(paqueteEnt);
			
			System.out.println("Has recibido un paquete de " +bufferEntrada.length + " bytes");
			ByteArrayInputStream transEntra = new ByteArrayInputStream(bufferEntrada);
			ObjectInputStream flujoEntrada = new ObjectInputStream(transEntra);
			String[] palabrasOrdenadas = (String[]) flujoEntrada.readObject();
			
			for (int i = 0;i<palabrasOrdenadas.length; i++) {
				System.out.println(palabrasOrdenadas[i]);
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
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
