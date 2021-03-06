package controler;

import java.awt.image.BufferedImage;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import model.FrontEndKorisnik;
import model.Izvodjac;
import model.Korisnik;
import model.KorisnikAplikacije;
import model.MuzickoDelo;
import model.Pol;
import model.Slikovit;
import model.Zanr;

public abstract class Constants {
	
	
	
	public static final SimpleDateFormat FORMAT_ZA_DATUM = new SimpleDateFormat("dd/MM/yyyy");

	public static final SimpleDateFormat NATASIN_FORMAT_ZA_DATUM = new SimpleDateFormat("dd.MM.yyyy.");

	public static BufferedImage MUZICKA_IKONA;
	
	public static BufferedImage ALBUM_IKONA;
	
	public static BufferedImage KORISNICKA_IKONA;
	
	public static BufferedImage UREDNIK_IKONA;
	
	public static BufferedImage POJEDINACAN_IZVODJAC_IKONA;
	
	public static BufferedImage GRUPA_IKONA;
	
	// dodaj konsruktor za ovaj dio
	public static final MuzickoDelo BARBIE_GIRL = new MuzickoDelo("Aqua - Barbie Girl", "Barbi devojka");
	public static final MuzickoDelo PANTERI = new MuzickoDelo("Roki Vulovic - Panteri", "Panteri");
	public static final MuzickoDelo ORELOLA = new MuzickoDelo("Narodna Pesma - Ore Lola", "Ore Lola");
	public static final MuzickoDelo KING = new MuzickoDelo("Toma Zdravkovic - Ej Branka Branka", "Ej Branka Branka");
	public static final MuzickoDelo KINGG = new MuzickoDelo("Toma Zdravkovic - Svirajte nocas samo za nju", "Svirajte nocas samo za nju");
	
	public static final List<Slikovit> DELA = Collections.unmodifiableList(Arrays.asList(PANTERI, BARBIE_GIRL));
	public static final List<Slikovit> DELA2 = Collections.unmodifiableList(Arrays.asList(ORELOLA));
	public static final List<Slikovit> DELA3 = Collections.unmodifiableList(Arrays.asList(KING, KINGG));

	public static final String FILE_SEPARATOR = System.getProperty("file.separator");
	
	public static final Date DATUM = new Date();
	
	public static final Korisnik DUMMY = new KorisnikAplikacije("Ime", "Prezime", "eMail", Pol.muski, DATUM, true, "sifra", "kime", 
			DATUM, new ArrayList<MuzickoDelo>(), new ArrayList<Zanr>(), new ArrayList<KorisnikAplikacije>(), new ArrayList<FrontEndKorisnik>(), 
			new ArrayList<Izvodjac>());

	
}
