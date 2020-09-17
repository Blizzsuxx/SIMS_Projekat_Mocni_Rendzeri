package kontroler;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import controler.ZakazanaRecenzijaMenadzer;
import model.Recenzija;

public class ZakazaneRecenzijaMenadzerTest {
	public static ZakazanaRecenzijaMenadzer zakazanaRecenzijaMenadzer = new ZakazanaRecenzijaMenadzer();
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
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
	public void testPronadiRecenziju() {
		List<Recenzija> recenzije = new ArrayList<>();
		recenzije.add(new Recenzija(null, "test 1"));
		recenzije.add(new Recenzija(null, "test 2"));
		recenzije.add(new Recenzija(null, "test 3"));
		
		assertNotNull(zakazanaRecenzijaMenadzer.pronadiRecenziju("test 1", (ArrayList<Recenzija>) recenzije));
		assertNull(zakazanaRecenzijaMenadzer.pronadiRecenziju("bla", (ArrayList<Recenzija>) recenzije));
	}

}
