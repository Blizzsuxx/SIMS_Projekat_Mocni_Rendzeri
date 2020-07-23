/***********************************************************************
 * Module:  ZakazanaRecenzija.java
 * Author:  Dragan
 * Purpose: Defines the Class ZakazanaRecenzija
 ***********************************************************************/

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

}