package Practica_01_UDP_Gonzalo;
public class HiloRecibir extends Thread {

    View view;
    int puerto;

    public HiloRecibir(View view, int puerto) {
        this.view = view;
        this.puerto = puerto;
    }


    public void run(){

        new TRecibeUDP(view, puerto);

    }



}
