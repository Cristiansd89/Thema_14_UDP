package Practica_04_UDP_Arrays;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class ServidorFusion {

	public static void main(String[] args) {
		
		try {
			DatagramSocket serverSocket = new DatagramSocket(8000);
			System.out.println("Esperando Datagrama......");
			
			byte [] bufferEntrada = new byte [1024];
			DatagramPacket paqEntrada = new DatagramPacket(bufferEntrada, bufferEntrada.length);
			serverSocket.receive(paqEntrada); /// Paquete de envio 
			
			ByteArrayInputStream transEntrada = new ByteArrayInputStream(bufferEntrada);
			ObjectInputStream flujoEntrada = new ObjectInputStream(transEntrada);
			
			String [] nomb1 = (String[]) flujoEntrada.readObject();
			String [] nomb2 = (String[]) flujoEntrada.readObject();
			
			burbuja(nomb1);
			burbuja(nomb2);
			String [] fision  = fusion(nomb1, nomb2);
			
		
			ByteArrayOutputStream  transSalida = new ByteArrayOutputStream();
			ObjectOutputStream flujoSalida = new ObjectOutputStream(transSalida);
			flujoSalida.writeObject(fision);
			
			InetAddress IpOrigen = paqEntrada.getAddress(); // Ip Origen
			int puertoOrig = paqEntrada.getPort();
			
			byte bufferEnvio[] = transSalida.toByteArray();
			System.out.println("Devolviendo paquete....");
			DatagramPacket paqEnvio = new DatagramPacket(bufferEnvio, bufferEnvio.length,IpOrigen,puertoOrig);
			serverSocket.send(paqEnvio);
			
			//Preguntar a luis si se pueden enviar 2 objetos en un mismo datagrama
			
			
		} catch (SocketException e) {
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
	
	
	public static void burbuja(String [] array) {
		
		boolean intercambio = false;
		
		do {
			for(int i = 0; i <array.length-1; i++) {
				
				if(array[i].compareTo(array[i +1])> 0) {
					//Se intercambia el elemento
					
					String aux = array[i+1];
					array[i+1] = array[i];
					array[i] = aux;
					intercambio = true;
				}
			}
			
			
		}while(intercambio);
	}
	
	
	public static String[] fusion(String [] array1, String [] array2) {
		
		String [] array3 = new String [array1.length + array2.length];
		
		int i = 0;
		int j = 0;
		int k = 0;
		
		/*Recorremos ambos arrays mientras tengan elementos, comparamos los elementos
		 * y el elemento más pequeño lo almacenamos en el tercer array
		 */
		while(i < array1.length && j < array2.length) {
			
			if(array1[i].compareTo(array2[i+1]) < 0) {
				array3[k] = array1[i];
				i++;
				k++;
			}else {
				array3[k] = array2[j];
				k++;
				j++;
				
			}
		}
		
		while(i < array1.length) {
			array3[k] = array1[i];
			k++;
			i++;
		}
		while(j < array2.length) {
			array3[k] = array2[j];
			j++;
			k++;
		}
		return array3;
		
		
		
		
		
	}

}
