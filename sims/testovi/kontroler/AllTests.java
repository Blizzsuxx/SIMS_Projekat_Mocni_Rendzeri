package kontroler;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)

@Suite.SuiteClasses({
	   IzvodjacMenadzerTest.class,
	   KorisniciMenadzerTest.class,
	   MuzickiSadrzajMenadzerTest.class,
	   ToplisteMenadzerTest.class,
	   UtisakMenadzerTest.class,
	   ZakazaneRecenzijaMenadzerTest.class,
	   ZanroviMenadzerTest.class
	})

public class AllTests {

}