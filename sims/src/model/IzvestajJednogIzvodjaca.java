package model;

public class IzvestajJednogIzvodjaca {
	private String ime; 
	private Izvodjac izvodjacReferenca;
	private int brojDela, brojRecenzija, brojKomentara;
	private double ocenaKorisnika, ocenaUrednika;
	
	
	
	public Izvodjac getIzvodjacReferenca() {
		return izvodjacReferenca;
	}
	public void setIzvodjacReferenca(Izvodjac izvodjacReferenca) {
		this.izvodjacReferenca = izvodjacReferenca;
	}
	public String getIme() {
		return ime;
	}
	public void setIme(String ime) {
		this.ime = ime;
	}
	public int getBrojDela() {
		return brojDela;
	}
	public void setBrojDela(int brojDela) {
		this.brojDela = brojDela;
	}
	public int getBrojRecenzija() {
		return brojRecenzija;
	}
	public void setBrojRecenzija(int brojRecenzija) {
		this.brojRecenzija = brojRecenzija;
	}
	public int getBrojKomentara() {
		return brojKomentara;
	}
	public void setBrojKomentara(int brojKomentara) {
		this.brojKomentara = brojKomentara;
	}
	public double getOcenaKorisnika() {
		return ocenaKorisnika;
	}
	public void setOcenaKorisnika(double ocenaKorisnika) {
		this.ocenaKorisnika = ocenaKorisnika;
	}
	public double getOcenaUrednika() {
		return ocenaUrednika;
	}
	public void setOcenaUrednika(double ocenaUrednika) {
		this.ocenaUrednika = ocenaUrednika;
	}
	public IzvestajJednogIzvodjaca(String ime, int brojDela, int brojRecenzija, int brojKomentara, double ocenaKorisnika,
			double ocenaUrednika) {
		super();
		this.ime = ime;
		this.brojDela = brojDela;
		this.brojRecenzija = brojRecenzija;
		this.brojKomentara = brojKomentara;
		this.ocenaKorisnika = ocenaKorisnika;
		this.ocenaUrednika = ocenaUrednika;
	}
	public IzvestajJednogIzvodjaca() {
		super();
	}
	public IzvestajJednogIzvodjaca(String ime) {
		super();
		this.ime = ime;
		this.brojDela = 0;
		this.brojRecenzija = 0;
		this.brojKomentara = 0;
		this.ocenaKorisnika = 0;
		this.ocenaUrednika = 0;
	}
	
	

}
