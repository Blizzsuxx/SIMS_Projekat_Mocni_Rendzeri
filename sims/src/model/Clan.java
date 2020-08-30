/***********************************************************************
 * Module:  Clan.java
 * Author:  Dragan
 * Purpose: Defines the Class Clan
 ***********************************************************************/
package model;
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

   public Date getDatumPrikljucivanja() {
      return this.datumPrikljucivanja;
   }

   public void setDatumPrikljucivanja(Date datumPrikljucivanja) {
      this.datumPrikljucivanja = datumPrikljucivanja;
   }

   public Date getDatumNapustanja() {
      return this.datumNapustanja;
   }

   public void setDatumNapustanja(Date datumNapustanja) {
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

}