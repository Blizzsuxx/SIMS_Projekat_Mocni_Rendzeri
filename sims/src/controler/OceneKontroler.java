package controler;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;

import model.FrontEndKorisnik;
import model.KorisnikAplikacije;
import model.MuzickoDjelo;
import model.Ocena;
import model.Urednik;
import model.ZakazanaRecenzija;
import model.Zanr;

public class OceneKontroler {
	private ArrayList<Ocena> sveOcene;
	

	public OceneKontroler() {
		super();
		this.sveOcene = new ArrayList<Ocena>();
	}


	public ArrayList<Ocena> getSveOcene() {
		return sveOcene;
	}


	public void setSveOcene(ArrayList<Ocena> sveOcene) {
		this.sveOcene = sveOcene;
	}


	public void ucitaj(MuzickoDeloMenadzer muzickaDela, KorisniciMenadzer korisnici) {
		//ocena, korisnik, delo
		
		String sep=System.getProperty("file.separator");
		String putanja ="."+sep+"fajlovi"+sep+"ocene.txt";
			sveOcene = new ArrayList<>();
			try {
				BufferedReader br = new BufferedReader(new FileReader(putanja));
				String linija = "";
				while ( (linija = br.readLine()) != null)
				{
					
					if (linija.trim().equals("")) {
					continue;}
					
					String[] linije = linija.trim().split(",");
					FrontEndKorisnik fKorisnik=korisnici.nadiPoSifri(linije[2].trim());
					MuzickoDjelo delo=muzickaDela.pronadiPoNazivu(linije[1].trim());
					Ocena a = new Ocena(Float.parseFloat(linije[0].trim()),fKorisnik);
					a.delo=delo;
					if(fKorisnik instanceof Urednik) {
						delo.dodajocenuUrednika((int)a.getOcena());
					}
					else if(fKorisnik instanceof KorisnikAplikacije) {
						delo.dodajocenuKorisnika((int)a.getOcena());
					}
					sveOcene.add( a);
				}
			} catch (FileNotFoundException e) {
				System.out.println("Fajl nije pronadjen");
			} catch (IOException e) {
				System.out.println("Greska prilikom citanja fajla");
			}
		
	}


	public void sacuvaj() {
		PrintWriter pw=null;
		String sep=System.getProperty("file.separator");
		String putanja ="."+sep+"fajlovi"+sep+"ocene.txt";
		try {
			
			pw=new PrintWriter(new FileWriter(putanja, false));
			for(Ocena a:sveOcene) {
				pw.println(a.toFileString());
				
			}pw.close();
			
		}catch(IOException e) {
			e.printStackTrace();
		}finally {
			if(pw!=null) {
				pw.close();
			}
		}
	}

}
