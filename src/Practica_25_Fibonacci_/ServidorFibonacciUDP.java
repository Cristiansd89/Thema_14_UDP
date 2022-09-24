package Practica_25_Fibonacci_;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ServidorFibonacciUDP {
	public static void main(String[] args) throws Exception {
		DatagramSocket serverSocket = new DatagramSocket(9876);
		
		System.out.println("Esperando datagrama.....");
		// RECIBO DATAGRAMA
		byte[] recibidos = new byte[1024];
		DatagramPacket paqRecibido = new DatagramPacket(recibidos, recibidos.length);
		serverSocket.receive(paqRecibido);

		// CONVERTIMOS bytes a objeto
		ByteArrayInputStream bais = new ByteArrayInputStream(recibidos); 
		ObjectInputStream in = new ObjectInputStream(bais);
		int numeroRecibido = in.readInt();
		in.close();
		
		// DIRECCION ORIGEN
		InetAddress IPOrigen = paqRecibido.getAddress();
		int puerto = paqRecibido.getPort();
		System.out.println("\tProcedente de: " + IPOrigen + ":" + puerto);
		System.out.println("Número recibido: " + numeroRecibido);
		int num1 = 0, num2 = 1, suma = 1;

		String serieFibonacci = "[" + num1;

		for (int i = 1; i <= numeroRecibido; i++) {

			// muestro la suma
			serieFibonacci += "," + suma;
			suma = num1 + num2;
			num1 = num2;
			num2 = suma;

		}

		serieFibonacci += "]";

		//convertimos objeto a bytes 
		ByteArrayOutputStream bs= new ByteArrayOutputStream();
	    ObjectOutputStream os = new ObjectOutputStream (bs);
	    os.writeUTF(serieFibonacci);  // this es de tipo DatoUdp
	    os.close();
	    byte[] bytes =  bs.toByteArray(); // devuelve byte[]
	    
	    // ENVIANDO AL CLIENTE
		System.out.println("Enviando " + bytes.length + " bytes al cliente.");
		DatagramPacket envio = new DatagramPacket(bytes, bytes.length, IPOrigen, puerto);
		serverSocket.send(envio);	
		serverSocket.close();
		System.out.println("Socket cerrado...");
	}
}
