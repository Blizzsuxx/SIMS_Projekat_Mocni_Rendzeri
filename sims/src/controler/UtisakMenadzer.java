package controler;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import model.FrontEndKorisnik;
import model.Komentar;
import model.Korisnik;
import model.MuzickoDelo;
import model.Recenzija;
import model.Urednik;
import model.Utisak;

public class UtisakMenadzer {
	private ArrayList<Utisak> svi;
	private ArrayList<Komentar> komentar;
	private ArrayList<Recenzija> rec;
	

	public UtisakMenadzer() {
		super();
		this.svi = new ArrayList<Utisak>();
		this.komentar = new ArrayList<Komentar>();
		this.rec = new ArrayList<Recenzija>();
	}

	public UtisakMenadzer(Collection<MuzickoDelo> muzickaDela, KorisniciMenadzer korisnici, List<String[]> data){
		this();
		ucitajUtiske(muzickaDela, korisnici, data);
	}

	private void ucitajUtiske(Collection<MuzickoDelo> muzickaDela, KorisniciMenadzer korisnici, List<String[]> data) {

		for ( String[] linije : data)
		{

			MuzickoDelo delo=pronadiDelo(muzickaDela, linije[0]);
			DateTimeFormatter df=DateTimeFormatter.ofPattern("dd.MM.yyyy.");
			LocalDate dan=LocalDate.parse(linije[1].trim(), df);
			Date datum=new Date(dan.getYear(), dan.getMonthValue(), dan.getDayOfMonth());
			boolean status=false;
			if(linije[2].trim().equals("true")) {status=true;}
		
			if(linije.length==6) {
			
			Recenzija a = new Recenzija(linije[3], datum, status, null, delo, linije[5].trim());//delo, datum status txt,urednik, naslov
		//	urednik.getIstorijaRecenzija().add(a); nadajmo se da zakomentarisano resava problem, jer mi fali deo koda koji ne 
			//ne mogu da nadjem, nekim cudom //ispario
			svi.add(a);
			rec.add(a);
			a.super().setPisac(korisnici.trazi(linije[6].trim()));
			(Urednik)korisnici.trazi(linije[6].trim()).getIstorijaRecenzija().add(a);
			
			}
			else if(linije.length==5) {
				Komentar k=new Komentar(linije[3], datum, status, delo, null);
				svi.add(k);
				komentar.add(k);
				a.setPisac(korisnici.trazi(linije[4].trim()));
				
			}else {
				System.out.println("Greska  u fajlu:");
			}
		}
	}

	private Korisnik pronadiKorisnika(KorisniciMenadzer korisnici, String korisnickoIme) {
		return korisnici.trazi(korisnickoIme);
	}



	private MuzickoDelo pronadiDelo(Collection<MuzickoDelo> muzickaDela, String string) {
		for(MuzickoDelo md:muzickaDela) {
			if(md.getNaziv().equals(string)) {
				return md;
			}
		}
		return null;
	}

	public Collection<Recenzija> getRecenzije() {
		
		return rec;
	}

	public Collection<Komentar> getKomentari() {
		
		return komentar;
	}

	public void sacuvaj() {
		PrintWriter pw=null;
		String sep=System.getProperty("file.separator");
		String putanja ="."+sep+"fajlovi"+sep+"utisci.txt";
		try {
			
			pw=new PrintWriter(new FileWriter(putanja, false));
			for(Utisak a:svi) {
				pw.println(a.toFileString());
				
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
