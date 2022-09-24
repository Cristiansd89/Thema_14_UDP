package palindromov2;
import java.io.*;
import java.net.*;

public class ClienteUDP2 {
	public static void main(String args[]) throws Exception {
		// FLUJO PARA ENTRADA ESTANDAR
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		// SOCKET cliente
		DatagramSocket clientSocket = new DatagramSocket();
		byte[] enviados = new byte[1024];
		byte[] recibidos = new byte[1024];

		// DATOS DEL SERVIDOR
		InetAddress IPServidor = InetAddress.getLocalHost();// localhost
		int puerto = 9876; // puerto por el que escucha

		System.out.print("Introduce mensaje: ");
		String cadena = in.readLine();
		enviados = cadena.getBytes();

		// ENVIANDO AL SERVIDOR
		System.out.println("Enviando " + enviados.length + " bytes al servidor.");
		DatagramPacket envio = new DatagramPacket(enviados, enviados.length,
				IPServidor, puerto);
		clientSocket.send(envio);

		// RECIBIENDO DEL SERVIDOR
		DatagramPacket recibo = new DatagramPacket(recibidos, recibidos.length);
		System.out.println("Esperando datagrama....");
		clientSocket.receive(recibo);
		String invertida = new String(recibo.getData());
		
		//Datos qe envia
		InetAddress IPOrigen = recibo.getAddress();
		int puertoOrigen = recibo.getPort();
		System.out.println("\tProcedente de: " + IPOrigen + ":" + puertoOrigen);
		System.out.println("\tDatos: " + invertida);
		invertida = invertida.trim();
		System.out.println(invertida.length());

		clientSocket.close();//cerrar socket
	}
} 
