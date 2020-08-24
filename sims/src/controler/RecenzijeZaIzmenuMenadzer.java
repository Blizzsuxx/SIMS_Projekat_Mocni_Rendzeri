package controler;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import model.Korisnik;
import model.Nalog;
import model.Ocena;
import model.Recenzija;
import model.RecezijaZaIzmenu;

public class RecenzijeZaIzmenuMenadzer {
	private ArrayList<RecezijaZaIzmenu> sveizmene;

	public ArrayList<RecezijaZaIzmenu> getSveizmene() {
		return sveizmene;
	}

	public void setSveizmene(ArrayList<RecezijaZaIzmenu> sveizmene) {
		this.sveizmene = sveizmene;
	}

	public RecenzijeZaIzmenuMenadzer() {
		super();
		this.sveizmene = new ArrayList<RecezijaZaIzmenu>();
	}

	public void ucitaj(Collection<Recenzija> recenzije) {
		String sep=System.getProperty("file.separator");
		String putanja ="."+sep+"fajlovi"+sep+"recenzijeZaIzmenu.txt";
			sveizmene = new ArrayList<>();
			try {
				BufferedReader br = new BufferedReader(new FileReader(putanja));
				String linija = "";
				while ( (linija = br.readLine()) != null)
				{
					
					if (linija.trim().equals("")) {
					continue;}
					
					String[] linije = linija.trim().split(";");
					boolean tf=false;
					if (linije[0].trim().equals("true")) {
						tf=true;
					}
					boolean tf1=false;
					if(linije[1].trim().equals("true")) {tf1=true;}
					boolean tf2=false;
					if(linije[3].trim().equals("true")) {tf2=true;}
					Recenzija nova=null;
					for(Recenzija r:recenzije) {
						if(r.getNaslov().equals(linije[2].trim())) {
							nova=r;
						}
					}
					RecezijaZaIzmenu a = new RecezijaZaIzmenu(tf, tf1, nova, tf2, linije[4].trim());
					
					sveizmene.add( a);
				}
				br.close();
			} catch (FileNotFoundException e) {
				System.out.println("Fajl nije pronadjen");
			} catch (IOException e) {
				System.out.println("Greska prilikom citanja fajla");
			}catch (NumberFormatException ignoreException) {
				System.out.println("Greska prilikom citanja fajla");
				ignoreException.printStackTrace();
			}catch(DateTimeException e) {
				System.out.println("Greska prilikom citanja datuma");
				
			};
		
	}

	public void sacuvaj() {
		PrintWriter pw=null;
		String sep=System.getProperty("file.separator");
		String putanja ="."+sep+"fajlovi"+sep+"recenzijeZaIzmenu.txt";
		try {
			
			pw=new PrintWriter(new FileWriter(putanja, false));
			for(RecezijaZaIzmenu a:this.sveizmene) {
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
