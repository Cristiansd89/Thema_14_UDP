package Practica_24_DescargaFTP_Hilo_pantalla;

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
public class DescargaFtp extends Thread {
	
	private static final int BUFFER_SIZE = 1024;
	
	String ftp;
	String savePath;
	
	public DescargaFtp (String ftp,String savePath) {
		this.ftp = ftp;
		this.savePath = savePath;
	}

	
	public void run() {
		
		

		

		try {
			URL url = new URL(ftp);
			URLConnection conn = url.openConnection();
			
			BufferedReader lecturaFichero = new BufferedReader(new InputStreamReader(conn.getInputStream()));

			

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

			
			lecturaFichero.close();

			System.out.println("File downloaded");
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}


