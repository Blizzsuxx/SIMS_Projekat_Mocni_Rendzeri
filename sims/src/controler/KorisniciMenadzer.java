/***********************************************************************
 * Module:  KorisniciMenadzer.java
 * Author:  Dragan
 * Purpose: Defines the Class KorisniciMenadzer
 ***********************************************************************/
package controler;
import java.io.File;
import java.io.IOException;
import java.util.*;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import model.Korisnik;

/** @pdOid 121daa1d-b073-437c-95b7-7f061b5ef5df */
public class KorisniciMenadzer {
   /** @pdRoleInfo migr=no name=Korisnik assc=association25 mult=1 type=Aggregation */
   private Map<String, Korisnik> korisnici;
   private final String putanjaKorisnika = "fajlovi" + System.getProperty("file.separator") + "korisnici.json";
   
   public KorisniciMenadzer() {
	   this.korisnici = new HashMap<>();
   }
   
   public void upisiKorisnike() {
	   ObjectMapper mapper = new ObjectMapper();
	   mapper.enable(SerializationFeature.INDENT_OUTPUT);
	   try {
		   mapper.writeValue(new File(putanjaKorisnika), korisnici);
	   } catch (JsonGenerationException e) {
		   // TODO Auto-generated catch block
		   e.printStackTrace();
	   } catch (JsonMappingException e) {
		   // TODO Auto-generated catch block
		   e.printStackTrace();
	   } catch (IOException e) {
		   // TODO Auto-generated catch block
		   e.printStackTrace();
	   }
   }
   
   @SuppressWarnings("unchecked")
   public void ucitajKorisnike() {
	   ObjectMapper mapper = new ObjectMapper();

	   try {
		   korisnici = mapper.readValue(new File(putanjaKorisnika), Map.class);
	   } catch (JsonParseException e) {
		   // TODO Auto-generated catch block
		   e.printStackTrace();
	   } catch (JsonMappingException e) {
		   // TODO Auto-generated catch block
		   e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
   }
   
   public boolean provjeriKorisnickoIme(String korisnickoIme) {
	   return korisnici.containsKey(korisnickoIme);
   }
   
   /** @pdOid de9351bc-01ca-477d-962b-27f63d3d7a01 */
   public void dodaj(Korisnik k) {
      if (!provjeriKorisnickoIme(k.getNalog().getKorisnickoIme()))
    	  korisnici.put(k.getNalog().getKorisnickoIme(), k);
   }
   
   /** @pdOid 59cb0526-ada2-4d39-93a6-eaaf6a8f1733 */
   public Korisnik trazi() {
      // TODO: implement
      return null;
   }
   
   /** @pdOid 2bd9b22e-b509-4e9b-ad56-ec39b892c5db */
   public Collection<Korisnik> getKorisniciCollection() {
      // TODO: implement
      return null;
   }

}