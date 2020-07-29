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
   
   /** @pdRoleInfo migr=no name=Recenzija assc=association40 mult=1..1 */
   public Recenzija recenzija;

}