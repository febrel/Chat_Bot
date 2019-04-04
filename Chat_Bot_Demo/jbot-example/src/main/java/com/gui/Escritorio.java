package com.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JDesktopPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import java.awt.Dimension;
import java.awt.Color;

public class Escritorio extends JFrame {

	private JPanel contentPane;
	private JDesktopPane Escritorio2;
	private JMenuBar menuBar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {

			public void run() {

				try {
					Escritorio frame = new Escritorio();
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
	public Escritorio() {
		setUndecorated(true);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 762, 474);
		contentPane = new JPanel();
		contentPane.setMinimumSize(new Dimension(0, 0));
		contentPane.setInheritsPopupMenu(true);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 762, 35);
		contentPane.add(menuBar);

		JMenu mnNewMenu = new JMenu("Consultar");
		mnNewMenu.setFont(new Font("Bell MT", Font.BOLD | Font.ITALIC, 17));
		menuBar.add(mnNewMenu);

		JMenuItem mntmPromedio = new JMenuItem("Promedio");
		mntmPromedio.setFont(new Font("Bell MT", Font.PLAIN, 14));
		mntmPromedio.addActionListener(new ActionListener() {
			
			// Accion
			public void actionPerformed(ActionEvent arg0) {
				Promedio inter = new Promedio();
				Escritorio2.add(inter);
				inter.setVisible(true);
			}
		});

		mnNewMenu.add(mntmPromedio);

		JMenuItem mntmPersonas = new JMenuItem("Personas");
		mntmPersonas.setFont(new Font("Bell MT", Font.PLAIN, 14));
		mntmPersonas.addActionListener(new ActionListener() {

			// Accion
			public void actionPerformed(ActionEvent arg0) {
				Personas inter = new Personas();
				Escritorio2.add(inter);
				inter.setVisible(true);
			}
		});
		mnNewMenu.add(mntmPersonas);

		JMenu mnEliminar = new JMenu("Eliminar");
		mnEliminar.setFont(new Font("Bell MT", Font.BOLD | Font.ITALIC, 17));
		menuBar.add(mnEliminar);

		JMenuItem mntmRegistro = new JMenuItem("Registro");
		mntmRegistro.setFont(new Font("Bell MT", Font.PLAIN, 14));
		mntmRegistro.addActionListener(new ActionListener() {
			// Accion
			public void actionPerformed(ActionEvent arg0) {
				Registro inter = new Registro();
				Escritorio2.add(inter);
				inter.setVisible(true);
			}
		});
		mnEliminar.add(mntmRegistro);

		Escritorio2 = new JDesktopPane();
		Escritorio2.setBounds(-20, 34, 792, 395);
		Escritorio2.setToolTipText("");
		contentPane.add(Escritorio2);
		
		JButton btnSalir = new JButton("SALIR");
		btnSalir.setForeground(new Color(255, 255, 255));
		btnSalir.setFont(new Font("Bell MT", Font.PLAIN, 15));
		btnSalir.setBackground(new Color(0, 0, 0));
		btnSalir.setBounds(671, 440, 81, 23);
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 System.exit(0);
			}
		});
		contentPane.add(btnSalir);

		// Para colocarlo en el centro
		this.setLocationRelativeTo(null);
	}
}
