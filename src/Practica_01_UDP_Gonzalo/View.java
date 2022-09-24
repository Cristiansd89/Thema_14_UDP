package Practica_01_UDP_Gonzalo;
import javax.swing.*;
import java.awt.*;

public class View {

    JFrame ventana;
    JPanel panel;
    JTextArea textArea1, textArea2;
    JScrollPane scrollEnviar;
    JScrollPane scrollRecibir;
    JTextField campoHost;
    JLabel label1, label2, label3;
    JButton botonHost;
    Container contenedor;


    public View(){
        crearComponentes();
        colocarComponentes();
    }


    public void crearComponentes(){

        ventana = new JFrame("TalkUDP");
        panel = new JPanel();
        campoHost = new JTextField("", 10);
        textArea1 = new JTextArea(7,30);
        textArea2 = new JTextArea(7, 30);
        scrollEnviar = new JScrollPane(textArea1);
        scrollRecibir = new JScrollPane(textArea2);
        label1 = new JLabel("Host destino");
        label2 = new JLabel("Texto a envíar");
        label3 = new JLabel("Texto a recibir");
        botonHost = new JButton("Enviar");
        contenedor = ventana.getContentPane();


    }

    public void colocarComponentes(){

        ventana.setSize(400,400);
        panel.add(label1);
        panel.add(campoHost);
        panel.add(botonHost);
        panel.add(label2);
        panel.add(scrollEnviar);
        panel.add(label3);
        panel.add(scrollRecibir);
        contenedor.add(panel);
        ventana.setVisible(true);
        ventana.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


    }

    public void conectaControlador(Controller controller){

        botonHost.addActionListener(controller);

    }





}
