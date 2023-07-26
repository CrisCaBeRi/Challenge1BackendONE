package com.cris.conversor.ui;

import java.awt.EventQueue;

import com.cris.conversor.logica.*;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

//Clase realizada con la interfaz gráfica de awt
public class MiConversor extends Operaciones {

	private JFrame frame;
	private JButton btn;
	private JComboBox cmb;
	private JLabel lbl;
	private JTextField txt;

	// Enumeracion para las opciones
	public enum Moneda {
		pesos_dolar, pesos_euro, pesos_libra, dolar_pesos, euro_pesos, libra_pesos
	}

	// Definicion del precio para cada moneda
	public double dolar = 3.965;
	public double euro = 4.381;
	public double libra = 5.102;

	// Valor de la caja de texto
	public double valorInput = 0.00;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MiConversor window = new MiConversor();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MiConversor() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		txt = new JTextField();
		txt.setBounds(28, 40, 86, 20);
		frame.getContentPane().add(txt);
		txt.setColumns(10);

		cmb = new JComboBox<Moneda>();
		//
		cmb.setModel(new DefaultComboBoxModel<>(Moneda.values()));// <- uso del enum
		cmb.setBounds(28, 81, 132, 22);
		frame.getContentPane().add(cmb);

		// BOTON
		btn = new JButton("Convertir");
		// Evento
		btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Convertir();
			}
		});
		btn.setBounds(190, 81, 89, 23);
		frame.getContentPane().add(btn);

		lbl = new JLabel("00.00");
		lbl.setBounds(124, 40, 132, 20);
		frame.getContentPane().add(lbl);
	}

	public void Convertir() {
		if (Validar(txt.getText())) {
			
			Moneda moneda = (Moneda) cmb.getSelectedItem();
			
			switch (moneda) {
			
			case pesos_dolar:
				lbl.setText(String.valueOf(Redondear(PesosADolar(dolar, valorInput))));
				break;
			case pesos_euro:
				lbl.setText(String.valueOf(Redondear(PesosAEuros(euro, valorInput))));
				break;
			case pesos_libra:
				lbl.setText(Redondear(PesosALibras(libra, valorInput)));
				break;
			case dolar_pesos:
				lbl.setText(Redondear(DolarAPesos(dolar, valorInput)));
				break;
			case euro_pesos:
				lbl.setText(Redondear(EuroAPesos(euro, valorInput)));
				break;
			case libra_pesos:
				lbl.setText(Redondear(LibraAPesos(libra, valorInput)));
				break;
			default:
				throw new IllegalArgumentException("Valor no esperado");
			}
		}
	}

	public String Redondear(double valor) {
		DecimalFormat df = new DecimalFormat("#.##");
		df.setRoundingMode(RoundingMode.HALF_UP);
		return df.format(valor);
	}

	//Validar que sean solo numero
	public boolean Validar(String texto) {
	    try {
	        double x = Double.parseDouble(texto);
	        if (x > 0) {
	            valorInput = x;
	            return true;
	        } else {
	            lbl.setText("El número debe ser mayor que cero");
	            return false;
	        }
	    } catch (NumberFormatException e) {
	        lbl.setText("Solo números");
	        return false;
	    }
	}
}
