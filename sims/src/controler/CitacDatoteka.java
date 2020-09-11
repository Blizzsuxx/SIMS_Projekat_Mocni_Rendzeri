package controler;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.imageio.ImageIO;

import au.com.bytecode.opencsv.CSVReader;
import model.Administrator;
import model.FrontEndKorisnik;
import model.Grupa;
import model.Korisnik;
import model.KorisnikAplikacije;
import model.MuzickoDelo;
import model.Recenzija;

public class CitacDatoteka {

	private KorisniciMenadzer korisnici;
	//private MuzickoDeloMenadzer deloMenadzer;//pozvace ocene, a prosledicu mu zanrove
	private IzvodjacMenadzer izvodjaci;
	private UtisakMenadzer utisakmenadzer;
	private ZakazanaRecenzijaMenadzer zakRecMenadzer;
	private ZanroviMenadzer zanrovi;
	//private AlbumKontroler albumi;
	private OceneKontroler ocene;
	private ClanoviMenadzer clanoviGrupe;
	private RecenzijeZaIzmenuMenadzer izmena;
	private GlasanjeMenadzer glasanjeMenadzer;
	
	private MuzickiSadrzajMenadzer muzickiSadrzajMenadzer;
	
	public MuzickiSadrzajMenadzer getMuzickiSadrzajMenadzer() {
		return muzickiSadrzajMenadzer;
	}

	public void setMuzickiSadrzajMenadzer(MuzickiSadrzajMenadzer muzickiSadrzajMenadzer) {
		this.muzickiSadrzajMenadzer = muzickiSadrzajMenadzer;
	}

	public KorisniciMenadzer getKorisnici() {
		return this.korisnici;
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

	public OceneKontroler getOcene() {
		return this.ocene;
	}

	public ClanoviMenadzer getClanoviGrupe() {
		return this.clanoviGrupe;
	}

	public RecenzijeZaIzmenuMenadzer getIzmena() {
		return this.izmena;
	}
	
	public GlasanjeMenadzer getGlasanjeMenadzer() {
		return glasanjeMenadzer;
	}
	
	public void setGlasanjeMenadzer(GlasanjeMenadzer glasanjeMenadzer) {
		this.glasanjeMenadzer = glasanjeMenadzer;
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
	

	public void inicijalizuj() throws ParseException, IOException {
		// TODO Auto-generated method stub

		korisnici = new KorisniciMenadzer(ucitaj("korisnici.txt", ','));
		zanrovi = new ZanroviMenadzer(ucitaj("zanrovi.txt", ','), korisnici);
		izvodjaci = new IzvodjacMenadzer(ucitaj("izvodjaci.txt", ';'), zanrovi);

		muzickiSadrzajMenadzer = new MuzickiSadrzajMenadzer(izvodjaci, korisnici, zanrovi, 
				"fajlovi"+System.getProperty("file.separator")+"muzickiSadrzaj.txt", 
				"fajlovi"+System.getProperty("file.separator")+"albumDjela.txt");
		
		utisakmenadzer = new UtisakMenadzer(muzickiSadrzajMenadzer.getMuzickaDela(), korisnici, ucitaj("utisci.txt", ';'));
		zakRecMenadzer = new ZakazanaRecenzijaMenadzer(korisnici,(ArrayList<Recenzija>)utisakmenadzer.getRecenzije(), ucitaj("zakazaneRecenzije.txt", ';'));
		ocene = new OceneKontroler(muzickiSadrzajMenadzer, korisnici, ucitaj("ocene.txt", ','));
		clanoviGrupe = new ClanoviMenadzer(izvodjaci, izvodjaci.getGrupe(), ucitaj("clanstva.txt",','));
		izmena = new RecenzijeZaIzmenuMenadzer((ArrayList<Recenzija>)utisakmenadzer.getRecenzije(), ucitaj("recenzijeZaIzmenu.txt", ';'));
		glasanjeMenadzer = new GlasanjeMenadzer(ucitajBuffered("glasovi.txt"), ucitajBuffered("uredniciKojiSuGlasali.txt"), 
				(ArrayList<MuzickoDelo>) muzickiSadrzajMenadzer.getMuzickaDela(), korisnici);

		

		Constants.MUZICKA_IKONA  = procitajSliku("fajlovi/muzika.png");
		Constants.KORISNICKA_IKONA = procitajSliku("fajlovi/korisnik.png");
		Constants.ALBUM_IKONA = procitajSliku("fajlovi/album.png");
		Constants.UREDNIK_IKONA = procitajSliku("fajlovi/urednik.png");
		Constants.POJEDINACAN_IZVODJAC_IKONA = procitajSliku("fajlovi/solo_izvodjac.png");
		Constants.GRUPA_IKONA = procitajSliku("fajlovi/grupa.png");

	}


	public static BufferedImage procitajSliku(String path) throws IOException {


		try {
			return ImageIO.read(new File(path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			
				return ImageIO.read(new File("sims/"+path));
		}

	}

	
	private List<String[]> readAll(Reader reader, char separator) throws IOException  {
	    CSVReader csvReader = new CSVReader(reader, separator);
	    List<String[]> list;
	    list = csvReader.readAll();
	    reader.close();
	    csvReader.close();
	    return list;
	}
	
	private ArrayList<String> ucitajBuffered(String fajl) throws FileNotFoundException{
		File f = new File("."+Constants.FILE_SEPARATOR + "fajlovi" + Constants.FILE_SEPARATOR+fajl);
		if(!f.exists()){
			f = new File("."+Constants.FILE_SEPARATOR + "sims" + Constants.FILE_SEPARATOR + "fajlovi" + Constants.FILE_SEPARATOR+fajl);
		}
		@SuppressWarnings("resource")
		BufferedReader bf = new BufferedReader(new FileReader(f));
		String linija;
		ArrayList<String> linije = new ArrayList<String>();
		try {
			while ((linija = bf.readLine()) != null) {
				linije.add(linija);
			}
		} catch (IOException e) {
			System.out.println(fajl + " NIJE PROCITAN!!!");
			return new ArrayList<String>();
		}
		return linije;
	}


	public void sacuvaj() throws ParseException {
		korisnici.sacuvaj();
		clanoviGrupe.sacuvaj();
		ocene.sacuvaj();
		zakRecMenadzer.sacuvaj();
		utisakmenadzer.sacuvaj();

		izvodjaci.sacuvaj();
		zanrovi.sacuvaj();//jos nesto treba sacuvati?? treba uzitati za izmenu
		izmena.sacuvaj();
		glasanjeMenadzer.sacuvaj();
		
		zanrovi.sacuvajZanroveUrednike(); //
		muzickiSadrzajMenadzer.sacuvaj("fajlovi"+System.getProperty("file.separator")+"muzickiSadrzaj.txt"); //
		muzickiSadrzajMenadzer.sacuvajAlbumeDjela("fajlovi"+System.getProperty("file.separator")+"albumDjela.txt");
	}


	public Collection<Grupa> getGrupe() {
		return this.izvodjaci.getGrupe();
	}

	public Collection<Recenzija> getRecenzije() {
		return this.utisakmenadzer.getRecenzije();
	}
	public void ucitajPratioce(KorisniciMenadzer korisnici, MuzickiSadrzajMenadzer md,ZanroviMenadzer zanrovi,IzvodjacMenadzer izvodjaci) {
		List<String[]> tekst=ucitaj("pracenje.txt",'|');
		for(String[] linija:tekst) {
			FrontEndKorisnik k=(FrontEndKorisnik)korisnici.trazi(linija[0].trim());
			
			String[] zanroviPracenje=linija[1].trim().split(";");
			for(String ime:zanroviPracenje) {
				k.getPreferiraniZanrovi().add(zanrovi.trazi(ime.trim()));
			}
			String[] pratioci=linija[2].trim().split(";");
			for(String p:pratioci) {
				k.getPratilac().add((KorisnikAplikacije)korisnici.trazi(p.trim()));
			}
			String[] dela=linija[3].trim().split(";");
			for(String d:dela) {
				k.getIstorija().add((MuzickoDelo)md.vratiNaOsnovuNazive(d.trim()));
			}
			if(linija.length>4) {
			String[] pratilac=linija[4].trim().split(";");
			for(String pr:pratilac) {
				((KorisnikAplikacije)k).getPratite().add((FrontEndKorisnik)korisnici.trazi(pr.trim()));
			}
			String[] izv=linija[5].trim().split(";");
			for(String i:izv) {
				((KorisnikAplikacije)k).getOnajKogaPrati().add( izvodjaci.nadiPoUmetnickomImenu(i.trim()));
			}
			 
			}
		}
	}
public void sacuvajPracenja() {
		
		PrintWriter pw=null;
		String sep=System.getProperty("file.separator");
		String putanja ="."+sep+"fajlovi"+sep+"pracenje.txt";
		try {
			pw=new PrintWriter(new FileWriter(putanja, false));
			for(Korisnik a:korisnici.getKorisnici().values()) {
				if(a instanceof Administrator) {
					continue;
				}else {
					
				pw.println(a.pratiociUpis());
				}
			}pw.close();
			
		}catch(IOException e) {
			e.printStackTrace();
		}finally {
			if(pw!=null) {
				pw.close();
			}
		}
	}
	

}
