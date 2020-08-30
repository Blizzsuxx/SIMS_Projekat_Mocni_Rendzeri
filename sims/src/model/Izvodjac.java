/***********************************************************************
 * Module:  Izvodjac.java
 * Author:  Dragan
 * Purpose: Defines the Class Izvodjac
 ***********************************************************************/
package model;

import java.util.ArrayList;

/** @pdOid 20a31e0c-cbdd-4a1e-956e-0c8ed03fce3d */
public abstract class Izvodjac {
   /** @pdOid 515ac754-9ea8-45bb-bc6e-0b899ea103d8 */
   private String umetnickoIme;
   /** @pdOid a5f2bea1-d21a-43c6-99b6-58d368935d68 */
   private boolean status;
   
   /** @pdRoleInfo migr=no name=KorisnikAplikacije assc=association16 mult=0..* side=A */
   public KorisnikAplikacije[] prati;
   
   public ArrayList<MuzickoDjelo> muzickaDela;
   
   

   public ArrayList<MuzickoDjelo> getMuzickaDela() {
   	return muzickaDela;
   }

   public void setMuzickaDela(ArrayList<MuzickoDjelo> muzickaDela) {
   	this.muzickaDela = muzickaDela;
   }

   public String getUmetnickoIme() {
   	return umetnickoIme;
   }

   public void setUmetnickoIme(String umetnickoIme) {
   	this.umetnickoIme = umetnickoIme;
   }

   public boolean isStatus() {
   	return status;
   }

   public void setStatus(boolean status) {
   	this.status = status;
   }

   public KorisnikAplikacije[] getPrati() {
   	return prati;
   }

   public void setPrati(KorisnikAplikacije[] prati) {
   	this.prati = prati;
   }

   public Izvodjac(String umetnickoIme, boolean status, KorisnikAplikacije[] prati) {
   	super();
   	this.umetnickoIme = umetnickoIme;
   	this.status = status;
   	if (prati != null)
   	{
   		this.prati = prati;
   	}
   }

   public Izvodjac() {
   	super();
   }

}