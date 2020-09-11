package controler;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import model.Korisnik;
import model.Urednik;
import model.Zanr;
import view.TableModelWrapper;

public class ZanroviMenadzer { //ovu klasu ili treba da ima sesija ili da bude prosledjena instanca klase IzvestajViseIzvodjaca
	private ArrayList<Zanr> sviZanrovi;
	private KorisniciMenadzer korisnici;
	
	public ArrayList<Zanr> getSviZanrovi() {
		return sviZanrovi;
	}

	public void setSviZanrovi(ArrayList<Zanr> sviZanrovi) {
		this.sviZanrovi = sviZanrovi;
	}

	public ZanroviMenadzer(ArrayList<Zanr> sviZanrovi) {
		super();
		this.sviZanrovi = sviZanrovi;
	}

	public ZanroviMenadzer() {
		this.sviZanrovi = new ArrayList<Zanr>();
	}

	public ZanroviMenadzer(List<String[]> data, KorisniciMenadzer korisnici){
		ucitajZanrove(data);
		this.korisnici = korisnici;
		ucitajZanroveUrednike(); //
	}
	
	
	public String[] izlistajSveZanrove() {
		String[] imena= new String[sviZanrovi.size()];
		int i=0;
		for(Zanr z:sviZanrovi) {
			imena[i]=z.getNazivZanra();
					i++;
		}
		return imena;}


	public ArrayList<Zanr> getZanrovi() {
		
		return this.sviZanrovi;
	}

	private void ucitajZanrove(List<String[]> data) {
		this.sviZanrovi = new ArrayList<Zanr>();
		for (String[] linije : data) {
			
			Zanr a = new Zanr(linije[0].trim(), Boolean.parseBoolean(linije[1].trim()));
			sviZanrovi.add(a);
		}	
	}

	public void sacuvaj() {
		PrintWriter pw=null;
		String sep=System.getProperty("file.separator");
		String putanja ="."+sep+"fajlovi"+sep+"zanrovi.txt";
		try {
			
			pw=new PrintWriter(new FileWriter(putanja, false));
			for(Zanr a:sviZanrovi) {
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
	
	public TableModelWrapper getTabelaZanrova() {
		String[] columns = {"Naziv"};
		Class<?>[] columnTypes = { String.class};
		boolean[] editableColumns = { false};
		int[] columnWidths = {80};
		ArrayList<Object[]> data = new ArrayList<Object[]>();
		for (Zanr z : sviZanrovi) {
			if (z.isStatus())
				data.add(new Object[] {z.getNazivZanra()});
		}
		return new TableModelWrapper(columns, columnTypes, editableColumns, columnWidths, data);
	}
	
	public Zanr trazi(String trim) { // vraca sve i aktivne i neaktivne
		for(Zanr z:sviZanrovi) {
			if(trim.equals(z.getNazivZanra())) {
				return z;
			}
		}
			return null;
	}
	
	// pomocne funkcije
	public List<Zanr> vratiAktivneZanrove(){
		List<Zanr> zanrovi = new ArrayList<>();
		for (Zanr z: this.sviZanrovi)
			if (z.isStatus())
				zanrovi.add(z);
		return zanrovi;
	}
	
	// veza izmedju zanrova i urednika
	public void ucitajZanroveUrednike() {
		String putanja = "fajlovi" + System.getProperty("file.separator") + "uredniciZanrovi.txt";
		try {
			BufferedReader br = new BufferedReader(new FileReader(putanja));
			String linija = "";
			String[] tokeni;
			String[] pomocniTokeni;
			while((linija = br.readLine()) != null) { // uvijek ima bar jedan zanr pri upisu tako da ovo ne bi trebalo da izazove problem
				tokeni = linija.split("\\|");
				Urednik u = (Urednik) korisnici.trazi(tokeni[0].trim());
				pomocniTokeni = tokeni[1].trim().split(",");
				for (String nazivZanra: pomocniTokeni) {
					u.getZanrovi().add(trazi(nazivZanra));
				}
			}
			br.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void sacuvajZanroveUrednike() {
		String putanja = "fajlovi" + System.getProperty("file.separator") + "uredniciZanrovi.txt";
		StringBuilder sb = new StringBuilder();
		try {
			PrintWriter pw = new PrintWriter(new FileWriter(putanja, false));
			for (Korisnik k : korisnici.getKorisnici().values()) {
				if (k instanceof Urednik) {
					if (!((Urednik)k).getZanrovi().isEmpty()) { // ako nije prazna upisuj u fajl
						sb.append(String.format("%s|", k.getNalog().getKorisnickoIme()));
						for (Zanr z: ((Urednik)k).getZanrovi()) {
							sb.append(String.format("%s,", z.getNazivZanra()));
						}
						sb.append("\n");
						pw.print(sb.toString());
					}
					sb.setLength(0);
				}
			}
			pw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void pretrageUrednikaNaOsnovuZanrova(List<Korisnik> urednici, List<Zanr> zanrovi) {
		urednici.clear();
		for (Korisnik k : korisnici.vratiUrednike()) {
			for (Zanr z : ((Urednik)k).getZanrovi()){
				if (zanrovi.contains(z)) {
					urednici.add(k);
					break;
				}
			}
		}
	}
}
