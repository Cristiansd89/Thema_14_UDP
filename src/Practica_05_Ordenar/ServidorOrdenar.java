package Practica_05_Ordenar;

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
			System.out.println("Esperando Datagrama....");
			
			byte [] bufferEntrada = new byte[1024];
			DatagramPacket paqEntrada = new DatagramPacket(bufferEntrada, bufferEntrada.length);
			
			serverSocket.receive(paqEntrada);
			// Creamos el transformador y le pasamos el array de bytes para que lo transforme
			System.out.println("Has recibido un paquete de " +bufferEntrada.length + " bytes");
			ByteArrayInputStream transEntrada = new ByteArrayInputStream(bufferEntrada); 
			ObjectInputStream flujoEntrada = new ObjectInputStream(transEntrada);
			int [] desOrdenado = (int[]) flujoEntrada.readObject();
			
			
			boolean intercambio;
			do {
				intercambio = false;
				
				for(int i = 0; i< desOrdenado.length-1 ; i++) {
					
					if(desOrdenado[i]> desOrdenado[i+1]) {
						int aux = desOrdenado[i+1];
						desOrdenado[i+1] = desOrdenado[i];
						desOrdenado [i] = aux;
						intercambio = true;
					}
				}
				
			}while(intercambio);
			 
			
			/*
			 * Devolver el Array
			 */
			
			InetAddress IpOrigen = paqEntrada.getAddress();
			int puerto = paqEntrada.getPort();
			ByteArrayOutputStream transEnvio = new ByteArrayOutputStream();
			ObjectOutputStream flujoSalida = new ObjectOutputStream(transEnvio);
			flujoSalida.writeObject(desOrdenado);
			flujoSalida.close();
			
			
			byte[] bufferSalida = transEnvio.toByteArray();
			System.out.println(" Devolviendo el paquete "  +bufferSalida.length +" Bytes");
			DatagramPacket paqEnvio = new DatagramPacket(bufferSalida, bufferSalida.length, IpOrigen,puerto);
			serverSocket.send(paqEnvio);
			serverSocket.close();
			
			
			
			
			
			
			
			
		} catch (SocketException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
