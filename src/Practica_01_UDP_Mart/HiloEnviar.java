package Practica_01_UDP_Mart;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class HiloEnviar extends Thread {
	Vista vista;
	byte[] arrayBytes;
	String mensaje;
	InetAddress host;

	public HiloEnviar(Vista vista) {
		this.vista = vista;
		this.arrayBytes = new byte[512];
		this.mensaje = mensaje;
		this.host = null;
	}

	public void run() {
		
		try {
			DatagramSocket socket = new DatagramSocket();
			host =  InetAddress.getByName(vista.textFieldHost.getText());
			mensaje = vista.textAreaEnviar.getText();
			arrayBytes = mensaje.getBytes();
			DatagramPacket dgPacket = new DatagramPacket(arrayBytes, arrayBytes.length, host, vista.puertoenvio);
			socket.send(dgPacket);
			socket.close();
			vista.textAreaEnviar.setText("");
			
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}