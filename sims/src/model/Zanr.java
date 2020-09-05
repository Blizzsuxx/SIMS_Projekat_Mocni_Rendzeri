/***********************************************************************
 * Module:  Zanr.java
 * Author:  Dragan
 * Purpose: Defines the Class Zanr
 ***********************************************************************/
package model;
import java.util.*;

/** @pdOid e84ce192-a75f-4715-aec5-18c9b0cb0079 */
public class Zanr {
   /** @pdOid d9ad4a8c-42a0-44b4-b358-800ff7e78f7e */
   private String nazivZanra;
   /** @pdOid 0338ddb0-c2e7-4d8d-9920-408569676b79 */
   private boolean status;

   
   public String getNazivZanra() {
	return nazivZanra;
}
public void setNazivZanra(String nazivZanra) {
	this.nazivZanra = nazivZanra;
}
public boolean isStatus() {
	return status;
}
public void setStatus(boolean status) {
	this.status = status;
}
public Zanr(String nazivZanra, boolean status) {
	super();
	this.nazivZanra = nazivZanra;
	this.status = status;
}
public Zanr() {
	super();
}
public String toFileString() {
	String ad="";
	ad+=this.getNazivZanra()+";";
	ad+=this.isStatus();
	return ad;
}

   
}