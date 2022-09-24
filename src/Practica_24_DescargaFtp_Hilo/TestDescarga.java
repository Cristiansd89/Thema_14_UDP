package Practica_24_DescargaFtp_Hilo;

public class TestDescarga {

	public static void main(String[] args) {
		
		String ftpUrl = "ftp://%s:%s@%s/%s;type=i";
		String host = "localhost";
		String usuario = "cristian";
		String contraseña = "Cristian";
		String archivo = "/project/2012/Project.zip";
		String ruta = "E:/Download/Project.zip";
		
		ftpUrl = String.format(ftpUrl, usuario,contraseña,host,archivo);
		
		DescargaFtp descargar = new DescargaFtp(ftpUrl, ruta);
		descargar.start();
	}

}
