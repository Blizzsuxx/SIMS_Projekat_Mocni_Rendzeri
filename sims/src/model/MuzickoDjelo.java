/***********************************************************************
 * Module:  MuzickoDjelo.java
 * Author:  Dragan
 * Purpose: Defines the Class MuzickoDjelo
 ***********************************************************************/
package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
   
   /** @pdRoleInfo migr=no name=Utisak assc=association5 mult=0..* */
   private List<Utisak> utisci;
   
   private List<Zanr> zanrovi;
   

   public List<Zanr> getZanrovi() {
   	return zanrovi;
   }

   public void setZanrovi(List<Zanr> zanrovi) {
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

   public void setUtisci(List<Utisak> utisci) {
   	this.utisci = utisci;
   }

   public MuzickoDjelo(String naziv, String opis, Date datumIzdavanja, boolean status, float prosecnaOcenaKorisnika,
   		float prosecnaOcenaUrednika, List<Utisak> utisci) {
   	super();
   	this.naziv = naziv;
   	this.opis = opis;
   	this.datumIzdavanja = datumIzdavanja;
   	this.status = status;
   	this.prosecnaOcenaKorisnika = prosecnaOcenaKorisnika;
   	this.prosecnaOcenaUrednika = prosecnaOcenaUrednika;
   	this.utisci = utisci;
   }

   public MuzickoDjelo() {
   	super();
   }
      
      

}