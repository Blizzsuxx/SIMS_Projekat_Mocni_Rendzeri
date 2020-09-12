package application;

import java.awt.Color;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.UIManager;

import controler.CitacDatoteka;
import controler.IzvestajSvihIzvodjacaMenadzer;
import controler.LoginMenadzer;
import model.IzvestajSvihZanrova;
import model.Recenzija;
import model.Sesija;
import view.IzvestajRecenzije;
import view.IzvestajUrednika;
import view.IzvestajViseIzvodjaca;
import view.IzvestajViseUrednika;
import view.IzvestajViseZanrova;
import view.IzvestajZanra;

public class Main {
	

	public static void main(String[] args) throws ParseException, IOException {
		// TODO Auto-generated method stub
		
		
		initUI();
		
		
		
		
		CitacDatoteka datoteke = new CitacDatoteka();
		
		datoteke.inicijalizuj();
		
		
		//LoginMenadzer login = new LoginMenadzer(datoteke);
		//login.uloguj();
		Sesija s=Sesija.namestiSesiju(datoteke.getKorisnici().trazi("pera1"), datoteke,null);
		
		IzvestajZanra iz=new IzvestajZanra(s, s.getZanroviMenadzer().getSviZanrovi().get(0));
		
		iz.setVisible(true);
	}

	private static void initUI() {
		// TODO Auto-generated method stub
		UIManager.put( "control", new Color( 128, 128, 128) );
		  UIManager.put( "info", new Color(128,128,128) );
		  UIManager.put( "nimbusBase", new Color( 18, 30, 49) );
		  UIManager.put( "nimbusAlertYellow", new Color( 248, 187, 0) );
		  UIManager.put( "nimbusDisabledText", new Color( 128, 128, 128) );
		  UIManager.put( "nimbusFocus", new Color(115,164,209) );
		  UIManager.put( "nimbusGreen", new Color(176,179,50) );
		  UIManager.put( "nimbusInfoBlue", new Color( 66, 139, 221) );
		  UIManager.put( "nimbusLightBackground", new Color( 18, 30, 49) );
		  UIManager.put( "nimbusOrange", new Color(191,98,4) );
		  UIManager.put( "nimbusRed", new Color(169,46,34) );
		  UIManager.put( "nimbusSelectedText", new Color( 255, 255, 255) );
		  UIManager.put( "nimbusSelectionBackground", new Color( 104, 93, 156) );
		  UIManager.put( "text", new Color( 230, 230, 230) );
		  try {
		    for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
		      if ("Nimbus".equals(info.getName())) {
		          javax.swing.UIManager.setLookAndFeel(info.getClassName());
		          break;
		      }
		    }
		  } catch (ClassNotFoundException e) {
		    e.printStackTrace();
		  } catch (InstantiationException e) {
		    e.printStackTrace();
		  } catch (IllegalAccessException e) {
		    e.printStackTrace();
		  } catch (javax.swing.UnsupportedLookAndFeelException e) {
		    e.printStackTrace();
		  } catch (Exception e) {
		    e.printStackTrace();
		  }
		  // Show your JFrame
		}

}
