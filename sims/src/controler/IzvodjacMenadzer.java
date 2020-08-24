package controler;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import model.Grupa;
import model.Izvodjac;
import model.Ocena;
import model.Pojedinacanizvodjac;
import model.Pol;
import model.Zanr;

public class IzvodjacMenadzer {
	private ArrayList<Grupa> grupe;
	private ArrayList<Pojedinacanizvodjac> solo;
	private ArrayList<Izvodjac> svi;
	
	

	public IzvodjacMenadzer() {
		super();
		this.grupe = new ArrayList<Grupa>();
		this.solo = new ArrayList<Pojedinacanizvodjac>();
		this.svi = new ArrayList<Izvodjac>();
	}



	public ArrayList<Grupa> getGrupe() {
		return grupe;
	}



	public void setGrupe(ArrayList<Grupa> grupe) {
		this.grupe = grupe;
	}



	public ArrayList<Pojedinacanizvodjac> getSolo() {
		return solo;
	}



	public void setSolo(ArrayList<Pojedinacanizvodjac> solo) {
		this.solo = solo;
	}



	public ArrayList<Izvodjac> getSvi() {
		return svi;
	}



	public void setSvi(ArrayList<Izvodjac> svi) {
		this.svi = svi;
	}



	public void ucitajIzvodjace(Collection<Grupa> grupe, Collection<Pojedinacanizvodjac> izvodjaciSolo) {
		//potrebno je i pratioce???
		String sep=System.getProperty("file.separator");
		String putanja ="."+sep+"fajlovi"+sep+"izvodjaci.txt";
			svi = new ArrayList<>();
			try {
				BufferedReader br = new BufferedReader(new FileReader(putanja));
				String linija = "";
				while ( (linija = br.readLine()) != null)
				{
					
					if (linija.trim().equals("")) {
					continue;}
					DateTimeFormatter df=DateTimeFormatter.ofPattern("dd.MM.yyyy.");
					
					String[] linije = linija.trim().split(";");
					if(linije.length==8){
						//poj
						Date smrt=null;
						LocalDate dan=LocalDate.parse(linije[4].trim(), df);
						Date rodjenje=new Date(dan.getYear(), dan.getMonthValue(), dan.getDayOfMonth());
						if(linije[5].trim().equals("/")) {
							
						}else {
						LocalDate dan2=LocalDate.parse(linije[5].trim(),df);
						smrt=new Date(dan2.getYear(), dan2.getMonthValue(), dan2.getDayOfMonth());
						}
						Pol p=Pol.zenski;
						if(linije[7].trim().equals("musko")) {p=Pol.muski;}
						Pojedinacanizvodjac a = new Pojedinacanizvodjac(linije[0].trim(), true, linije[2].trim(), linije[3].trim(), rodjenje, smrt,linije[6].trim(), p );
							if (linije[1].trim().equals("true")) {
							a.setStatus(true);
							}else {
								a.setStatus(false);
							}
							svi.add( a);
							this.getSolo().add(a);
					}else if(linije.length==5) {
						Date smrt=null;
						LocalDate dan=LocalDate.parse(linije[3].trim(), df);
						Date rodjenje=new Date(dan.getYear(), dan.getMonthValue(), dan.getDayOfMonth());
						if(linije[4].trim().equals("/")) {
							
						}else {
						LocalDate dan2=LocalDate.parse(linije[5].trim(),df);
						smrt=new Date(dan2.getYear(), dan2.getMonthValue(), dan2.getDayOfMonth());
						}
						Integer br1=Integer.parseInt(linije[2].trim());
						Grupa a = new Grupa(linije[0].trim(), true, br1, rodjenje, smrt );
							if (linije[1].trim().equals("true")) {
							a.setStatus(true);
							}else {
								a.setStatus(false);
							}
							svi.add( a);
							this.getGrupe().add(a);
						
					}else {
						System.out.println("Greska pri ucitavanju izvodjaca.");
					}
					
				}
			} catch (FileNotFoundException e) {
				System.out.println("Fajl nije pronadjen");
			} catch (IOException e) {
				System.out.println("Greska prilikom citanja fajla");
			}
	}



	public Izvodjac nadiPoUmetnickomImenu(String trim) {
		for(Izvodjac iz:this.getSvi()) {
			if(iz.getUmetnickoIme().equals(trim)) {
				return iz;
			}
		}
		return null;
	}



	public void sacuvaj() {
		PrintWriter pw=null;
		String sep=System.getProperty("file.separator");
		String putanja ="."+sep+"fajlovi"+sep+"izvodjaci.txt";
		try {
			
			pw=new PrintWriter(new FileWriter(putanja, false));
			for(Izvodjac a:svi) {
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
