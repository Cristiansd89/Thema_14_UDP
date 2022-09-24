package Practica_01_UDP_Mart;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class HiloRecibir extends Thread {

	DatagramSocket socket;
	DatagramPacket datagramRecibido;
	Vista vista;
	byte[] arrayBytes;
	
	public HiloRecibir(Vista vista) {
		this.vista = vista;
		this.arrayBytes = new byte[512];
		this.socket = null;
		this.datagramRecibido = null;
	}

	public void run() {
		
		try {
			socket = new DatagramSocket( vista.puertorecibo);
			
			datagramRecibido= new DatagramPacket(arrayBytes, arrayBytes.length);
			
			while(true){
				socket.receive(datagramRecibido);
				String mensaje = new String(datagramRecibido.getData(), 0 ,datagramRecibido.getLength());
				//String mensaje = new String(datagramRecibido.getData());

				vista.textAreaRecibir.setText(vista.textAreaRecibir.getText()+"\n"+mensaje);			
				//añadimos la información en el área de texto. Otra forma de hacerlo podría ser
				//vista.textAreaRecibir.append(mensaje + "\n");
			}	
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
