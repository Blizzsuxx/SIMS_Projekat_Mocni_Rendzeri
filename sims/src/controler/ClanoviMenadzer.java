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
import java.util.Date;

import model.Clan;
import model.Grupa;
import model.Izvodjac;
import model.Ocena;
import model.Pojedinacanizvodjac;
import model.Zanr;

public class ClanoviMenadzer {
	private ArrayList<Clan> sviClanovi;

	public ArrayList<Clan> getSviClanovi() {
		return sviClanovi;
	}

	public void setSviClanovi(ArrayList<Clan> sviClanovi) {
		this.sviClanovi = sviClanovi;
	}

	public ClanoviMenadzer() {
		super();
		this.sviClanovi = new ArrayList<Clan>();
	}

	public void ucitaj(IzvodjacMenadzer izvodjaci, ArrayList<Grupa> grupe) {
		String sep=System.getProperty("file.separator");
		String putanja ="."+sep+"fajlovi"+sep+"clanstva.txt";
			sviClanovi = new ArrayList<>();
			try {
				BufferedReader br = new BufferedReader(new FileReader(putanja));
				String linija = "";
				while ( (linija = br.readLine()) != null)
				{
					
					if (linija.trim().equals("")) {
					continue;}
					
					String[] linije = linija.trim().split(",");
					DateTimeFormatter df=DateTimeFormatter.ofPattern("dd.MM.yyyy.");
					LocalDate dan=LocalDate.parse(linije[0].trim(), df);
					Date d=new Date(dan.getYear(), dan.getMonthValue(), dan.getDayOfMonth());
					Date izlazak=null;
					if(!linije[1].trim().equals("/")) {
						LocalDate dan1=LocalDate.parse(linije[1].trim(), df);
						izlazak=new Date(dan1.getYear(), dan1.getMonthValue(), dan1.getDayOfMonth());
					}
					Izvodjac iz=izvodjaci.nadiPoUmetnickomImenu(linije[2].trim());
					Clan a = new Clan(d, izlazak, (Pojedinacanizvodjac)iz);
					Grupa g=(Grupa)izvodjaci.nadiPoUmetnickomImenu(linije[3].trim());
					g.getClanovi().add(a);
					a.setGrupa(g);
					((Pojedinacanizvodjac)iz).getClanstvaUGrupama().add(a);
					
					sviClanovi.add( a);
				}
			} catch (FileNotFoundException e) {
				System.out.println("Fajl nije pronadjen");
			} catch (IOException e) {
				System.out.println("Greska prilikom citanja fajla");
			}
		
	}

	public void sacuvaj() {
		PrintWriter pw=null;
		String sep=System.getProperty("file.separator");
		String putanja ="."+sep+"fajlovi"+sep+"clanstva.txt";
		try {
			
			pw=new PrintWriter(new FileWriter(putanja, false));
			for(Clan a:sviClanovi) {
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
