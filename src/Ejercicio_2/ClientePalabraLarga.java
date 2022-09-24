package Ejercicio_2;

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
import java.nio.Buffer;
import java.util.Scanner;

import Practica_02_Objetos_01.Contacto;

public class ClientePalabraLarga {

	
	
	public static void main(String[] args) {
			Scanner ent = new Scanner(System.in);
		try { 
			
			/**
			 * Envio de Objetos
			 */
			DatagramSocket socketCliente = new DatagramSocket();
			
			
			InetAddress IpServidor = InetAddress.getLocalHost();  // Dame la Ip(direccion del campo) del localhost
			int puerto =8000; // La puerta a la que  vamos a llamar
			
			
			String sTexto = "En un manchas  de la lugar ";
			
			/*
			 * Envio del Objeto
			 */
			ByteArrayOutputStream transformador = new ByteArrayOutputStream(); //Transforma el objeto en bytes para enviarlos fuera
			ObjectOutputStream flujoSalida = new ObjectOutputStream(transformador); // Flujo de salida 
			flujoSalida.writeUTF(sTexto); //Envio del Objeto
			flujoSalida.close();
			
			
			
			byte [] bufferEnvio = transformador.toByteArray(); // tamaño de bytes del objeto que enviamos
			System.out.println("Enviando " +bufferEnvio.length + " bytes al servidor.");
			DatagramPacket envio = new DatagramPacket(bufferEnvio,bufferEnvio.length,IpServidor,puerto);
			socketCliente.send(envio); // Orden envio
			
			/**
			 * Recepcion de objetos
			 */
			
			byte [] bufferRecibi = new byte[1024]; // Tamaño de buffer de entrada
			DatagramPacket recibo = new DatagramPacket(bufferRecibi, bufferRecibi.length); // Paquete de entrada
			socketCliente.receive(recibo); // Orden de entrada
			
			/*
			 * Convertimos el flujo de bytes en un Objeto
			 */
			ByteArrayInputStream transEntrada = new ByteArrayInputStream(bufferRecibi); //Transformador de bytes
			ObjectInputStream flujoEntrada = new ObjectInputStream (transEntrada); // 
			String cadenaLarga =  flujoEntrada.readUTF(); // Lectura del objeto
			flujoEntrada.close();
			
			
			InetAddress IPOrigen = recibo.getAddress();
			int puertoOrigen = recibo.getPort();
			System.out.println("Entrando paquete....");
			
			System.out.println( "La palabra Mas Larga es "+cadenaLarga);
			socketCliente.close(); // Cierre del Socket
			
			
			
			
			
			

			
			/*
			 * Creamos el Objeto que vamos a enviar
			 */
			
		} catch (SocketException | UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //apertura del Socket Cliente
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
	}

}
