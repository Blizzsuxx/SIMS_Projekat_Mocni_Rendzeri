/***********************************************************************
 * Module:  Komentar.java
 * Author:  Dragan
 * Purpose: Defines the Class Komentar
 ***********************************************************************/
package model;

import java.util.Date;

/** @pdOid 10543295-3d2a-49e5-876e-e10dbe844cf8 */
public class Komentar extends Utisak {
   

public Komentar(String text, Date datumUpisa, boolean status, MuzickoDelo delo, FrontEndKorisnik komentator) {
	super(text, datumUpisa, status, komentator, delo);
}

//@Override
public String toFileString() {
	String ad="";
	ad+=this.getDelo().getNaziv()+";";
	ad+=this.getDatumUpisa().getDay()+"."+this.getDatumUpisa().getMonth()+"."+this.getDatumUpisa().getYear()+".;";//
	ad+=this.isStatus()+";";
	ad+=this.getText()+";";
	ad+=this.getKomentator().getNalog().getKorisnickoIme();
	return ad;
}
   
   
   

   

   public Komentar() {
      super();
   }


   public FrontEndKorisnik getKomentator() {
      return this.getPisac();
   }

   public void setKomentator(FrontEndKorisnik komentator) {
      this.setPisac(komentator);
   }


   @Override
   public String toString() {
      return "{" +
         " komentator='" + getKomentator() + "'" +
         "}";
   }

}