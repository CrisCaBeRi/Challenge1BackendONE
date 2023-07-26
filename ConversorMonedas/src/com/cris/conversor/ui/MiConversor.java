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
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import java.awt.Window.Type;

//Clase realizada con la interfaz gráfica de awt
public class MiConversor extends Operaciones {

	private JFrame frmHello;
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
					window.frmHello.setVisible(true);
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
		frmHello = new JFrame();
		frmHello.setTitle("Challenge 1 ONE Backend");
		frmHello.setBounds(100, 100, 450, 300);
		frmHello.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmHello.getContentPane().setLayout(null);

		txt = new JTextField();
		txt.setBounds(28, 46, 132, 31);
		frmHello.getContentPane().add(txt);
		txt.setColumns(10);

		cmb = new JComboBox<Moneda>();
		//
		cmb.setModel(new DefaultComboBoxModel<>(Moneda.values()));// <- uso del enum
		cmb.setBounds(238, 50, 132, 22);
		frmHello.getContentPane().add(cmb);

		// BOTON
		btn = new JButton("Convertir");
		btn.setForeground(new Color(255, 255, 255));
		btn.setFont(new Font("Noto Sans", Font.BOLD, 16));
		btn.setBackground(new Color(0, 128, 255));
		// Evento
		btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Convertir();
			}
		});
		btn.setBounds(286, 219, 138, 31);
		frmHello.getContentPane().add(btn);

		lbl = new JLabel("00.00");
		lbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lbl.setForeground(new Color(0, 128, 255));
		lbl.setBackground(new Color(255, 255, 255));
		lbl.setHorizontalAlignment(SwingConstants.CENTER);
		lbl.setBounds(28, 98, 132, 20);
		frmHello.getContentPane().add(lbl);
		
		JLabel lblInput = new JLabel("Valor ingresado");
		lblInput.setHorizontalAlignment(SwingConstants.CENTER);
		lblInput.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		lblInput.setBounds(27, 11, 152, 25);
		frmHello.getContentPane().add(lblInput);
		
		JLabel lblNewLabel = new JLabel("Resultado");
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		lblNewLabel.setBounds(39, 116, 101, 22);
		frmHello.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Conversion");
		lblNewLabel_1.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 16));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(246, 13, 95, 20);
		frmHello.getContentPane().add(lblNewLabel_1);
	}

	public void Convertir() {
		if (Validar(txt.getText())) {
			
			Moneda moneda = (Moneda) cmb.getSelectedItem();
			
			switch (moneda) {
			
			case pesos_dolar:
				lbl.setText(Redondear(PesosADolar(dolar, valorInput)));
				break;
			case pesos_euro:
				lbl.setText(Redondear(PesosAEuros(euro, valorInput)));
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
		DecimalFormat df = new DecimalFormat();
		df.setRoundingMode(RoundingMode.HALF_UP);
		return df.format(valor);//Conversion a string
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
