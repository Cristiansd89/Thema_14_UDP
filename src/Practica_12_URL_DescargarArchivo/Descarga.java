package Practica_12_URL_DescargarArchivo;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import java.net.URL;
import java.net.HttpURLConnection;


public class Descarga implements Runnable{
	
	String link;
	File archivo;
	
	
	
	
	public Descarga (String link, File archivo) {
		this.link = link;
		this.archivo = archivo;
		
		
	}




	@Override
	public void run() {
		
		
		try {
			URL url = new URL(link); // Creamos una clase URL y le pasamos el link
			HttpURLConnection http = (HttpURLConnection)url.openConnection(); //Para Convertir la conexion URL a la Conexion  HTTP
			
			double tamañoArchivo = (double)http.getContentLengthLong(); // Para Conseguir el tamaño del archivo que vamos a descargar
			
			BufferedInputStream flujoEntrada =new BufferedInputStream(http.getInputStream()); // Le pasamos el flujo de entrada de la Conexion
			//HTTP
			
			/*
			 * Necesitamos un archivo de salida y pasarle como parametro un archivo
			 */
			FileOutputStream archivoSalida = new FileOutputStream(this.archivo); 
			
			
			BufferedOutputStream flujoSalida = new BufferedOutputStream(archivoSalida,1024); // Al flujo de salida le pasamos el archivo y el tamaño del buffer
			
			byte [] buffer = new byte[1024];
			double descargando = 0.00;
			int lectura = 0;
			
			double porcentajeDescargado = 0.00;
			
			
			/*
			 * Establecemos el tamaño de los bloques  de entrada al flujo de lectura desde 0 a 1024
			 */
			while((lectura = flujoEntrada.read(buffer,0,1024)) >= 0){
				/*
				 * Ahora escribimos los datos en archivoSalida para poder escribirlos
				 * en el flujo de salida
				 */
				flujoSalida.write(buffer,0,lectura); // ira de cero al tamaño leido
				descargando += lectura; // Incrementamos el porcentaje de descarga
				porcentajeDescargado = (descargando*100)/tamañoArchivo;// Calculo la media de desarga
				
				String porcertaje = String.format("%.4f",porcentajeDescargado);
				System.out.println("Descargando "  +porcertaje);
				
			}
			flujoSalida.close();
			flujoEntrada.close();
			
			System.out.println("Descarga Completa");
			
			
		}catch(IOException e) {
			
		}
	}
}
