package palindromov2;

import java.io.*; 
import java.net.*; 
  
public class ServidorUDP2 { 
  public static void main(String args[]) throws Exception { 
    DatagramSocket serverSocket = new DatagramSocket(9876);   
    byte[] recibidos = new byte[1024]; 
    byte[] enviados  = new byte[1024]; 
    String cadena;
  
           
      System.out.println ("Esperando datagrama....."); 
      //RECIBO DATAGRAMA
      recibidos = new byte[1024]; 
      DatagramPacket paqRecibido = new DatagramPacket(recibidos, recibidos.length); 
      serverSocket.receive(paqRecibido); 
      cadena = new String(paqRecibido.getData()); 
		  
      //DIRECCION ORIGEN
      InetAddress IPOrigen = paqRecibido.getAddress();           
      int puerto = paqRecibido.getPort(); 
      System.out.println ("\tOrigen: " + IPOrigen + ":" + puerto);
      System.out.println ("\tMensaje recibido: " + cadena);
      cadena = cadena.trim();
      
      String cadenaInvertida = "";
      
      for(int i = cadena.length() - 1; i >= 0; i--) {
    	  cadenaInvertida += cadena.charAt(i);
      }
      
      System.out.println(cadenaInvertida);
      
      enviados = cadenaInvertida.getBytes();
          
      //ENVIO DATAGRAMA
      DatagramPacket paqEnviado = new DatagramPacket
               (enviados, enviados.length, IPOrigen, puerto);   
      serverSocket.send(paqEnviado);
		  
	 
    serverSocket.close();
	System.out.println ("Socket cerrado...");
   } 
}  


