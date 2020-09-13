package controler;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class ReklameMenadzer {
	private String putanjaPrveReklame;
	private String putanjaDrugeReklame;
	private String putanja;
	
	public ReklameMenadzer() {	
	}
	
	public ReklameMenadzer(String putanjaPrveReklame, String putanjaDrugeReklame) {
		this();
		this.putanjaPrveReklame = putanjaPrveReklame;
		this.putanjaDrugeReklame = putanjaDrugeReklame;
	}

	public ReklameMenadzer(String putanja) {
		this.putanja = putanja;
		this.ucitaj();
	}
	
	public void ucitaj() {
		try {
			BufferedReader br = new BufferedReader(new FileReader(putanja));
			this.putanjaPrveReklame = br.readLine().trim();
			this.putanjaDrugeReklame = br.readLine().trim();
			br.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void sacuvaj() {
		try {
			PrintWriter pw = new PrintWriter(new FileWriter(putanja));
			pw.print(toFile());
			pw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String toFile() {
		return String.format("%s\n%s", this.putanjaPrveReklame, this.putanjaDrugeReklame);
	}

	public String getPutanjaPrveReklame() {
		return putanjaPrveReklame;
	}

	public void setPutanjaPrveReklame(String putanjaPrveReklame) {
		this.putanjaPrveReklame = putanjaPrveReklame;
	}

	public String getPutanjaDrugeReklame() {
		return putanjaDrugeReklame;
	}

	public void setPutanjaDrugeReklame(String putanjaDrugeReklame) {
		this.putanjaDrugeReklame = putanjaDrugeReklame;
	}
	
	
}
