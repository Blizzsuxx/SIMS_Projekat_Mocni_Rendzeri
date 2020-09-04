package controler;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import model.Izvodjac;
import model.Korisnik;
import model.MuzickoDelo;
import model.Sesija;
import model.Zanr;
import view.TableModelWrapper;

public class MuzickoDeloMenadzer {
	private ArrayList<MuzickoDelo> dela;
	

	public MuzickoDeloMenadzer() {
		super();
		this.dela = new ArrayList<MuzickoDelo>();
	}

	public MuzickoDeloMenadzer(IzvodjacMenadzer izvodjaci, ArrayList<Zanr> sviZanrovi, List<String[]> data) throws ParseException{
		this();
		ucitajDela(izvodjaci, sviZanrovi, data);
	}


	public ArrayList<MuzickoDelo> getDela() {
		return dela;
	}

	public void setDela(ArrayList<MuzickoDelo> dela) {
		this.dela = dela;
	}
	
	
	private void ucitajDela(IzvodjacMenadzer izvodjaci, ArrayList<Zanr> sviZanrovi, List<String[]> data) throws ParseException {
				for (String[] linije : data) {
					String[] parts = linije[0].split(";");
					String naslov = parts[0];
					String opis = parts[1].trim();
					Date datumIzdavanja = new SimpleDateFormat("dd.MM.yyyy.").parse(parts[2].trim());
					boolean status = Boolean.parseBoolean(parts[3]);
					String imeIzvodjaca = parts[4].trim();
					Izvodjac izvodjac = null;
					ArrayList<Zanr> zanrovi = new ArrayList<Zanr>();
					String[] viseZanrova = parts[5].split("\\|");
					for(int j = 0; j < viseZanrova.length; j++) {
						String naziv = viseZanrova[j].trim();
						for(Zanr z : sviZanrovi) {
							if(z.getNazivZanra().equals(naziv)) {
								zanrovi.add(z);
							}
						}
					}
					MuzickoDelo a = new MuzickoDelo(naslov, opis, datumIzdavanja, status, zanrovi);
					dela.add(a);
					for(Izvodjac iz : izvodjaci.getSvi()) {
						if(iz.getUmetnickoIme().equals(imeIzvodjaca)) {
							izvodjac = iz;
						}
					}
					izvodjac.addDelo(a);
				}
	}


	public MuzickoDelo pronadiPoNazivu(String trim) {
		for(MuzickoDelo m:this.dela) {
			if(m.getNaziv().equals(trim)) {
				return m;
			}
		}
		return null;
	}


	public void sacuvaj(ArrayList<Izvodjac> iz ) {
		PrintWriter pw=null;
		String sep=System.getProperty("file.separator");
		String putanja ="."+sep+"fajlovi"+sep+"muzickaDela.txt";
		try {
			
			pw=new PrintWriter(new FileWriter(putanja, false));
			Izvodjac trazenIzv=null;
			for(MuzickoDelo a:dela) {
				trazenIzv=null;
				for(Izvodjac izv:iz) {
					for(MuzickoDelo md: izv.getMuzickaDela()) {
						if(md.getNaziv()==a.getNaziv()) {
							trazenIzv=izv;
							break;
						}
					}
					if(trazenIzv!=null) {
						break;
					}
				}
				pw.print(a.toFileString(trazenIzv));
				
			}pw.close();
			
		}catch(IOException e) {
			e.printStackTrace();
		}finally {
			if(pw!=null) {
				pw.close();
			}
		}
		
	}
	
	public TableModelWrapper getTabelaMuzickihDela(Izvodjac izvodjac)  throws Exception {
		String[] columns = { "Naziv" ,"Opis", "Datum izdavanja"};
		Class<?>[] columnTypes = { String.class, String.class, Date.class};
		boolean[] editableColumns = { false, false, false};
		int[] columnWidths = { 200, 200, 200};
		ArrayList<Object[]> data = new ArrayList<Object[]>();
		for (MuzickoDelo md : izvodjac.getMuzickaDela()) {
			data.add(new Object[] {md.getNaziv(), md.getOpis(), md.getDatumIzdavanja()});
		}
		return new TableModelWrapper(columns, columnTypes, editableColumns, columnWidths, data);
	}
}
