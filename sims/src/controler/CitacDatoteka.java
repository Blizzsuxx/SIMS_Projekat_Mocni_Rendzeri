package controler;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Collection;
import java.util.List;

import javax.imageio.ImageIO;

import au.com.bytecode.opencsv.CSVReader;
import model.Grupa;
import model.MuzickoDelo;
import model.Pojedinacanizvodjac;
import model.Recenzija;

public class CitacDatoteka {
	
	private KorisniciMenadzer korisnici;
	private Collection<MuzickoDelo> muzickaDela;
	private Collection<Grupa> grupe;
	private Collection<Pojedinacanizvodjac> izvodjaci;
	private Collection<Recenzija> recenzije;
	
	public Collection<MuzickoDelo> getMuzickaDela() {
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
			reader = new FileReader("./fajlovi/korisnici.txt");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			try {
				reader = new FileReader("./sims/fajlovi/korisnici.txt");
			} catch (FileNotFoundException e2) {
				//TODO: handle exception
			}
		}
		try {
			korisnici = new KorisniciMenadzer(readAll(reader));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		Constants.MUZICKA_IKONA  = procitajSliku("fajlovi/muzika.png");

	}

	public static BufferedImage procitajSliku(String path){


		try {
			return ImageIO.read(new File(path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			try {
				return ImageIO.read(new File("sims/"+path));
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		}
		return null;

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
