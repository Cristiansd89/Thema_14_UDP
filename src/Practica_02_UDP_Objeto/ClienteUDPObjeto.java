 package Practica_02_UDP_Objeto;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ClienteUDPObjeto {
	public static void main(String args[]) throws Exception {
		// SOCKET cliente
		DatagramSocket clientSocket = new DatagramSocket();
		byte[] recibidos = new byte[1024];

		// DATOS DEL SERVIDOR
		InetAddress IPServidor = InetAddress.getLocalHost();// localhost
		int puerto = 9876; // puerto por el que escucha

		Contacto contacto = new Contacto("Maria", "6662255");
		// convertimos objeto a bytes
		ByteArrayOutputStream bs = new ByteArrayOutputStream();
		ObjectOutputStream os = new ObjectOutputStream(bs);
		os.writeObject(contacto);
		os.close();
		byte[] bytes = bs.toByteArray(); // devuelve byte[]

		// ENVIANDO AL SERVIDOR
		System.out.println("Enviando " + bytes.length + " bytes al servidor.");
		DatagramPacket envio = new DatagramPacket(bytes, bytes.length, IPServidor, puerto);
		clientSocket.send(envio);

		// RECIBIENDO DEL SERVIDOR
		DatagramPacket recibo = new DatagramPacket(recibidos, recibidos.length);
		System.out.println("Esperando datagrama....");
		clientSocket.receive(recibo);

		// CONVERTIMOS bytes a objeto
		ByteArrayInputStream bais = new ByteArrayInputStream(recibidos); // bytes es el byte[]
		ObjectInputStream is = new ObjectInputStream(bais);
		contacto = (Contacto) is.readObject();
		is.close();

		// visualizo datos
		InetAddress IPOrigen = recibo.getAddress();
		int puertoOrigen = recibo.getPort();
		System.out.println("\tProcedente de: " + IPOrigen + ":" + puertoOrigen);
		System.out.println(contacto);
		clientSocket.close();// cerrar socket
	}
}