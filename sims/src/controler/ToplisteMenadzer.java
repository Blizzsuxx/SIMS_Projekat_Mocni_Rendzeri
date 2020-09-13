package controler;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import model.Korisnik;
import model.MuzickiSadrzaj;
import model.TopLista;
import model.Urednik;

public class ToplisteMenadzer {
	private List<TopLista> topListe;
	private KorisniciMenadzer korisniciMenadzer;
	private MuzickiSadrzajMenadzer muzickiSadrzajMenadzer;
	
	public ToplisteMenadzer() {
		super();
		this.topListe = new ArrayList<>();
	}
	
	public ToplisteMenadzer(KorisniciMenadzer korisniciMenadzer, MuzickiSadrzajMenadzer muzickiSadrzajMenadzer,
			String putanja) {
		this();
		this.korisniciMenadzer = korisniciMenadzer;
		this.muzickiSadrzajMenadzer = muzickiSadrzajMenadzer;
		
		ucitaj(putanja);
	}
	
	private void ucitaj(String putanja) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(putanja));
			String linija = "";
			String[] tokeni;
			String[] pomocniTokeni;
			while((linija = br.readLine()) != null) {
				tokeni = linija.split(";");
				String naziv = tokeni[0].trim();
				boolean status = Boolean.valueOf(tokeni[1].trim());
				Korisnik k = this.korisniciMenadzer.trazi(tokeni[2].trim());
				pomocniTokeni = tokeni[3].split(",");
				List<MuzickiSadrzaj> lista = new ArrayList<>();
				for (String imena: pomocniTokeni)
					lista.add(this.muzickiSadrzajMenadzer.vratiNaOsnovuNazive(imena));
				TopLista tl = new TopLista(naziv, status, k, lista);
				topListe.add(tl);
			}
			br.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void upisi(String putanja) {
		try {
			PrintWriter pw = new PrintWriter(new FileWriter(putanja));
			for (TopLista tp: this.topListe)
				pw.print(tp.toFile());
			pw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean kreirajTopListu(String nazivListe, String nazivKorisnika, List<MuzickiSadrzaj> lista, List<TopLista> topListeKorisnika) {
		for (TopLista tp: this.topListe) 
			if (tp.getKorisnik().getNalog().getKorisnickoIme().equals(nazivKorisnika) && 
					tp.getNaziv().equals(nazivListe))
				return false;
		Korisnik k = this.korisniciMenadzer.trazi(nazivKorisnika);
		TopLista tp = new TopLista(nazivListe, true, k, lista);
		this.topListe.add(tp);
		if(topListeKorisnika != null)
			topListeKorisnika.add(tp);
		return true;
	}
	
	public List<TopLista> topListeKorisnika(String korisnickoIme){
		List<TopLista> temp = new ArrayList<>();
		for (TopLista tp: this.topListe)
			if (tp.isStatus() && tp.getKorisnik().getNalog().getKorisnickoIme().equals(korisnickoIme))
				temp.add(tp);
		return temp;
	}

	public TopLista vratiTopListu(String nazivTopListe, String nazivKorisnika) {
		for (TopLista tp: this.topListe)
			if (tp.getKorisnik().getNalog().getKorisnickoIme().equals(nazivKorisnika) && tp.getNaziv().equals(nazivTopListe))
				return tp;
		return null;
	}
	
	public TopLista vratiTopListuNaOsnovuImena(String nazivTopListe) {
		for (TopLista tp: this.topListe)
			if (tp.getNaziv().equals(nazivTopListe))
				return tp;
		return null;
	}
	
	public List<TopLista> vratiTopListeUrednika() {
		List<TopLista> temp = new ArrayList<>();
		for (TopLista tp: this.topListe)
			if (tp.isStatus() && tp.getKorisnik() instanceof Urednik)
				temp.add(tp);
		return temp;
	}
	
	public List<TopLista> getTopListe() {
		return topListe;
	}

	public void setTopListe(List<TopLista> topListe) {
		this.topListe = topListe;
	}

	public KorisniciMenadzer getKorisniciMenadzer() {
		return korisniciMenadzer;
	}

	public void setKorisniciMenadzer(KorisniciMenadzer korisniciMenadzer) {
		this.korisniciMenadzer = korisniciMenadzer;
	}

	public MuzickiSadrzajMenadzer getMuzickiSadrzajMenadzer() {
		return muzickiSadrzajMenadzer;
	}

	public void setMuzickiSadrzajMenadzer(MuzickiSadrzajMenadzer muzickiSadrzajMenadzer) {
		this.muzickiSadrzajMenadzer = muzickiSadrzajMenadzer;
	}
	
	
}
