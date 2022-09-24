package Practica_26_DescargaFTP_Etiqueta;



public class TestDescarga {

	public static void main(String[] args) {
		
		String ftp = "ftp://alumno:Alumno2020@10.230.99.160/cron.php";
		String savePath = "copia.php";
		DescargarFTP_Etiqueta hilo = new DescargarFTP_Etiqueta(ftp,savePath);
		hilo.start();
		


	}

}
