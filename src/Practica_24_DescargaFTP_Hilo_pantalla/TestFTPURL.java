package Practica_24_DescargaFTP_Hilo_pantalla;

public class TestFTPURL {

	public static void main(String[] args) {
		
		String ftp = "ftp://alumno:Alumno2020@10.230.99.160/cron.php";
		String savePath = "copia.php";
		DescargaFtp hilo = new DescargaFtp(ftp,savePath);
		hilo.start();
		

	}

}
