package Practica_08_B_CuentaLetras;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class ServidorCuentaVocales {
	
	

	
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
				
				System.out.println( " Mensaje  "+mensaje);
				
				
				String cadena = mensaje;
				int letra = 0;

				 for(int i = 0; i<cadena.length(); i++) {
					 
					 if(cadena.charAt(i)=='a' ||  cadena.charAt(i) == 'e' || cadena.charAt(i) == 'i'
			                    || cadena.charAt(i) == 'o' || cadena.charAt(i) == 'u'){
						 letra++;
					 }
					 
				 }
				 String numeroLetras = String.valueOf(letra);
				 
				 
				 
				 InetAddress ipOrigen = paqEntrada.getAddress(); // direccion del pequete de Origen
				 int puertoOrig = paqEntrada.getPort();  // Puerto de origen
				
				 byte []  bufferSalida = numeroLetras.getBytes();
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

