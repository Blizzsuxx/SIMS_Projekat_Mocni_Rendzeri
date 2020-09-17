package kontroler;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import controler.KorisniciMenadzer;
import model.Administrator;
import model.Korisnik;
import model.KorisnikAplikacije;
import model.Nalog;
import model.Urednik;

public class KorisniciMenadzerTest {
	public static KorisniciMenadzer km = new KorisniciMenadzer();
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Korisnik k1 = new Administrator();
		k1.setNalog(new Nalog()); k1.getNalog().setKorisnickoIme("admin 1"); k1.setStatus(true);
		
		Korisnik k2 = new Urednik();
		k2.setNalog(new Nalog()); k2.getNalog().setKorisnickoIme("urednik 1"); k2.setStatus(true);
		
		Korisnik k3 = new KorisnikAplikacije();
		k3.setNalog(new Nalog()); k3.getNalog().setKorisnickoIme("obican 1"); k3.setStatus(true);
		
		km.getKorisnici().put(k1.getNalog().getKorisnickoIme(), k1);
		km.getKorisnici().put(k2.getNalog().getKorisnickoIme(), k2);
		km.getKorisnici().put(k3.getNalog().getKorisnickoIme(), k3);
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
	public void testTrazi() {
		assertEquals(km.getKorisnici().get("admin 1"), km.trazi("admin 1"));
	}

	@Test
	public void testVratiSveAktivneKorisnike() {
		assertEquals(3, km.vratiSveAktivneKorisnike().size());
	}
	
	@Test
	public void testTraziZaSearch() {
		assertEquals(1, km.traziZaSearch("ur").size());
		
		assertEquals(0, km.traziZaSearch("t").size());
		
		assertEquals(0, km.traziZaSearch("p").size());
	}
}
