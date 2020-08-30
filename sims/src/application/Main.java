package application;

import controler.CitacDatoteka;
import controler.LoginMenadzer;
import model.Korisnik;
import model.Sesija;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CitacDatoteka datoteke = new CitacDatoteka();
		datoteke.inicijalizuj();
		LoginMenadzer login = new LoginMenadzer(datoteke);
		login.uloguj();
		
	}

}
