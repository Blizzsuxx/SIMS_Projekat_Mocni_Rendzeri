/***********************************************************************
 * Module:  MuzickoDjelo.java
 * Author:  Dragan
 * Purpose: Defines the Class MuzickoDjelo
 ***********************************************************************/
package model;
import java.util.*;

import javax.swing.JTextField;

/** @pdOid fe24355d-b240-471a-9a18-0672095a63e6 */
public class MuzickoDjelo {
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

/** @pdRoleInfo migr=no name=Utisak assc=association5 mult=0..* */
   public Utisak[] utisci;

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

public Utisak[] getUtisci() {
	return utisci;
}

public void setUtisci(Utisak[] utisci) {
	this.utisci = utisci;
}

public MuzickoDjelo(String naziv, String opis, Date datumIzdavanja, boolean status, float prosecnaOcenaKorisnika,
		float prosecnaOcenaUrednika, Utisak[] utisci) {
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

public MuzickoDjelo() {
	super();
	this.dosadasnjeOceneKorisnika=new ArrayList<Integer>();
	this.dosadasnjeOceneUrednika=new ArrayList<Integer>();
}
   
public MuzickoDjelo(String naslov, String opis2, Date datumIzdavanja2, boolean b, ArrayList<Zanr> zanrovi) {
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
}