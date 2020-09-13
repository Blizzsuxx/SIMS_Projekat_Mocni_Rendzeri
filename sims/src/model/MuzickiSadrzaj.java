package model;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import controler.Constants;
import view.Slikovit;

public abstract class MuzickiSadrzaj implements Slikovit, DeljivPoZanrovima {
	protected String naslov;
	protected String opis;
	protected Date datumIzdavanja;
	protected Izvodjac izvodjac;
	protected Urednik urednik;
	protected boolean status;
	protected List<Zanr> zanrovi;
	
	public String toFileString() {
		DateFormat df = Constants.NATASIN_FORMAT_ZA_DATUM;
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("%s;%s;%s;%s;%s;%s;", naslov, opis, df.format(datumIzdavanja), izvodjac.getUmetnickoIme(),
				urednik.getNalog().getKorisnickoIme(), status));
		for (Zanr z: zanrovi) // pretpostavljamo da ima bar jedan zanr (u programu implementirano)
			sb.append(z.getNazivZanra()+",");
		return sb.toString();
	}
	
	public MuzickiSadrzaj() {
		super();
	}
	
	public MuzickiSadrzaj(String naslov, String opis, Date datumIzadavanja, Izvodjac izvodjac, Urednik urednik,
			boolean status) {
		super();
		this.naslov = naslov;
		this.opis = opis;
		this.datumIzdavanja = datumIzadavanja;
		this.izvodjac = izvodjac;
		this.urednik = urednik;
		this.status = status;
		this.zanrovi = new ArrayList<>();
	}

	public MuzickiSadrzaj(String naslov, String opis, Date datumIzadavanja, Izvodjac izvodjac, Urednik urednik,
			boolean status, List<Zanr> zanrovi) {
		super();
		this.naslov = naslov;
		this.opis = opis;
		this.datumIzdavanja = datumIzadavanja;
		this.izvodjac = izvodjac;
		this.urednik = urednik;
		this.status = status;
		this.zanrovi = zanrovi;
	}

	public String getNaslov() {
		return naslov;
	}
	public void setNaslov(String naslov) {
		this.naslov = naslov;
	}
	public String getOpis() {
		return opis;
	}
	public void setOpis(String opis) {
		this.opis = opis;
	}
	public Date getDatumIzadavanja() {
		return datumIzdavanja;
	}
	public void setDatumIzdavanja(Date datumIzadavanja) {
		this.datumIzdavanja = datumIzadavanja;
	}
	public Izvodjac getIzvodjac() {
		return izvodjac;
	}
	public void setIzvodjac(Izvodjac izvodjac) {
		this.izvodjac = izvodjac;
	}
	public Urednik getUrednik() {
		return urednik;
	}
	public void setUrednik(Urednik urednik) {
		this.urednik = urednik;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}

	@Override
	public List<Zanr> getZanrovi() {
		return zanrovi;
	}
	
	public void setZanrovi(List<Zanr> zanrovi) {
		this.zanrovi = zanrovi;
	}


	
	
	
}
