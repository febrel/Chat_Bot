package com.gui;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import java.awt.Font;
import java.sql.ResultSet;

import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.table.DefaultTableModel;

import com.bl.BLExamenChat;

import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Personas extends JInternalFrame {
	// Importo objeto BLExamenChat para usar sus metodos
	BLExamenChat objExamen = new BLExamenChat();
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Personas frame = new Personas();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Personas() {
		// Cambia valor 0,0 para que aparesca dentro del JDesktopPane
		setBounds(20, 0, 762, 395);
		getContentPane().setLayout(null);

		JLabel lblPersonasQueRindieron = new JLabel("BUSQUEDA POR PROVINCIA");
		lblPersonasQueRindieron.setFont(new Font("Bell MT", Font.BOLD, 20));
		lblPersonasQueRindieron.setBounds(254, 11, 284, 14);
		getContentPane().add(lblPersonasQueRindieron);

		// Creo aqui los tex label para ingreso o recolección de datos
		JTextPane textProvincia = new JTextPane();
		textProvincia.setBounds(165, 49, 110, 20);
		getContentPane().add(textProvincia);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "MASCULINO", "FEMENINO" }));
		comboBox.setBounds(409, 49, 101, 20);
		getContentPane().add(comboBox);

		table = new JTable();
		table.setBounds(10, 148, 726, 156);
		getContentPane().add(table);

		JLabel lblEscribaLaProvincia = new JLabel("Escriba la provincia:");
		lblEscribaLaProvincia.setFont(new Font("Bell MT", Font.BOLD, 16));
		lblEscribaLaProvincia.setBounds(10, 55, 151, 14);
		getContentPane().add(lblEscribaLaProvincia);

		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				// Creo variables para poder ingresarlas al metodo
				String genero = "";
				String provincia = textProvincia.getText().toUpperCase();
				
				if (comboBox.getSelectedItem().toString().equals("MASCULINO")) {
		            genero = "MASCULINO";
		        } else if (comboBox.getSelectedItem().toString().equals("FEMENINO")) {
		            genero = "FEMENINO";
		        }
				
				// Invoco el metodo consultar
				consultar(provincia, genero);
				
				// Limpiar
				textProvincia.setText("");
			}
		});
		btnBuscar.setForeground(Color.BLACK);
		btnBuscar.setFont(new Font("Bell MT", Font.BOLD, 15));
		btnBuscar.setBackground(new Color(255, 69, 0));
		btnBuscar.setBounds(647, 57, 89, 30);
		getContentPane().add(btnBuscar);

		JLabel lblResultador = new JLabel("Resultados\r\n");
		lblResultador.setFont(new Font("Bell MT", Font.BOLD, 18));
		lblResultador.setBounds(329, 107, 89, 14);
		getContentPane().add(lblResultador);

		JLabel lblGenero = new JLabel("Género:");
		lblGenero.setFont(new Font("Bell MT", Font.BOLD, 16));
		lblGenero.setBounds(341, 55, 58, 14);
		getContentPane().add(lblGenero);
		
		JLabel lblNombres = new JLabel("NOMBRES");
		lblNombres.setFont(new Font("Bell MT", Font.BOLD, 12));
		lblNombres.setBounds(28, 133, 68, 14);
		getContentPane().add(lblNombres);
		
		JLabel lblProvincia = new JLabel("PROVINCIA");
		lblProvincia.setFont(new Font("Bell MT", Font.BOLD, 12));
		lblProvincia.setBounds(124, 133, 75, 14);
		getContentPane().add(lblProvincia);
		
		JLabel lblGenero_1 = new JLabel("GÉNERO");
		lblGenero_1.setFont(new Font("Bell MT", Font.BOLD, 12));
		lblGenero_1.setBounds(238, 133, 58, 14);
		getContentPane().add(lblGenero_1);
		
		JLabel lblFecha = new JLabel("FECHA");
		lblFecha.setFont(new Font("Bell MT", Font.BOLD, 12));
		lblFecha.setBounds(339, 133, 46, 14);
		getContentPane().add(lblFecha);
		
		JLabel label = new JLabel("100%");
		label.setFont(new Font("Bell MT", Font.BOLD, 12));
		label.setBounds(446, 133, 46, 14);
		getContentPane().add(label);
		
		JLabel lblAciertos = new JLabel("ACIERTOS");
		lblAciertos.setFont(new Font("Bell MT", Font.BOLD, 12));
		lblAciertos.setBounds(552, 133, 63, 14);
		getContentPane().add(lblAciertos);
		
		JLabel lblErrores = new JLabel("ERRORES");
		lblErrores.setFont(new Font("Bell MT", Font.BOLD, 12));
		lblErrores.setBounds(660, 133, 57, 14);
		getContentPane().add(lblErrores);

		

	}
	
	// Metodo Consultar
	public void consultar(String provincia , String gene) {
		Object[] columnas = { "NOMBRES", "PROVINCIA", "GENERO", "FECHA", "NOTA","ACIERTOS", "ERRORES" };
		DefaultTableModel modelo = new DefaultTableModel(null, columnas);
		table.setModel(modelo);

		try {

			ResultSet bus;
			bus = objExamen.consultarProvinciaGenero(provincia, gene);

			while (bus.next()) {

				
				String nombres = bus.getString("nombres");
				String provincias = bus.getString("provincia");
				String genero = bus.getString("genero");
				String fecha = bus.getString("fecha");
				int notas = bus.getInt("nota");
				int aciertos = bus.getInt("aciertos");
				int errores = bus.getInt("errores");

				// Trasforma a String para mostrar en tabla
				
				String trasforma2 = String.valueOf(notas);
				String trasforma3 = String.valueOf(aciertos);
				String trasforma4 = String.valueOf(errores);

				String modeloTemp[] = {nombres, provincias, genero, fecha,trasforma2, trasforma3, trasforma4 };
				modelo.addRow(modeloTemp);

			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "No se encontro registro");
		}

	}
}
