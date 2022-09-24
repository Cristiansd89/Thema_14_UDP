package Practica_24_DescargaFtp;

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

	
	public void run() {
		/*	String ftpUrl = "ftp://%s:%s@%s/%s;type=i";
		String host = "10.230.99.160";
		String user = "alumno";
		String pass = "Alumno2020";*/
		//String filePath = "cron.php";
		String savePath = "copia.php";

		//ftpUrl = String.format(ftpUrl, user, pass, host, filePath);
		//System.out.println("URL: " + ftpUrl);

		try {
			URL url = new URL("ftp://alumno:Alumno2020@10.230.99.160/cron.php");
			URLConnection conn = url.openConnection();
			//InputStream inputStream = conn.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

			FileOutputStream outputStream = new FileOutputStream(savePath);

			byte[] buffer = new byte[BUFFER_SIZE];
			String linea = br.readLine();
			while (linea != null) {
				//outputStream.write(buffer, 0, bytesRead);
				//bytesRead =  inputStream.read(buffer);ç
				System.out.println(linea);
				linea = br.readLine();
				
			}

			outputStream.close();
			br.close();

			System.out.println("File downloaded");
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}


