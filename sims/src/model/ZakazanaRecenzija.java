/***********************************************************************
 * Module:  ZakazanaRecenzija.java
 * Author:  Dragan
 * Purpose: Defines the Class ZakazanaRecenzija
 ***********************************************************************/
package model;
import java.util.*;

/** @pdOid 1aed17a0-74ed-476f-886a-73eca8c0108a */
public class ZakazanaRecenzija {
   /** @pdOid 2f242f73-260f-463c-ab5c-669460e683c8 */
   private Date datumZakazivanja;
   /** @pdOid 5802addd-0641-4d6a-9c5f-2e17bd382cbf */
   private String opis;
   /** @pdOid 59b24d7e-9a44-4b73-887c-dfadcf243bb3 */
   private boolean uradeno = false;
   /** @pdOid 28e9db3a-2a42-458d-96be-d5d604fb4f26 */
   private Date rok;
   
   /** @pdRoleInfo migr=no name=Recenzija assc=association34 mult=1 */
   public Recenzija recenzija;
   /** @pdRoleInfo migr=no name=Urednik assc=association10 mult=1 side=A */
   public Urednik urednik;
   
   
   public Date getDatumZakazivanja() {
	return datumZakazivanja;
}

public ZakazanaRecenzija(Date datumZakazivanja, String opis, boolean uradeno, Date rok, Recenzija recenzija,
		Urednik urednik) {
	super();
	this.datumZakazivanja = datumZakazivanja;
	this.opis = opis;
	this.uradeno = uradeno;
	this.rok = rok;
	this.recenzija = recenzija;
	this.urednik = urednik;
}

public void setDatumZakazivanja(Date datumZakazivanja) {
	this.datumZakazivanja = datumZakazivanja;
}

public String getOpis() {
	return opis;
}

public void setOpis(String opis) {
	this.opis = opis;
}

public boolean isUradeno() {
	return uradeno;
}

public void setUradeno(boolean uradeno) {
	this.uradeno = uradeno;
}

public Date getRok() {
	return rok;
}

public void setRok(Date rok) {
	this.rok = rok;
}

public Recenzija getRecenzija() {
	return recenzija;
}

public void setRecenzija(Recenzija recenzija) {
	this.recenzija = recenzija;
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
            oldUrednik.removeZakazaneRecenzije(this);
         }
         if (newUrednik != null)
         {
            this.urednik = newUrednik;
            this.urednik.addZakazaneRecenzije(this);
         }
      }
   }

public String toFileString() {
	//opis tru dan dan rec
	String ad=this.getOpis()+";"+this.isUradeno()+";";
	ad+=this.getDatumZakazivanja().getDate()+"."+this.getDatumZakazivanja().getMonth()+"."+this.getDatumZakazivanja().getYear()+".;";
	ad+=this.getRok().getDate()+"."+this.getRok().getMonth()+"."+this.getRok().getYear()+".;";
	ad+=this.getRecenzija().getNaslov();
	
	
	return ad;
}

}