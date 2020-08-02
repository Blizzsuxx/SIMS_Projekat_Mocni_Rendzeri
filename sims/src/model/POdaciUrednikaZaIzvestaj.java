package model;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;

public class POdaciUrednikaZaIzvestaj {
	
	public LocalDate danPocetka;
	public LocalDate danKraja;
	public String Ime;
	public int brojRecenzija, brojZadatihRecenzija, brojZaIzmenu;
	public LocalDate getDanPocetka() {
		return danPocetka;
	}
	public void setDanPocetka(LocalDate danPocetka) {
		this.danPocetka = danPocetka;
	}
	public LocalDate getDanKraja() {
		return danKraja;
	}
	public void setDanKraja(LocalDate danKraja) {
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
	public POdaciUrednikaZaIzvestaj(LocalDate danPocetka, LocalDate danKraja, String ime, int brojRecenzija,
			int brojZadatihRecenzija, int brojZaIzmenu) {
		super();
		this.danPocetka = danPocetka;
		this.danKraja = danKraja;
		Ime = ime;
		this.brojRecenzija = brojRecenzija;
		this.brojZadatihRecenzija = brojZadatihRecenzija;
		this.brojZaIzmenu = brojZaIzmenu;
	}
	public POdaciUrednikaZaIzvestaj() {
		super();
	}
	public POdaciUrednikaZaIzvestaj(LocalDate danPocetka, LocalDate danKraja, Urednik u) {
		super();
		this.danPocetka = danPocetka;
		this.danKraja = danKraja;
		Date d=new Date(danPocetka.getYear(),danPocetka.getMonthValue(), danPocetka.getDayOfMonth());
		Date d1=new Date(danKraja.getYear(), danKraja.getMonthValue(), danKraja.getDayOfMonth());
		this.Ime=u.getIme()+" "+u.getPrezime();
		this.brojRecenzija = 0;
		this.brojZadatihRecenzija = 0;
		this.brojZaIzmenu = 0;
		ArrayList<Recenzija> rec=(ArrayList<Recenzija>) u.getIstorijaRecenzija();
		for(int i=0; i<u.getIstorijaRecenzija().size(); i++) {
			if(rec.get(i).getDatumUpisa().after(d) && rec.get(i).getDatumUpisa().before(d1)) {
				brojRecenzija++;
			}
		}
		ArrayList<ZakazanaRecenzija> zRec=(ArrayList<ZakazanaRecenzija>) u.getZakazaneRecenzije();
		for(int i=0; i<zRec.size(); i++) {
			if(zRec.get(i).getDatumZakazivanja().after(d) && zRec.get(i).isUradeno()) {
				brojZadatihRecenzija++;
			}
		}
		ArrayList<RecezijaZaIzmenu> rIz=(ArrayList<RecezijaZaIzmenu>)u.getRecezijaZaIzmenu();
		for(int i=0; i<rIz.size(); i++) {
			if(rIz.get(i).uradeno==false) {
				brojZaIzmenu++;
			}
		}
	}
	
	

}
