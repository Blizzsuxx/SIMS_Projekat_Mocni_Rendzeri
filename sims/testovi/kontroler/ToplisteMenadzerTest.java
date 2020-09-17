package kontroler;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import controler.ToplisteMenadzer;
import model.Korisnik;
import model.Nalog;
import model.TopLista;
import model.Urednik;

public class ToplisteMenadzerTest {
	public static ToplisteMenadzer tm = new ToplisteMenadzer();
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Korisnik k1 = new Urednik();
		k1.setNalog(new Nalog());
		k1.getNalog().setKorisnickoIme("pera");
		
		Korisnik k2 = new Urednik();
		k2.setNalog(new Nalog());
		k2.getNalog().setKorisnickoIme("mico");
		
		TopLista t1 = new TopLista("mix 2020", true, k1);
		TopLista t2 = new TopLista("mix 2019", false, k1);
		TopLista t3 = new TopLista("mix", true, k2);
		
		tm.getTopListe().add(t1);
		tm.getTopListe().add(t2);
		tm.getTopListe().add(t3);
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
	public void testTopListeKorisnika() {
		assertEquals(1, tm.topListeKorisnika("pera").size());
		assertEquals(1, tm.topListeKorisnika("mico").size());
		assertEquals(0, tm.topListeKorisnika("m").size());
	}

	@Test
	public void testVratiTopListu() {
		assertNotNull(tm.vratiTopListu("mix 2020", "pera"));
		assertNull(tm.vratiTopListu("mix 2020", "mico"));
	}
	
	@Test
	public void testVratiTopListuNaOsnovuImena() {
		assertNotNull(tm.vratiTopListuNaOsnovuImena("mix 2020"));
		assertNull(tm.vratiTopListuNaOsnovuImena("nesto"));
	}
	
	@Test
	public void testVratiTopListeUrednika() {
		assertEquals(2, tm.vratiTopListeUrednika().size());
	}
}
