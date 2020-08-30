/***********************************************************************
 * Module:  Grupa.java
 * Author:  Dragan
 * Purpose: Defines the Class Grupa
 ***********************************************************************/
package model;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
/** @pdOid 38a4054e-e6d7-430f-83e2-c22dad90681d */
public class Grupa extends Izvodjac {
   /** @pdOid 4ad9e500-3e23-4910-a130-75d40fcd6374 */
   private int brojClanova;
   /** @pdOid adad38f8-8fac-43ba-85dd-eb9faf4ea934 */
   private Date datumOsnivanja;
   /** @pdOid e4429194-5897-4195-9b98-cdf8a48f2c45 */
   private Date datumRaspada;
   //novo
   public int getBrojClanova() {
		return brojClanova;
	}
	public void setBrojClanova(int brojClanova) {
		this.brojClanova = brojClanova;
	}
	public Date getDatumOsnivanja() {
		return datumOsnivanja;
	}
	public void setDatumOsnivanja(Date datumOsnivanja) {
		this.datumOsnivanja = datumOsnivanja;
	}
	public Date getDatumRaspada() {
		return datumRaspada;
	}
	public void setDatumRaspada(Date datumRaspada) {
		this.datumRaspada = datumRaspada;
	}
	public Grupa(String umetnickoIme, boolean status, KorisnikAplikacije[] prati, int brojClanova, Date datumOsnivanja,
			Date datumRaspada) {
		super(umetnickoIme, status, prati);
		this.brojClanova = brojClanova;
		this.datumOsnivanja = datumOsnivanja;
		this.datumRaspada = datumRaspada;
	}
	public Grupa(String umetnickoIme, boolean status, KorisnikAplikacije[] prati) {
		super(umetnickoIme, status, prati);
	}
	
	public Grupa(String umetnickoIme, boolean status, Integer brojClanova, Date datumOsnivanja, Date datumRaspada) {
		super(umetnickoIme, status, null);
		this.brojClanova = brojClanova;
		this.datumOsnivanja = datumOsnivanja;
		this.datumRaspada = datumRaspada;
	}
	
	public static boolean string2Bool(String value) {
		return (value.equals("1") ? true : false);
	}
	
	public static String bool2String(boolean value) {
		return (value ? "1" : "0");
	}
	
	public static String Grupa2String(Grupa g) {
		String pattern = "dd.MM.yyyy";
		DateFormat df = new SimpleDateFormat(pattern);
		return g.getUmetnickoIme() + "|" + bool2String(g.isStatus()) + "|" + g.getBrojClanova() + "|" +
				 df.format(g.getDatumOsnivanja()) + "|" + df.format(g.getDatumRaspada()) + System.lineSeparator();
	}


}