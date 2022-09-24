package Practica_04_UDP_Arrays;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class ClienteFusion {

	public static void main(String[] args) {
		
		
		
		try {
			
			DatagramSocket socketClient = new DatagramSocket();
			
			
			
			InetAddress IpServidor = InetAddress.getLocalHost(); //Obtenemos la ip del localhost
			int puertoServidor = 8000; // Puerto Servidor
			
			String [] nombre1 = {"Ana", "Tamara", "Susana"};
			String [] nombre2 = {"Juana","Beatriz","Rebeca"};
			
			ByteArrayOutputStream transEnvio = new ByteArrayOutputStream(); ///Transformamos el primer array para enviarlo
			ObjectOutputStream  flujoSalida = new ObjectOutputStream(transEnvio); // Añadimos los bytes al flujo de salida
			flujoSalida.writeObject(nombre1); // Orden de envio
			flujoSalida.writeObject(nombre2);
			
			flujoSalida.close(); // Cerramos el flujo
			
			byte [] bufferEnvio = transEnvio.toByteArray(); // Tamaño de bytes del objeto que enviamos
			System.out.println("Enviando el paquete  tamaño  " +bufferEnvio.length+ " al servidor");
			DatagramPacket paqEnvio = new DatagramPacket(bufferEnvio,bufferEnvio.length,IpServidor, puertoServidor);
			socketClient.send(paqEnvio);
			
			
			/*
			 * Recepcion de Datos
			 * 
			 */
			
			byte [] bufferRecibir = new byte[1024]; // Tamaño del buffer de entrada
			DatagramPacket paqEntrada = new DatagramPacket(bufferRecibir,bufferRecibir.length);
			socketClient.receive(paqEntrada);
			
			
			/*
			 * Transformamos los bytes en Un Objeto 
			 */
			
			ByteArrayInputStream transEntra = new ByteArrayInputStream(bufferRecibir);
			ObjectInputStream flujoEntrada = new ObjectInputStream(transEntra);
			
			
			String [] fusion = new String [nombre1.length+nombre2.length]; // Creamos el Array tipo String 
			fusion=	(String[]) flujoEntrada.readObject(); //Introducimos el array dentro de la variable
			flujoEntrada.close();
			
			InetAddress IpOrigen = paqEntrada.getAddress(); // 
			int puertoOrigen = paqEntrada.getPort();
			
			System.out.println("Paquete Prodecedente de "+IpOrigen + " " +puertoOrigen);
			
			
			for(String nombre : fusion) {
				System.out.println(nombre);
				
			}
			
			socketClient.close();
			
			
	
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
		} catch (SocketException | UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
		

	}

}
