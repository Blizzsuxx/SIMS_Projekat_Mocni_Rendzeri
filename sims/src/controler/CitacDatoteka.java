package controler;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Collection;
import java.util.List;

import au.com.bytecode.opencsv.CSVReader;
import model.Grupa;
import model.MuzickoDjelo;
import model.Pojedinacanizvodjac;
import model.Recenzija;

public class CitacDatoteka {
	
	private KorisniciMenadzer korisnici;
	private Collection<MuzickoDjelo> muzickaDela;
	private Collection<Grupa> grupe;
	private Collection<Pojedinacanizvodjac> izvodjaci;
	private Collection<Recenzija> recenzije;
	
	public Collection<MuzickoDjelo> getMuzickaDela() {
		return muzickaDela;
	}

	public Collection<Grupa> getGrupe() {
		return grupe;
	}

	public Collection<Pojedinacanizvodjac> getIzvodjaci() {
		return izvodjaci;
	}

	public Collection<Recenzija> getRecenzije() {
		return recenzije;
	}

	public void inicijalizuj() {
		// TODO Auto-generated method stub
		FileReader reader = null;
		try {
			reader = new FileReader("./fajlovi/Korisnici.txt");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			korisnici = new KorisniciMenadzer(readAll(reader));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public KorisniciMenadzer getKorisnici() {
		// TODO Auto-generated method stub
		return korisnici;
	}
	
	
	
	private List<String[]> readAll(Reader reader) throws IOException  {
	    CSVReader csvReader = new CSVReader(reader, '|');
	    List<String[]> list;
	    list = csvReader.readAll();
	    reader.close();
	    csvReader.close();
	    return list;
	}

	public void sacuvaj() {
		// TODO Auto-generated method stub
		
	}

}
