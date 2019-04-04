package com.gui;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.ResultSet;
import java.text.DecimalFormat;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.bl.BLExamenChat;

import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;

public class Promedio extends JInternalFrame {
	// Importo objeto BLExamenChat para usar sus metodos
	BLExamenChat objExamen = new BLExamenChat();
	private JTable table;
	double total = 0;
	double retorno = 0;
	int contadorPri = 0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Promedio frame = new Promedio();
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
	public Promedio() {
		// Cambia valor 0,0 para que aparesca dentro del JDesktopPane
		setBounds(20, 0, 762, 395);
		getContentPane().setLayout(null);

		JLabel lblPromedioDeLos = new JLabel("PROMEDIO DE EXAMENES");
		lblPromedioDeLos.setFont(new Font("Bell MT", Font.BOLD, 20));
		lblPromedioDeLos.setBounds(242, 11, 265, 14);
		getContentPane().add(lblPromedioDeLos);

		JTextPane textTotal = new JTextPane();
		textTotal.setBounds(618, 296, 64, 20);
		getContentPane().add(textTotal);

		JTextPane textPromedio = new JTextPane();
		textPromedio.setBounds(618, 322, 64, 20);
		getContentPane().add(textPromedio);

		table = new JTable();
		table.setBounds(10, 114, 726, 156);
		getContentPane().add(table);

		JButton btnCalcular = new JButton("Calcular");
		btnCalcular.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				retorno = consultar();
				contadorPri = consultar2();
				total = (retorno / contadorPri);

				// Formato
				DecimalFormat df = new DecimalFormat(".00");
				
				// Trasforma
				String convierte = String.valueOf(retorno);
				String convierte2 = String.valueOf(df.format(total));
				
				
				// Muestra en cuadro
				textTotal.setText(convierte);
				textPromedio.setText(convierte2);
			}
		});
		btnCalcular.setForeground(Color.BLACK);
		btnCalcular.setFont(new Font("Bell MT", Font.BOLD, 15));
		btnCalcular.setBackground(new Color(255, 69, 0));
		btnCalcular.setBounds(647, 36, 89, 30);
		getContentPane().add(btnCalcular);

		JLabel label = new JLabel("ID");
		label.setFont(new Font("Bell MT", Font.BOLD, 12));
		label.setBounds(29, 96, 19, 14);
		getContentPane().add(label);

		JLabel label_1 = new JLabel("NOMBRES");
		label_1.setFont(new Font("Bell MT", Font.BOLD, 12));
		label_1.setBounds(87, 96, 64, 14);
		getContentPane().add(label_1);

		JLabel label_2 = new JLabel("CIUDAD");
		label_2.setFont(new Font("Bell MT", Font.BOLD, 12));
		label_2.setBounds(163, 96, 56, 14);
		getContentPane().add(label_2);

		JLabel label_3 = new JLabel("GENERO");
		label_3.setFont(new Font("Bell MT", Font.BOLD, 12));
		label_3.setBounds(229, 96, 56, 14);
		getContentPane().add(label_3);

		JLabel label_4 = new JLabel("FECHA");
		label_4.setFont(new Font("Bell MT", Font.BOLD, 12));
		label_4.setBounds(306, 96, 46, 14);
		getContentPane().add(label_4);

		JLabel label_5 = new JLabel("HORA_INIC");
		label_5.setFont(new Font("Bell MT", Font.BOLD, 12));
		label_5.setBounds(372, 96, 71, 14);
		getContentPane().add(label_5);

		JLabel label_6 = new JLabel("HORA_FIN");
		label_6.setFont(new Font("Bell MT", Font.BOLD, 12));
		label_6.setBounds(454, 96, 80, 14);
		getContentPane().add(label_6);

		JLabel label_7 = new JLabel("100%\r\n");
		label_7.setFont(new Font("Bell MT", Font.BOLD, 12));
		label_7.setBounds(544, 96, 31, 14);
		getContentPane().add(label_7);

		JLabel label_8 = new JLabel("ACIERTOS");
		label_8.setFont(new Font("Bell MT", Font.BOLD, 12));
		label_8.setBounds(597, 96, 64, 14);
		getContentPane().add(label_8);

		JLabel label_9 = new JLabel("ERRORES");
		label_9.setFont(new Font("Bell MT", Font.BOLD, 12));
		label_9.setBounds(678, 96, 58, 14);
		getContentPane().add(label_9);

		JLabel lblTotal = new JLabel("TOTAL:");
		lblTotal.setFont(new Font("Bell MT", Font.BOLD, 13));
		lblTotal.setBounds(547, 302, 61, 14);
		getContentPane().add(lblTotal);

		JLabel lblPromedio = new JLabel("PROMEDIO:");
		lblPromedio.setFont(new Font("Bell MT", Font.BOLD, 13));
		lblPromedio.setBounds(528, 328, 80, 14);
		getContentPane().add(lblPromedio);
		
		JLabel label_10 = new JLabel("%");
		label_10.setBounds(690, 328, 19, 14);
		getContentPane().add(label_10);

	}

	// Metodo Consultar
	public double consultar() {
		double calcula = 0;

		Object[] columnas = { "ID", "NOMBRES", "PROVINCIA", "GENERO", "FECHA", "HORA_INICIO", "HORA_FINAL", "NOTA",
				"ACIERTOS", "ERRORES" };
		DefaultTableModel modelo = new DefaultTableModel(null, columnas);
		table.setModel(modelo);

		try {

			ResultSet bus;
			bus = objExamen.consultarRegistros();

			while (bus.next()) {

				int id_registros = bus.getInt("id_Examenchat");
				String nombres = bus.getString("nombres");
				String provincias = bus.getString("provincia");
				String genero = bus.getString("genero");
				String fecha = bus.getString("fecha");
				String horaInicio = bus.getString("horaInicio");
				String horaFinal = bus.getString("horaFinal");
				int notas = bus.getInt("nota");
				int aciertos = bus.getInt("aciertos");
				int errores = bus.getInt("errores");

				// Trasforma a String para mostrar en tabla
				String trasforma1 = String.valueOf(id_registros);
				String trasforma2 = String.valueOf(notas);
				String trasforma3 = String.valueOf(aciertos);
				String trasforma4 = String.valueOf(errores);

				String modeloTemp[] = { trasforma1, nombres, provincias, genero, fecha, horaInicio, horaFinal,
						trasforma2, trasforma3, trasforma4 };
				modelo.addRow(modeloTemp);

				calcula = notas + calcula;

			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "No se encontro registro");
		}
		// Retorna valor
		return calcula;
	}

	// otr
	// Metodo Consultar
	public int consultar2() {
		int contador = 0;

		try {

			ResultSet bus;
			bus = objExamen.consultarRegistros();

			while (bus.next()) {

				int id_registros = bus.getInt("id_Examenchat");
				String nombres = bus.getString("nombres");
				String provincias = bus.getString("provincia");
				String genero = bus.getString("genero");
				String fecha = bus.getString("fecha");
				String horaInicio = bus.getString("horaInicio");
				String horaFinal = bus.getString("horaFinal");
				int notas = bus.getInt("nota");
				int aciertos = bus.getInt("aciertos");
				int errores = bus.getInt("errores");

				// Trasforma a String para mostrar en tabla
				String trasforma1 = String.valueOf(id_registros);
				String trasforma2 = String.valueOf(notas);
				String trasforma3 = String.valueOf(aciertos);
				String trasforma4 = String.valueOf(errores);

				contador++;

			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "No se encontro registro");
		}
		// Retorna valor
		return contador;
	}
}
