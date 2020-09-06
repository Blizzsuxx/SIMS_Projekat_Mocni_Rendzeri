package model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
/***********************************************************************
 * Module:  Clan.java
 * Author:  Dragan
 * Purpose: Defines the Class Clan
 ***********************************************************************/
import java.util.Date;

/** @pdOid 2bdbbe03-46e7-43b8-86a3-41039d253a69 */
public class Clan {
   /** @pdOid 33ff4bd2-c6f0-4551-a448-297e62cc43bd */
   private Date datumPrikljucivanja;
   /** @pdOid 1e9b541c-bb08-41f3-8538-31041cc92a96 */

   public Clan() {
   }

   public Clan(Date datumPrikljucivanja, Date datumNapustanja) {
      this.datumPrikljucivanja = datumPrikljucivanja;
      this.datumNapustanja = datumNapustanja;
   }


   public Clan datumPrikljucivanja(Date datumPrikljucivanja) {
      this.datumPrikljucivanja = datumPrikljucivanja;
      return this;
   }

   public Clan datumNapustanja(Date datumNapustanja) {
      this.datumNapustanja = datumNapustanja;
      return this;
   }



   @Override
   public String toString() {
      return "{" +
         " datumPrikljucivanja='" + getDatumPrikljucivanja() + "'" +
         ", datumNapustanja='" + getDatumNapustanja() + "'" +
         "}";
   }
   private Date datumNapustanja;
   private Pojedinacanizvodjac izvodjac;
   private Grupa grupa;
   
public Grupa getGrupa() {
	return grupa;
}
public void setGrupa(Grupa grupa) {
	this.grupa = grupa;
}
public Date getDatumPrikljucivanja() {
	return datumPrikljucivanja;
}
public void setDatumPrikljucivanja(Date datumPrikljucivanja) {
	this.datumPrikljucivanja = datumPrikljucivanja;
}
public Date getDatumNapustanja() {
	return datumNapustanja;
}
public void setDatumNapustanja(Date datumNapustanja) {
	this.datumNapustanja = datumNapustanja;
}
public Pojedinacanizvodjac getIzvodjac() {
	return izvodjac;
}
public void setIzvodjac(Pojedinacanizvodjac izvodjac) {
	this.izvodjac = izvodjac;
}
public Clan(Date datumPrikljucivanja, Date datumNapustanja, Pojedinacanizvodjac izvodjac) {
	super();
	this.datumPrikljucivanja = datumPrikljucivanja;
	this.datumNapustanja = datumNapustanja;
	this.izvodjac = izvodjac;
}
public String toFileString() {
	String ad="";
	String pattern = "dd.MM.yyyy.";
	DateFormat df = new SimpleDateFormat(pattern);
	ad+=df.format(datumPrikljucivanja)+",";//
	if(this.getDatumNapustanja()==null) {
		ad+="/,";
	}else {
		ad+=df.format(this.datumNapustanja)+",";//	
	}
	ad+=this.getIzvodjac().getUmetnickoIme()+",";
	ad+=this.getGrupa().getUmetnickoIme();
	return ad;
}

   
   

}