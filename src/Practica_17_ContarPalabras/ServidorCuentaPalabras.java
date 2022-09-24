package Practica_17_ContarPalabras;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import javax.annotation.processing.SupportedSourceVersion;

public class ServidorCuentaPalabras {

	public static void main(String[] args) {
		
		
		final int PUERTO = 8000;
		
		
		
		try {
			
			System.out.println("Iniciado el servidor :");
			DatagramSocket serverSocket = new DatagramSocket(PUERTO);
			byte [] bufferEntrada  = new byte [1024];
			
			
			System.out.println("Esperando pequete....");
				
			DatagramPacket paqEntrada = new DatagramPacket(bufferEntrada, bufferEntrada.length );
			serverSocket.receive(paqEntrada);
			
			String mensaje = new String(paqEntrada.getData()); // Transformamos los bytes en un string para porder leer los
			// con este metodo no hace falta El byte Array
			
			

	        String palabra = "jamon";
	        
	        int contador =0;
	        String [] cont = mensaje.split(" "); 
	        
	        for(int i = 0; i < cont.length;i++) {
	        	System.out.println(cont[i]);
	        }
			
	        for(String pal : cont) {
	        	if(pal.equals(palabra)) {
	        		System.out.println(pal);
	        		
	        		contador++;	
	        	}
	        	
	        }
			String cadena= String.valueOf(contador);
			
			
		
			
			
			 InetAddress ipOrigen = paqEntrada.getAddress(); // direccion del pequete de Origen
			 int puertoOrig = paqEntrada.getPort();  // Puerto de origen
			
			 byte []  bufferSalida = cadena.getBytes();
			 DatagramPacket paqEnvio = new DatagramPacket(bufferSalida, bufferSalida.length, ipOrigen,puertoOrig);
			 System.out.println("Enviando paquete");
			 serverSocket.send(paqEnvio);
			 
			 
			 serverSocket.close();
		 
		 
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		

	}

}
