package kontroler;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import controler.ZanroviMenadzer;
import model.Zanr;

public class ZanroviMenadzerTest {
	public static ZanroviMenadzer zanroviMenadzer = new ZanroviMenadzer();
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Zanr z1 = new Zanr("Rock", true);
		Zanr z2 = new Zanr("Pop", true);
		Zanr z3 = new Zanr("Dance", false);
		Zanr z4 = new Zanr("Tehno", true);
		
		zanroviMenadzer.getZanrovi().add(z1);
		zanroviMenadzer.getZanrovi().add(z2);
		zanroviMenadzer.getZanrovi().add(z3);
		zanroviMenadzer.getZanrovi().add(z4);
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
	public void testIzlistajSveZanrove() {
		assertEquals(4, zanroviMenadzer.izlistajSveZanrove().length);
	}

	@Test
	public void testTrazi() {
		assertNull(zanroviMenadzer.trazi("bla"));
		
		assertNotNull(zanroviMenadzer.trazi("Rock"));
	}
	
	@Test
	public void testVratiAktivneZanrove(){
		assertEquals(3, zanroviMenadzer.vratiAktivneZanrove().size());
	}
}
