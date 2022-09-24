package Practica_15_URL_DescargarArchivo;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

public class Descargar {

	  public static void main(String[] args) {
		  
		  OutputStream flujoSalida = null;
		  InputStream flujoEntrada = null;
		  
		  String link = "https://www.bocm.es/boletin/CM_Orden_BOCM/2022/02/04/BOCM-20220204-10.PDF";
		  String ruta = "prueba.pdf";
		  
		  
		  try {
			  URL url = new URL(link);
			  URLConnection connection = url.openConnection(); // Abrimos conexion
			  
			  flujoEntrada = connection.getInputStream();
			  flujoSalida = new FileOutputStream(ruta);
			  
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


