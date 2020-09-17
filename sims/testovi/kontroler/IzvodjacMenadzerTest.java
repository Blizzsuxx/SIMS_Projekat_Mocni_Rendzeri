package kontroler;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import controler.IzvodjacMenadzer;
import model.Grupa;
import model.Izvodjac;
import model.Pojedinacanizvodjac;

public class IzvodjacMenadzerTest {
	public static IzvodjacMenadzer izvodjacMenadzer = new IzvodjacMenadzer();
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Izvodjac i1 = new Pojedinacanizvodjac(); i1.setStatus(true); i1.setUmetnickoIme("i1"); i1.setOdobrenost(true);
		Izvodjac i2 = new Pojedinacanizvodjac(); i2.setStatus(true); i2.setUmetnickoIme("i2"); i2.setOdobrenost(true);
		Izvodjac i3 = new Pojedinacanizvodjac(); i3.setStatus(true); i3.setUmetnickoIme("i3"); i3.setOdobrenost(true);
		
		Izvodjac g1 = new Grupa(); g1.setStatus(true); g1.setUmetnickoIme("g1"); g1.setOdobrenost(true);
		Izvodjac g2 = new Grupa(); g2.setStatus(true); g2.setUmetnickoIme("g2"); g2.setOdobrenost(true);
		Izvodjac g3 = new Grupa(); g3.setStatus(true); g3.setUmetnickoIme("g3"); g3.setOdobrenost(true);
		
		izvodjacMenadzer.getSvi().add(i1);
		izvodjacMenadzer.getSvi().add(i2);
		izvodjacMenadzer.getSvi().add(i3);
		izvodjacMenadzer.getSolo().add((Pojedinacanizvodjac) i1);
		izvodjacMenadzer.getSolo().add((Pojedinacanizvodjac) i2);
		izvodjacMenadzer.getSolo().add((Pojedinacanizvodjac) i3);
		
		izvodjacMenadzer.getSvi().add(g1);
		izvodjacMenadzer.getSvi().add(g2);
		izvodjacMenadzer.getSvi().add(g3);
		izvodjacMenadzer.getGrupe().add((Grupa) g1);
		izvodjacMenadzer.getGrupe().add((Grupa) g2);
		izvodjacMenadzer.getGrupe().add((Grupa) g3);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testNadiPoUmetnickomImenu() {
		assertNotNull(izvodjacMenadzer.nadiPoUmetnickomImenu("i1"));
		
		assertNull(izvodjacMenadzer.nadiPoUmetnickomImenu("sddsa"));
		
		assertNotNull(izvodjacMenadzer.nadiPoUmetnickomImenu("g2"));
	}

	@Test
	public void testTraziGrupe() {
		assertEquals(3, izvodjacMenadzer.traziGrupe("g").size());
		
		assertEquals(1, izvodjacMenadzer.traziGrupe("1").size());
		
		assertEquals(0, izvodjacMenadzer.traziGrupe("dsa").size());
	}
	
	@Test
	public void testTraziSoloIzvodjace() {
		assertEquals(3, izvodjacMenadzer.traziSoloIzvodjace("i").size());
		
		assertEquals(1, izvodjacMenadzer.traziSoloIzvodjace("2").size());
		
		assertEquals(0, izvodjacMenadzer.traziSoloIzvodjace("dsa").size());
	}
	
	@Test
	public void testVratiIzvodjaceNaOsnovuOdobrenosti() {
		assertEquals(6, izvodjacMenadzer.vratiIzvodjaceNaOsnovuOdobrenosti(true).size());
		
		assertEquals(0, izvodjacMenadzer.vratiIzvodjaceNaOsnovuOdobrenosti(false).size());
	}
}
