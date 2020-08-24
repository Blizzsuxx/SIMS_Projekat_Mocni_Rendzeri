package controler;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import au.com.bytecode.opencsv.CSVReader;
import model.Album;
import model.Grupa;
import model.Izvodjac;
import model.Komentar;
import model.Korisnik;
import model.MuzickoDjelo;
import model.Nalog;
import model.Pojedinacanizvodjac;
import model.Recenzija;
import model.Sesija;
import model.ZakazanaRecenzija;
import model.Zanr;

public class CitacDatoteka {
	//bla
	private KorisniciMenadzer korisnici;
	private NalogMenadzer nalogMenadzer;
	private Collection<Nalog> nalozi;
	private MuzickoDeloMenadzer deloMenadzer;//pozvace ocene, a prosledicu mu zanrove
	private Collection<MuzickoDjelo> muzickaDela;
	private Collection<Grupa> grupe;
	private IzvodjacMenadzer izvodjaci;
	private Collection<Pojedinacanizvodjac> izvodjaciSolo;
	private UtisakMenadzer utisakmenadzer;
	private Collection<Recenzija> recenzije;//ucitaj i koje su za izmenu
	private ZakazanaRecenzijaMenadzer zakRecMenadzer;
	private Collection<ZakazanaRecenzija> zakazaneRecenzije;
	private ZanroviMenadzer zanrovi;
	private Collection<Zanr> kolekcijaZanrova;
	private Collection<Komentar> komentari;
	private AlbumKontroler albumi;
	private OceneKontroler ocene;
	private ClanoviMenadzer clanoviGrupe;
	private RecenzijeZaIzmenuMenadzer izmena;
	private Sesija s;
	
	
	
	
	public Collection<MuzickoDjelo> getMuzickaDela() {
		return muzickaDela;
	}

	public Collection<Grupa> getGrupe() {
		return grupe;
	}

	public Collection<Pojedinacanizvodjac> getIzvodjaci() {
		return izvodjaciSolo;
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
			//korisnici = new KorisniciMenadzer(readAll(reader));
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

	public CitacDatoteka(Sesija s) {
		super();
		this.s = s;
		ucitaj();
		s.setSviZanrovi(zanrovi.getSviZanrovi());
		s.setDela(muzickaDela);
		s.setGrupe(grupe);
		s.setRecenzije(recenzije);
		s.setUmetnici(izvodjaciSolo);
		//treba jos svasta setovati, jer ovde je sve bukvalno a nzm sta sve treba biti u sesiji
	
	}

	public void ucitaj() {
		zanrovi.ucitajZanrove();
		nalozi=nalogMenadzer.ucitajNaloge(korisnici.getKorisniciCollection());
		izvodjaci.ucitajIzvodjace(grupe, izvodjaciSolo);
		muzickaDela=deloMenadzer.ucitajDela(izvodjaci, zanrovi.getZanrovi());
		utisakmenadzer.ucitajUtiske(muzickaDela, korisnici);
		recenzije=utisakmenadzer.getRecenzije();
		komentari=utisakmenadzer.getKomentari();
		zakRecMenadzer.ucitajZakazane(korisnici,(ArrayList<Recenzija>)recenzije);
		//potrebno jos albume, i ocene
		//recenzije za izmenu, clanove grupe
		albumi.ucitaj(deloMenadzer, izvodjaci, korisnici);
		ocene.ucitaj(deloMenadzer, korisnici);
		clanoviGrupe.ucitaj(izvodjaci, izvodjaci.getGrupe());
		izmena.ucitaj(recenzije);
	}
	


	public void sacuvaj() {
		clanoviGrupe.sacuvaj();
		ocene.sacuvaj();
		albumi.sacuvaj();
		zakRecMenadzer.sacuvaj();
		utisakmenadzer.sacuvaj();
		deloMenadzer.sacuvaj(izvodjaci.getSvi());
		izvodjaci.sacuvaj();
		nalogMenadzer.sacuvaj((ArrayList<Korisnik>)korisnici.getKorisniciCollection());
		zanrovi.sacuvaj();//jos nesto treba sacuvati?? treba uzitati za izmenu
		izmena.sacuvaj();
		
	}

}
