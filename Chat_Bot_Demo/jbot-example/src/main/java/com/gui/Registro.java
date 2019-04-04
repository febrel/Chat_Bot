package com.gui;

import java.awt.EventQueue;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;
import java.sql.ResultSet;
import com.bl.BLExamenChat;
import java.awt.Component;
import javax.swing.ListSelectionModel;
import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Registro extends JInternalFrame {
	// Importo objeto BLExamenChat para usar sus metodos
	BLExamenChat objExamen = new BLExamenChat();
	private javax.swing.JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Registro frame = new Registro();
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
	public Registro() {
		// Cambia valor 0,0 para que aparesca dentro del JDesktopPane
		setBounds(20, 0, 762, 395);
		getContentPane().setLayout(null);

		JLabel lblPersonas = new JLabel("PERSONAS QUE RINDIERON EL EXAMEN\r\n");
		lblPersonas.setBounds(160, 11, 443, 14);
		lblPersonas.setFont(new Font("Bell MT", Font.BOLD | Font.ITALIC, 20));
		getContentPane().add(lblPersonas);

		// Creo aqui los tex label para ingreso o recolección de datos
		JTextPane textNombre = new JTextPane();
		textNombre.setBounds(97, 275, 189, 20);
		getContentPane().add(textNombre);

		JLabel labelId = new JLabel("");
		labelId.setBounds(97, 248, 40, 20);
		getContentPane().add(labelId);

		table = new javax.swing.JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				String valor1 = null;
				String valor2 = null;
				String valor3 = null;
				String valor4 = null;
				String valor5 = null;

				int row = (int) table.getSelectedRow();
				valor1 = (String) table.getModel().getValueAt(row, 0);
				valor2 = (String) table.getModel().getValueAt(row, 1);
				valor3 = (String) table.getModel().getValueAt(row, 2);
				valor4 = (String) table.getModel().getValueAt(row, 3);
				valor5 = (String) table.getModel().getValueAt(row, 4);

				labelId.setText(valor1);
				textNombre.setText(valor2);

			}
		});
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setName("");
		table.setAlignmentX(Component.LEFT_ALIGNMENT);
		table.setFont(new Font("Tahoma", Font.PLAIN, 11));
		table.setColumnSelectionAllowed(true);
		table.setBounds(10, 88, 726, 133);
		getContentPane().add(table);

		// jLabel para Tabla
		JLabel lblId = new JLabel("ID");
		lblId.setFont(new Font("Bell MT", Font.BOLD, 12));
		lblId.setBounds(29, 74, 19, 14);
		getContentPane().add(lblId);

		JLabel lblNombres = new JLabel("NOMBRES");
		lblNombres.setFont(new Font("Bell MT", Font.BOLD, 12));
		lblNombres.setBounds(87, 74, 64, 14);
		getContentPane().add(lblNombres);

		JLabel lblCiudad = new JLabel("CIUDAD");
		lblCiudad.setFont(new Font("Bell MT", Font.BOLD, 12));
		lblCiudad.setBounds(163, 74, 56, 14);
		getContentPane().add(lblCiudad);

		JLabel lblGenero = new JLabel("GENERO");
		lblGenero.setFont(new Font("Bell MT", Font.BOLD, 12));
		lblGenero.setBounds(229, 74, 56, 14);
		getContentPane().add(lblGenero);

		JLabel lblFecha = new JLabel("FECHA");
		lblFecha.setFont(new Font("Bell MT", Font.BOLD, 12));
		lblFecha.setBounds(306, 74, 46, 14);
		getContentPane().add(lblFecha);

		JLabel lblHorainic = new JLabel("HORA_INIC");
		lblHorainic.setFont(new Font("Bell MT", Font.BOLD, 12));
		lblHorainic.setBounds(372, 74, 71, 14);
		getContentPane().add(lblHorainic);

		JLabel lblHorafinal = new JLabel("HORA_FIN");
		lblHorafinal.setFont(new Font("Bell MT", Font.BOLD, 12));
		lblHorafinal.setBounds(454, 74, 80, 14);
		getContentPane().add(lblHorafinal);

		JLabel lblPorcentaje = new JLabel("100%\r\n");
		lblPorcentaje.setFont(new Font("Bell MT", Font.BOLD, 12));
		lblPorcentaje.setBounds(544, 74, 31, 14);
		getContentPane().add(lblPorcentaje);

		JLabel lblAciertos = new JLabel("ACIERTOS");
		lblAciertos.setFont(new Font("Bell MT", Font.BOLD, 12));
		lblAciertos.setBounds(597, 74, 64, 14);
		getContentPane().add(lblAciertos);

		JLabel lblErrores = new JLabel("ERRORES");
		lblErrores.setFont(new Font("Bell MT", Font.BOLD, 12));
		lblErrores.setBounds(678, 74, 58, 14);
		getContentPane().add(lblErrores);

		JLabel lblId_1 = new JLabel("Id:");
		lblId_1.setFont(new Font("Bell MT", Font.BOLD, 15));
		lblId_1.setBounds(68, 254, 19, 14);
		getContentPane().add(lblId_1);

		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(new Font("Bell MT", Font.BOLD, 15));
		lblNombre.setBounds(29, 281, 63, 14);
		getContentPane().add(lblNombre);

		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			// Accion
			public void actionPerformed(ActionEvent e) {
				// Creo variable para poder ingresar al metodo
				String id = labelId.getText();
				eliminarDetalle(id);
				actualizar();
			}
		});
		btnEliminar.setForeground(new Color(0, 0, 0));
		btnEliminar.setBackground(new Color(255, 69, 0));
		btnEliminar.setFont(new Font("Bell MT", Font.BOLD, 15));
		btnEliminar.setBounds(657, 324, 89, 30);
		getContentPane().add(btnEliminar);

		// Invoca
		consultar();

	}// Fin de la clase

	// Metodo Actualizar
	public void actualizar() {
		consultar();
	}

	// Metodo eliminar
	public void eliminarDetalle(String ingresa) {
		
		
		try {
			
			int retorno;
			int id_Usuario = Integer.parseInt(ingresa);
			retorno = objExamen.eliminarUsuario(id_Usuario);

			if (retorno == 1) {
				JOptionPane.showMessageDialog(null, "Eliminado");

			} else {
				JOptionPane.showMessageDialog(null, "No se pudo eliminar");
			}
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "No se pudo eliminar");
		}
		

	}

	// Metodo Consultar
	public void consultar() {
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

			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "No se encontro registro");
		}

	}
}
