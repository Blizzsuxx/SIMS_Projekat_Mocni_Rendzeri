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
					rodjenje = Constants.NATASIN_FORMAT_ZA_DATUM.parse(linije[5].trim());
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(!linije[5].trim().equals("/")) {
					try {
						smrt = Constants.NATASIN_FORMAT_ZA_DATUM.parse(linije[6].trim());
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				Pol p = Pol.zenski;
				if(linije[8].trim().equals(Pol.muski.name())) {p = Pol.muski;}
				boolean status = linije[2].trim().equals("true");
				Pojedinacanizvodjac a = new Pojedinacanizvodjac(linije[0].trim(), new Zanr(linije[1].trim(),true), 
						status, linije[3].trim(), linije[4].trim(), rodjenje, smrt,linije[7].trim(), p );
				svi.add(a);
				this.getSolo().add(a);
			}
			else if(linije.length == 5) {
				Date smrt = null;
				Date rodjenje = null;
				try {
					rodjenje = Constants.NATASIN_FORMAT_ZA_DATUM.parse(linije[4].trim());
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(!linije[4].trim().equals("/")) {
					try {
						smrt = Constants.NATASIN_FORMAT_ZA_DATUM.parse(linije[5].trim());
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				Integer br1 = Integer.parseInt(linije[3].trim());
				boolean status = linije[2].trim().equals("true");
				Grupa a = new Grupa(linije[0].trim(), new Zanr(linije[1].trim(),true), status, br1, rodjenje, smrt );
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
}
