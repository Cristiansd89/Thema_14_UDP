package Practica_21_CuentaPares;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class ServidorPar {

	public static void main(String[] args) {
		try {
			/**
			 * Recibir Servidor
			 */
			/*
			 * Esta es toda la conexion que necesitamos para establecer la entrada del paquete 
			 * al servidor
			 */
			DatagramSocket serverSocket = new DatagramSocket(8000); //conexion con el cliente
			System.out.println("Esperando datagrama.....");
			
			
			byte [] bufferRecibo = new byte [1024]; // Tamaño de la entrada
			DatagramPacket paqEntrada = new DatagramPacket(bufferRecibo, bufferRecibo.length); // Paquete de entrada paquete y tamaño
			
			serverSocket.receive(paqEntrada);
			
			
			/*
			 * Convertimos el flujo de bytes en un objeto de nuevo
			 */
			
			ByteArrayInputStream transEntrada = new ByteArrayInputStream(bufferRecibo); // Le pasamos el flujo de entrada
			ObjectInputStream flujoEntrada = new ObjectInputStream(transEntrada);
			int numero = flujoEntrada.readInt();
			int cont = 0;
			flujoEntrada.close();
			
			for(int i = 0; i< numero; i++) {
				if(i %2 ==0) {
					cont++;
				}
			}
			
			/**
			 * Devolvemos el Objeto
			 */
			InetAddress IpOrigen = paqEntrada.getAddress(); // Para Obtener la Ip desde donde nos lo envian(Colocalo antes dela transfromacion si no no funciona)
			int puertoOr = paqEntrada.getPort(); // Para Obtener el puerto del cliente (Colocalo antes dela transfromacion si no no funciona)
			System.out.println("Paquete Procedente de " +IpOrigen+ " : " +puertoOr);
			
			ByteArrayOutputStream transSalida = new ByteArrayOutputStream();
			ObjectOutputStream flujoSalida = new ObjectOutputStream(transSalida);
			flujoSalida.writeInt(cont); //Enviamos el booleano
			flujoSalida.close();
			
			
			byte [] bufferSalida = transSalida.toByteArray(); // Tamaño del Array
			
			System.out.println("Devolviendo " +bufferSalida.length+ " cantidad de bytes");
			DatagramPacket paqEnvio = new DatagramPacket(bufferSalida, bufferSalida.length,IpOrigen,puertoOr);
			serverSocket.send(paqEnvio); // Orden del paquete
			serverSocket.close();
			
			
			
			
			
			
			
			
			
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
