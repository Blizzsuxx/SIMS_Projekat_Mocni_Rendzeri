/***********************************************************************
 * Module:  Nalog.java
 * Author:  Dragan
 * Purpose: Defines the Class Nalog
 ***********************************************************************/
package model;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import controler.Constants;

/** @pdOid 160f1d49-ad7d-4dbd-ad61-a747a8aa941a */
public class Nalog {
   /** @pdOid 501b74e5-d072-42f0-8c0c-57bb2a68d7b2 */
   private String sifra;
   /** @pdOid 3ca3a3c4-9cb0-4f50-8941-af64af2095dd */
   private String korisnickoIme;
   /** @pdOid 62aa15b1-116b-4726-865c-5b0ab03b3a60 */
   private Date datumKreiranja;
   /** @pdOid 49827b69-4bc5-4dbd-8c57-60c7bb1fa9f5 */
   private boolean status;
   
   public Nalog() {}
   
   public Nalog(String sifra, String korisnickoIme, Date datumKreiranja, boolean status) {
		super();
		this.sifra = sifra;
		this.korisnickoIme = korisnickoIme;
		this.datumKreiranja = datumKreiranja;
		this.status = status;
   }


/** @pdOid 449db2fa-018a-4b03-8896-77483d6d08f5 */
	public boolean potvrdiSifru(String sifra) {
		// TODO: implement
		return this.sifra.equals(sifra);
 	}
	
	public String getSifra() {
		return sifra;
	}

	public void setSifra(String sifra) {
		this.sifra = sifra;
	}
	
	public String getKorisnickoIme() {
		return korisnickoIme;
	}
	
	public void setKorisnickoIme(String korisnickoIme) {
		this.korisnickoIme = korisnickoIme;
	}
	
	public Date getDatumKreiranja() {
		return datumKreiranja;
	}
	
	public void setDatumKreiranja(Date datumKreiranja) {
		this.datumKreiranja = datumKreiranja;
	}
	
	public boolean isStatus() {
		return status;
	}
	
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	public boolean izbrisi() {
		if(this.status==false) {
			return false;
		}else {this.status=false;
				return true;}
	}

	@SuppressWarnings("deprecation")
	public String toFileString(int i) {//txt txt datum tf
		String ad="";
		ad+=this.sifra+",";
		ad+=this.getKorisnickoIme()+",";
		ad+=this.getDatumKreiranja().getDay()+"."+this.getDatumKreiranja().getMonth()+"."+this.getDatumKreiranja().getYear()+".,";//
		ad+=this.isStatus()+",";
		ad+=(i+"");
		return ad;
	}

	
	public static boolean string2Bool(String value) {
		return (value.equals("1") ? true : false);
	}
	
	public static String bool2String(boolean value) {
		return (value ? "1" : "0");
	}
	
	public static String Nalog2String(Nalog n) {
		DateFormat df = Constants.NATASIN_FORMAT_ZA_DATUM;;
		return n.getKorisnickoIme() + "|" + n.sifra + "|" + df.format(n.getDatumKreiranja()) + "|" +
				bool2String(n.isStatus()) + System.lineSeparator();
	}
	
	public static Nalog string2Nalog(String linija) throws ParseException {
		Nalog nalog = new Nalog();
		String[] parts = linija.split("\\|");
		nalog.setKorisnickoIme(parts[0]);
		nalog.setSifra(parts[1]);
		nalog.setDatumKreiranja(new SimpleDateFormat("dd.MM.yyyy.").parse(parts[2]));
		nalog.setStatus(string2Bool(parts[3]));
		return nalog;
	}

}