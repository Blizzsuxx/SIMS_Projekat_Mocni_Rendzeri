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
import model.Urednik;
import model.ZakazanaRecenzija;
import view.TableModelWrapper;

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
					LocalDate dan=LocalDate.parse(linije[1].trim(), df);
					@SuppressWarnings("deprecation")
					Date d=new Date(dan.getYear(), dan.getMonthValue(), dan.getDayOfMonth());
					
					LocalDate rok=LocalDate.parse(linije[2].trim(), df);
					@SuppressWarnings("deprecation")
					Date rok2=new Date(rok.getYear(), rok.getMonthValue(), rok.getDayOfMonth());
					Recenzija r=pronadiRecenziju(linije[4].trim(), recenzije);
					if(r!=null) {
					ZakazanaRecenzija a = new ZakazanaRecenzija(d,linije[0].trim(), false,rok2, r, r.getUrednik() );
					if (linije[5].trim().equals("true")) {
					a.setUradeno(true);
					}
					sve.add( a);}
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
	
	public TableModelWrapper getTabelaZavrsenihRecenzija(boolean zavrsene)  throws Exception { 
		String[] columns = { "Naziv" ,"Opis", "Rok"};
		Class<?>[] columnTypes = { String.class, String.class, Date.class};
		boolean[] editableColumns = { false, false, false};
		int[] columnWidths = { 120, 120, 100};
		ArrayList<Object[]> data = new ArrayList<Object[]>();
		for (ZakazanaRecenzija zr : sve) {
			if (zavrsene && zr.isUradeno())
				data.add(new Object[] {zr.getRecenzija().getNaslov(), zr.getOpis(), zr.getRok()});
			if (!zavrsene && !zr.isUradeno()) {
				data.add(new Object[] {zr.getRecenzija().getNaslov(), zr.getOpis(), zr.getRok()});
			}
		}
		return new TableModelWrapper(columns, columnTypes, editableColumns, columnWidths, data);
	}
	
	
	public TableModelWrapper getTabelaZakazanihRecenzijaZaUrednika(Urednik urednik)  throws Exception { 
		String[] columns = { "Naziv" ,"Opis", "Datum zakazivanja", "Rok"};
		Class<?>[] columnTypes = { String.class, String.class, Date.class, Date.class};
		boolean[] editableColumns = { false, false, false, false};
		int[] columnWidths = { 120, 120, 100, 80};
		ArrayList<Object[]> data = new ArrayList<Object[]>();
		for (ZakazanaRecenzija zr : urednik.getZakazaneRecenzije()) {
			if (!zr.isUradeno())
				data.add(new Object[] {zr.getRecenzija().getNaslov(), zr.getOpis(), zr.getRok()});
		}
		return new TableModelWrapper(columns, columnTypes, editableColumns, columnWidths, data);
	}
}
