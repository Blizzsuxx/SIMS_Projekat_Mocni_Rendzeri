/***********************************************************************
 * Module:  KorisniciMenadzer.java
 * Author:  Dragan
 * Purpose: Defines the Class KorisniciMenadzer
 ***********************************************************************/
package controler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import model.Administrator;
import model.Korisnik;
import model.KorisnikAplikacije;
import model.Pol;
import model.Urednik;

/** @pdOid 121daa1d-b073-437c-95b7-7f061b5ef5df */
public class KorisniciMenadzer {
   /** @pdRoleInfo migr=no name=Korisnik assc=association25 mult=1 type=Aggregation */
   private HashMap<String, Korisnik> korisnici;
   
	public KorisniciMenadzer(List<String[]> readAll) {
	// TODO Auto-generated constructor stub
	   korisnici = new HashMap<String, Korisnik>();
	   SimpleDateFormat format = Constants.FORMAT_ZA_DATUM;
	   for(String[] s : readAll) {
		   String ime = s[0];
		   String prezime = s[1];
		   String eMail = s[2];
		   Pol pol = Pol.valueOf(s[3]);
		   Date datumRodjenja;
		try {
			datumRodjenja = format.parse(s[4]);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			continue;
		}
		   String sifra = s[5];
		   String korisnickoIme = s[6];
		   Date datum;
		try {
			datum = format.parse(s[7]);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			continue;
		}
		   boolean status = Boolean.parseBoolean(s[8]);
		   String uloga = s[9];
		   switch(uloga) {
		   case "a":
			   dodaj(new Administrator(ime, prezime, eMail, pol, datumRodjenja, sifra, korisnickoIme, datum, status));
			   break;
			case "k":
				dodaj(new KorisnikAplikacije(ime, prezime, eMail, pol, datumRodjenja, sifra, korisnickoIme, datum, status));
				break;
			case "u":
				dodaj(new Urednik(ime, prezime, eMail, pol, datumRodjenja, sifra, korisnickoIme, datum, status));
				break;
		   }
	   }
	}
   
   
   
   
   public boolean provjeriKorisnickoIme(String korisnickoIme) {
	   return korisnici.containsKey(korisnickoIme);
   }
   
   /** @pdOid de9351bc-01ca-477d-962b-27f63d3d7a01 */
   public void dodaj(Korisnik k) {
      if (!provjeriKorisnickoIme(k.getNalog().getKorisnickoIme()))
    	  korisnici.put(k.getNalog().getKorisnickoIme(), k);
   }
   
   /** @pdOid 59cb0526-ada2-4d39-93a6-eaaf6a8f1733 */
   public Korisnik trazi(String korisnickoIme) {
      // TODO: implement
      return this.korisnici.get(korisnickoIme);
   }

   public void sacuvaj(){
	   
   }

   
}