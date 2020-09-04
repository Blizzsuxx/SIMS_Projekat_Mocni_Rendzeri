package application;

import java.io.IOException;
import java.text.ParseException;

import controler.CitacDatoteka;
import controler.LoginMenadzer;

public class Main {

	public static void main(String[] args) throws ParseException, IOException {
		// TODO Auto-generated method stub
		
		CitacDatoteka datoteke = new CitacDatoteka();
		
			datoteke.inicijalizuj();
		
		
		LoginMenadzer login = new LoginMenadzer(datoteke);
		login.uloguj();
		
	}

}
