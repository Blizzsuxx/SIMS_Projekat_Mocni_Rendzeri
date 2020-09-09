/***********************************************************************
 * Module:  MuzickoDelo.java
 * Author:  Dragan
 * Purpose: Defines the Class MuzickoDelo
 ***********************************************************************/
package model;

import java.awt.image.BufferedImage;

import java.text.DateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import controler.Constants;
import view.Slikovit;

/** @pdOid fe24355d-b240-471a-9a18-0672095a63e6 */
public class MuzickoDelo implements Slikovit {
   /** @pdOid 85e700ff-acf1-4663-ad34-fb23a5477991 */
   private String naziv;
   /** @pdOid a2b90104-3b01-42b5-94c2-45a2042c4750 */
   private String opis;
   /** @pdOid 59de974d-96ef-40c3-b9e4-bb3a3ce00d39 */
   private Date datumIzdavanja;
   /** @pdOid 50468429-1c78-4995-b779-804722e14b9b */
   private boolean status;
   /** @pdOid e156f7e2-aaed-42f2-8a16-592a92a57cdb */
   private float prosecnaOcenaKorisnika;
   /** @pdOid 1fc30649-4984-4545-831e-01357cd4cb19 */
   private float prosecnaOcenaUrednika;
   
   private ArrayList<Zanr> zanrovi;
   
   private ArrayList<Integer> dosadasnjeOceneKorisnika;
   private ArrayList<Integer> dosadasnjeOceneUrednika;
   /** @pdRoleInfo migr=no name=Utisak assc=association5 mult=0..* */
   private List<Utisak> utisci;
   

public ArrayList<Integer> getDosadasnjeOceneKorisnika() {
	return dosadasnjeOceneKorisnika;
}

public void setDosadasnjeOceneKorisnika(ArrayList<Integer> dosadasnjeOceneKorisnika) {
	this.dosadasnjeOceneKorisnika = dosadasnjeOceneKorisnika;
}

public ArrayList<Integer> getDosadasnjeOceneUrednika() {
	return dosadasnjeOceneUrednika;
}

public void setDosadasnjeOceneUrednika(ArrayList<Integer> dosadasnjeOceneUrednika) {
	this.dosadasnjeOceneUrednika = dosadasnjeOceneUrednika;
}

public ArrayList<Zanr> getZanrovi() {
	return zanrovi;
}

public void setZanrovi(ArrayList<Zanr> zanrovi) {
	this.zanrovi = zanrovi;
}



public String getNaziv() {
	return naziv;
}

public void setNaziv(String naziv) {
	this.naziv = naziv;
}

public String getOpis() {
	return opis;
}

public void setOpis(String opis) {
	this.opis = opis;
}

public Date getDatumIzdavanja() {
	return datumIzdavanja;
}

public void setDatumIzdavanja(Date datumIzdavanja) {
	this.datumIzdavanja = datumIzdavanja;
}

public boolean isStatus() {
	return status;
}

public void setStatus(boolean status) {
	this.status = status;
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

public List<Utisak> getUtisci() {
	return utisci;
}


public MuzickoDelo(String naziv, String opis, Date datumIzdavanja, boolean status, float prosecnaOcenaKorisnika,
		float prosecnaOcenaUrednika, List<Utisak> utisci) {
	super();
	this.naziv = naziv;
	this.opis = opis;
	this.datumIzdavanja = datumIzdavanja;
	this.status = status;
	this.prosecnaOcenaKorisnika = prosecnaOcenaKorisnika;
	this.prosecnaOcenaUrednika = prosecnaOcenaUrednika;
	this.utisci = utisci;
	this.dosadasnjeOceneKorisnika=new ArrayList<Integer>();
	this.dosadasnjeOceneUrednika=new ArrayList<Integer>();
}

public MuzickoDelo() {
	super();
	this.dosadasnjeOceneKorisnika=new ArrayList<Integer>();
	this.dosadasnjeOceneUrednika=new ArrayList<Integer>();
}
   
public MuzickoDelo(String naslov, String opis2, Date datumIzdavanja2, boolean b, ArrayList<Zanr> zanrovi) {
	this.dosadasnjeOceneKorisnika=new ArrayList<Integer>();
	this.dosadasnjeOceneUrednika=new ArrayList<Integer>();
	this.naziv=naslov;
	this.opis=opis2;
	this.prosecnaOcenaKorisnika=0;
	this.prosecnaOcenaUrednika=0;
	this.status=b;
	this.zanrovi=zanrovi;
	this.datumIzdavanja=datumIzdavanja2;
}


public MuzickoDelo(String trim, String trim2, Date d, boolean b) {
	this.dosadasnjeOceneKorisnika=new ArrayList<Integer>();
	this.dosadasnjeOceneUrednika=new ArrayList<Integer>();
	this.naziv=trim;
	this.opis=trim2;
	this.prosecnaOcenaKorisnika=0;
	this.prosecnaOcenaUrednika=0;
	this.status=b;
	this.zanrovi=new ArrayList<Zanr>();
	this.datumIzdavanja=d;
}

public MuzickoDelo(String naziv, String opis) {
	this.naziv = naziv;
	this.opis = opis;
	this.status = true;
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

public String toFileString(Izvodjac iz) {
	DateFormat df = Constants.NATASIN_FORMAT_ZA_DATUM;
	String ad="";
	ad+=this.getNaziv()+";";
	ad+=this.getOpis()+";";
	ad+=df.format(this.getDatumIzdavanja())+";";
	ad+=this.isStatus()+";";
	ad+=iz.getUmetnickoIme()+";";
	int i=0;
	for(Zanr z:this.getZanrovi()) {
		if(i!=0) {
			ad+="|";
		}i++;
	ad+=z.getNazivZanra();
	}
	ad+=System.lineSeparator();
	return ad;
}

@Override
public String Ime() {
	// TODO Auto-generated method stub
	return this.getNaziv();
}

@Override
public String putDoSlike() {
	// TODO Auto-generated method stub
	return "fajlovi/muzika/"+getNaziv() + ".jpg";
}

@Override
public BufferedImage defaultSlika() {
	// TODO Auto-generated method stub
	return Constants.MUZICKA_IKONA;
}
}