package Practica_01_UDP_Gonzalo;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller implements ActionListener {

    private final View view;
    private final int puertoSalida;

    public Controller(View view, int puertoSalida){
        this.view = view;
        this.puertoSalida = puertoSalida;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getActionCommand().equals("Enviar")){
            if(this.view.campoHost.getText().equals("")){
                JOptionPane.showMessageDialog(this.view.contenedor, "Error. No se ha introducido ningún host");
            } else {
                HiloEnviar hiloEnviar = new HiloEnviar(view, puertoSalida, view.campoHost.getText());
                hiloEnviar.start();
            }
        }



    }
}
