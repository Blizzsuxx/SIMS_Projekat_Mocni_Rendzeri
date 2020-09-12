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
}
