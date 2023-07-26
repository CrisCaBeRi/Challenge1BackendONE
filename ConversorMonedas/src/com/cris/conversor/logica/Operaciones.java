package com.cris.conversor.logica;



public class Operaciones {	
	public double PesosADolar (double dolar, double valorInput) {
		double res = valorInput / dolar; 		
		return res;
		
	}
	public double PesosAEuros (double euros, double valorInput) {
		double res = valorInput / euros; 
		return res;		
	}
	
	public double PesosALibras (double libras, double valorInput) {
		double res = valorInput / libras; 
		return res;	
		
	}
	public double DolarAPesos (double dolar, double valorInput) {
		double res = valorInput * dolar; 
		return res;	
		
	}
	public double EuroAPesos (double euro, double valorInput) {
		double res = valorInput * euro; 
		return res;
		
	}
	public double LibraAPesos (double libra, double valorInput) {
		double res = valorInput * libra; 
		return res;		
	}
	
	

}
