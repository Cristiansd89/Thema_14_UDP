package Practica_14_URL_Existe;

import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class PaginaExiste {

	public static void main(String[] args) {
		final String DireccionWeb = "https://www.google.es";
		try {
			URL url = new URL(DireccionWeb);
			HttpsURLConnection.setFollowRedirects(false); // Con este metodo le decimos que no se aceptan redirecciones
			
			HttpsURLConnection conexion = (HttpsURLConnection) url.openConnection();
			
			conexion.setRequestMethod("HEAD");
			
			
			
			System.out.printf("Direccion existe ?: %b\n", conexion.getResponseCode() == HttpURLConnection.HTTP_OK);
			
		}catch (Exception e) {
			System.out.println("Ocurrio un Error" + e.getMessage());
		}
	}

}
