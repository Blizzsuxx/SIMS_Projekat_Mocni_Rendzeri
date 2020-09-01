package controler;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.Album;
import model.Izvodjac;
import model.MuzickoDelo;
import model.Urednik;

public class AlbumKontroler {
	private ArrayList<Album> sviAlbumi;

	public ArrayList<Album> getSviAlbumi() {
		return sviAlbumi;
	}

	public void setSviAlbumi(ArrayList<Album> sviAlbumi) {
		this.sviAlbumi = sviAlbumi;
	}

	public AlbumKontroler() {
		super();
		this.sviAlbumi = new ArrayList<Album>();
	}

	public AlbumKontroler(MuzickoDeloMenadzer muzickaDela, IzvodjacMenadzer izvodjaci, KorisniciMenadzer menadzerKorisnici, List<String[]> data){
		this();
		ucitaj(muzickaDela, izvodjaci, menadzerKorisnici, data);
	}

	private void ucitaj(MuzickoDeloMenadzer muzickaDela, IzvodjacMenadzer izvodjaci, KorisniciMenadzer menadzerKorisnici, List<String[]> data) {
		for(String[] linije : data){
					//izvodjac,urednik, dan,odobreno, naslov, izbrisi, dela....
					DateTimeFormatter df=DateTimeFormatter.ofPattern("dd.MM.yyyy.");
					LocalDate dan=LocalDate.parse(linije[2].trim(), df);
					Date dan1=new Date(dan.getYear(), dan.getMonthValue(), dan.getDayOfMonth());
					
					Urednik u= (Urednik) menadzerKorisnici.trazi(linije[1].trim());
					Izvodjac iz=izvodjaci.nadiPoUmetnickomImenu(linije[0].trim());
					
					Album a=new Album(linije[4].trim(), new ArrayList<MuzickoDelo>(), u, iz, dan1, false);
					
					if (linije[5].trim().equals("true")) {
					a.setIzbrisi(true);
					}else {
						 a.setIzbrisi(false);
					}
					if (linije[3].trim().equals("true")) {
						a.setOdobreno(true);
						}
					sviAlbumi.add( a);
					if(a.isOdobreno() && !a.isIzbrisi()) {
					a.getIzvodjac().getIzdatiAlbumi().add(a);
					
					}
					for(int i=6; i<linije.length-6; i++) {
						MuzickoDelo delo1=muzickaDela.pronadiPoNazivu( linije[i].trim());
						if(delo1!=null) {
						a.getListaPesama().add(delo1);}
					}
				}
		
	}

	public void sacuvaj() {
		PrintWriter pw=null;
		String sep=System.getProperty("file.separator");
		String putanja ="."+sep+"fajlovi"+sep+"zakazaneRecenzije.txt";
		try {
			
			pw=new PrintWriter(new FileWriter(putanja, false));
			for(Album a:sviAlbumi) {
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
