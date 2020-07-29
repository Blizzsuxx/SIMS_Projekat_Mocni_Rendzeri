/***********************************************************************
 * Module:  Komentar.java
 * Author:  Dragan
 * Purpose: Defines the Class Komentar
 ***********************************************************************/
package model;
/** @pdOid 10543295-3d2a-49e5-876e-e10dbe844cf8 */
public class Komentar extends Utisak {
   /** @pdRoleInfo migr=no name=FrontEndKorisnik assc=association21 mult=1..1 */
   public FrontEndKorisnik komentator;

}