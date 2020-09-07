package controler;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import model.Album;
import model.Grupa;
import model.Izvodjac;
import model.MuzickoDelo;
import model.Urednik;
import view.Slikovit;

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
	
	public void addAlbum(Album album) {
		for (Album a : sviAlbumi) {
			if (a.getNaslov().equals(album.getNaslov())) {
				return;
			}
		}
		sviAlbumi.add(album);
	}

	public AlbumKontroler(MuzickoDeloMenadzer muzickaDela, IzvodjacMenadzer izvodjaci, KorisniciMenadzer menadzerKorisnici, List<String[]> data) throws ParseException{
		this();
		ucitaj(izvodjaci, menadzerKorisnici, data);
	}

	private void ucitaj(IzvodjacMenadzer izvodjaci, KorisniciMenadzer menadzerKorisnici, List<String[]> data) throws ParseException {
		for(String[] linije : data){
					//izvodjac,urednik, dan,odobreno, naslov, izbrisi, dela....
					Izvodjac iz = izvodjaci.nadiPoUmetnickomImenu(linije[0].trim());
					Urednik u = (Urednik) menadzerKorisnici.trazi(linije[1].trim());
					
					Date datumRegistracije = new SimpleDateFormat("dd.MM.yyyy.").parse(linije[2].trim());
					boolean odobreno = Boolean.parseBoolean(linije[3].trim());
					String nazivAlbuma = linije[4].trim();
					boolean izbrisan = Boolean.parseBoolean(linije[5].trim());
					ArrayList<MuzickoDelo> muzickaDela = new ArrayList<MuzickoDelo>();
					String pesme = linije[6];
					String[] parts = pesme.split("\\|");
					for (int i = 0; i < parts.length; i++) {
						for (MuzickoDelo md : iz.getMuzickaDela()) {
							if (md.getNaziv().equals(parts[i])) {
								muzickaDela.add(md);
							}
						}
					}
					Album a = new Album(nazivAlbuma, muzickaDela, u, iz, datumRegistracije, odobreno);
					if (izbrisan) {
						a.setIzbrisi(true);
					}
					else {
						a.setIzbrisi(false);
					}
					if (odobreno) {
						a.setOdobreno(true);
					}
					sviAlbumi.add(a);
					if(a.isOdobreno() && !a.isIzbrisi()) {
						a.getIzvodjac().getIzdatiAlbumi().add(a);
					}
			}
	}

	public void sacuvaj() {
		PrintWriter pw=null;
		String sep=System.getProperty("file.separator");
		String putanja ="."+sep+"fajlovi"+sep+"albumi.txt";
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

	public Collection<? extends Slikovit> trazi(String textZaSearch) {
		// TODO Auto-generated method stub
		
		ArrayList<Slikovit> rezultat = new ArrayList<>();
		for(Album a : this.sviAlbumi) {
			if(a.Ime().contains(textZaSearch)) {
				rezultat.add(a);
			}
		}
		return rezultat;
	}
	
	
	

}
