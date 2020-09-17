package kontroler;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import controler.UtisakMenadzer;
import model.MuzickoDelo;

public class UtisakMenadzerTest {
	public static UtisakMenadzer um = new UtisakMenadzer();
	
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
	public void testPronadiDelo() {
		Collection<MuzickoDelo> dela = new ArrayList<>();
		dela.add(new MuzickoDelo("a","b"));
		dela.add(new MuzickoDelo("c","b"));
		dela.add(new MuzickoDelo("d","b"));
		dela.add(new MuzickoDelo("b","b"));
		
		assertNull(um.pronadiDelo(dela, "t"));
		assertNotNull(um.pronadiDelo(dela, "a"));
	}

}
