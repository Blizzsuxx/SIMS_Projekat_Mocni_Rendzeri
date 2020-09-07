package controler;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import model.FrontEndKorisnik;
import model.KorisnikAplikacije;
import model.MuzickoDelo;
import model.Ocena;
import model.Urednik;

public class OceneKontroler {
	private ArrayList<Ocena> sveOcene;
	

	public OceneKontroler() {
		super();
		this.sveOcene = new ArrayList<Ocena>();
	}

	public OceneKontroler(MuzickoDeloMenadzer muzickaDela, KorisniciMenadzer korisnici, List<String[]> data){
		this();
		ucitaj(muzickaDela, korisnici, data);
	}


	public ArrayList<Ocena> getSveOcene() {
		return sveOcene;
	}


	public void setSveOcene(ArrayList<Ocena> sveOcene) {
		this.sveOcene = sveOcene;
	}


	public void ucitaj(MuzickoDeloMenadzer muzickaDela, KorisniciMenadzer korisnici, List<String[]> data) {
		//ocena, korisnik, delo
		
		for(String[] linije : data){
			//System.out.print(linije[2]);
					FrontEndKorisnik fKorisnik= (FrontEndKorisnik) korisnici.trazi(linije[2].trim());
					MuzickoDelo delo=muzickaDela.pronadiPoNazivu(linije[1].trim());
					Ocena a = new Ocena(Float.parseFloat(linije[0].trim()),fKorisnik);
					a.delo=delo;
					if(fKorisnik instanceof Urednik) {
						if(delo.getDosadasnjeOceneKorisnika()==null) {
							delo.setDosadasnjeOceneKorisnika(new ArrayList<Integer>());
						}
						delo.dodajocenuUrednika((int)a.getOcena());
					}
					else if(fKorisnik instanceof KorisnikAplikacije) {
						if(delo.getDosadasnjeOceneUrednika()==null) {
							delo.setDosadasnjeOceneUrednika(new ArrayList<Integer>());
						}
						delo.dodajocenuKorisnika((int)a.getOcena());
					}
					sveOcene.add( a);
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
