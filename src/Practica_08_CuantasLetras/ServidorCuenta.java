package Practica_08_CuantasLetras;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class ServidorCuenta {
	
	private static DatagramSocket serverSocket;
	
	private static DatagramPacket paqEntrada;
	
	public static void main(String[] args) {
		 String mensaje = recibir();
		 
		  int contador = 0;
		  for(int i = 0 ;i < mensaje.length();i++) {
			  contador++;
		  }
		  
		  String num = String.valueOf(contador); // transformamos  el contador en una cadena
		  
		 
		 
		 
			 devolver(num);
		 

		
		
	}
	
	public static String  recibir() {
		
		String mensaje = null;
		
		try {
			 serverSocket = new DatagramSocket(8000);
			byte [] bufferEntrada = new byte [1024];
			System.out.println("Esperando pequete....");
			 paqEntrada = new DatagramPacket(bufferEntrada, bufferEntrada.length );
			serverSocket.receive(paqEntrada);
			mensaje = new String(paqEntrada.getData()); // Transformamos los bytes en un strign para porder leer los
			// con este metodo no hace falta El byte Array
			
			
			
			
			
			
			
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		}
		return mensaje;
		
	}
	
	
	public static void devolver(String mensaje) {
		try {
			InetAddress ipOrigen = paqEntrada.getAddress(); 
			int puertoOrig = paqEntrada.getPort();
			
			byte [] buffeSalida = mensaje.getBytes();
			DatagramPacket paqEnvio = new DatagramPacket(buffeSalida, buffeSalida.length, ipOrigen,puertoOrig);
			System.out.println("Enviando paquete");
			serverSocket.send(paqEnvio);
			
			
			
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}

}
