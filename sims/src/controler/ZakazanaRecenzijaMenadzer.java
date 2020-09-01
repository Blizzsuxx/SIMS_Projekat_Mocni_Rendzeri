package controler;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.Recenzija;
import model.ZakazanaRecenzija;

public class ZakazanaRecenzijaMenadzer {
	private ArrayList<ZakazanaRecenzija> sve;
	

	public ZakazanaRecenzijaMenadzer() {
		super();
		this.sve = new ArrayList<ZakazanaRecenzija>();
	}

	public ZakazanaRecenzijaMenadzer(KorisniciMenadzer korisnici,ArrayList<Recenzija> recenzije, List<String[]> data) {
		this();
		ucitajZakazane(korisnici, recenzije, data);

	}

	public ArrayList<ZakazanaRecenzija> getSve() {
		return sve;
	}


	public void setSve(ArrayList<ZakazanaRecenzija> sve) {
		this.sve = sve;
	}


	private void ucitajZakazane(KorisniciMenadzer korisnici,ArrayList<Recenzija> recenzije, List<String[]> data) {
		
					for(String[] linije : data){
					DateTimeFormatter df=DateTimeFormatter.ofPattern("dd.MM.yyyy.");
					LocalDate dan=LocalDate.parse(linije[2].trim(), df);
					Date d=new Date(dan.getYear(), dan.getMonthValue(), dan.getDayOfMonth());
					
					LocalDate rok=LocalDate.parse(linije[3].trim(), df);
					Date rok2=new Date(rok.getYear(), rok.getMonthValue(), rok.getDayOfMonth());
					Recenzija r=pronadiRecenziju(linije[4].trim(), recenzije);
					ZakazanaRecenzija a = new ZakazanaRecenzija(d,linije[0].trim(), false,rok2, r, r.getUrednik() );
					if (linije[1].trim().equals("true")) {
					a.setUradeno(true);
					}
					sve.add( a);
				}
		
	}


	private Recenzija pronadiRecenziju(String trim, ArrayList<Recenzija> recenzije) {
		for(Recenzija r:recenzije) {
			if(r.getNaslov().equals(trim)) {return r;}
		}
		return null;
	}


	public void sacuvaj() {
		PrintWriter pw=null;
		String sep=System.getProperty("file.separator");
		String putanja ="."+sep+"fajlovi"+sep+"zakazaneRecenzije.txt";
		try {
			
			pw=new PrintWriter(new FileWriter(putanja, false));
			for(ZakazanaRecenzija a:sve) {
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
