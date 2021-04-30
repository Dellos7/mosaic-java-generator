package main;

import java.awt.FlowLayout;
import java.util.List;

import javax.swing.SwingUtilities;

import gui.ModalMostrarProceso;
import gui.VentanaPrincipal;

public class AplicacionGUI {
	
	public static VentanaPrincipal ventanaPrincipal;
	public static ModalMostrarProceso modalMostrarProceso;
	
	public static void mostrar() {
		SwingUtilities.invokeLater( new Runnable() {
			@Override
			public void run() {
				ventanaPrincipal = new VentanaPrincipal();
				modalMostrarProceso = new ModalMostrarProceso();
				ventanaPrincipal.setVisible( true );
				ventanaPrincipal.pack();
			}
		});
	}

}