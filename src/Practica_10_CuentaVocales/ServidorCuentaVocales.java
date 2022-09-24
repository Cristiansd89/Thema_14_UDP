package Practica_10_CuentaVocales;

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
				
				String mensajeSinEspacios = ""; 
				int vocales = 0;
				int consonante =0;
				
				System.out.println(mensaje.length());
				
				for (int i = 0; i < mensaje.length(); i++) {
					mensaje = mensaje.replace(" ", "");
					
					if(mensaje.charAt(i)=='a' ||  mensaje.charAt(i) == 'e' || mensaje.charAt(i) == 'i'
		                    || mensaje.charAt(i) == 'o' || mensaje.charAt(i) == 'u') {
						vocales++;
						
					}else {
						consonante++;
					}
				}
				
				
				
				//String mensajeSinEspacios = mensaje.replace(" ","");
				
				System.out.println("Tiene " +vocales+ " Vocales");
				System.out.println("Tiene " +consonante+ " Consonantes");
				 
				
				 ////Tampoco Funciona
				 
				 InetAddress ipOrigen = paqEntrada.getAddress(); // direccion del pequete de Origen
				 int puertoOrig = paqEntrada.getPort();  // Puerto de origen
				
				 byte []  bufferSalida = mensajeSinEspacios.getBytes();
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

