package Practica_15_DescargarArchivo_Hilos;



public class TestDescarga {

	public static void main(String[] args) {
		
		String link = "https://www.bocm.es/boletin/CM_Orden_BOCM/2022/02/04/BOCM-20220204-10.PDF";
		String ruta = "prueba2.pdf";

		
		Descarga descarga = new Descarga(link, ruta);
		descarga.start();
	}

}
