package controler;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.imageio.ImageIO;

import au.com.bytecode.opencsv.CSVReader;
import model.Grupa;
import model.MuzickoDelo;
import model.Recenzija;

public class CitacDatoteka {

	private KorisniciMenadzer korisnici;
	private MuzickoDeloMenadzer deloMenadzer;//pozvace ocene, a prosledicu mu zanrove
	private IzvodjacMenadzer izvodjaci;
	private UtisakMenadzer utisakmenadzer;
	private ZakazanaRecenzijaMenadzer zakRecMenadzer;
	private ZanroviMenadzer zanrovi;
	private AlbumKontroler albumi;
	private OceneKontroler ocene;
	private ClanoviMenadzer clanoviGrupe;
	private RecenzijeZaIzmenuMenadzer izmena;
	

	public KorisniciMenadzer getKorisnici() {
		return this.korisnici;
	}

	public MuzickoDeloMenadzer getDeloMenadzer() {
		return this.deloMenadzer;
	}

	public IzvodjacMenadzer getIzvodjaci() {
		return this.izvodjaci;
	}

	public UtisakMenadzer getUtisakmenadzer() {
		return this.utisakmenadzer;
	}

	public ZakazanaRecenzijaMenadzer getZakRecMenadzer() {
		return this.zakRecMenadzer;
	}

	public ZanroviMenadzer getZanrovi() {
		return this.zanrovi;
	}

	public AlbumKontroler getAlbumi() {
		return this.albumi;
	}

	public OceneKontroler getOcene() {
		return this.ocene;
	}

	public ClanoviMenadzer getClanoviGrupe() {
		return this.clanoviGrupe;
	}

	public RecenzijeZaIzmenuMenadzer getIzmena() {
		return this.izmena;
	}
	
	private List<String[]> ucitaj(String nazivFajla, char separator){
		FileReader reader = null;
		try {
			reader = new FileReader("."+Constants.FILE_SEPARATOR + "fajlovi" + Constants.FILE_SEPARATOR+nazivFajla);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			try {
				reader = new FileReader("."+Constants.FILE_SEPARATOR+"sims"+Constants.FILE_SEPARATOR+"fajlovi"+Constants.FILE_SEPARATOR+nazivFajla);
			} catch (FileNotFoundException e2) {
				System.out.println(nazivFajla + " NIJE PROCITAN!!!");
				return new ArrayList<>();
			}
		}

		List<String[]> procitanTekst = null;

		try {
			procitanTekst = readAll(reader, separator);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return procitanTekst;

	}
	

	public void inicijalizuj() {
		// TODO Auto-generated method stub

		korisnici = new KorisniciMenadzer(ucitaj("korisnici.txt", ','));
		zanrovi = new ZanroviMenadzer(ucitaj("zanrovi.txt", ','));
		izvodjaci = new IzvodjacMenadzer(ucitaj("izvodjaci.txt", ','));

		deloMenadzer = new MuzickoDeloMenadzer(izvodjaci, zanrovi.getSviZanrovi(), ucitaj("muzickaDela.txt", ','));
		utisakmenadzer = new UtisakMenadzer(deloMenadzer.getDela(), korisnici, ucitaj("utisci.txt", ','));
		zakRecMenadzer = new ZakazanaRecenzijaMenadzer(korisnici,(ArrayList<Recenzija>)utisakmenadzer.getRecenzije(), ucitaj("zakazaneRecenzije.txt", ';'));
		//potrebno jos albume, i ocene
		//recenzije za izmenu, clanove grupe
		albumi = new AlbumKontroler(deloMenadzer, izvodjaci, korisnici, ucitaj("albumi.txt", ','));
		ocene = new OceneKontroler(deloMenadzer, korisnici, ucitaj("ocene.txt", ','));
		clanoviGrupe = new ClanoviMenadzer(izvodjaci, izvodjaci.getGrupe(), ucitaj("clanstva.txt",','));
		izmena = new RecenzijeZaIzmenuMenadzer((ArrayList<Recenzija>)utisakmenadzer.getRecenzije(), ucitaj("recenzijeZaIzmenu.txt", ';'));


		

		Constants.MUZICKA_IKONA  = procitajSliku("fajlovi/muzika.png");
		Constants.KORISNICKA_IKONA = procitajSliku("fajlovi/korisnik.png");

	}


	public static BufferedImage procitajSliku(String path) {


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

	
	private List<String[]> readAll(Reader reader, char separator) throws IOException  {
	    CSVReader csvReader = new CSVReader(reader, separator);
	    List<String[]> list;
	    list = csvReader.readAll();
	    reader.close();
	    csvReader.close();
	    return list;
	}


	public void sacuvaj() {
		korisnici.sacuvaj();
		clanoviGrupe.sacuvaj();
		ocene.sacuvaj();
		albumi.sacuvaj();
		zakRecMenadzer.sacuvaj();
		utisakmenadzer.sacuvaj();
		deloMenadzer.sacuvaj(izvodjaci.getSvi());
		//izvodjaci.sacuvaj();
		zanrovi.sacuvaj();//jos nesto treba sacuvati?? treba uzitati za izmenu
		izmena.sacuvaj();
		
	}

	public Collection<MuzickoDelo> getMuzickaDela() {
		return this.deloMenadzer.getDela();
	}

	public Collection<Grupa> getGrupe() {
		return this.izvodjaci.getGrupe();
	}

	public Collection<Recenzija> getRecenzije() {
		return this.utisakmenadzer.getRecenzije();
	}

	

}
