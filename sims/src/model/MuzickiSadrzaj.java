package model;

import java.util.Date;

public abstract class MuzickiSadrzaj {
	private String naslov;
	private String opis;
	private Date datumIzadavanja;
	private Izvodjac izvodjac;
	private Urednik urednik;
	private boolean status;
	
	
	public MuzickiSadrzaj() {
		super();
	}
	
	public MuzickiSadrzaj(String naslov, String opis, Date datumIzadavanja, Izvodjac izvodjac, Urednik urednik,
			boolean status) {
		super();
		this.naslov = naslov;
		this.opis = opis;
		this.datumIzadavanja = datumIzadavanja;
		this.izvodjac = izvodjac;
		this.urednik = urednik;
		this.status = status;
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
		return datumIzadavanja;
	}
	public void setDatumIzadavanja(Date datumIzadavanja) {
		this.datumIzadavanja = datumIzadavanja;
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
	
	
}
