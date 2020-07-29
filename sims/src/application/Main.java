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
		System.out.println("Test");
		LoginMenadzer login = new LoginMenadzer(datoteke.getKorisnici());
		boolean radi = true;
		while(radi) {
			Korisnik korisnik = login.uloguj();
			Sesija sesija = Sesija.namestiSesiju(korisnik, datoteke);
			radi = sesija.izvrsi();
		}
		datoteke.sacuvaj();
		
	}

}
