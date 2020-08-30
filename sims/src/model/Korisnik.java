/***********************************************************************
 * Module:  Korisnik.java
 * Author:  Dragan
 * Purpose: Defines the Class Korisnik
 ***********************************************************************/
package model;
import java.util.*;

/** @pdOid 821f1adf-c3a0-4478-9680-7e8b2f65437c */
public abstract class Korisnik {
   /** @pdOid 5b9ed12b-0833-4281-ba9d-e989c84e9fbd */
   protected String ime;
   /** @pdOid d2e5ef38-cdba-4f7a-b70f-76396bcb5fd7 */
   protected String prezime;
   /** @pdOid f11b3d2c-7948-4480-a3a5-e2b538401f1e */
   protected String eMail;
   /** @pdOid ad4c25d9-b484-4817-9c0d-49b8d544ad3b */
   protected Pol pol;
   /** @pdOid ea30ec0d-001e-493f-b70c-002d616633a3 */
   protected Date datumRodjenja;
   /** @pdOid be0f0d3c-a6da-4bed-98e0-397c13d099f8 */
   protected boolean status;
   private int sifra;
   
   public int getSifra() {
	return sifra;
}


public void setSifra(int sifra) {
	this.sifra = sifra;
}


/** @pdRoleInfo migr=no name=Nalog assc=association26 mult=1 */
   protected Nalog nalog;
   
   public Korisnik() {
	   super();
   }

   
   public Korisnik(String ime, String prezime, String eMail, Pol pol, Date datumRodjenja, boolean status, int sifra) {
	   // konstruktor bez naloga
	   	super();
		this.ime = ime;
		this.prezime = prezime;
		this.eMail = eMail;
		this.pol = pol;
		this.datumRodjenja = datumRodjenja;
		this.status = status;
		this.nalog = new Nalog();
		this.sifra=sifra;
   }

   public Korisnik(String ime, String prezime, String eMail, Pol pol, Date datumRodjenja, boolean status, Nalog nalog, int sifra) {
	   super();
	   this.ime = ime;
	   this.prezime = prezime;
	   this.eMail = eMail;
	   this.pol = pol;
	   this.datumRodjenja = datumRodjenja;
	   this.status = status;
	   this.nalog = nalog;
	   this.sifra=sifra;
   }

   /** @pdOid b5db7dcd-9237-4763-b6ad-4f21f00ab10c */
   public void promeniSifru() {
      // TODO: implement
   }
   
   /** @pdOid 8a10c116-d1f6-4b12-ba00-78128209a21b */
   public void promeniPodatke() {
      // TODO: implement
   }
   
   /** @pdOid e64ea0ca-f330-4fc5-870a-633e6a03731f */
   public void odjaviSe() {
      // TODO: implement
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
	
	
	public String geteMail() {
		return eMail;
	}
	
	
	public void seteMail(String eMail) {
		this.eMail = eMail;
	}
	
	
	public Pol getPol() {
		return pol;
	}
	
	
	public void setPol(Pol pol) {
		this.pol = pol;
	}
	
	
	public Date getDatumRodjenja() {
		return datumRodjenja;
	}
	
	
	public void setDatumRodjenja(Date datumRodjenja) {
		this.datumRodjenja = datumRodjenja;
	}
	
	
	public boolean isStatus() {
		return status;
	}
	
	
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	
	public Nalog getNalog() {
		return nalog;
	}
	
	
	public void setNalog(Nalog nalog) {
		this.nalog = nalog;
	}

   
}