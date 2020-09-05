/***********************************************************************
 * Module:  Recenzija.java
 * Author:  Dragan
 * Purpose: Defines the Class Recenzija
 ***********************************************************************/
package model;
import java.util.Date;

/** @pdOid 7bbc8580-778f-4a3f-8306-57c0cbc02cce */
public class Recenzija extends Utisak {
   /** @pdRoleInfo migr=no name=Urednik assc=association9 mult=1 side=A */
   
   public String naslov;
   
   
   public Recenzija(Urednik urednik, String naslov) {
	super();
	this.setPisac(urednik);;
	this.naslov = naslov;
}

   
public Recenzija(String text, Date datumUpisa, boolean status, FrontEndKorisnik pisac, MuzickoDelo delo, String naslov) {
	super(text, datumUpisa, status, pisac, delo);
	this.naslov = naslov;
}


public String getNaslov() {
	return naslov;
}

public void setNaslov(String naslov) {
	this.naslov = naslov;
}

/** @pdGenerated default parent getter */
   public Urednik getUrednik() {
      return (Urednik) this.getPisac();
   }
   
   /** @pdGenerated default parent setter
     * @param newUrednik */
   public void setUrednik(Urednik newUrednik) {
      Urednik urednik = this.getUrednik();
      if (urednik == null || urednik.equals(newUrednik))
      {
         if (urednik != null)
         {
            Urednik oldUrednik = urednik;
            urednik = null;
            oldUrednik.removeIstorijaRecenzija(this);
         }
         if (newUrednik != null)
         {
            urednik = newUrednik;
            urednik.addIstorijaRecenzija(this);
         }
      }
   }


public void setAll(String text, String naslov2, Izvodjac i, MuzickoDelo md) {
	this.setDatumUpisa(new Date());
	this.setDelo(md);
	this.setText(text);
	this.setNaslov(naslov2);
	this.setStatus(true);
	
	
}


//@Override
public String toFileString() {
	String ad="";
	ad+=this.getDelo().getNaziv()+";";
	ad+=this.getDatumUpisa().getDay()+"."+this.getDatumUpisa().getMonth()+"."+this.getDatumUpisa().getYear()+".;";//
	ad+=this.isStatus()+";";
	ad+=this.getText()+";";
	ad+=this.getUrednik().getNalog().getKorisnickoIme()+";";
	ad+=this.getNaslov();
	return ad;
}

}