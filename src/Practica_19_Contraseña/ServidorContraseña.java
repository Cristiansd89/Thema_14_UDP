package Practica_19_Contraseña;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ServidorContraseña {

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
			
			String contraseña = mensaje;
			
			boolean contraseñaCorrecta = false;
			String pasword = "pajaritos";
			String mensajeSalida =null;
			
				
				if(contraseña.equals(pasword)) {
					contraseñaCorrecta =true;
				}
				if(contraseñaCorrecta) {
					mensajeSalida = "La contraseña es correcta";
				}else {
					mensajeSalida = "La contraseña es incorrecta";
				}
				
			
			
			
			
			
			 InetAddress ipOrigen = paqEntrada.getAddress(); // direccion del pequete de Origen
			 int puertoOrig = paqEntrada.getPort();  // Puerto de origen
			
			 byte []  bufferSalida = mensajeSalida.getBytes();
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
