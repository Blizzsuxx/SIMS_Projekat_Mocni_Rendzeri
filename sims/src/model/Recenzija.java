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
   public Urednik urednik;
   
   public String naslov;
   
   
   public Recenzija(Urednik urednik, String naslov) {
	super();
	this.urednik = urednik;
	this.naslov = naslov;
}

   
public Recenzija(MuzickoDelo delo, Date d, boolean bf, String string, Urednik u, String trim) {
	super(string, d,bf,delo);
	this.urednik = u;
	this.naslov = trim;
}


public String getNaslov() {
	return naslov;
}

public void setNaslov(String naslov) {
	this.naslov = naslov;
}

/** @pdGenerated default parent getter */
   public Urednik getUrednik() {
      return urednik;
   }
   
   /** @pdGenerated default parent setter
     * @param newUrednik */
   public void setUrednik(Urednik newUrednik) {
      if (this.urednik == null || !this.urednik.equals(newUrednik))
      {
         if (this.urednik != null)
         {
            Urednik oldUrednik = this.urednik;
            this.urednik = null;
            oldUrednik.removeIstorijaRecenzija(this);
         }
         if (newUrednik != null)
         {
            this.urednik = newUrednik;
            this.urednik.addIstorijaRecenzija(this);
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