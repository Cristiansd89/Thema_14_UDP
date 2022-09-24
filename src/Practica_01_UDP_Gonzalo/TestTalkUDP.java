package Practica_01_UDP_Gonzalo;
import java.util.Scanner;

public class TestTalkUDP {

    public static void main(String[] args) {

        Scanner entrada = new Scanner(System.in);


        System.out.println("Introduce el puerto de salida: ");
        int puertoSalida = entrada.nextInt();
        System.out.println("Introduce el puerto de entrada: ");
        int puertoEntrada = entrada.nextInt();
        View view = new View();
        Controller controller = new Controller(view, puertoSalida);
        view.conectaControlador(controller); 
        HiloRecibir hiloRecibir = new HiloRecibir(view, puertoEntrada);
        hiloRecibir.start();



    }



}
