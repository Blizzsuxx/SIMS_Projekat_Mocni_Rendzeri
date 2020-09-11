package controler;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.Clan;
import model.Grupa;
import model.Izvodjac;
import model.Pojedinacanizvodjac;

public class ClanoviMenadzer {
	private ArrayList<Clan> sviClanovi;

	public ArrayList<Clan> getSviClanovi() {
		return sviClanovi;
	}

	public void setSviClanovi(ArrayList<Clan> sviClanovi) {
		this.sviClanovi = sviClanovi;
	}

	public ClanoviMenadzer() {
		super();
		this.sviClanovi = new ArrayList<Clan>();
	}

	public ClanoviMenadzer(IzvodjacMenadzer izvodjaci, ArrayList<Grupa> grupe, List<String[]> data){
		this();
		ucitaj(izvodjaci, grupe, data);
	}

	private void ucitaj(IzvodjacMenadzer izvodjaci, ArrayList<Grupa> grupe, List<String[]> data) {
		for(String[] linije : data){
			SimpleDateFormat df= Constants.NATASIN_FORMAT_ZA_DATUM;
			Date datum=null;
			try {
				datum = df.parse(linije[0].trim());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
					
					Date izlazak=null;
					if(!linije[1].trim().equals("/")) {
						try {
							datum = df.parse(linije[1].trim());
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					Izvodjac iz=izvodjaci.nadiPoUmetnickomImenu(linije[2].trim());
					Clan a = new Clan(datum, izlazak, (Pojedinacanizvodjac)iz);
					Grupa g=(Grupa)izvodjaci.nadiPoUmetnickomImenu(linije[3].trim());
					g.getClanovi().add(a);
					a.setGrupa(g);
					((Pojedinacanizvodjac)iz).getClanstvaUGrupama().add(a);
					
					sviClanovi.add( a);
				}
		
	}

	public void sacuvaj() {
		PrintWriter pw=null;
		String sep=System.getProperty("file.separator");
		String putanja ="."+sep+"fajlovi"+sep+"clanstva.txt";
		try {
			
			pw=new PrintWriter(new FileWriter(putanja, false));
			for(Clan a:sviClanovi) {
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
