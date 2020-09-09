package model;

import java.util.ArrayList;
import java.util.List;

public class TopLista {
	private String naziv;
	private boolean status;
	private Korisnik korisnik;
	private List<MuzickiSadrzaj> muzickiSadrzaj;
	
	public TopLista() {}
	
	
	public TopLista(String naziv, boolean status, Korisnik korisnik) {
		super();
		this.naziv = naziv;
		this.status = status;
		this.korisnik = korisnik;
		this.muzickiSadrzaj = new ArrayList<>();
	}


	public TopLista(String naziv, boolean status, Korisnik korisnik, List<MuzickiSadrzaj> muzickiSadrzaj) {
		super();
		this.naziv = naziv;
		this.status = status;
		this.korisnik = korisnik;
		this.muzickiSadrzaj = muzickiSadrzaj;
	}

	public String toFile() { // ovu funkciju bozivati kada muzickiSadrzaj ima size bar 1
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("%s,%s,%s|",this.status, this.naziv, this.korisnik.getNalog().getKorisnickoIme()));
		for (MuzickiSadrzaj ms: this.muzickiSadrzaj)
			sb.append(String.format("%s,", ms.getNaslov()));
		sb.append("\n");
		return sb.toString();
	}
	
	public String getNaziv() {
		return naziv;
	}


	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}


	public boolean isStatus() {
		return status;
	}


	public void setStatus(boolean status) {
		this.status = status;
	}


	public Korisnik getKorisnik() {
		return korisnik;
	}


	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}


	public List<MuzickiSadrzaj> getMuzickiSadrzaj() {
		return muzickiSadrzaj;
	}


	public void setMuzickiSadrzaj(List<MuzickiSadrzaj> muzickiSadrzaj) {
		this.muzickiSadrzaj = muzickiSadrzaj;
	}
	
	
}
