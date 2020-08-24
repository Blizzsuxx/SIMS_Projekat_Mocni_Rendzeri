package controler;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import model.Korisnik;
import model.Nalog;
import model.Ocena;
import model.Zanr;

public class NalogMenadzer {
	private ArrayList<Nalog> nalozi;
	

	public NalogMenadzer() {
		super();
		this.nalozi = new ArrayList<Nalog>();
	}


	public ArrayList<Nalog> getNalozi() {
		return nalozi;
	}


	public void setNalozi(ArrayList<Nalog> nalozi) {
		this.nalozi = nalozi;
	}


	public Collection<Nalog> ucitajNaloge(Collection<Korisnik> korisnici) {
		String sep=System.getProperty("file.separator");
		String putanja ="."+sep+"fajlovi"+sep+"nalozi.txt";
			nalozi = new ArrayList<>();
			try {
				BufferedReader br = new BufferedReader(new FileReader(putanja));
				String linija = "";
				while ( (linija = br.readLine()) != null)
				{
					
					if (linija.trim().equals("")) {
					continue;}
					
					String[] linije = linija.trim().split(",");
					DateTimeFormatter df=DateTimeFormatter.ofPattern("dd.MM.yyyy.");
					LocalDate dan=LocalDate.parse(linije[2].trim(), df);
					Date d=new Date(dan.getYear(), dan.getMonthValue(), dan.getDayOfMonth());
					boolean tf=false;
					if (linije[3].trim().equals("true")) {
						tf=true;
					}
					Nalog a = new Nalog(linije[0].trim(), linije[1].trim(), d,tf);
					Integer sifraKorisnika=Integer.parseInt(linije[4].trim());
					for(Korisnik k:korisnici) {
						if(k.getSifra()==sifraKorisnika) {k.setNalog(a);break;}
					}
					nalozi.add( a);
				}
				br.close();
			} catch (FileNotFoundException e) {
				System.out.println("Fajl nije pronadjen");
			} catch (IOException e) {
				System.out.println("Greska prilikom citanja fajla");
			}catch (NumberFormatException ignoreException) {
				System.out.println("Greska prilikom citanja fajla");
				ignoreException.printStackTrace();
			}catch(DateTimeException e) {
				System.out.println("Greska prilikom citanja datuma");
				
			}return nalozi;
		
	}


	public void sacuvaj(ArrayList<Korisnik> korisnici) {
		PrintWriter pw=null;
		String sep=System.getProperty("file.separator");
		String putanja ="."+sep+"fajlovi"+sep+"nalozi.txt";
		try {
			
			pw=new PrintWriter(new FileWriter(putanja, false));
			boolean potvrda=false;
			for(Nalog a:nalozi) {
				potvrda=false;
				for(Korisnik k:korisnici) {
					if(k.getNalog().getKorisnickoIme().equals(a.getKorisnickoIme()) && k.getNalog().getSifra().equals(a.getSifra())) {
						pw.println(a.toFileString(k.getSifra()));//ovo je druga sifra
						potvrda=true;
						break;
					}
				}
				if(!potvrda) {
				pw.println(a.toFileString(-1));	
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
