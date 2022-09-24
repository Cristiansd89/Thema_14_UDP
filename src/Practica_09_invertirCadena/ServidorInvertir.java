package Practica_09_invertirCadena;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class ServidorInvertir {
	
	

	
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
				
				
				
				
				String txt ="";
				
				for (int i = mensaje.length()-1; i >=0 ; i--) { // For va desde el tamaño del menaje hacia abajo
					txt +=(mensaje.charAt(i)); //Devuelve el char valor en el índice especificado
				}
				
				String EnviarCadena = txt;
				
				System.out.println(EnviarCadena);

				 
				
				 ////Tampoco Funciona
				 
				 InetAddress ipOrigen = paqEntrada.getAddress(); // direccion del pequete de Origen
				 int puertoOrig = paqEntrada.getPort();  // Puerto de origen
				
				 byte []  bufferSalida = EnviarCadena.getBytes();
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

