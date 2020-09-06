package controler;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import model.Glasanje;
import model.MuzickoDelo;
import model.Pol;
import model.Urednik;

public class GlasanjeMenadzer {
	private ArrayList<Glasanje> glasovi;
	private boolean pokrenutoGlasanje;
	private ArrayList<Urednik> uredniciKojiSuGlasali;
	
	public GlasanjeMenadzer() {
		
	}
	
	public GlasanjeMenadzer(ArrayList<String> data, ArrayList<String> urednici, ArrayList<MuzickoDelo> dela) throws ParseException {
		this.glasovi = new ArrayList<Glasanje>();
		this.uredniciKojiSuGlasali = new ArrayList<Urednik>();
		dodajUrednikeKojiSuGlasali(urednici);
		if (!data.isEmpty()) {
			String prvaLinija = data.get(0);
			if (prvaLinija.equals("true"))
				this.pokrenutoGlasanje = true;
			else
				this.pokrenutoGlasanje = false;
			for (int i = 1; i < data.size(); i++) {
				String linija = data.get(i);
				String[] parts = linija.split(";");
				String nazivDela = parts[0];
				int brojGlasova = Integer.parseInt(parts[1]);
				for (MuzickoDelo m : dela) {
					if (m.getNaziv().equals(nazivDela)) {
						Glasanje g = new Glasanje(m,brojGlasova);
						glasovi.add(g);
					}
				}
			}
		}
	}
	
	private void dodajUrednikeKojiSuGlasali(ArrayList<String> urednici) throws ParseException {
		if (!urednici.isEmpty()) {
			for (String urednik : urednici) {
				String[] delovi = urednik.split(";");
				Pol p = Pol.valueOf(delovi[3]);
				Urednik u = new Urednik(delovi[0], delovi[1], delovi[2], p, new SimpleDateFormat("dd.MM.yyyy").parse(delovi[4]), delovi[5],
						 delovi[6], new SimpleDateFormat("dd.MM.yyyy").parse(delovi[7]), Boolean.parseBoolean(delovi[8]));
				uredniciKojiSuGlasali.add(u);
			}
		}
	}

	public ArrayList<Glasanje> getGlasovi() {
		return glasovi;
	}

	public void setGlasovi(ArrayList<Glasanje> glasovi) {
		this.glasovi = glasovi;
	}

	public boolean isPokrenutoGlasanje() {
		return pokrenutoGlasanje;
	}

	public void setPokrenutoGlasanje(boolean pokrenutoGlasanje) {
		this.pokrenutoGlasanje = pokrenutoGlasanje;
	}
	
	public void addGlas(Glasanje g) {
		for (Glasanje glas : glasovi) {
			if (glas.getMuzickoDelo().getNaziv().equals(g.getMuzickoDelo().getNaziv())) {
				return;
			}
		}
		glasovi.add(g);
	}
	
	public void addUrednik(Urednik u) {
		for (Urednik urednik : uredniciKojiSuGlasali) {
			if (urednik.getNalog().getKorisnickoIme().equals(u.getNalog().getKorisnickoIme())) {
				return;
			}
		}
		uredniciKojiSuGlasali.add(u);
	}
	
	public void clearListuUrednika() {
		uredniciKojiSuGlasali.clear();
	}
	
	public ArrayList<Urednik> getUredniciKojiSuGlasali() {
		return uredniciKojiSuGlasali;
	}

	public void setUredniciKojiSuGlasali(ArrayList<Urednik> uredniciKojiSuGlasali) {
		this.uredniciKojiSuGlasali = uredniciKojiSuGlasali;
	}

	public void sacuvaj() {
		PrintWriter pw = null;
		String sep = System.getProperty("file.separator");
		String putanja ="." + sep + "fajlovi" + sep + "glasovi.txt";
		try {
			pw = new PrintWriter(new FileWriter(putanja, false));
			pw.println(this.pokrenutoGlasanje);
			for(Glasanje g : glasovi) {
				pw.print(Glasanje.Glasanje2String(g));
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
			sacuvajUrednike();
		}
	}
	
	private void sacuvajUrednike() {
		PrintWriter pw = null;
		String sep = System.getProperty("file.separator");
		String putanja ="." + sep + "fajlovi" + sep + "uredniciKojiSuGlasali.txt";
		try {
			pw = new PrintWriter(new FileWriter(putanja, false));
			if (uredniciKojiSuGlasali.isEmpty()) {
				pw.print("");
				return;
			}
			for(Urednik u : uredniciKojiSuGlasali) {
				pw.print(Urednik.Urednik2String(u));
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
}

