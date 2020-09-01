/***********************************************************************
 * Module:  ZakazanaRecenzija.java
 * Author:  Dragan
 * Purpose: Defines the Class ZakazanaRecenzija
 ***********************************************************************/
package model;

import java.util.Date;

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

   /** @pdRoleInfo migr=no name=Recenzija assc=association34 mult=1..1 */
   private Recenzija recenzija;
   /** @pdRoleInfo migr=no name=Urednik assc=association10 mult=1..1 side=A */
   private Urednik urednik;



   public ZakazanaRecenzija(Date datumZakazivanja, String opis, boolean uradeno, Date rok, Recenzija recenzija, Urednik urednik) {
      this.datumZakazivanja = datumZakazivanja;
      this.opis = opis;
      this.uradeno = uradeno;
      this.rok = rok;
      this.recenzija = recenzija;
      this.urednik = urednik;
   }



   /** @pdGenerated default parent getter */
   public Urednik getUrednik() {
      return urednik;
   }

   /**
    * @return the datumZakazivanja
    */
   public Date getDatumZakazivanja() {
      return datumZakazivanja;
   }

   /**
    * @param datumZakazivanja the datumZakazivanja to set
    */
   public void setDatumZakazivanja(Date datumZakazivanja) {
      this.datumZakazivanja = datumZakazivanja;
   }

   /**
    * @return the uradeno
    */
   public boolean isUradeno() {
      return uradeno;
   }

   /**
    * @param uradeno the uradeno to set
    */
   public void setUradeno(boolean uradeno) {
      this.uradeno = uradeno;
   }

   /**
    * @return the recenzija
    */
   public Recenzija getRecenzija() {
      return recenzija;
   }

   /**
    * @param recenzija the recenzija to set
    */
   public void setRecenzija(Recenzija recenzija) {
      this.recenzija = recenzija;
   }

   /**
    * @return the rok
    */
   public Date getRok() {
      return rok;
   }

   /**
    * @param rok the rok to set
    */
   public void setRok(Date rok) {
      this.rok = rok;
   }

   /**
    * @return the opis
    */
   public String getOpis() {
      return opis;
   }

   /**
    * @param opis the opis to set
    */
   public void setOpis(String opis) {
      this.opis = opis;
   }

   /**
    * @pdGenerated default parent setter
    * @param newUrednik
    */
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