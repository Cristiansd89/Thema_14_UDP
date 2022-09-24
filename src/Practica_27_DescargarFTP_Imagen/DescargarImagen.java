package Practica_27_DescargarFTP_Imagen;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class DescargarImagen extends Thread{

	String ftp;
	String guardar;
	
	public DescargarImagen (String ftp,String guardar) {
		this.ftp = ftp;
		this.guardar = guardar;
	}
	
	
	
	public void run() {
		
		try {
			URL url = new URL(ftp);
			URLConnection conexion = url.openConnection();  //Conexion abierta
			
			
			InputStream flujoEntrada = conexion.getInputStream(); // Flujo entrada
			
			byte [] buffer = new byte [1024];
			
			int tamaño;
			
			
			File archivo = new File(guardar); // Creamos el archivo
			
			OutputStream flujoSalida = new FileOutputStream(archivo);
			
			
			//comienza a leer
				
			tamaño = flujoEntrada.read(buffer);
			while (tamaño != -1) {
				flujoSalida.write(buffer,0,tamaño);
			}
			
			
			flujoEntrada.close();
			flujoSalida.close();
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
