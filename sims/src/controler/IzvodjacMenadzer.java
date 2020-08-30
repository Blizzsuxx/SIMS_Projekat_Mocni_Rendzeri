package controler;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import model.Grupa;
import model.Izvodjac;
import model.Ocena;
import model.Pojedinacanizvodjac;
import model.Pol;
import model.Zanr;

public class IzvodjacMenadzer {
	private ArrayList<Grupa> grupe;
	private ArrayList<Pojedinacanizvodjac> solo;
	private ArrayList<Izvodjac> svi;
	
	

	public IzvodjacMenadzer() {
		super();
		this.grupe = new ArrayList<Grupa>();
		this.solo = new ArrayList<Pojedinacanizvodjac>();
		this.svi = new ArrayList<Izvodjac>();
	}



	public ArrayList<Grupa> getGrupe() {
		return grupe;
	}



	public void setGrupe(ArrayList<Grupa> grupe) {
		this.grupe = grupe;
	}



	public ArrayList<Pojedinacanizvodjac> getSolo() {
		return solo;
	}



	public void setSolo(ArrayList<Pojedinacanizvodjac> solo) {
		this.solo = solo;
	}



	public ArrayList<Izvodjac> getSvi() {
		return svi;
	}



	public void setSvi(ArrayList<Izvodjac> svi) {
		this.svi = svi;
	}



	public void ucitajIzvodjace(Collection<Grupa> grupe, Collection<Pojedinacanizvodjac> izvodjaciSolo) throws ParseException {
		//potrebno je i pratioce???
		String sep = System.getProperty("file.separator");
		String putanja ="." + sep + "fajlovi" + sep + "izvodjaci.txt";
			svi = new ArrayList<>();
			try {
				BufferedReader br = new BufferedReader(new FileReader(putanja));
				String linija = "";
				while ((linija = br.readLine()) != null)
				{
					if (linija.trim().equals("")) {
						continue;
					}
					String[] linije = linija.trim().split(";");
					if(linije.length == 8){
						Date smrt = null;
						Date rodjenje= new SimpleDateFormat("dd.MM.yyyy").parse(linije[4].trim());
						if(!linije[5].trim().equals("/")) {
							smrt = new SimpleDateFormat("dd.MM.yyyy").parse(linije[5].trim());
						}
						Pol p = Pol.zenski;
						if(linije[7].trim().equals(Pol.muski.name())) {p = Pol.muski;}
						boolean status = linije[1].trim().equals("true");
						Pojedinacanizvodjac a = new Pojedinacanizvodjac(linije[0].trim(), status, linije[2].trim(), linije[3].trim(), rodjenje, smrt,linije[6].trim(), p );
						svi.add(a);
						this.getSolo().add(a);
					}
					else if(linije.length == 5) {
						Date smrt = null;
						Date rodjenje = new SimpleDateFormat("dd.MM.yyyy").parse(linije[3].trim());
						if(!linije[4].trim().equals("/")) {
							smrt = new SimpleDateFormat("dd.MM.yyyy").parse(linije[4].trim());
						}
						Integer br1 = Integer.parseInt(linije[2].trim());
						boolean status = linije[1].trim().equals("true");
						Grupa a = new Grupa(linije[0].trim(), status, br1, rodjenje, smrt );
						svi.add(a);
						this.getGrupe().add(a);
						
					}
					else {
						System.out.println("Greska pri ucitavanju izvodjaca.");
					}
					
				}
			} catch (FileNotFoundException e) {
				System.out.println("Fajl nije pronadjen");
			} catch (IOException e) {
				System.out.println("Greska prilikom citanja fajla");
			}
	}



	public Izvodjac nadiPoUmetnickomImenu(String trim) {
		for(Izvodjac iz:this.getSvi()) {
			if(iz.getUmetnickoIme().equals(trim)) {
				return iz;
			}
		}
		return null;
	}



	public void sacuvaj(Izvodjac iz, boolean dodaj) throws ParseException {
		ucitajIzvodjace(null, null);
		PrintWriter pw = null;
		String sep = System.getProperty("file.separator");
		String putanja ="." + sep + "fajlovi" + sep + "izvodjaci.txt";
		try {
			pw = new PrintWriter(new FileWriter(putanja, false));
			for(Izvodjac i : svi) {
				if (dodaj || !iz.getUmetnickoIme().equals(i.getUmetnickoIme()))
				{
					if (i.getClass() == Pojedinacanizvodjac.class)
						pw.println(Pojedinacanizvodjac.PojedinacniIzvodjac2String((Pojedinacanizvodjac)i));
					else
						pw.println(Grupa.Grupa2String((Grupa)i));
				}
			}
			if (iz.getClass() == Pojedinacanizvodjac.class)
				pw.println(Pojedinacanizvodjac.PojedinacniIzvodjac2String((Pojedinacanizvodjac)iz));
			else
				pw.println(Grupa.Grupa2String((Grupa)iz));
			
			pw.close();	
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		finally {
			if(pw != null) {
				pw.close();
			}
		}
	}
	
	public boolean proveriDuplikat(String umetnickoIme) throws ParseException
	{
		ucitajIzvodjace(null, null);
		for (Izvodjac i : svi)
		{
			if (i.getUmetnickoIme().equals(umetnickoIme))
				return false;
		}
		return true;
	}
}
