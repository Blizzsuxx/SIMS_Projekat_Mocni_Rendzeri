/***********************************************************************
 * Module:  Komentar.java
 * Author:  Dragan
 * Purpose: Defines the Class Komentar
 ***********************************************************************/
package model;

import java.util.Date;

/** @pdOid 10543295-3d2a-49e5-876e-e10dbe844cf8 */
public class Komentar extends Utisak {
   /** @pdRoleInfo migr=no name=FrontEndKorisnik assc=association21 mult=1..1 */
	private FrontEndKorisnik komentator;

   

   public Komentar() {
      super();
   }

   public Komentar(String text, Date datumUpisa, boolean status, MuzickoDelo delo, FrontEndKorisnik komentator) {
      super(text, datumUpisa, status, delo);
      this.komentator = komentator;
	}

   public FrontEndKorisnik getKomentator() {
      return this.komentator;
   }

   public void setKomentator(FrontEndKorisnik komentator) {
      this.komentator = komentator;
   }

   public Komentar komentator(FrontEndKorisnik komentator) {
      this.komentator = komentator;
      return this;
   }


   @Override
   public String toString() {
      return "{" +
         " komentator='" + getKomentator() + "'" +
         "}";
   }

}