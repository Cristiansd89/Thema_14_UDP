package Practica_15_DescargarArchivo_Hilos;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

public class Descarga extends Thread {

	
	String link;
	String ruta;
	
	 public Descarga(String link,String ruta) {
		 this.link=link;
		 this.ruta=ruta;
	 }
		
		
	public void run() {
		
		try {
				  URL url = new URL(link);
				  URLConnection connection = url.openConnection(); // Abrimos conexion
				  
				  InputStream flujoEntrada = connection.getInputStream();
				  OutputStream flujoSalida = new FileOutputStream(ruta);
				  
				  byte [ ] buffer = new byte [1024];
				  int tamaño;
				  
				  while((tamaño = flujoEntrada.read(buffer)) != -1) {
					  flujoSalida.write(buffer, 0, tamaño);
				  }
				  
				  
				  
			  }catch(IOException ex) {
				  
			  }finally {
				  System.out.println("Descarga Completada");
			  }
		}
		
	}

