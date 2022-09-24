package Practica_01_UDP_Gonzalo;
public class HiloEnviar extends Thread{

    View view;
    int puerto;
    String host;


    public HiloEnviar(View view, int puerto, String host){
        this.view = view;
        this.puerto = puerto;
        this.host = host;
    }

    public void run(){
        TEnviaUDP envia = new TEnviaUDP(view.textArea1.getText(), host, puerto, view);
    }






}
