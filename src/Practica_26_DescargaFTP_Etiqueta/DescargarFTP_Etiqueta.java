package Practica_26_DescargaFTP_Etiqueta;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.rmi.server.UID;

public class DescargarFTP_Etiqueta  extends Thread{
	
	String ftp;
	String guardar;
	
	public DescargarFTP_Etiqueta(String ftp, String guardar) {
		this.ftp = ftp;
		this.guardar = guardar;
	}
	
	
	public void run() {
		
		
		try {
			URL url = new URL(ftp);
			URLConnection conexion = url.openConnection();
			
			BufferedReader flujoEntrada = new  BufferedReader(new InputStreamReader(conexion.getInputStream()));
			
			String contenidoCompleto = "";
			
			String linea = flujoEntrada.readLine();
			
			while (linea != null) {
				
				contenidoCompleto += linea;
				int posicion = linea.indexOf("<img");
				if(posicion != -1) {
					int posicion2 = linea.indexOf(">",posicion);
					String etiqueta = linea.substring(posicion, posicion2+1);
					System.out.println(etiqueta);
				}
				
				linea = flujoEntrada.readLine();
				
			}
			
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
}
