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

import model.Izvodjac;
import model.MuzickoDjelo;
import model.Ocena;
import model.Zanr;

public class MuzickoDeloMenadzer {
	private ArrayList<MuzickoDjelo> dela;
	

	public MuzickoDeloMenadzer() {
		super();
		this.dela = new ArrayList<MuzickoDjelo>();
	}


	public ArrayList<MuzickoDjelo> getDela() {
		return dela;
	}

	public void setDela(ArrayList<MuzickoDjelo> dela) {
		this.dela = dela;
	}

	public Collection<MuzickoDjelo> ucitajDela(IzvodjacMenadzer izvodjaci, ArrayList<Zanr> sviZanrovi) {
		String sep=System.getProperty("file.separator");
		String putanja ="."+sep+"fajlovi"+sep+"muzickaDela.txt";
			dela = new ArrayList<>();
			try {
				BufferedReader br = new BufferedReader(new FileReader(putanja));
				String linija = "";
				while ( (linija = br.readLine()) != null)
				{
					
					if (linija.trim().equals("")) {
					continue;}
					
					String[] linije = linija.trim().split(";");
					DateTimeFormatter df=DateTimeFormatter.ofPattern("dd.MM.yyyy.");
					LocalDate dan=LocalDate.parse(linije[2].trim(), df);
					Date d=new Date(dan.getYear(), dan.getMonthValue(), dan.getDayOfMonth());
					MuzickoDjelo a = new MuzickoDjelo(linije[0].trim(), linije[1].trim(), d,false );
					if (linije[3].trim().equals("true")) {
					a.setStatus(true);
					}else {
						a.setStatus(false);
					}
					String imeIzvodjaca=linije[4].trim();
					for(Izvodjac i:izvodjaci.getSvi()) {
						if(i.getUmetnickoIme().equals(imeIzvodjaca)) {
							i.getMuzickaDela().add(a);
						}
					}
					for(int i=5; i<linije.length-5;i++) {
						String naziv=linije[i].trim();
						for(Zanr z:sviZanrovi) {
							if(z.getNazivZanra().equals(naziv)) {
								a.getZanrovi().add(z);
								break;
							}
						}
					}
					dela.add( a);
				}
			} catch (FileNotFoundException e) {
				System.out.println("Fajl nije pronadjen");
			} catch (IOException e) {
				System.out.println("Greska prilikom citanja fajla");
			}
		
		return dela;
	}


	public MuzickoDjelo pronadiPoNazivu(String trim) {
		for(MuzickoDjelo m:this.dela) {
			if(m.getNaziv().equals(trim)) {
				return m;
			}
		}
		return null;
	}


	public void sacuvaj(ArrayList<Izvodjac> iz ) {
		PrintWriter pw=null;
		String sep=System.getProperty("file.separator");
		String putanja ="."+sep+"fajlovi"+sep+"muzickaDela.txt";
		try {
			
			pw=new PrintWriter(new FileWriter(putanja, false));
			Izvodjac trazenIzv=null;
			for(MuzickoDjelo a:dela) {
				trazenIzv=null;
				for(Izvodjac izv:iz) {
					for(MuzickoDjelo md: izv.getMuzickaDela()) {
						if(md.getNaziv()==a.getNaziv()) {
							trazenIzv=izv;
							break;
						}
					}
					if(trazenIzv!=null) {
						break;
					}
				}
				pw.println(a.toFileString(trazenIzv));
				
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
