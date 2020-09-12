package model;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class PodaciUrednikaZaIzvestaj {
	
	private Date danPocetka;
	private Date danKraja;
	private String Ime;
	private int brojRecenzija, brojZadatihRecenzija, brojZaIzmenu;

	public Date getDanPocetka() {
		return danPocetka;
	}
	public void setDanPocetka(Date danPocetka) {
		this.danPocetka = danPocetka;
	}
	public Date getDanKraja() {
		return danKraja;
	}
	public void setDanKraja(Date danKraja) {
		this.danKraja = danKraja;
	}
	public String getIme() {
		return Ime;
	}
	public void setIme(String ime) {
		Ime = ime;
	}
	public int getBrojRecenzija() {
		return brojRecenzija;
	}
	public void setBrojRecenzija(int brojRecenzija) {
		this.brojRecenzija = brojRecenzija;
	}
	public int getBrojZadatihRecenzija() {
		return brojZadatihRecenzija;
	}
	public void setBrojZadatihRecenzija(int brojZadatihRecenzija) {
		this.brojZadatihRecenzija = brojZadatihRecenzija;
	}
	public int getBrojZaIzmenu() {
		return brojZaIzmenu;
	}
	public void setBrojZaIzmenu(int brojZaIzmenu) {
		this.brojZaIzmenu = brojZaIzmenu;
	}
	public PodaciUrednikaZaIzvestaj(Date danPocetka, Date danKraja, String ime, int brojRecenzija,
			int brojZadatihRecenzija, int brojZaIzmenu) {
		super();
		this.danPocetka = danPocetka;
		this.danKraja = danKraja;
		Ime = ime;
		this.brojRecenzija = brojRecenzija;
		this.brojZadatihRecenzija = brojZadatihRecenzija;
		this.brojZaIzmenu = brojZaIzmenu;
	}
	public PodaciUrednikaZaIzvestaj() {
		super();
	}
	@SuppressWarnings("deprecation")
	public PodaciUrednikaZaIzvestaj(java.util.Date danPocetka2, java.util.Date danKraja2, Urednik u) {
		super();
		this.danPocetka = danPocetka2;
		this.danKraja =danKraja2;
		this.Ime=u.getIme()+" "+u.getPrezime();
		this.brojRecenzija = 0;
		this.brojZadatihRecenzija = 0;
		this.brojZaIzmenu = 0;
		if(danPocetka2==null) {
			brojRecenzija=u.getIstorijaRecenzija().size();
			brojZadatihRecenzija=u.getZakazaneRecenzije().size();
			brojZaIzmenu=u.getRecezijaZaIzmenu().size();
		}else {
		ArrayList<Recenzija> rec=(ArrayList<Recenzija>) u.getIstorijaRecenzija();
		for(int i=0; i<u.getIstorijaRecenzija().size(); i++) {		
			if(rec.get(i).getDatumUpisa().after(danPocetka2) && rec.get(i).getDatumUpisa().before(danKraja2)) {
				brojRecenzija++;
			}
		}
		ArrayList<ZakazanaRecenzija> zRec=(ArrayList<ZakazanaRecenzija>) u.getZakazaneRecenzije();
		for(int i=0; i<zRec.size(); i++) {
			if(zRec.get(i).getDatumZakazivanja().after(danPocetka2) && zRec.get(i).isUradeno()) {
				brojZadatihRecenzija++;
			}
		}
		ArrayList<RecezijaZaIzmenu> rIz=(ArrayList<RecezijaZaIzmenu>)u.getRecezijaZaIzmenu();
		for(int i=0; i<rIz.size(); i++) {
			if(rIz.get(i).isUradeno()==false) {
				brojZaIzmenu++;
			}
		}
	}
	}
	

}
