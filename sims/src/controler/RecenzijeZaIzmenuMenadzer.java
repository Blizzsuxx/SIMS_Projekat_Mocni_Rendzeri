package controler;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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

	public RecenzijeZaIzmenuMenadzer(Collection<Recenzija> recenzije, List<String[]> data){
		this();
		ucitaj(recenzije, data);
	}

	private void ucitaj(Collection<Recenzija> recenzije, List<String[]> data) {
		for(String[] linije : data){
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
