package Practica_12_URL_DescargarArchivo;

import java.io.File;

public class TestDescarga {

	public static void main(String[] args) {
		
		String link ="https://www.boe.es/boe/dias/2021/03/04/pdfs/BOE-A-2021-3404.pdf";
		File archivo = new File("home/cristian/Escritorio");
		
		Descarga desarga= new Descarga(link, archivo);
		Thread hilo = new Thread(desarga);
		hilo.start();

	}

}
