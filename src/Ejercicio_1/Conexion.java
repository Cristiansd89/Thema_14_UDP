package Ejercicio_1;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * This program demonstrates how to download a file from FTP server
 * using FTP URL.
 * @author www.codejava.net
 *
 */
public class Conexion extends Thread {
	
	private static final int BUFFER_SIZE = 1024;
	
	
	public Conexion () {
		
	}

	
	public void run() {
		
		String ftpUrl = "ftp://%s:%s@%s/%s;type=i";
		String host = "ftp://localhost:21";
		String usuario = "cristian";
		String contraseña = "Cristian";
		String archivo = "/project/2012/Project.zip";
		String ruta = "descarga.pdf";
		URLConnection connexion;
		BufferedReader lecturaFichero;


		try {

			ftpUrl = String.format(ftpUrl, usuario,contraseña,host,archivo);
			
			while(true) {

				URL url = new URL(ftpUrl);
				connexion = url.openConnection();
				lecturaFichero = new BufferedReader(new InputStreamReader(connexion.getInputStream()));
				byte[] buffer = new byte[BUFFER_SIZE];
				String linea = lecturaFichero.readLine();
				
				
				while (linea != null) {
					
					/*Si pide descagar algo de ftp 
					 * Para mostrarlo por pantalla
					 * aqui pondra alguna condicion
					 */
					System.out.println(linea);
					linea = lecturaFichero.readLine();
					
					
				}

			}
			
			

	
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}


