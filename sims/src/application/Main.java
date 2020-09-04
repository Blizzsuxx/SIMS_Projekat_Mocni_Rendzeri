package application;

import controler.CitacDatoteka;
import controler.LoginMenadzer;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		CitacDatoteka datoteke = new CitacDatoteka();
		try {
			datoteke.inicijalizuj();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		LoginMenadzer login = new LoginMenadzer(datoteke);
		login.uloguj();
		
	}

}
