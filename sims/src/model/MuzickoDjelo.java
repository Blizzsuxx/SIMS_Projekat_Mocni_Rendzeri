/***********************************************************************
 * Module:  MuzickoDjelo.java
 * Author:  Dragan
 * Purpose: Defines the Class MuzickoDjelo
 ***********************************************************************/
package model;

import java.util.Date;
/** @pdOid fe24355d-b240-471a-9a18-0672095a63e6 */
public class MuzickoDjelo {
   /** @pdOid 85e700ff-acf1-4663-ad34-fb23a5477991 */
   private String naziv;
   /** @pdOid a2b90104-3b01-42b5-94c2-45a2042c4750 */
   private String opis;
   /** @pdOid 59de974d-96ef-40c3-b9e4-bb3a3ce00d39 */
   private Date datumIzdavanja;
   /** @pdOid 50468429-1c78-4995-b779-804722e14b9b */
   private boolean status;
   /** @pdOid e156f7e2-aaed-42f2-8a16-592a92a57cdb */
   private float prosecnaOcenaKorisnika;
   /** @pdOid 1fc30649-4984-4545-831e-01357cd4cb19 */
   private float prosecnaOcenaUrednika;
   
   /** @pdRoleInfo migr=no name=Utisak assc=association5 mult=0..* */
   public Utisak[] utisci;

}