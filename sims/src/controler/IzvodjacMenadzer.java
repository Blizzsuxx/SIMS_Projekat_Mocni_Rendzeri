package controler;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.Grupa;
import model.Izvodjac;
import model.Pojedinacanizvodjac;
import model.Pol;

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

	public IzvodjacMenadzer(List<String[]> data){
		this();
		ucitajIzvodjace(data);
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



	private void ucitajIzvodjace(List<String[]> data) {
		//potrebno je i pratioce??? 
		//Da -Dragan

		for (String[] linije : data)
		{
			if(linije.length == 8){
				Date smrt = null;
				Date rodjenje = null;
				try {
					rodjenje = Constants.NATASIN_FORMAT_ZA_DATUM.parse(linije[4].trim());
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(!linije[5].trim().equals("/")) {
					try {
						smrt = Constants.NATASIN_FORMAT_ZA_DATUM.parse(linije[5].trim());
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
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
				Date rodjenje = null;
				try {
					rodjenje = Constants.NATASIN_FORMAT_ZA_DATUM.parse(linije[3].trim());
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(!linije[4].trim().equals("/")) {
					try {
						smrt = Constants.NATASIN_FORMAT_ZA_DATUM.parse(linije[4].trim());
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
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
}
