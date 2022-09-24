package Practica_06_Ordenar_String;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class ServidorOrdenar {

	public static void main(String[] args) {

		try {
			DatagramSocket serverSocket = new DatagramSocket(8000);
			
			byte [] bufferEntrada = new byte [1024]; // Tamaño del buffer de entrada
			System.out.println("Esperando DataGrama.....");
			DatagramPacket paqueteEntrada = new DatagramPacket(bufferEntrada, bufferEntrada.length);
			serverSocket.receive(paqueteEntrada);
			
			System.out.println(" Ha entrado un paquete de...." +bufferEntrada.length +"bytes");
			ByteArrayInputStream transEntrada = new ByteArrayInputStream(bufferEntrada);
			ObjectInputStream flujoEntrada = new ObjectInputStream(transEntrada);
			String [] palabras =  (String[]) flujoEntrada.readObject();
			
			boolean hayIntercambio;
			do {
				hayIntercambio = false;
				
				for(int i = 0; i < palabras.length -1; i++){
					
					if(palabras[i].compareTo(palabras[i+1])>0) {
						String aux = palabras[i+1];
						palabras[i+1] = palabras[i];
						palabras[i] = aux;
						hayIntercambio = true;
					}
				}
				
			}while(hayIntercambio);
			
			
			System.out.println("Devolviendo Paquete...");
			ByteArrayOutputStream transEnvio = new ByteArrayOutputStream();
			ObjectOutputStream flujoSalida = new ObjectOutputStream(transEnvio);
			flujoSalida.writeObject(palabras);
			flujoSalida.close();
			
			
			InetAddress IpOrigen = paqueteEntrada.getAddress();
			int puerto = paqueteEntrada.getPort();
			
			byte [] bufferSalida = transEnvio.toByteArray();
			DatagramPacket paqEnvio = new DatagramPacket(bufferSalida, bufferSalida.length,IpOrigen,puerto);
			serverSocket.send(paqEnvio);
			serverSocket.close();
			
			
			
			
			
			
			
			
			
			
			
			
			
		} catch (SocketException e) {
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
