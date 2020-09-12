package model;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import controler.Constants;

public class MuzickoDelo extends MuzickiSadrzaj {
	private float prosecnaOcenaKorisnika;
	private float prosecnaOcenaUrednika;
	
	private List<Integer> dosadasnjeOceneKorisnika;
	private List<Integer> dosadasnjeOceneUrednika;
	private List<Utisak> utisci;
	
	public MuzickoDelo(String naslov, String opis) {
		super();
		this.naslov = naslov;
		this.opis = opis;
		this.utisci = new ArrayList<Utisak>();
	}
	
	public MuzickoDelo(String naslov, String opis, Date datumIzadavanja, Izvodjac izvodjac, Urednik urednik,
			boolean status) {
		super(naslov, opis, datumIzadavanja, izvodjac, urednik, status);
		this.dosadasnjeOceneKorisnika = new ArrayList<>();
		this.dosadasnjeOceneUrednika = new ArrayList<>();
		this.utisci = new ArrayList<>();
	}

	
	public MuzickoDelo(String naslov, String opis, Date datumIzadavanja, Izvodjac izvodjac, Urednik urednik,
			boolean status, List<Zanr> zanrovi) {
		super(naslov, opis, datumIzadavanja, izvodjac, urednik, status, zanrovi);
		this.dosadasnjeOceneKorisnika = new ArrayList<>();
		this.dosadasnjeOceneUrednika = new ArrayList<>();
		this.utisci = new ArrayList<>();
	}

	public MuzickoDelo(String naslov, String opis, Date datumIzadavanja, Izvodjac izvodjac, Urednik urednik,
			boolean status, float prosecnaOcenaKorisnika, float prosecnaOcenaUrednika) {
		super(naslov, opis, datumIzadavanja, izvodjac, urednik, status);
		this.dosadasnjeOceneKorisnika = new ArrayList<>();
		this.dosadasnjeOceneUrednika = new ArrayList<>();
		this.utisci = new ArrayList<>();
		this.prosecnaOcenaKorisnika = prosecnaOcenaKorisnika;
		this.prosecnaOcenaUrednika = prosecnaOcenaUrednika;
	}
	
	public MuzickoDelo(String naslov, String opis, Date datumIzadavanja, Izvodjac izvodjac, Urednik urednik,
			boolean status, List<Zanr> zanrovi, float prosecnaOcenaKorisnika, float prosecnaOcenaUrednika) {
		super(naslov, opis, datumIzadavanja, izvodjac, urednik, status, zanrovi);
		this.dosadasnjeOceneKorisnika = new ArrayList<>();
		this.dosadasnjeOceneUrednika = new ArrayList<>();
		this.utisci = new ArrayList<>();
		this.prosecnaOcenaKorisnika = prosecnaOcenaKorisnika;
		this.prosecnaOcenaUrednika = prosecnaOcenaUrednika;
	}
	
	@Override
	public String toFileString() {
		return String.format("%s;%s;%s\n", super.toFileString(), prosecnaOcenaKorisnika, prosecnaOcenaUrednika);
	}


	public void dodajocenuKorisnika(int ocena) {
		this.dosadasnjeOceneKorisnika.add(ocena);
		int suma=0;
		for(int i:this.dosadasnjeOceneKorisnika) {suma+=i;}
		this.setProsecnaOcenaKorisnika(suma/this.dosadasnjeOceneKorisnika.size());
	}
	
	public void dodajocenuUrednika(int ocena) {
		this.dosadasnjeOceneUrednika.add(ocena);
		int suma=0;
		for(int i:this.dosadasnjeOceneUrednika) {suma+=i;}
		this.setProsecnaOcenaUrednika(suma/this.dosadasnjeOceneUrednika.size());
	}
	
	@Override
	public String Ime() {
		return this.getNaslov();
	}

	@Override
	public String putDoSlike() {
		return "fajlovi/muzika/"+getNaslov() + ".jpg";
	}

	@Override
	public BufferedImage defaultSlika() {
		return Constants.MUZICKA_IKONA;
	}

	public float getProsecnaOcenaKorisnika() {
		return prosecnaOcenaKorisnika;
	}

	public void setProsecnaOcenaKorisnika(float prosecnaOcenaKorisnika) {
		this.prosecnaOcenaKorisnika = prosecnaOcenaKorisnika;
	}

	public float getProsecnaOcenaUrednika() {
		return prosecnaOcenaUrednika;
	}

	public void setProsecnaOcenaUrednika(float prosecnaOcenaUrednika) {
		this.prosecnaOcenaUrednika = prosecnaOcenaUrednika;
	}

	public List<Integer> getDosadasnjeOceneKorisnika() {
		return dosadasnjeOceneKorisnika;
	}

	public void setDosadasnjeOceneKorisnika(List<Integer> dosadasnjeOceneKorisnika) {
		this.dosadasnjeOceneKorisnika = dosadasnjeOceneKorisnika;
	}

	public List<Integer> getDosadasnjeOceneUrednika() {
		return dosadasnjeOceneUrednika;
	}

	public void setDosadasnjeOceneUrednika(List<Integer> dosadasnjeOceneUrednika) {
		this.dosadasnjeOceneUrednika = dosadasnjeOceneUrednika;
	}

	public List<Utisak> getUtisci() {
		return utisci;
	}

	public void setUtisci(List<Utisak> utisci) {
		this.utisci = utisci;
	}

	
}
