package controler;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import model.Grupa;
import model.Izvodjac;
import model.Pojedinacanizvodjac;
import model.Pol;
import model.Zanr;
import view.Slikovit;
import view.TableModelWrapper;

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

	public IzvodjacMenadzer(List<String[]> data, ZanroviMenadzer zm){
		this();
		ucitajIzvodjace(data, zm);
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



	private void ucitajIzvodjace(List<String[]> data, ZanroviMenadzer zm) {
		//potrebno je i pratioce??? 
		//Da -Dragan
		this.svi = new ArrayList<Izvodjac>();
		this.solo = new ArrayList<Pojedinacanizvodjac>();
		this.grupe = new ArrayList<Grupa>();
		for (String[] linije : data)
		{
			if(linije.length == 10){
				Date smrt = null;
				Date rodjenje = null;
				try {
					rodjenje = Constants.NATASIN_FORMAT_ZA_DATUM.parse(linije[6].trim());
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(!linije[7].trim().equals("/")) {
					try {
						smrt = Constants.NATASIN_FORMAT_ZA_DATUM.parse(linije[7].trim());
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				Pol p = Pol.zenski;
				if(linije[9].trim().equals(Pol.muski.name())) {p = Pol.muski;}
				
				Pojedinacanizvodjac a = new Pojedinacanizvodjac(Boolean.parseBoolean(linije[0].trim()),
						linije[1].trim(), zm.trazi(linije[2].trim()),
						Boolean.parseBoolean(linije[3]), linije[4].trim(), linije[5].trim(), rodjenje, smrt, linije[8].trim(), p);
				svi.add(a);
				solo.add(a);
			}
			else if(linije.length == 7) {
				Date smrt = null;
				Date rodjenje = null;
				try {
					System.out.println(linije[5]);
					rodjenje = Constants.NATASIN_FORMAT_ZA_DATUM.parse(linije[5].trim());
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(!linije[6].trim().equals("/")) {
					try {
						smrt = Constants.NATASIN_FORMAT_ZA_DATUM.parse(linije[6].trim());
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				Integer br1 = Integer.parseInt(linije[4].trim());
				Grupa a = new Grupa(Boolean.parseBoolean(linije[0].trim()), linije[1].trim(), zm.trazi(linije[2].trim()), Boolean.parseBoolean(linije[3]), br1, rodjenje, smrt );
				svi.add(a);
				this.getGrupe().add(a);
				
			}
			else {
				System.out.println(linije[0]);
				System.out.println("Greska pri ucitavanju izvodjaca.");
			}
					
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



	public void sacuvaj() throws ParseException {
		PrintWriter pw = null;
		String sep = System.getProperty("file.separator");
		String putanja ="." + sep + "fajlovi" + sep + "izvodjaci.txt";
		try {
			pw = new PrintWriter(new FileWriter(putanja, false));
			for(Izvodjac i : svi) {
				if (i.getClass() == Pojedinacanizvodjac.class)
					pw.print(Pojedinacanizvodjac.PojedinacniIzvodjac2String((Pojedinacanizvodjac)i));
				else
					pw.print(Grupa.Grupa2String((Grupa)i));
				
			}
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
	
	public TableModelWrapper getTabelaPojednicanihIzvodjaca()  throws Exception {
		String[] columns = { "Umetnicko ime" ,"Zanr", "Ime", "Prezime", "Datum rodjenja"};
		Class<?>[] columnTypes = { String.class, String.class, String.class, String.class, Date.class};
		boolean[] editableColumns = { false, false, false, false, false};
		int[] columnWidths = { 120, 80, 120, 120, 120};
		ArrayList<Object[]> data = new ArrayList<Object[]>();
		for (Pojedinacanizvodjac pi : solo) {
			data.add(new Object[] {pi.getUmetnickoIme(), pi.getZanr().getNazivZanra(), pi.getIme(), pi.getPrezime(), pi.getDatumRodjenja()});
		}
		return new TableModelWrapper(columns, columnTypes, editableColumns, columnWidths, data);
	}
	
	public TableModelWrapper getTabelaGrupa()  throws Exception {
		String[] columns = { "Umetnicko ime" ,"Zanr", "Broj clanova", "Datum osnivanja", "Datum raspada"};
		Class<?>[] columnTypes = { String.class, String.class, Integer.class, Date.class, Date.class};
		boolean[] editableColumns = { false, false, false, false, false};
		int[] columnWidths = { 120, 80, 80, 120, 120};
		ArrayList<Object[]> data = new ArrayList<Object[]>();
		for (Grupa g : grupe) {
			data.add(new Object[] {g.getUmetnickoIme(), g.getZanr().getNazivZanra(), g.getBrojClanova(), g.getDatumOsnivanja(), g.getDatumRaspada()});
		}
		return new TableModelWrapper(columns, columnTypes, editableColumns, columnWidths, data);
	}

	public Collection<? extends Slikovit> traziGrupe(String textZaSearch) {
		// TODO Auto-generated method stub
		ArrayList<Slikovit> rezultat = new ArrayList<>();
		for(Grupa g : grupe) {
			if(g.Ime().contains(textZaSearch)) {
				rezultat.add(g);
			}
		}
		return rezultat;
	}

	public Collection<? extends Slikovit> traziSoloIzvodjace(String textZaSearch) {
		ArrayList<Slikovit> rezultat = new ArrayList<>();
		for(Pojedinacanizvodjac g : this.solo) {
			if(g.Ime().contains(textZaSearch)) {
				rezultat.add(g);
			}
		}
		return rezultat;
	}
	
	// POMOCNE FUNKCIJE
	// na osnovu indikatora po zelji dobijamo odobrene ili neodobrene izvodjace
	public List<Izvodjac> vratiIzvodjaceNaOsnovuOdobrenosti(boolean indikator){
		List<Izvodjac> izvodjaci = new ArrayList<>();
		for(Izvodjac i: this.svi)
			if (i.isOdobrenost() == indikator)
				izvodjaci.add(i);
		return izvodjaci;
	}
	
	public boolean dozvolaIzvodjaca(String umjetnickoIme) {
		for (Izvodjac i : this.svi)
			if (i.isStatus() && !i.isOdobrenost() && i.getUmetnickoIme().equals(umjetnickoIme)) {
				i.setOdobrenost(true);
				return true;
			}
		return false;
	}

	public void dodaj(Izvodjac pi) {
		// TODO Auto-generated method stub
		this.svi.add(pi);
		if(pi instanceof Pojedinacanizvodjac) this.solo.add((Pojedinacanizvodjac) pi);
		else this.grupe.add((Grupa) pi);
	}
}
