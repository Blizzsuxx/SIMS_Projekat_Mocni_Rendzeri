/***********************************************************************
 * Module:  Izvodjac.java
 * Author:  Dragan
 * Purpose: Defines the Class Izvodjac
 ***********************************************************************/

import java.util.*;

/** @pdOid 20a31e0c-cbdd-4a1e-956e-0c8ed03fce3d */
public abstract class Izvodjac {
   /** @pdOid 515ac754-9ea8-45bb-bc6e-0b899ea103d8 */
   private String umetnickoIme;
   /** @pdOid a5f2bea1-d21a-43c6-99b6-58d368935d68 */
   private boolean status;
   
   /** @pdRoleInfo migr=no name=KorisnikAplikacije assc=association16 mult=0..* side=A */
   public KorisnikAplikacije[] prati;

}