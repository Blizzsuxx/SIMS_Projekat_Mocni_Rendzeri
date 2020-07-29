/***********************************************************************
 * Module:  Recenzija.java
 * Author:  Dragan
 * Purpose: Defines the Class Recenzija
 ***********************************************************************/
package model;
/** @pdOid 7bbc8580-778f-4a3f-8306-57c0cbc02cce */
public class Recenzija extends Utisak {
   /** @pdRoleInfo migr=no name=Urednik assc=association9 mult=1..1 side=A */
   public Urednik urednik;
   
   
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

}