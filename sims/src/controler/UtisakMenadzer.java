package controler;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import model.FrontEndKorisnik;
import model.Komentar;
import model.Korisnik;
import model.MuzickoDjelo;
import model.Recenzija;
import model.Urednik;
import model.Utisak;
import model.Zanr;

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

	public void ucitajUtiske(Collection<MuzickoDjelo> muzickaDela, KorisniciMenadzer korisnici) {
		String sep=System.getProperty("file.separator");
		String putanja ="."+sep+"fajlovi"+sep+"utisci.txt";
			svi = new ArrayList<>();
			try {
				BufferedReader br = new BufferedReader(new FileReader(putanja));
				String linija = "";
				while ( (linija = br.readLine()) != null)
				{
					
					if (linija.trim().equals("")) {
					continue;}
					
					String[] linije = linija.trim().split(";");
					MuzickoDjelo delo=pronadiDelo(muzickaDela, linije[0]);
					DateTimeFormatter df=DateTimeFormatter.ofPattern("dd.MM.yyyy.");
					LocalDate dan=LocalDate.parse(linije[1].trim(), df);
					Date d=new Date(dan.getYear(), dan.getMonthValue(), dan.getDayOfMonth());
					boolean bf=false;
					if(linije[2].trim().equals("true")) {bf=true;}
				
					if(linije.length==6) {
					Urednik u=(Urednik)pronadiKorisnika(korisnici, linije[4].trim());
					Recenzija a = new Recenzija(delo, d, bf, linije[3],u ,linije[5].trim());//delo, datum status txt,urednik, naslov
					u.getIstorijaRecenzija().add(a);
					svi.add( a);
					rec.add(a);
					}
					else if(linije.length==5) {
						FrontEndKorisnik km=(FrontEndKorisnik)pronadiKorisnika(korisnici, linije[4].trim());
						Komentar k=new Komentar(linije[3], d, bf, delo, km);
						svi.add(k);
						komentar.add(k);
						
					}else {
						System.out.println("Greska  u fajlu:");
						System.out.println(linija);
					}
				}
			} catch (FileNotFoundException e) {
				System.out.println("Fajl nije pronadjen");
			} catch (IOException e) {
				System.out.println("Greska prilikom citanja fajla");
			}
		
	}

	private Korisnik pronadiKorisnika(KorisniciMenadzer korisnici, String kljuc) {
		Integer sifra=Integer.parseInt(kljuc);
		for(Korisnik f:korisnici.getKorisniciCollection()) {
			if(f.getSifra()==sifra) {return f;}
		}
		return null;
	}



	private MuzickoDjelo pronadiDelo(Collection<MuzickoDjelo> muzickaDela, String string) {
		for(MuzickoDjelo md:muzickaDela) {
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
