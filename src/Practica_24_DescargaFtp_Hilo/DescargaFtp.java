package Practica_24_DescargaFtp_Hilo;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class DescargaFtp extends Thread {
	
	private static final int TAMAÑO_BUFFER = 1024;
	String ftpUrl;
	String ruta;
	
	public DescargaFtp (String ftpUrl, String ruta) {
		 this.ftpUrl = ftpUrl;
		 this.ruta = ruta;
	}
	
	
	
	public void run() {
		
		
		
		try {
			URL url = new URL(ftpUrl);
			URLConnection conexion = url.openConnection(); // Abrimos la conexion
			
			InputStream flujoEntrada = conexion.getInputStream();
			
			FileOutputStream escrbirArchivo = new  FileOutputStream(ruta); /// Creamos el Archivo
			
			byte [] bufferEntrada = new byte[TAMAÑO_BUFFER];
			int leyendoBytes = flujoEntrada.read(bufferEntrada);
			
			while(leyendoBytes != -1) {
				
				escrbirArchivo.write(bufferEntrada,0, leyendoBytes); // Escritura sobre el archivo
				leyendoBytes = flujoEntrada.read(bufferEntrada);
			}
			escrbirArchivo.close();
			flujoEntrada.close();
			
			
			System.out.println("Archivo Descargado");
		}catch(Exception e) {
			
		}
	}

}
