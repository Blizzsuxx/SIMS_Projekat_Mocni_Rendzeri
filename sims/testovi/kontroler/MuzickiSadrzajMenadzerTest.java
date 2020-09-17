package kontroler;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import controler.MuzickiSadrzajMenadzer;
import model.Album;
import model.MuzickiSadrzaj;
import model.MuzickoDelo;

public class MuzickiSadrzajMenadzerTest {
	public static MuzickiSadrzajMenadzer muzickiSadrzajMenadzer = new MuzickiSadrzajMenadzer();
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		MuzickiSadrzaj ms1 = new MuzickoDelo("delo 1", "");
		MuzickiSadrzaj ms2 = new MuzickoDelo("delo 2", "");
		MuzickiSadrzaj ms3 = new MuzickoDelo("delo 3", "");
		
		muzickiSadrzajMenadzer.getMuzickaDela().add((MuzickoDelo) ms1);
		muzickiSadrzajMenadzer.getMuzickaDela().add((MuzickoDelo) ms2);
		muzickiSadrzajMenadzer.getMuzickaDela().add((MuzickoDelo) ms3);
		muzickiSadrzajMenadzer.getMuzickiSadrzaj().add(ms1);
		muzickiSadrzajMenadzer.getMuzickiSadrzaj().add(ms2);
		muzickiSadrzajMenadzer.getMuzickiSadrzaj().add(ms3);
		
		MuzickiSadrzaj a1 = new Album(); a1.setNaslov("album 1"); ((Album)a1).setOdobreno(true);
		MuzickiSadrzaj a2 = new Album(); a2.setNaslov("album 2"); ((Album)a2).setOdobreno(true);
		MuzickiSadrzaj a3 = new Album(); a3.setNaslov("album 3"); ((Album)a3).setOdobreno(true);
		muzickiSadrzajMenadzer.getAlbumi().add((Album)a1);
		muzickiSadrzajMenadzer.getAlbumi().add((Album)a1);
		muzickiSadrzajMenadzer.getAlbumi().add((Album)a1);
		muzickiSadrzajMenadzer.getMuzickiSadrzaj().add(a1);
		muzickiSadrzajMenadzer.getMuzickiSadrzaj().add(a2);
		muzickiSadrzajMenadzer.getMuzickiSadrzaj().add(a3);
		
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
	public void testVratiNaOsnovuNazive() {
		assertNotNull(muzickiSadrzajMenadzer.vratiNaOsnovuNazive("album 1"));
		assertNull(muzickiSadrzajMenadzer.vratiNaOsnovuNazive("nesto"));
	}

	@Test
	public void testVratiAlbumeNaOsnovuOdobrenosti() {
		assertEquals(3, muzickiSadrzajMenadzer.vratiAlbumeNaOsnovuOdobrenosti(true).size());
		assertEquals(0, muzickiSadrzajMenadzer.vratiAlbumeNaOsnovuOdobrenosti(false).size());
	}
	
	@Test
	public void testVratiAktivanMuzickiSadrzaj() {
		assertEquals(0, muzickiSadrzajMenadzer.vratiAktivanMuzickiSadrzaj().size());
	}
	
	@Test
	public void testTraziAlbume() {
		assertEquals(3, muzickiSadrzajMenadzer.traziAlbume("al").size());
		assertEquals(0, muzickiSadrzajMenadzer.traziAlbume("beta").size());
	}
	
	@Test
	public void testTraziMuzickaDela() {
		assertEquals(3, muzickiSadrzajMenadzer.traziMuzickaDela("del").size());
		assertEquals(0, muzickiSadrzajMenadzer.traziMuzickaDela("beta").size());
	}
}
