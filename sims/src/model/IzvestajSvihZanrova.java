package model;

public class IzvestajSvihZanrova {
	private String naziv;
	private int BrojMuzdela;
	private int brojRecenzija;
	private int brojKOmentara;
	
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public int getBrojMuzdela() {
		return BrojMuzdela;
	}
	public void setBrojMuzdela(int brojMuzdela) {
		BrojMuzdela = brojMuzdela;
	}
	public int getBrojRecenzija() {
		return brojRecenzija;
	}
	public void setBrojRecenzija(int brojRecenzija) {
		this.brojRecenzija = brojRecenzija;
	}
	
	
	public int getBrojKOmentara() {
		return brojKOmentara;
	}
	public void setBrojKOmentara(int brojKOmentara) {
		this.brojKOmentara = brojKOmentara;
	}
	public IzvestajSvihZanrova(String naziv, int brojMuzdela, int brojRecenzija) {
		super();
		this.naziv = naziv;
		BrojMuzdela = brojMuzdela;
		this.brojRecenzija = brojRecenzija;
		
	}
	public IzvestajSvihZanrova() {
		super();
	}
	public IzvestajSvihZanrova(String naziv) {
		super();
		this.naziv = naziv;
		BrojMuzdela = 0;
		this.brojRecenzija = 0;
		
	}
	
	
	
	
}
