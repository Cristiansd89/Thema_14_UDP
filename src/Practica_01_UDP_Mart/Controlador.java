package Practica_01_UDP_Mart;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class Controlador implements ActionListener {
	Vista vista;

	public Controlador(Vista vista) {
		this.vista = vista;
	}

	public void actionPerformed(ActionEvent ae) {
		if (ae.getActionCommand().equalsIgnoreCase("enviar")) {
			enviar();
		}
	}

	public void enviar() {
		if (vista.textFieldHost.getText().equals("")) {
			JOptionPane.showMessageDialog(vista.container, "Error, falta el host");
		} else {
			HiloEnviar hiloEnviar = new HiloEnviar(vista);
			hiloEnviar.start();
		}
	}
}