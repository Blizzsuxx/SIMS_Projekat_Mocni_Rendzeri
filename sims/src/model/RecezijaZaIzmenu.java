/***********************************************************************
 * Module:  RecezijaZaIzmenu.java
 * Author:  Dragan
 * Purpose: Defines the Class RecezijaZaIzmenu
 ***********************************************************************/
package model;

/** @pdOid 4fb52d4e-b575-4fd4-9014-e10a987168db */
public class RecezijaZaIzmenu {
   /** @pdOid ef166f3c-22d9-45fe-92a4-0a1a73f117a3 */
   private boolean menjanje;
   /** @pdOid 8820b92f-5441-4323-b26e-a88d97b8af11 */
   private boolean brisanje;
   
   /** @pdRoleInfo migr=no name=Recenzija assc=association40 mult=1 */
   public Recenzija recenzija;
   
   public boolean uradeno;
   public String poruka; //kao sta nije ok sa recenzijom
   
   

public RecezijaZaIzmenu(boolean menjanje, boolean brisanje, Recenzija recenzija, boolean uradeno, String poruka) {
	super();
	this.menjanje = menjanje;
	this.brisanje = brisanje;
	this.recenzija = recenzija;
	this.uradeno = uradeno;
	this.poruka = poruka;
}

public boolean isUradeno() {
	return uradeno;
}

public void setUradeno(boolean uradeno) {
	this.uradeno = uradeno;
}

public boolean isMenjanje() {
	return menjanje;
}

public void setMenjanje(boolean menjanje) {
	this.menjanje = menjanje;
}

public boolean isBrisanje() {
	return brisanje;
}

public void setBrisanje(boolean brisanje) {
	this.brisanje = brisanje;
}

public Recenzija getRecenzija() {
	return recenzija;
}

public void setRecenzija(Recenzija recenzija) {
	this.recenzija = recenzija;
}

   
   public String toFileString() {
	   String ad="";
		ad+=this.menjanje+";";
		ad+=this.brisanje+";";
		ad+=this.recenzija.naslov+";";
		ad+=this.uradeno+";";
		ad+=this.poruka;
		return ad;
   }

}