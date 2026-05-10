/**
 * 
 */
package rs.ac.bg.fon.nprog.mvnbiblioteka2026.interfejs;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import rs.ac.bg.fon.nprog.mvnbiblioteka2026.Knjiga;

/**
 * 
 */
public abstract class BibliotekaInterfaceTest {

	BibliotekaInterface b;

	Knjiga k, k2, k3;

	public abstract BibliotekaInterface getInstance();

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		b = getInstance();

		k = new Knjiga();
		k.setIsbn(123);

		k2 = new Knjiga();
		k2.setIsbn(345);

		k3 = new Knjiga();
		k3.setIsbn(987);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
		b = null;

		File fajl = new File("test-knjige.json");
		if (fajl.exists()) {
			fajl.delete();
		}
	}

	/**
	 * Test method for
	 * {@link biblioteka.interfejs.BibliotekaInterface#dodajKnjigu(biblioteka.Knjiga)}.
	 */
	@Test
	void testDodajKnjiguNull() {
		assertThrows(java.lang.NullPointerException.class, () -> b.dodajKnjigu(null));
	}

	@Test
	void testDodajKnjiguDuplikat() {
		b.dodajKnjigu(k);

		k2.setIsbn(123);

		assertThrows(java.lang.IllegalArgumentException.class, () -> b.dodajKnjigu(k2));
	}

	@Test
	void testDodajKnjigu() {
		b.dodajKnjigu(k);

		b.dodajKnjigu(k2);

		List<Knjiga> knjige = b.vratiSveKnjige();

		assertEquals(2, knjige.size());
		assertTrue(knjige.contains(k));
		assertTrue(knjige.contains(k2));
	}

	/**
	 * Test method for
	 * {@link biblioteka.interfejs.BibliotekaInterface#obrisiKnjigu(biblioteka.Knjiga)}.
	 */
	@Test
	void testObrisiKnjiguNull() {
		assertThrows(java.lang.NullPointerException.class, () -> b.obrisiKnjigu(null));
	}

	@Test
	void testObrisiKnjiguNePostoji() {
		b.dodajKnjigu(k);

		b.dodajKnjigu(k2);

		assertThrows(java.lang.IllegalArgumentException.class, () -> b.obrisiKnjigu(k3));
	}

	@Test
	void testObrisiKnjigu() {
		b.dodajKnjigu(k);
		b.dodajKnjigu(k2);
		b.dodajKnjigu(k3);

		b.obrisiKnjigu(k2);

		List<Knjiga> knjige = b.vratiSveKnjige();

		assertEquals(2, knjige.size());
		assertTrue(knjige.contains(k));
		assertTrue(knjige.contains(k3));
	}

	/**
	 * Test method for
	 * {@link biblioteka.interfejs.BibliotekaInterface#pronadjiKnjigu(biblioteka.Autor, long, java.lang.String, java.lang.String)}.
	 */
	@Test
	void testPronadjiKnjiguSveNull() {
		assertThrows(java.lang.IllegalArgumentException.class, () -> b.pronadjiKnjigu(null, 0, null, null));
	}

	@Test
	void testPronadjiKnjiguPrazno() {
		k.setNaslov("Knjiga 1");
		k2.setNaslov("Knjiga 2");
		k3.setNaslov("Knjiga 3");
		;

		b.dodajKnjigu(k);
		b.dodajKnjigu(k2);
		b.dodajKnjigu(k3);

		List<Knjiga> knjige = b.pronadjiKnjigu(null, 0, "Gospodar", null);

		assertEquals(0, knjige.size());

	}

	@Test
	void testPronadjiKnjiguJednaKnjiga() {
		k.setNaslov("Knjiga 1");
		k2.setNaslov("1 gospodar prstenova");
		k3.setNaslov("Knjiga 3");
		;

		b.dodajKnjigu(k);
		b.dodajKnjigu(k2);
		b.dodajKnjigu(k3);

		List<Knjiga> knjige = b.pronadjiKnjigu(null, 0, "Gospodar", null);

		assertEquals(1, knjige.size());
		assertTrue(knjige.contains(k2));

	}

	@Test
	void testPronadjiKnjiguViseKnjiga() {
		k.setNaslov("Knjiga 1");
		k2.setNaslov("1 gospodar prstenova");
		k3.setNaslov("2 gospodar prstenova");
		;

		b.dodajKnjigu(k);
		b.dodajKnjigu(k2);
		b.dodajKnjigu(k3);

		List<Knjiga> knjige = b.pronadjiKnjigu(null, 0, "Gospodar", null);

		assertEquals(2, knjige.size());
		assertTrue(knjige.contains(k2));
		assertTrue(knjige.contains(k3));

	}

	@Test
	void testSacuvajUFajlNullFile() {
		Exception ex = assertThrows(NullPointerException.class, () -> b.sacuvajUFajl(null));

		assertEquals("Putanja do fajla ne sme biti null", ex.getMessage());
	}

	@Test
	void testSacuvajUFajlException() {
		Exception ex = assertThrows(RuntimeException.class, () -> b.sacuvajUFajl("?:/nepostojeciFolder/test.json"));

		assertEquals("Greska pri upisu u JSON fajl", ex.getMessage());
	}

	@Test
	void testSacuvajUFajl() {
		String putanja = "test-knjige.json";

		k.setNaslov("Na Drini cuprija");
		k.setIzdavac("Laguna");
		k.setIzdanje(1);

		k2.setNaslov("Gospodar prstenova");
		k2.setIzdanje(2);
		k2.setIzdavac("Vulkan");

		b.dodajKnjigu(k);
		b.dodajKnjigu(k2);

		assertDoesNotThrow(() -> b.sacuvajUFajl(putanja));

		File fajl = new File(putanja);

		assertTrue(fajl.exists());
		assertTrue(fajl.length() > 0);
		
		assertDoesNotThrow(() -> {
			String sadrzaj = Files.readString(Path.of(putanja));
			
			assertTrue(sadrzaj.contains("Na Drini cuprija"));
			assertTrue(sadrzaj.contains("Gospodar prstenova"));
			assertTrue(sadrzaj.contains("Laguna"));
			assertTrue(sadrzaj.contains("Vulkan"));
		});

	}
	
	@Test
	void testUcitajIzFajlaNull() {
		Exception ex = assertThrows(NullPointerException.class, () -> b.ucitajIzFajla(null));

		assertEquals("Putanja do fajla ne sme biti null", ex.getMessage());
	}
	
	@Test
	void testUcitajIzFajlaException() {
		Exception ex = assertThrows(RuntimeException.class, () -> b.ucitajIzFajla("?:/nepostojeciFolder/test.json"));

		assertEquals("Greska pri ucitavanju iz JSON fajla", ex.getMessage());
	}
	
	@Test
	void testUcitajIzFajla() {
		String putanja = "test-knjige.json";

		k.setNaslov("Na Drini cuprija");
		k.setIzdavac("Laguna");
		k.setIzdanje(1);

		k2.setNaslov("Gospodar prstenova");
		k2.setIzdanje(2);
		k2.setIzdavac("Vulkan");

		List<Knjiga> knjigeZaUpis = Arrays.asList(k,k2);

		assertDoesNotThrow(() -> {
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			
			try(FileWriter fw = new FileWriter(putanja)){
				gson.toJson(knjigeZaUpis,fw);
			}
		});
		
		assertDoesNotThrow(() -> b.ucitajIzFajla(putanja));

		List<Knjiga> knjige = b.vratiSveKnjige();
		
		assertEquals(2, knjige.size());
		
		assertTrue(knjige.contains(k));
		assertTrue(knjige.contains(k2));

	}
	
	@Test
	void testUcitajIzFajlaDuplikati() {
		String putanja = "test-knjige.json";

		k.setNaslov("Na Drini cuprija");
		k.setIzdavac("Laguna");
		k.setIzdanje(1);

		k2.setNaslov("Gospodar prstenova");
		k2.setIzdanje(2);
		k2.setIzdavac("Vulkan");
		
		b.dodajKnjigu(k);

		List<Knjiga> knjigeZaUpis = Arrays.asList(k,k2);

		assertDoesNotThrow(() -> {
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			
			try(FileWriter fw = new FileWriter(putanja)){
				gson.toJson(knjigeZaUpis,fw);
			}
		});
		
		assertDoesNotThrow(() -> b.ucitajIzFajla(putanja));

		List<Knjiga> knjige = b.vratiSveKnjige();
		
		assertEquals(2, knjige.size());
		
		assertTrue(knjige.contains(k));
		assertTrue(knjige.contains(k2));

	}

}
