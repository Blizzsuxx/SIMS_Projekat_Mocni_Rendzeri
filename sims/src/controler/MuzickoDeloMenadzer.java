package controler;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.Izvodjac;
import model.MuzickoDelo;
import model.Zanr;

public class MuzickoDeloMenadzer {
	private ArrayList<MuzickoDelo> dela;
	

	public MuzickoDeloMenadzer() {
		super();
		this.dela = new ArrayList<MuzickoDelo>();
	}

	public MuzickoDeloMenadzer(IzvodjacMenadzer izvodjaci, ArrayList<Zanr> sviZanrovi, List<String[]> data){
		this();
		ucitajDela(izvodjaci, sviZanrovi, data);
	}


	public ArrayList<MuzickoDelo> getDela() {
		return dela;
	}

	public void setDela(ArrayList<MuzickoDelo> dela) {
		this.dela = dela;
	}

	private void ucitajDela(IzvodjacMenadzer izvodjaci, ArrayList<Zanr> sviZanrovi, List<String[]> data) {
				for  (String[] linije : data)
				{
					
					DateTimeFormatter df=DateTimeFormatter.ofPattern("dd.MM.yyyy.");
					LocalDate dan=LocalDate.parse(linije[2].trim(), df);
					Date d=new Date(dan.getYear(), dan.getMonthValue(), dan.getDayOfMonth());
					MuzickoDelo a = new MuzickoDelo(linije[0].trim(), linije[1].trim(), d,false );
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
					//sta je ovo??? -Dragan
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
	}


	public MuzickoDelo pronadiPoNazivu(String trim) {
		for(MuzickoDelo m:this.dela) {
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
			for(MuzickoDelo a:dela) {
				trazenIzv=null;
				for(Izvodjac izv:iz) {
					for(MuzickoDelo md: izv.getMuzickaDela()) {
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
