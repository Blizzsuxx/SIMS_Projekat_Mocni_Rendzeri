/***********************************************************************
 * Module:  Korisnik.java
 * Author:  Dragan
 * Purpose: Defines the Class Korisnik
 ***********************************************************************/
package model;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import controler.Constants;

/** @pdOid 821f1adf-c3a0-4478-9680-7e8b2f65437c */
public abstract class Korisnik {
   /** @pdOid 5b9ed12b-0833-4281-ba9d-e989c84e9fbd */
   private String ime;
   /** @pdOid d2e5ef38-cdba-4f7a-b70f-76396bcb5fd7 */
   private String prezime;
   /** @pdOid f11b3d2c-7948-4480-a3a5-e2b538401f1e */
   private String eMail;
   /** @pdOid ad4c25d9-b484-4817-9c0d-49b8d544ad3b */
   private Pol pol;
   /** @pdOid ea30ec0d-001e-493f-b70c-002d616633a3 */
   private Date datumRodjenja;
   
   /** @pdRoleInfo migr=no name=Nalog assc=association26 mult=1..1 */
   private Nalog nalog;
   
   /** @pdOid b5db7dcd-9237-4763-b6ad-4f21f00ab10c */
   public void setSifra(String sifra) {
      // TODO: implement
	   nalog.setSifra(sifra);
   }
   
   @SuppressWarnings("deprecation")
public void setKorisnickoIme(String korisnickoIme) {
	   if(nalog == null) {
		   nalog = new Nalog("password123", korisnickoIme, new Date(), true);
		   
	   } else {
		   throw new RuntimeException();
	   }
	   }
   
   public Korisnik(String ime, String prezime, String eMail, Pol pol, Date datumRodjenja, String sifra, String korisnickoIme, Date datum, boolean status) {
	super();
	this.ime = ime;
	this.prezime = prezime;
	this.eMail = eMail;
	this.pol = pol;
	this.datumRodjenja = datumRodjenja;
	this.nalog = new Nalog(sifra, korisnickoIme, datum, status);
}
   
   public Korisnik() {
	   nalog = null;
   }

public String getIme() {
	return ime;
}

public void setIme(String ime) {
	this.ime = ime;
}

public String getPrezime() {
	return prezime;
}

public void setPrezime(String prezime) {
	this.prezime = prezime;
}

public String getEMail() {
	return eMail;
}

public void setEMail(String eMail) {
	this.eMail = eMail;
}

public Pol getPol() {
	return pol;
}

public void setPol(Pol pol) {
	this.pol = pol;
}

public void setPol(String pol) {
	this.pol = Pol.valueOf(pol);
}

public String getDatumRodjenja() {
	if(datumRodjenja == null) {
		return "";
	}
	return datumRodjenja.toString();
}

public void setDatumRodjenja(Date datumRodjenja) {
	this.datumRodjenja = datumRodjenja;
}
public void setDatumRodjenja(String datumRodjenja) throws ParseException {
		this.datumRodjenja = Constants.FORMAT_ZA_DATUM.parse(datumRodjenja);
}

public Nalog getNalog() {
	return nalog;
}


/** @pdOid 8a10c116-d1f6-4b12-ba00-78128209a21b */
   public void promeniPodatke() {
      // TODO: implement
   }
   
   /** @pdOid e64ea0ca-f330-4fc5-870a-633e6a03731f */
   public void odjaviSe() {
      // TODO: implement
   }

public String getKorisnickoIme() {
	// TODO Auto-generated method stub
	if(nalog == null) {
		return "";
	}
	return nalog.getKorisnickoIme();
}




}