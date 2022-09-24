package Practica_22_Capicua;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class ServidorCapicua {

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
			
			String cadenaNumero = String.valueOf(numero);
			
			
			boolean esCapicua = true;
			StringBuilder invertir = new StringBuilder(cadenaNumero);
			
			String capicuaInverso = invertir.reverse().toString();
			
			for(int i =0 ; i<cadenaNumero.length(); i++) {
				
				if(cadenaNumero.charAt(i) != capicuaInverso.charAt(i)) {
					esCapicua = false;
					i=cadenaNumero.length();
				}
			}
			if(esCapicua) {
				esCapicua = true;
			}else {
				esCapicua = false;
			}
			
			
			/**
			 * Devolvemos el Objeto
			 */
			InetAddress IpOrigen = paqEntrada.getAddress(); // Para Obtener la Ip desde donde nos lo envian(Colocalo antes dela transfromacion si no no funciona)
			int puertoOr = paqEntrada.getPort(); // Para Obtener el puerto del cliente (Colocalo antes dela transfromacion si no no funciona)
			System.out.println("Paquete Procedente de " +IpOrigen+ " : " +puertoOr);
			
			ByteArrayOutputStream transSalida = new ByteArrayOutputStream();
			ObjectOutputStream flujoSalida = new ObjectOutputStream(transSalida);
			flujoSalida.writeBoolean(esCapicua); //Enviamos el booleano
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
