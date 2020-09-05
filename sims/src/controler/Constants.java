package controler;

import java.awt.image.BufferedImage;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import model.FrontEndKorisnik;
import model.Izvodjac;
import model.Korisnik;
import model.KorisnikAplikacije;
import model.MuzickoDelo;
import model.Par;
import model.Pol;
import model.Zanr;

public abstract class Constants {
	
	
	
	public static final List<Par<String, Boolean>> POLJA_ZA_REGISTRACIJU = Collections.unmodifiableList(
		    Arrays.asList(new Par<String, Boolean>("Ime", true), new Par<String, Boolean>("Prezime", true), new Par<String, Boolean>("E-Mail", true), new Par<String, Boolean>("Datum Rodjenja", true), new Par<String, Boolean>("Korisnicko Ime", true), new Par<String, Boolean>("Sifra", true)));
	
	public static final List<String> ATRIBUTI_ZA_REGISTRACIJU = Collections.unmodifiableList(
		    Arrays.asList(new String[] {"Ime", "Prezime", "EMail", "DatumReflektor", "KorisnickoIme", "Sifra"}));
	
	public static final SimpleDateFormat FORMAT_ZA_DATUM = new SimpleDateFormat("dd/MM/yyyy");

	public static final SimpleDateFormat NATASIN_FORMAT_ZA_DATUM = new SimpleDateFormat("dd.MM.yyyy");

	public static BufferedImage MUZICKA_IKONA;
	
	public static BufferedImage KORISNICKA_IKONA;

	public static final MuzickoDelo BARBIE_GIRL = new MuzickoDelo("Aqua - Barbie Girl", "Barbi devojka");

	public static final String FILE_SEPARATOR = System.getProperty("file.separator");
	
	public static final Date DATUM = new Date();
	
	public static final Korisnik DUMMY = new KorisnikAplikacije("Ime", "Prezime", "eMail", Pol.muski, DATUM, true, "sifra", "kime", 
			DATUM, new ArrayList<MuzickoDelo>(), new ArrayList<Zanr>(), new ArrayList<KorisnikAplikacije>(), new ArrayList<FrontEndKorisnik>(), 
			new ArrayList<Izvodjac>());

	
}
