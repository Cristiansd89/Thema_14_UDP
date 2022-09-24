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
import java.net.UnknownHostException;

public class ClienteOrdenar {

	public static void main(String[] args) {
		
		
		try {
			DatagramSocket socketCliente = new DatagramSocket(); /// Creamos el socket
			InetAddress IpServidor = InetAddress.getLocalHost(); // Ip del servidor
			int puerto = 8000; //Puerta a la que vamos  a llamar
			
			int[] desOrden = {1,4,10,8,7,3};
			
			
			/*
			 * Envio de datos
			 */
			
			ByteArrayOutputStream transEnvio = new ByteArrayOutputStream(); // Transformamos en  byte el Array
			ObjectOutputStream flujoSalida = new ObjectOutputStream(transEnvio); // Lo serializamos
			flujoSalida.writeObject(desOrden);
			
			byte [] bufferSalida = transEnvio.toByteArray(); // Establecemos tamaño del paquete
			System.out.println("Enviado paquete tamaño " +bufferSalida.length);
			DatagramPacket envio = new DatagramPacket(bufferSalida, bufferSalida.length,IpServidor, puerto);  // Enviamos el paquete
			socketCliente.send(envio); // Orden de envio
			
			
			
			/*
			 * Recepcio de datos
			 */
			
			
			byte [] bufferEntrada =  new byte [1024]; /// Tamaño del buffer de entrada
			System.out.println(" Entrando paquete tamaño " +bufferEntrada.length);  // Paquete de entrada
			DatagramPacket recibo = new DatagramPacket(bufferEntrada, bufferEntrada.length);
			socketCliente.receive(recibo); // Orden de entrada
			
			
			ByteArrayInputStream transEntrada = new ByteArrayInputStream(bufferEntrada);
			ObjectInputStream flujoEntrada = new ObjectInputStream(transEntrada);
			int [] ordenado = (int[]) flujoEntrada.readObject();
			
			for(int i = 0 ; i< ordenado.length; i++) {
				System.out.println(ordenado[i]);
			}
			
			
			
			
			
			
			
			
			
			
			
			
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnknownHostException e) {
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

